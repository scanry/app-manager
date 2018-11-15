package com.sixliu.app.user.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.app.user.dto.AppDTO;
import com.sixliu.app.user.repository.dao.AppDao;
import com.sixliu.app.user.service.AppService;
import com.sixliu.app.util.Convertor;

/**
*@author:MG01867
*@date:2018年11月12日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
@RestController
public class AppServiceImpl implements AppService{

	@Autowired
	private AppDao appDao;
	
	@Override
	public AppDTO get(String id) {
		return Convertor.convert(appDao.get(id),()->new AppDTO());
	}

	@Override
	public AppDTO getByCode(String code) {
		return Convertor.convert(appDao.getByCode(code),()->new AppDTO());
	}

}
