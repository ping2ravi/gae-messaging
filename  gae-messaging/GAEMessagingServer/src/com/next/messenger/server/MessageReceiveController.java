package com.next.messenger.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public class MessageReceiveController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException {
	  XMPPService xmpp = XMPPServiceFactory.getXMPPService();
	  Message message = xmpp.parseMessage(req);
	
	  JID fromJid = message.getFromJid();
	  String body = message.getBody();
	  
	  //First save the master message and mark it as pending or incomlete or in process
	  
	  //then get the MessageType object and see how many applications are listening to this Message
	  
	  //Replicate Above Master message for each of the application and start saving it in DB 
	  //and sending them once sent mark it as sent
	  //if time limit reached 28 second then create another task which will co mplete this procedure
	  
	  // ...
	}
}
