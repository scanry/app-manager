package com.sixliu.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 程序启动入口类
 */
@SpringCloudApplication
@EnableFeignClients(basePackageClasses = { com.sixliu.app.ServiceName.class})
public class StartUp implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(StartUp.class);
	}
}