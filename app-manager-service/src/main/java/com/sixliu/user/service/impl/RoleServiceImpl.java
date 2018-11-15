package com.sixliu.user.service.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.sixliu.user.dto.RoleDTO;
import com.sixliu.user.service.RoleService;

/**
*@author:MG01867
*@date:2018年11月15日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
@RestController
public class RoleServiceImpl implements RoleService{

	@Override
	public List<RoleDTO> listByAppId(String appId) {
		return null;
	}

}
