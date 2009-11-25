package com.next.messenger.server.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.next.message.client.entity.ApplicationMessage;
import com.next.message.client.entity.MessageConfiguration;
import com.next.message.client.entity.MessageConfigurations;
import com.next.message.client.exception.MessageException;
import com.next.message.client.service.MessageController;
import com.next.message.client.service.MessageHandler;
import com.next.message.client.service.XmlService;
import com.next.messenger.server.configuration.MessageHandlerConfiguration;
import com.next.messenger.service.GaeXmlService;

public class GAEDefaultMessageController extends HttpServlet implements MessageController {

	private static MessageConfigurations messageConfs = null;
	Logger logger = Logger.getLogger(GAEDefaultMessageController.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException {
		logger.info("Recived Message in reataal");
	  XMPPService xmpp = XMPPServiceFactory.getXMPPService();
	  Message message = xmpp.parseMessage(req);
	  JID fromJid = message.getFromJid();
	  String body = message.getBody();
 	  logger.info("Message = "+ body);
	  try {
		processMessage(message.getBody());
	} catch (MessageException e) {
		e.printStackTrace();
	}
	  
	  //Replicate Above Master message for each of the application and start saving it in DB 
	  //and sending them once sent mark it as sent
	  //if time limit reached 28 second then create another task which will co mplete this procedure
	  
	  // ...
	}
	
	@Override
	public void processMessage(String messageText) throws MessageException{
	 	  logger.info("Procesing Message ");
		if(messageConfs == null)
		{
		 	  logger.info("Configuration was not loaded so loading it now");
		    String filepath = getInitParameter("MessageConfFileName");
		    String realpath = getServletContext().getRealPath(filepath);
		 	  logger.info("Configuration Will be loaded from file " + realpath);
		    System.out.println("realpath=" + realpath);
			MessageHandlerConfiguration messageHandlerConfiguration = new MessageHandlerConfiguration();
			messageConfs = messageHandlerConfiguration.readMessageConfiguration(realpath);
		 	  logger.info("Configuration loaded from file " + realpath);
		}
		XmlService xmlService = new GaeXmlService();
		ApplicationMessage messageObject = (ApplicationMessage)xmlService.convertXmlToObject(messageText, ApplicationMessage.class,"ApplicationMessage");
		
		logger.info("Version="+messageObject.getVersion());
		logger.info("MessageType="+messageObject.getMessageType());
		logger.info("SenderApplicationId="+messageObject.getSenderApplicationId());
		
		if(messageConfs != null && messageConfs.getMessageConfiguration() != null)
		{
			String messageType = messageObject.getMessageType();
			MessageConfiguration[] allConfig = messageConfs.getMessageConfiguration();
			String defaultConfigClass = null;
			String messageHandlerClass = null;
			for(int i=0;i<allConfig.length;i++)
			{
				if(messageType.equals(allConfig[i].getMessageType()))
				{
			 	    logger.info("Handler found " );
					messageHandlerClass = allConfig[i].getClassName();
					break;
				}
				if("*".equals(allConfig[i].getMessageType()))
					defaultConfigClass = allConfig[i].getClassName();
			}
			if(messageHandlerClass == null)
				messageHandlerClass = defaultConfigClass;
			if(messageHandlerClass == null)
			{
				logger.log(Level.SEVERE, "No Message handler found for message type " + messageType);
			}
			else
			{
				try{
				Class cls = Class.forName(messageHandlerClass);
				MessageHandler messageHandler = (MessageHandler)cls.newInstance();
		 	    logger.info("Handling Message" );
				messageHandler.handleMessage(messageObject,messageText);
				}catch(Exception ex)
				{
					logger.log(Level.SEVERE, "Error while processing message of type" + messageType,ex);
				}
			}
			
		}
		
		// TODO Auto-generated method stub

	}

}
