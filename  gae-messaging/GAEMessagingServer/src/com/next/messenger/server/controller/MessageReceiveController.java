package com.next.messenger.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.next.message.entity.ApplicationInt;
import com.next.message.entity.ApplicationMessage;
import com.next.message.entity.ApplicationMessageAbstract;
import com.next.message.entity.MasterMessageInt;
import com.next.message.entity.PublishMessageInt;
import com.next.message.exception.MessageException;
import com.next.message.service.XMPPMessageService;
import com.next.message.xmlservice.XmlService;
import com.next.messenger.server.entity.MasterMessage;
import com.next.messenger.server.entity.TestClass;
import com.next.messenger.server.services.GaeXmlService;
import com.next.messenger.server.services.GaeXmppMessagingService;
import com.next.messenger.server.services.test.TestXmlService;

public class MessageReceiveController extends HttpServlet {
	Logger logger = Logger.getLogger(MessageReceiveController.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException {
		logger.info("Recived Message");
	  XMPPService xmpp = XMPPServiceFactory.getXMPPService();
	  Message message = xmpp.parseMessage(req);
	
	  JID fromJid = message.getFromJid();
	  String body = message.getBody();
		logger.info("Message = "+ body);
	  
	  try {
		processMessage(message.getBody());
	} catch (MessageException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  //Replicate Above Master message for each of the application and start saving it in DB 
	  //and sending them once sent mark it as sent
	  //if time limit reached 28 second then create another task which will co mplete this procedure
	  
	  // ...
	}
	private void processMessage(String messageText) throws MessageException
	{
	  //First convert String message to ApplicationMessageObject
		logger.info("Processing Message");
		TestXmlService.test();
		logger.info("TestRun");
		XmlService xmlService = new GaeXmlService();
		ApplicationMessage messageObject = (ApplicationMessage)xmlService.convertXmlToObject(messageText, ApplicationMessageAbstract.class);
		
		logger.info("Version="+messageObject.getVersion());
		logger.info("MessageType="+messageObject.getMessageType());
		logger.info("SenderApplicationId="+messageObject.getSenderApplicationId());
	  //Then save the master message and mark it as pending or incomlete or in process
		XMPPMessageService messageService = new GaeXmppMessagingService();
		logger.info("Saving Master Message");
		MasterMessageInt masterMessage = messageService.saveMasterXmppMessage(messageObject, messageText);
		logger.info("Saved Master Message");
		
		/*
	  //then check how many applications are listening to this Message
		List<ApplicationInt> allListeningApplication = messageService.getAllListningApplications(messageObject.getMessageType());
	  //Save published message for each Listner/Subscriber
		List<PublishMessageInt> allPublishingMessages = new ArrayList<PublishMessageInt>();
		PublishMessageInt onePublishMessageInt;
		for(ApplicationInt oneApplicationInt:allListeningApplication)
		{
			onePublishMessageInt = messageService.savePublishMessage(oneApplicationInt, ((MasterMessage)masterMessage).getKey().getId());
			allPublishingMessages.add(onePublishMessageInt);
		}
		for(PublishMessageInt onePMInt:allPublishingMessages)
		{
			if(messageService.publishXmppMessage(onePMInt, messageObject, messageText))
				messageService.messagePublished(onePMInt);
			
		}
		*/
		
	}
}
