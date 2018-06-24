package org.philmaster.boot.model;

import java.io.Serializable;
import java.time.DayOfWeek;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meal implements Serializable {

	private static final long serialVersionUID = 1L;

	public DayOfWeek day;
	public String type;
	public String desc;
	public float price;

	public Meal(DayOfWeek day, String type, String desc, float price) {
		super();
		this.day = day;
		this.type = type;
		this.desc = desc;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Meal [day=" + day + ", type=" + type + ", desc=" + desc + ", price=" + price + "]";
	}

}
