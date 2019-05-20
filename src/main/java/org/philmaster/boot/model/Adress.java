package org.philmaster.boot.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adress implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String street; // with housenumber
	private String zip;
	private String city;
	private String lat, lng;

	public Adress(String name, String street, String zip, String city, String lat, String lng) {
		super();
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.lat = lat;
		this.lng = lng;
	}

}
