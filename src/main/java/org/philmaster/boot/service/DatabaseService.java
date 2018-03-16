package org.philmaster.boot.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.query.SelectQuery;
import org.philmaster.boot.model.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Philmasteryeah
 * 
 *         This class uses the database scheme which is generated by the cayenne
 *         modeler.
 * 
 *         pure jdbc example: JdbcTemplate jdbcTemplate;
 *         jdbcTemplate.update("INSERT INTO car(brand)VALUES(?)", "Honda"));
 * 
 *         orm cayenne example: ObjectContext ctx = dbBean.getContext(); Cars
 *         car = ctx.newObject(Cars.class); car.setName("test car");
 *         ctx.commitChanges();
 *
 */

@Getter
@Setter
@Named
@ApplicationScoped
public class DatabaseService {

	private static final String CAYENNE_CONFIG = "cayenne-project.xml";

	private ObjectContext context;

	@PostConstruct
	void init() {
		context = ServerRuntime.builder().addConfig(CAYENNE_CONFIG).build().newContext();
	}

	public Client clientByName() {
		// TODO String param with name
		// insert into client (client_id, name) values (1, 'default')
		return fetchAll(Client.class).get(0);
	}

	public <T extends BaseDataObject> T createNew(Class<T> clazz) {
		return context.newObject(clazz);
	}

	public <T extends BaseDataObject> List<T> fetchAll(Class<T> clazz) {
		return context.select(SelectQuery.query(clazz));
	}

	public <T extends BaseDataObject> List<T> fetch(Class<T> clazz, Expression where) {
		return ObjectSelect.query(clazz).where(where).select(context);
	}
}
