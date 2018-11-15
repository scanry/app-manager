package com.sixliu.app.util;

import org.springframework.util.Base64Utils;

/**
*@author:MG01867
*@date:2018年11月15日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
public class Base64UtilsTest {

	public static void main(String[] args) {
		System.out.println(Base64Utils.encodeToString("app:$2a$10$fVZn7fxncgFSVhrYf.KEeOwl7DbaLsNV2NxJg.g6/40TWMUi7rhWu".getBytes()));
	}

}
