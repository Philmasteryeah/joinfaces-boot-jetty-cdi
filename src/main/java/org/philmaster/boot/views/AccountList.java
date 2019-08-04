package org.philmaster.boot.views;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

@Named
@RequestScoped
public class AccountList extends ContextListBean<Account> {

	@Autowired
	private StringRedisTemplate redisTemplate;

	private List<String> activeUsernames;

	@Override
	@PostConstruct
	public void init() {
		super.init();
		// do your init stuff
		System.err.println("-> " + getItems());
		// TODO
		List<String> collect = redisTemplate.opsForHash()
				.getOperations()
				.keys("spring:session:sessions:*")
				.stream()
				.filter(key -> !key.contains("expires"))
				.collect(Collectors.toList());

		collect.forEach(System.out::println);
	}

	//////

	public List<String> getActiveUsernames() {
		// all saved user sessions
		// spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sa
		if (activeUsernames != null)
			return activeUsernames;
		activeUsernames = redisTemplate.opsForHash()
				.getOperations()
				.keys("*PRINCIPAL_NAME_INDEX_NAME*")
				.stream()
				.map(p -> p.replaceAll(".+:", ""))
				.collect(Collectors.toList());
		return activeUsernames;
	}

//	public boolean isLoggedIn(String username) {
//		if (username == null || activeUsernames == null || activeUsernames.isEmpty())
//			return false;
//		return activeUsernames.contains(username);
//	}

}
