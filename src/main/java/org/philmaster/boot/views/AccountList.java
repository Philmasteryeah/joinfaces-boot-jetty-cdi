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
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

@Named
@RequestScoped
public class AccountList extends ContextListBean<Account> {
//
//	@Autowired
//	private StringRedisTemplate redisTemplate;

//	@Autowired
//	FindByIndexNameSessionRepository<? extends Session> redisSession;

	private List<String> sessionUsernames;

	@Override
	@PostConstruct
	public void init() {
		super.init();
		// do your init stuff
		System.err.println("-> " + getItems());
		// TODO
		//sessionUsernames = initSessionUsernames();

		getSessionUsernamesActive();

	}
	///// Redis stuff
	// TODO server push notification if user logged off
	////// Redis stuff

	public List<String> getSessionUsernamesActive() {
		return null;
//		// actual logged in users
//		return sessionUsernames.stream()
//				.filter(p -> redisSession.findByPrincipalName(p)
//						.size() != 0)
//				.collect(Collectors.toList());
	}

//	public String getMOTD() {
//		// test motd = message of the day
//		return redisTemplate.opsForValue()
//				.get("motd");
//	}

	public List<String> getSessionUsernames() {
		// only for view usage
		return getSessionUsernamesActive();

	}

//	private List<String> initSessionUsernames() {
//		// all saved user sessions
//		// spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sa
//		// hold the usernames of all users who ever logged in
//		return redisTemplate.opsForHash()
//				.getOperations()
//				.keys("*PRINCIPAL_NAME_INDEX_NAME*")
//				.stream()
//				.map(p -> p.replaceAll(".+:", ""))
//				.collect(Collectors.toList());
//	}

//	List<String> collect = redisTemplate.opsForHash()
//	.getOperations()
//	.keys("spring:session:sessions:*")
//	.stream()
//	.map(key -> key.replaceAll(".+:", ""))
//
//	.collect(Collectors.toList());
//
//collect.forEach(System.out::println);

//	public boolean isLoggedIn(String username) {
//		if (username == null || activeUsernames == null || activeUsernames.isEmpty())
//			return false;
//		return activeUsernames.contains(username);
//	}

}
