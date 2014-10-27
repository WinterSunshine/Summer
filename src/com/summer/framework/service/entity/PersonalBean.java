package com.summer.framework.service.entity;

public class PersonalBean
{
	String userId;
	String userBalance;
	String userAccount;
	String unreadMessages;
	String bindPhone;
	
	public String getBindPhone()
	{
		return bindPhone;
	}
	
	public void setBindPhone(String bindPhone)
	{
		this.bindPhone = bindPhone;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public String getUserBalance()
	{
		return userBalance;
	}
	
	public void setUserBalance(String userBalance)
	{
		this.userBalance = userBalance;
	}
	
	public String getUserAccount()
	{
		return userAccount;
	}
	
	public void setUserAccount(String userAccount)
	{
		this.userAccount = userAccount;
	}
	
	public String getUnreadMessages()
	{
		return unreadMessages;
	}
	
	public void setUnreadMessages(String unreadMessages)
	{
		this.unreadMessages = unreadMessages;
	}
	
	@Override
	public String toString()
	{
		return userAccount;
	}
}
