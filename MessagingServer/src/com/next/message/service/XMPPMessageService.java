package com.next.message.service;

import java.util.List;

import com.next.message.entity.ApplicationInt;
import com.next.message.entity.ApplicationMessage;
import com.next.message.entity.MasterMessageInt;
import com.next.message.entity.PublishMessageInt;
import com.next.message.exception.MessageException;

public interface XMPPMessageService {

	public boolean publishXmppMessage(PublishMessageInt message,ApplicationMessage messageObject, String msgBody) throws MessageException;
	public PublishMessageInt savePublishMessage(ApplicationInt application,long masterMessageId) throws MessageException;
	public PublishMessageInt messagePublished(PublishMessageInt publishMessageInt) throws MessageException;
	public MasterMessageInt saveMasterXmppMessage(ApplicationMessage messageObject,String messageText) throws MessageException;
	public List<ApplicationInt> getAllListningApplications(String messageType) throws MessageException;
	public void ping(ApplicationMessage message);
	
}
