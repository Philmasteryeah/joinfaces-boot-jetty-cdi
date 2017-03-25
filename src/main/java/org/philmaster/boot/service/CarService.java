package org.philmaster.boot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.example.cayenne.persistent.Cars;
import org.philmaster.boot.beans.DatabaseBean;
import org.philmaster.boot.model.Car;

@Named
@ApplicationScoped
public class CarService {

    private final static String[] colors;

    private final static String[] brands;

    static {
	colors = new String[10];
	colors[0] = "Black";
	colors[1] = "White";
	colors[2] = "Green";
	colors[3] = "Red";
	colors[4] = "Blue";
	colors[5] = "Orange";
	colors[6] = "Silver";
	colors[7] = "Yellow";
	colors[8] = "Brown";
	colors[9] = "Maroon";

	brands = new String[10];
	brands[0] = "BMW";
	brands[1] = "Mercedes";
	brands[2] = "Volvo";
	brands[3] = "Audi";
	brands[4] = "Renault";
	brands[5] = "Fiat";
	brands[6] = "Volkswagen";
	brands[7] = "Honda";
	brands[8] = "Jaguar";
	brands[9] = "Ford";
    }


    
    @Inject
    private DatabaseBean dbBean;

    public List<Car> createCars(int size) {
	// TODO Testing cayenne

	ObjectContext ctx = dbBean.getContext();
	Cars b = ctx.newObject(Cars.class);
	b.setName("test car");
	System.err.println(b);
	ctx.commitChanges();

	//
	
	List<Car> list = new ArrayList<Car>();
	for (int i = 0; i < size; i++) {
	    list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(),
		    getRandomSoldState()));
	}

	return list;
    }

    private String getRandomId() {
	return UUID.randomUUID().toString().substring(0, 8);
    }

    private int getRandomYear() {
	return (int) (Math.random() * 50 + 1960);
    }

    private String getRandomColor() {
	return colors[(int) (Math.random() * 10)];
    }

    private String getRandomBrand() {
	return brands[(int) (Math.random() * 10)];
    }

    private int getRandomPrice() {
	return (int) (Math.random() * 100000);
    }

    private boolean getRandomSoldState() {
	return (Math.random() > 0.5) ? true : false;
    }

    public List<String> getColors() {
	return Arrays.asList(colors);
    }

    public List<String> getBrands() {
	return Arrays.asList(brands);
    }
}
