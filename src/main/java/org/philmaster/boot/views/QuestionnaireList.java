package org.philmaster.boot.views;

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
		// spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sa

		String object = redisTemplate.opsForHash()
				.getOperations()
				.keys("spring*")
				.stream()
				.collect(Collectors.joining(", "));
		// all keYS with spring in it
		System.err.println(object);

//
//		Jedis jedis = fac.getConnection()..getResource();
//	    jedis.select(1);
//	    String attributeName = jedis.get("client");
//	    jedis.select(0);

	}

}
