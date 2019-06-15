package org.philmaster.boot.cayenne;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.ObjectContext;

public abstract class ExtBaseContext extends BaseContext {

	/**
	 * Method used to get
	 *
	 * @return
	 * @throws IllegalStateException
	 */
	public static ObjectContext getThreadObjectContextNull() throws IllegalStateException {
		return threadObjectContext.get();
	}
}
