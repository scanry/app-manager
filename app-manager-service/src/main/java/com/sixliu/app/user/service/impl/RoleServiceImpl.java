package com.sixliu.app.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.app.user.dto.RoleDTO;
import com.sixliu.app.user.repository.dao.RoleDao;
import com.sixliu.app.user.service.RoleService;
import com.sixliu.app.util.Convertor;

/**
*@author:MG01867
*@date:2018年11月15日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
@RestController
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public RoleDTO get(String id) {
		return Convertor.convert(roleDao.get(id),()->new RoleDTO());
	}
	
	@Override
	public List<RoleDTO> listByAppId(String appId) {
		return null;
	}
}
