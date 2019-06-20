package org.philmaster.boot.views;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Role;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.PMUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class RoleList {

	private SessionBean session;

	@Setter
	@Getter
	private List<Role> items, itemsFiltered, itemsSelected;

	@Setter
	@Getter
	private Role selectedItem;

	@Setter
	@Getter
	private Date date = new Date(); // Testing

	private ObjectContext context;

	@PostConstruct
	public void init() {
		context = getContext();
		session = getSession();
		// TODO copy account in local context if needed

		// TODO add client

		try {
			items = DatabaseService.fetchAll(context, Role.class);
		} catch (Exception e) {
			System.err.println(e);
			PMUtil.statusMessageError(e.getMessage());
		}

	}

	@PreDestroy
	public void dispose() {
		// System.err.println("dispose");
	}

	public ObjectContext getContext() {
		if (context == null)
			context = DatabaseService.getContext();
		return context;
	}

	public SessionBean getSession() {
		if (session == null) {
			// get session out of the faces context
			// TODO need to test this vs inject because it seems to be copied on inject
			FacesContext ctx = FacesContext.getCurrentInstance();
			session = ctx.getApplication()
					.evaluateExpressionGet(ctx, "#{sessionBean}", SessionBean.class);
		}
		return session;
	}

}
