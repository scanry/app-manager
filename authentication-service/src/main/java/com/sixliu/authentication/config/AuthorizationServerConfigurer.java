package com.sixliu.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * @author:MG01867
 * @date:2018年11月10日
 * @email:359852326@qq.com
 * @version:
 * @describe
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private ClientDetailsServiceImpl clientDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		return converter;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService);
	}

	@Primary
	@Bean
	public DefaultTokenServices defaultTokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setClientDetailsService(clientDetailsService);
		tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12); // token有效期自定义设置，默认12小时
		tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);// 默认30天，这里修改
		return tokenServices;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore());
		endpoints.tokenServices(defaultTokenServices());
		endpoints.userDetailsService(userDetailsService);
		endpoints.authenticationManager(authenticationManager);
		endpoints.setClientDetailsService(clientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
	}
}
