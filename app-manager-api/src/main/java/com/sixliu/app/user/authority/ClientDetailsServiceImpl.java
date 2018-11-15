package com.sixliu.app.user.authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import com.sixliu.app.user.dto.AppDTO;
import com.sixliu.app.user.dto.ResourceDTO;
import com.sixliu.app.user.dto.RoleDTO;
import com.sixliu.app.user.service.AppService;
import com.sixliu.app.user.service.ResourceService;
import com.sixliu.app.user.service.RoleService;

/**
 * @author:MG01867
 * @date:2018年11月13日
 * @email:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Component
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private AppService appService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private RoleService roleService;

	private Map<String, ClientDetails> cache = new ConcurrentHashMap<>();

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return cache.computeIfAbsent(clientId, newKek -> {
			AppDTO app = appService.getByCode(clientId);
			if (null == app) {
				throw new ClientRegistrationException(String.format("The app[%s] is illeagl", clientId));
			}
			List<ResourceDTO> resources = resourceService.listByAppId(app.getId());
			Set<String> resourceIds = null;
			if (null != resources) {
				resourceIds = new HashSet<>(resources.size());
				for (ResourceDTO resource : resources) {
					resourceIds.add(resource.getId());
				}

			}
			List<GrantedAuthority> authorities = null;
			List<RoleDTO> roles = roleService.listByAppId(clientId);
			if (null != roles) {
				authorities = new ArrayList<>(roles.size());
				for (RoleDTO role : roles) {
					authorities.add(() -> role.getName());
				}

			}
			Set<String> scope = null;
			if (StringUtils.isNotBlank(app.getScope())) {
				String[] tempScopes = StringUtils.split(app.getScope(), ";");
				scope = new HashSet<>(tempScopes.length);
				for (String tempScope : tempScopes) {
					scope.add(tempScope);
				}
			}

			Set<String> authorizedGrantTypes = null;
			if (StringUtils.isNotBlank(app.getAuthorizedGrantTypes())) {
				String[] tempAuthorizedGrantTypes = StringUtils.split(app.getAuthorizedGrantTypes(), ";");
				authorizedGrantTypes = new HashSet<>(tempAuthorizedGrantTypes.length);
				for (String tempAuthorizedGrantType : tempAuthorizedGrantTypes) {
					authorizedGrantTypes.add(tempAuthorizedGrantType);
				}
			}
			return new ClientDetailsImpl(app, resourceIds, authorities, scope, authorizedGrantTypes);
		});
	}

	public static class ClientDetailsImpl implements ClientDetails {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5132305652639507445L;

		private AppDTO app;
		private Set<String> resourceIds;
		private List<GrantedAuthority> authorities;
		private Set<String> scope;
		private Set<String> authorizedGrantTypes;

		public ClientDetailsImpl(AppDTO app, Set<String> resourceIds, List<GrantedAuthority> authorities,
				Set<String> scope, Set<String> authorizedGrantTypes) {
			this.app = app;
			this.resourceIds = resourceIds;
			this.authorities = null == authorities ? Collections.emptyList() : authorities;
			this.scope = null == scope ? Collections.emptySet() : scope;
			this.authorizedGrantTypes = null == authorizedGrantTypes ? Collections.emptySet() : authorizedGrantTypes;
		}

		@Override
		public String getClientId() {
			return app.getCode();
		}

		@Override
		public Set<String> getResourceIds() {
			return resourceIds;
		}

		@Override
		public boolean isSecretRequired() {
			return true;
		}

		@Override
		public String getClientSecret() {
			return app.getSecret();
		}

		@Override
		public boolean isScoped() {
			return app.isScoped();
		}

		@Override
		public Set<String> getScope() {
			return scope;
		}

		@Override
		public Set<String> getAuthorizedGrantTypes() {
			return authorizedGrantTypes;
		}

		@Override
		public Set<String> getRegisteredRedirectUri() {
			return null;
		}

		@Override
		public Collection<GrantedAuthority> getAuthorities() {
			return authorities;
		}

		@Override
		public Integer getAccessTokenValiditySeconds() {
			return app.getAccessTokenValiditySeconds();
		}

		@Override
		public Integer getRefreshTokenValiditySeconds() {
			return app.getRefreshTokenValiditySeconds();
		}

		@Override
		public boolean isAutoApprove(String scope) {
			return app.isAutoApprove();
		}

		@Override
		public Map<String, Object> getAdditionalInformation() {
			return null;
		}

	}

}
