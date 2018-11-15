package com.sixliu.user.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.sixliu.user.BaseTest;
import com.sixliu.user.authority.ClientDetailsServiceImpl;

/**
*@author:MG01867
*@date:2018年11月15日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
public class ClientDetailsServiceTest extends BaseTest{

	@Autowired
	private ClientDetailsServiceImpl clientDetailsService;
	
	@Test
	public void testLoadClientByClientId() {
		ClientDetails clientDetails=clientDetailsService.loadClientByClientId("app");
		checkOk(clientDetails);
	}
}
