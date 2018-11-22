package com.sixliu.app.user.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sixliu.app.BaseTest;
import com.sixliu.app.user.dto.UserDTO;
import com.sixliu.app.user.service.UserService;

/**
 * @author:MG01867
 * @date:2018年11月5日
 * @email:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class UserServiceTest extends BaseTest {

	@Autowired
	UserService userService;

	@Test
	public void testAdd() {
		UserDTO user = null;
		String result = userService.add(user);
		checkOk(result);
	}

	@Test
	public void testGet() {
		UserDTO result = userService.get("98d3c392dda211e89e01005056986f0b");
		checkOk(result);
	}
}
