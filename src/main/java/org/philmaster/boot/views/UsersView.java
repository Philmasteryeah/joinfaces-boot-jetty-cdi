package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Named
@RequestScoped
public class UsersView {

	private String signatureValue;

}
