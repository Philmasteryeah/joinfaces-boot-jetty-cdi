package org.philmaster.boot.views;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

@Named
@RequestScoped
public class AccountList extends ContextListBean<Account> {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	@PostConstruct
	public void init() {
		super.init();
		// do your init stuff
		System.err.println("-> " + getItems());

	}

	//////

	public List<String> getActiveUsernames() {
		// spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sa
		return redisTemplate.opsForHash()
				.getOperations()
				.keys("*PRINCIPAL_NAME_INDEX_NAME*")
				.stream()
				.map(p -> p.replaceAll(".+:", ""))
				.collect(Collectors.toList());
	}

	public boolean isLoggedIn(String username) {
		List<String> activeUsernames = getActiveUsernames();
		if (username == null || activeUsernames == null || activeUsernames.isEmpty())
			return false;
		return activeUsernames.contains(username);
	}

}
