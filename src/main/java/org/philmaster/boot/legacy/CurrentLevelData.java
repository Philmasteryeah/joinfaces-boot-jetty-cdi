package org.philmaster.boot.legacy;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@RequestScoped
public class CurrentLevelData implements Serializable {

	private static final long serialVersionUID = 1L;

	private int currentLevel = 1;
}