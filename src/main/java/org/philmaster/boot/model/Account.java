package org.philmaster.boot.model;

import java.util.Collection;

import org.apache.cayenne.ObjectId;
import org.philmaster.boot.model.auto._Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Account extends _Account implements UserDetails{

	private static final long serialVersionUID = 1L;

	public int id() {
		ObjectId oj = getObjectId();
		return (oj == null || oj.isTemporary()) ? -1
				: (int) oj.getIdSnapshot()
						.get(_Account.ACCOUNT_ID_PK_COLUMN);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
