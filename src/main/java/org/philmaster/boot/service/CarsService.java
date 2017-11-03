package org.philmaster.boot.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Cars;

@Named
@ApplicationScoped
public class CarsService {

    @Inject
    private DatabaseService db;

    public void test() {
	ObjectContext ctx = db.getContext();
	Cars b = ctx.newObject(Cars.class);
	b.setName("test car");
	System.err.println(b);
	ctx.commitChanges();
    }

}
