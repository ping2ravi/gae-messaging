package com.next.messenger.server.entity.helper;

public class PersistanceHelper {

	private static MasterMessagePersistance masterMessagePersistance;
	public static MasterMessagePersistance getMasterMessagePersistance()
	{
		if(masterMessagePersistance == null)
			masterMessagePersistance = new MasterMessagePersistance();
		return masterMessagePersistance;
	}

	private static ApplicationPersistance applicationPersistance;
	public static ApplicationPersistance getApplicationPersistance()
	{
		if(applicationPersistance == null)
			applicationPersistance = new ApplicationPersistance();
		return applicationPersistance;
	}
	
	private static MessageTypePersistance messageTypePersistance;
	public static MessageTypePersistance getMessageTypePersistance()
	{
		if(messageTypePersistance == null)
			messageTypePersistance = new MessageTypePersistance();
		return messageTypePersistance;
	}
	
	private static PublishMessagePersistance publishMessagePersistance;
	public static PublishMessagePersistance getPublishMessagePersistance()
	{
		if(publishMessagePersistance == null)
			publishMessagePersistance = new PublishMessagePersistance();
		return publishMessagePersistance;
	}
}
