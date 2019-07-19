package org.philmaster.boot.views;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Named
@RequestScoped
public class QuestionnaireList extends ContextListBean<Questionnaire> {

	// TODO in Session?!
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

//	private static final String KEY = "spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME";

//
//	public Object getAtIndex(Integer index) {
//		return redisTemplate.opsForList()
//	}

	public void initView() {
		// Testing
		System.err
				.println("init quest list called from xhtml <f:viewAction action=\"#{questionnaireList.initView}\" />");
		List<String> users = getActiveUsernames();

		System.err.println(users);

//
//		Jedis jedis = fac.getConnection()..getResource();
//	    jedis.select(1);
//	    String attributeName = jedis.get("client");
//	    jedis.select(0);

	}

	private List<String> getActiveUsernames() {
		// spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sa
		return redisTemplate.opsForHash()
				.getOperations()
				.keys("*PRINCIPAL_NAME_INDEX_NAME*")
				.stream()
				.map(p -> p.replaceAll(".+:", ""))
				.collect(Collectors.toList());
	}

}
