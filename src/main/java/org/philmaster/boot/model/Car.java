package org.philmaster.boot.model;

import java.io.Serializable;

/**
 * @author Philmasteryeah
 * 
 *         old Model for testing
 *
 */

public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;
    public String brand;
    public int year;
    public String color;
    public int price;
    public boolean sold;

    public Car() {
    }

    public Car(String id, String brand, int year, String color) {
	this.id = id;
	this.brand = brand;
	this.year = year;
	this.color = color;
    }

    public Car(String id, String brand, int year, String color, int price, boolean sold) {
	this.id = id;
	this.brand = brand;
	this.year = year;
	this.color = color;
	this.price = price;
	this.sold = sold;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getBrand() {
	return brand;
    }

    public void setBrand(String brand) {
	this.brand = brand;
    }

    public int getYear() {
	return year;
    }

    public void setYear(int year) {
	this.year = year;
    }

    public String getColor() {
	return color;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public boolean isSold() {
	return sold;
    }

    public void setSold(boolean sold) {
	this.sold = sold;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Car other = (Car) obj;
	if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
	    return false;
	}
	return true;
    }
}
