package com.sixliu.app.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
/**
 * @author:MG01867
 * @date:2018年11月12日
 * @email:359852326@qq.com
 * @version:
 * @describe TODO
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

}
