package org.philmaster.boot.service;

import org.philmaster.boot.model.Account;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//	public Account getUserInfo(String username) {
//		String sql = "SELECT u.username name, u.password pass, a.authority role FROM "
//				+ "comp_users u INNER JOIN comp_authorities a on u.username=a.username WHERE "
//				+ "u.enabled =1 and u.username = ?";
//		User userInfo = dbcTemplate.queryForObject(sql, new Object[] { username }, new RowMapper<UserInfo>() {
//			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				UserInfo user = new UserInfo();
//				user.setUsername(rs.getString("name"));
//				user.setPassword(rs.getString("pass"));
//				user.setRole(rs.getString("role"));
//				return user;
//			}
//		});
//		return userInfo;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		UserInfo userInfo = userDAO.getUserInfo(username);
//		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
//		UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(), userInfo.getPassword(),
//				Arrays.asList(authority));
//		return userDetails;
//		return null;
//	}

}
