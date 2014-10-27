package com.summer.framework.service.api;

import com.summer.framework.base.annotation.HttpRequest;
import com.summer.framework.service.entity.GameBean;
import com.summer.framework.service.entity.PersonalBean;

public interface IHttpService
{
	@HttpRequest(arguments = { "" }, resultClass = GameBean.class, refreshMethod = "refreshGames")
	public void getAllGameList(Object context);
	
	@HttpRequest(arguments = { "userID" }, resultClass = PersonalBean.class, refreshMethod = "refreshPersonal")
	public void getUserInfo(String session, Object context);
}
