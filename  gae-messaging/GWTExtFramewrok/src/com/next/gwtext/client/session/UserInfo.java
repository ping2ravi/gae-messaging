package com.next.gwtext.client.session;

import com.next.gwtext.client.generic.beans.ApplicationFunction;
import com.next.gwtext.client.generic.beans.ApplicationUser;


public class UserInfo {

	private static UserInfo instance;
	private ApplicationUser user;
	public static UserInfo getInstance()
	{
		if(instance == null)
		{
			instance = new UserInfo();
		}
		return instance;
	}
	private UserInfo()
	{
		
	}
	public ApplicationUser getUser() {
		return user;
	}
	public void setUserInfoWrapper(ApplicationUser user) {
		this.user = user;
	}
	public boolean isUserAllowedForOperation(String function)
	{
		boolean allowed = false;
		if(user == null)
			allowed = false;
		else
		{
			if(user.getFunctions() != null)
			{
				for(ApplicationFunction oneFunction:user.getFunctions())
				{
					//GWT.log(function + "=" + oneFunction.getName() , null);
					if(oneFunction.getName().equalsIgnoreCase(function))
						allowed = true;
				}
			}
		}
		return allowed;
	}
	
}
