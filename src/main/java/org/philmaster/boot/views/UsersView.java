package org.philmaster.boot.views;

import javax.faces.view.ViewScoped;

import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Named
@ViewScoped
public class UsersView {

    private String signatureValue;

}
