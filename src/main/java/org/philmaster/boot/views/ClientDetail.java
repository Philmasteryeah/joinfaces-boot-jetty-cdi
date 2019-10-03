package org.philmaster.boot.views;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.PMUtil;

import lombok.Getter;

@Named
@RequestScoped
public class ClientDetail {

	private static final List<String> layoutSkins = Arrays.asList("skin-blue", "skin-blue-light", "skin-yellow",
			"skin-yellow-light", "skin-green", "skin-green-light", "skin-purple", "skin-purple-light", "skin-red",
			"skin-red-light", "skin-black", "skin-black-light");

	public static List<String> getLayoutSkins() {
		return layoutSkins;
	}

	private SessionBean session;

	private ObjectContext context;

	@Getter
	private String detailPageName;

	@Getter
	private Client detailObject;

	@PostConstruct
	public void init() {
		System.err.println("init client");
		context = getContext();
		session = getSession();
		detailObject = DatabaseService.fetchClientByName(context, null);

	}

	public ObjectContext getContext() {
		if (context == null)
			context = DatabaseService.getContext();
		return context;
	}

	private SessionBean getSession() {
		System.err.println("get Session");
		if (session == null) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			session = ctx.getApplication()
					.evaluateExpressionGet(ctx, "#{sessionBean}", SessionBean.class);
			System.err.println("->" + session);
		}
		return session;
	}

	public void actionSave() {
		try {
			getContext().commitChanges();
		} catch (Exception e) {
			PMUtil.statusMessageError(e.getMessage());
			return;
		}
		PMUtil.statusMessageInfo("Saved", "Saved");
	}

}
