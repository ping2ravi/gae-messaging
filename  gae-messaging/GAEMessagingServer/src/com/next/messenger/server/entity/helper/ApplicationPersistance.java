package com.next.messenger.server.entity.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.next.core.entity.helper.BasePersistance;
import com.next.core.sessions.UserSession;
import com.next.messenger.server.entity.Application;

public class ApplicationPersistance extends BasePersistance{
	public static final long PAGE_SIZE = 10;
	public Application createApplication(UserSession userSession,Application application)
	{
		super.createObject(userSession, application);
		return application;
	}
	public Application updateApplication(UserSession userSession,Application application)
	{
		super.updateObject(userSession, application);
		return application;
	}
	public Application getApplicationById(UserSession userSession,long applicationId)
	{
		return (Application)super.getObjectById(userSession, Application.class, applicationId);
	}
	public Application getApplicationById(UserSession userSession,Key applicationId)
	{
		return (Application)super.getObjectById(userSession, Application.class, applicationId);
	}
	public Application getApplicationByApplicationId(UserSession userSession,long applicationId)
	{
		long startRecord = 0;
		Map<String, Object> crit = new HashMap<String, Object>();
		crit.put("id", applicationId);
		List<Application> allApplications = super.runQueryGetList(userSession, Application.class, crit, "id",0L, startRecord+PAGE_SIZE); 
		if(allApplications == null || allApplications.size() <=0)
			return null;
		System.out.println("Found "+ allApplications.size() +" records for id="+applicationId);
		return allApplications.get(0);
	}
	public List<Application> getApplicationList(UserSession userSession,long pageNum)
	{
		long startRecord = (pageNum-1) * PAGE_SIZE;
		return super.runQueryGetList(userSession, Application.class, null, "creationDate",pageNum, startRecord+PAGE_SIZE);
	}
	
	public List<Application> getRecentApplications(UserSession userSession,int count)
	{
		//Map<String, Object> crit = new HashMap<String, Object>();
		return super.runQueryGetList(userSession, Application.class, null, "creationDate desc",0, count);
	}

}
