package com.sixliu.app.user.repository.dao;

import java.util.List;

import com.sixliu.app.user.repository.entity.AppEntity;

/**
*@author:MG01867
*@date:2018年11月10日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface AppDao extends BaseDao<AppEntity>{

	AppEntity getByCode(String code);
	
	List<AppEntity> listAll();
}
