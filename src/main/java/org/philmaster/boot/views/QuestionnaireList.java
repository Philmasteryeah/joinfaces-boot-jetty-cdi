package org.philmaster.boot.views;

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
	private RedisTemplate<String, Object> template;
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;

	public Object getValue(final String key) {
		return template.opsForValue()
				.get(key);
	}

	public void setValue(final String key, final String value) {
		template.opsForValue()
				.set(key, value);
	}

	public void initView() {
		// Testing
		System.err
				.println("init quest list called from xhtml <f:viewAction action=\"#{questionnaireList.initView}\" />");
		
		System.err.println(template.getClientList());
		
//
//		Jedis jedis = fac.getConnection()..getResource();
//	    jedis.select(1);
//	    String attributeName = jedis.get("client");
//	    jedis.select(0);

	}

}
