package org.philmaster.boot.model;

import org.philmaster.boot.model.auto._Account;

public class Account extends _Account {

    private static final long serialVersionUID = 1L; 

    public Account(String nameFirst, String nameLast) {
    	this.setNameFirst(nameFirst);
    	this.setNameLast(nameLast);
    }
    
}
