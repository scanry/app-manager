package com.sixliu.app.user.repository.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sixliu.app.BaseTest;
import com.sixliu.app.user.repository.dao.AppDao;
import com.sixliu.app.user.repository.entity.AppEntity;

/**
*@author:MG01867
*@date:2018年11月10日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
public class AppDaoTest extends BaseTest{

	@Autowired
	AppDao appDao;
	
	@Test
	public void testAdd() {
		AppEntity app=new AppEntity();
		app.setCode("oauth2_demo");
		app.setSecret("oauth2_demo");
		app.setRedirectUrl("url");
		app.setScope("scopes");
		app.setAuthorizedGrantTypes("authorization_code;refresh_token");
		app.setAccessTokenValiditySeconds(3000);
		app.setRefreshTokenValiditySeconds(18000);
		app.setDescribe("测试应用");
		app.setCreateUserId("02460d0de0e811e89e01005056986f0b");
		app.setUpdateUserId("02460d0de0e811e89e01005056986f0b");
		int count=appDao.add(app);
		checkOk(count);
	}

}
