package org.philmaster.boot.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.query.Ordering;
import org.apache.cayenne.query.SelectQuery;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;

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

@Named
@ApplicationScoped
public class DatabaseService {

	private static final String CAYENNE_CONFIG = "cayenne-project.xml";

	private static final String DEFAULT_CLIENT_NAME = "default";

	private ServerRuntime runtime;

	@PostConstruct
	void init() {
		runtime = ServerRuntime.builder()
				.addConfig(CAYENNE_CONFIG)
				.build(); // db runntime
	}

	public ObjectContext newContext() {
		return runtime.newContext();
	}

	public DataSource getDataSource() {
		return runtime.getDataSource();
	}

	public static Client fetchDefaultClient(ObjectContext context) {
		return fetchClientByName(context, DEFAULT_CLIENT_NAME);
	}

	public static Client fetchClientByName(ObjectContext context, String name) {
		if (name == null || "null".equals(name.trim()))
			return fetchDefaultClient(context);
		return ObjectSelect.query(Client.class)
				.where(Client.NAME.eq(name))
				.selectOne(context);
	}

	@SuppressWarnings("unchecked")
	public static Account fetchAccountByUsername(ObjectContext context, String username, String clientname) {
		// dont want to fetch all accounts from client like this
		// Client client = fetchClientByName(context, clientname);
		// client.getAccounts().stream().findFirst().orElse(null);
		// wanted to use a join like raw sql
		SelectQuery<Account> query = new SelectQuery<>(Account.class);
		query.andQualifier(ExpressionFactory.matchExp("username", username));
		query.andQualifier(ExpressionFactory.matchExp("client.name", clientname));
		List<Account> accounts = context.performQuery(query);
		if (accounts == null || accounts.isEmpty())
			return null; // TODO logging not found
		if (accounts.size() > 1)
			return null; // TODO logging should not happend
		return accounts.get(0);
	}

	public static <T extends BaseDataObject> T createNew(ObjectContext context, Class<T> clazz) {
		return context.newObject(clazz);
	}

	public static <T extends BaseDataObject> List<T> fetchAll(ObjectContext context, Class<T> clazz) {
		return context.select(SelectQuery.query(clazz));
	}

	public static <T extends BaseDataObject> List<T> fetch(ObjectContext context, Class<T> clazz, Expression where) {
		return ObjectSelect.query(clazz)
				.where(where)
				.select(context);
	}

	public static <T extends BaseDataObject> List<T> fetch(ObjectContext context, Class<T> clazz, Expression where,
			Ordering order) {
		return ObjectSelect.query(clazz)
				.orderBy(order)
				.where(where)
				.select(context);
	}

	//
	// Example:
	// Artist a = ObjectSelect
	// .query(Artist.class)
	// .where(Artist.ARTIST_NAME.eq("Picasso"))
	// .selectOne(context);

	// List<String> names = ObjectSelect
	// .columnQuery(Artist.class, Artist.ARTIST_NAME)
	// .where(Artist.ARTIST_NAME.length().gt(6))
	// .select(context);

}
