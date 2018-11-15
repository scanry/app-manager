package com.sixliu.app.user.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sixliu.app.ServiceName;
import com.sixliu.app.user.dto.RoleDTO;
/**
*@author:MG01867
*@date:2018年11月5日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
@FeignClient(ServiceName.SERVICE_NAME)
@Validated
@RequestMapping("/role")
public interface RoleService {

	@RequestMapping(value = "/listByAppId", method = RequestMethod.POST)
	List<RoleDTO> listByAppId(@RequestParam(name="appId") String appId);
}
