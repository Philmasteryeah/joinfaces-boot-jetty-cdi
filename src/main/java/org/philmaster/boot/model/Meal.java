package org.philmaster.boot.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	public static enum TypePrice {
		EINTOPF("Eintopf", 3.25f), TRADITIONELL_1("Traditionell 1", 3.30f), TRADITIONELL_2("Traditionell 2", 3.30f),
		KLEINER_HUNGER("Kleiner Hunger", 2.80f), VITAL("Vital", 3.90f), FESTTAGSMENUE("Festtagsmenü", 4.0f),
		SONDERMENUE("Sondermenü", 5.79f), DESSERT("Dessert", 1.2f), SALAT("Beilagensalat", 0.8f);

		// Sondermenü is changing every week

		private String name;
		private float price;

		TypePrice(String name, float price) {
			this.name = name;
			this.price = price;
		}

		public static TypePrice getTypePrice(String type) {
			return Arrays.asList(TypePrice.values()).stream().filter(val -> val.getName().equals(type)).findFirst()
					.orElse(null);
		}

		public static float getPrice(String type) {
			TypePrice tp = getTypePrice(type);
			return tp != null ? tp.getPrice() : 0.0f;
		}

	}

	public DayOfWeek day;
	public String type;
	public String desc;
	public String kcal;
	public float price;

	public Meal(DayOfWeek day, String type, String desc, float price, String kcal) {
		super();
		this.day = day;
		this.type = type;
		this.desc = desc;
		this.price = price;
		this.kcal = kcal;
	}

	@Override
	public String toString() {
		return "Meal [day=" + day + ", type=" + type + ", desc=" + desc + ", kcal=" + kcal + ", price=" + price + "]";
	}

}
