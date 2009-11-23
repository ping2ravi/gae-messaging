package com.next.messenger.server.entity.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.next.core.entity.helper.BasePersistance;
import com.next.core.sessions.UserSession;
import com.next.messenger.server.entity.MasterMessage;

public class MasterMessagePersistance extends BasePersistance {
	public static final long PAGE_SIZE = 10;
	public MasterMessage createMasterMessage(UserSession userSession,MasterMessage masterMessage)
	{
		super.createObject(userSession, masterMessage);
		return masterMessage;
	}
	public MasterMessage updateMasterMessage(UserSession userSession,MasterMessage masterMessage)
	{
		super.updateObject(userSession, masterMessage);
		return masterMessage;
	}
	public MasterMessage getMasterMessageById(UserSession userSession,long masterMessageId)
	{
		return (MasterMessage)super.getObjectById(userSession, MasterMessage.class, masterMessageId);
	}
	public MasterMessage getMasterMessageById(UserSession userSession,Key masterMessageId)
	{
		return (MasterMessage)super.getObjectById(userSession, MasterMessage.class, masterMessageId);
	}
	public MasterMessage getMasterMessageByMasterMessageId(UserSession userSession,long masterMessageId)
	{
		long startRecord = 0;
		Map<String, Object> crit = new HashMap<String, Object>();
		crit.put("id", masterMessageId);
		List<MasterMessage> allMasterMessages = super.runQueryGetList(userSession, MasterMessage.class, crit, "id",0L, startRecord+PAGE_SIZE); 
		if(allMasterMessages == null || allMasterMessages.size() <=0)
			return null;
		System.out.println("Found "+ allMasterMessages.size() +" records for id="+masterMessageId);
		return allMasterMessages.get(0);
	}
	public List<MasterMessage> getMasterMessageList(UserSession userSession,long pageNum)
	{
		long startRecord = (pageNum-1) * PAGE_SIZE;
		return super.runQueryGetList(userSession, MasterMessage.class, null, "creationDate",pageNum, startRecord+PAGE_SIZE);
	}
	
	public List<MasterMessage> getRecentMasterMessages(UserSession userSession,int count)
	{
		//Map<String, Object> crit = new HashMap<String, Object>();
		return super.runQueryGetList(userSession, MasterMessage.class, null, "creationDate desc",0, count);
	}

}
