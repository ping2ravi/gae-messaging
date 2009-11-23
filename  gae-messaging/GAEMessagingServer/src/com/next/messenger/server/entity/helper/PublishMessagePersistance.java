package com.next.messenger.server.entity.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.next.core.entity.helper.BasePersistance;
import com.next.core.sessions.UserSession;
import com.next.messenger.server.entity.PublishMessage;

public class PublishMessagePersistance extends BasePersistance {
	public static final long PAGE_SIZE = 10;
	public PublishMessage createPublishMessage(UserSession userSession,PublishMessage publishMessage)
	{
		super.createObject(userSession, publishMessage);
		return publishMessage;
	}
	public PublishMessage updatePublishMessage(UserSession userSession,PublishMessage publishMessage)
	{
		super.updateObject(userSession, publishMessage);
		return publishMessage;
	}
	public PublishMessage getPublishMessageById(UserSession userSession,long publishMessageId)
	{
		return (PublishMessage)super.getObjectById(userSession, PublishMessage.class, publishMessageId);
	}
	public PublishMessage getPublishMessageById(UserSession userSession,Key publishMessageId)
	{
		return (PublishMessage)super.getObjectById(userSession, PublishMessage.class, publishMessageId);
	}
	public PublishMessage getPublishMessageByPublishMessageId(UserSession userSession,long publishMessageId)
	{
		long startRecord = 0;
		Map<String, Object> crit = new HashMap<String, Object>();
		crit.put("id", publishMessageId);
		List<PublishMessage> allPublishMessages = super.runQueryGetList(userSession, PublishMessage.class, crit, "id",0L, startRecord+PAGE_SIZE); 
		if(allPublishMessages == null || allPublishMessages.size() <=0)
			return null;
		System.out.println("Found "+ allPublishMessages.size() +" records for id="+publishMessageId);
		return allPublishMessages.get(0);
	}
	public PublishMessage getPublishMessageByPublishMessage(UserSession userSession,String publishMessage)
	{
		long startRecord = 0;
		Map<String, Object> crit = new HashMap<String, Object>();
		crit.put("type", publishMessage);
		List<PublishMessage> allPublishMessages = super.runQueryGetList(userSession, PublishMessage.class, crit, "id",0L, startRecord+PAGE_SIZE); 
		if(allPublishMessages == null || allPublishMessages.size() <=0)
			return null;
		System.out.println("Found "+ allPublishMessages.size() +" records for id="+publishMessage);
		return allPublishMessages.get(0);
	}
	public List<PublishMessage> getPublishMessageList(UserSession userSession,long pageNum)
	{
		long startRecord = (pageNum-1) * PAGE_SIZE;
		return super.runQueryGetList(userSession, PublishMessage.class, null, "creationDate",pageNum, startRecord+PAGE_SIZE);
	}
	
	public List<PublishMessage> getRecentPublishMessages(UserSession userSession,int count)
	{
		//Map<String, Object> crit = new HashMap<String, Object>();
		return super.runQueryGetList(userSession, PublishMessage.class, null, "creationDate desc",0, count);
	}

}
