package com.next.messenger.server.services;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.next.core.sessions.SessionUtil;
import com.next.core.sessions.UserSession;
import com.next.message.entity.ApplicationInt;
import com.next.message.entity.ApplicationMessage;
import com.next.message.entity.MasterMessageInt;
import com.next.message.entity.PublishMessageInt;
import com.next.message.exception.MessageException;
import com.next.message.service.XMPPMessageService;
import com.next.messenger.server.entity.MasterMessage;
import com.next.messenger.server.entity.PublishMessage;
import com.next.messenger.server.entity.helper.PersistanceHelper;
import com.next.messenger.server.util.FieldConstantUtil;

public class GaeXmppMessagingService implements XMPPMessageService {

	@Override
	public boolean publishXmppMessage(PublishMessageInt publishMessage,ApplicationMessage messageObject, String msgBody) throws MessageException 
	{
		JID jid = new JID(publishMessage.getJid());        
		Message msg = new MessageBuilder().withRecipientJids(jid).withBody(msgBody).build();
		boolean messageSent = false;        
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();        
		if (xmpp.getPresence(jid).isAvailable()) {            
			SendResponse status = xmpp.sendMessage(msg);            
			messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);        
		}
		return messageSent;
	}

	@Override
	public MasterMessageInt saveMasterXmppMessage(ApplicationMessage messageObject,String messageText) throws MessageException 
	{
		MasterMessage masterMessage = new MasterMessage();
		masterMessage.setMessage(new Text(messageText));
		masterMessage.setMessageServerReciveTime(new Date());
		masterMessage.setMessageType(messageObject.getMessageType());
		masterMessage.setPublisherApplicationId(messageObject.getSenderApplicationId());
		masterMessage.setVersion(messageObject.getVersion());
		UserSession userSession = new UserSession();
		SessionUtil.startDbSession(userSession);
		try{
			PersistanceHelper.getMasterMessagePersistance().createMasterMessage(userSession, masterMessage);
			SessionUtil.commitDbSession(userSession);
		}catch(Exception ex)
		{
			if(userSession != null)
				SessionUtil.rollbackDbSession(userSession);
		}
		return masterMessage;
	}

	@Override
	public List<ApplicationInt> getAllListningApplications(String messageType)
			throws MessageException {
		UserSession userSession = new UserSession();
		SessionUtil.startDbSession(userSession);
		List<ApplicationInt> allListeningApplication=null;
		try{
			allListeningApplication = PersistanceHelper.getApplicationPersistance().getListeningApplicationOfMessage(userSession, messageType);
			SessionUtil.commitDbSession(userSession);
		}catch(Exception ex)
		{
			if(userSession != null)
				SessionUtil.rollbackDbSession(userSession);
		}
		return allListeningApplication;
	}

	@Override
	public PublishMessageInt savePublishMessage(ApplicationInt application,long masterMessageId) throws MessageException {
		UserSession userSession = new UserSession();
		PublishMessage publishMessage = new PublishMessage();
		publishMessage.setJid(application.getJid());
		publishMessage.setMasterMessageId(masterMessageId);
		publishMessage.setStatus(FieldConstantUtil.PUBLISH_MESSAGE_RECIEVED_STATUS);
		SessionUtil.startDbSession(userSession);
		try{
			publishMessage = PersistanceHelper.getPublishMessagePersistance().createPublishMessage(userSession, publishMessage);
			SessionUtil.commitDbSession(userSession);
		}catch(Exception ex)
		{
			if(userSession != null)
				SessionUtil.rollbackDbSession(userSession);
		}
		return publishMessage;
	}

	@Override
	public PublishMessageInt messagePublished(PublishMessageInt paramPublishMessage)
			throws MessageException {
		UserSession userSession = new UserSession();
		PublishMessage publishMessage = null;
		SessionUtil.startDbSession(userSession);
		List<ApplicationInt> allListeningApplication=null;
		PublishMessage pParamPublishMessage = (PublishMessage)paramPublishMessage;
		try{
			publishMessage = PersistanceHelper.getPublishMessagePersistance().getPublishMessageById(userSession, pParamPublishMessage.getKey());
			publishMessage.setStatus(FieldConstantUtil.PUBLISH_MESSAGE_SENT_STATUS);
			publishMessage = PersistanceHelper.getPublishMessagePersistance().updatePublishMessage(userSession, publishMessage);
			SessionUtil.commitDbSession(userSession);
		}catch(Exception ex)
		{
			if(userSession != null)
				SessionUtil.rollbackDbSession(userSession);
		}
		return publishMessage;
	}

}
