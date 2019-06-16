package org.philmaster.boot.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.philmaster.boot.model.auto._Account;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Account extends _Account implements UserDetails {

	private static final long serialVersionUID = 1L;

//	public Object id() {
//		return objectId.getIdSnapshot()
//				.get(_Account.ACCOUNT_ID_PK_COLUMN);
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		GrantedAuthority grantedAuthority = new GrantedAuthority() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return "ROLE_ADMIN";
			}
		};

		return List.of(grantedAuthority);
	}

	@Override
	public boolean isAccountNonExpired() {

		return false;
	}

	@Override
	public boolean isAccountNonLocked() {

		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return false;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
