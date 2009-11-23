package com.next.messenger.server.entity.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.next.core.entity.helper.BasePersistance;
import com.next.core.sessions.UserSession;
import com.next.messenger.server.entity.MessageType;

public class MessageTypePersistance extends BasePersistance {
	public static final long PAGE_SIZE = 10;
	public MessageType createMessageType(UserSession userSession,MessageType messageType)
	{
		super.createObject(userSession, messageType);
		return messageType;
	}
	public MessageType updateMessageType(UserSession userSession,MessageType messageType)
	{
		super.updateObject(userSession, messageType);
		return messageType;
	}
	public MessageType getMessageTypeById(UserSession userSession,long messageTypeId)
	{
		return (MessageType)super.getObjectById(userSession, MessageType.class, messageTypeId);
	}
	public MessageType getMessageTypeById(UserSession userSession,Key messageTypeId)
	{
		return (MessageType)super.getObjectById(userSession, MessageType.class, messageTypeId);
	}
	public MessageType getMessageTypeByMessageTypeId(UserSession userSession,long messageTypeId)
	{
		long startRecord = 0;
		Map<String, Object> crit = new HashMap<String, Object>();
		crit.put("id", messageTypeId);
		List<MessageType> allMessageTypes = super.runQueryGetList(userSession, MessageType.class, crit, "id",0L, startRecord+PAGE_SIZE); 
		if(allMessageTypes == null || allMessageTypes.size() <=0)
			return null;
		System.out.println("Found "+ allMessageTypes.size() +" records for id="+messageTypeId);
		return allMessageTypes.get(0);
	}
	public MessageType getMessageTypeByMessageType(UserSession userSession,String messageType)
	{
		long startRecord = 0;
		Map<String, Object> crit = new HashMap<String, Object>();
		crit.put("type", messageType);
		List<MessageType> allMessageTypes = super.runQueryGetList(userSession, MessageType.class, crit, "id",0L, startRecord+PAGE_SIZE); 
		if(allMessageTypes == null || allMessageTypes.size() <=0)
			return null;
		System.out.println("Found "+ allMessageTypes.size() +" records for id="+messageType);
		return allMessageTypes.get(0);
	}
	public List<MessageType> getMessageTypeList(UserSession userSession,long pageNum)
	{
		long startRecord = (pageNum-1) * PAGE_SIZE;
		return super.runQueryGetList(userSession, MessageType.class, null, "creationDate",pageNum, startRecord+PAGE_SIZE);
	}
	
	public List<MessageType> getRecentMessageTypes(UserSession userSession,int count)
	{
		//Map<String, Object> crit = new HashMap<String, Object>();
		return super.runQueryGetList(userSession, MessageType.class, null, "creationDate desc",0, count);
	}

}
