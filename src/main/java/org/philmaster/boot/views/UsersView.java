package org.philmaster.boot.views;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Named
@ViewScoped
public class UsersView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String signatureValue;

}
