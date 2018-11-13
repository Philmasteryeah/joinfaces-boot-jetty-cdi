package org.philmaster.boot.model.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Car;

/**
 * Class _Client was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Client extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String CLIENT_ID_PK_COLUMN = "client_id";

    public static final Property<String> NAME = Property.create("name", String.class);
    public static final Property<byte[]> PICTURE_LEFT = Property.create("pictureLeft", byte[].class);
    public static final Property<byte[]> PICTURE_MID = Property.create("pictureMid", byte[].class);
    public static final Property<byte[]> PICTURE_RIGHT = Property.create("pictureRight", byte[].class);
    public static final Property<List<Account>> ACCOUNTS = Property.create("accounts", List.class);
    public static final Property<List<Car>> CARS = Property.create("cars", List.class);

    protected String name;
    protected byte[] pictureLeft;
    protected byte[] pictureMid;
    protected byte[] pictureRight;

    protected Object accounts;
    protected Object cars;

    public void setName(String name) {
        beforePropertyWrite("name", this.name, name);
        this.name = name;
    }

    public String getName() {
        beforePropertyRead("name");
        return this.name;
    }

    public void setPictureLeft(byte[] pictureLeft) {
        beforePropertyWrite("pictureLeft", this.pictureLeft, pictureLeft);
        this.pictureLeft = pictureLeft;
    }

    public byte[] getPictureLeft() {
        beforePropertyRead("pictureLeft");
        return this.pictureLeft;
    }

    public void setPictureMid(byte[] pictureMid) {
        beforePropertyWrite("pictureMid", this.pictureMid, pictureMid);
        this.pictureMid = pictureMid;
    }

    public byte[] getPictureMid() {
        beforePropertyRead("pictureMid");
        return this.pictureMid;
    }

    public void setPictureRight(byte[] pictureRight) {
        beforePropertyWrite("pictureRight", this.pictureRight, pictureRight);
        this.pictureRight = pictureRight;
    }

    public byte[] getPictureRight() {
        beforePropertyRead("pictureRight");
        return this.pictureRight;
    }

    public void addToAccounts(Account obj) {
        addToManyTarget("accounts", obj, true);
    }

    public void removeFromAccounts(Account obj) {
        removeToManyTarget("accounts", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Account> getAccounts() {
        return (List<Account>)readProperty("accounts");
    }

    public void addToCars(Car obj) {
        addToManyTarget("cars", obj, true);
    }

    public void removeFromCars(Car obj) {
        removeToManyTarget("cars", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Car> getCars() {
        return (List<Car>)readProperty("cars");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "name":
                return this.name;
            case "pictureLeft":
                return this.pictureLeft;
            case "pictureMid":
                return this.pictureMid;
            case "pictureRight":
                return this.pictureRight;
            case "accounts":
                return this.accounts;
            case "cars":
                return this.cars;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "name":
                this.name = (String)val;
                break;
            case "pictureLeft":
                this.pictureLeft = (byte[])val;
                break;
            case "pictureMid":
                this.pictureMid = (byte[])val;
                break;
            case "pictureRight":
                this.pictureRight = (byte[])val;
                break;
            case "accounts":
                this.accounts = val;
                break;
            case "cars":
                this.cars = val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.name);
        out.writeObject(this.pictureLeft);
        out.writeObject(this.pictureMid);
        out.writeObject(this.pictureRight);
        out.writeObject(this.accounts);
        out.writeObject(this.cars);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.name = (String)in.readObject();
        this.pictureLeft = (byte[])in.readObject();
        this.pictureMid = (byte[])in.readObject();
        this.pictureRight = (byte[])in.readObject();
        this.accounts = in.readObject();
        this.cars = in.readObject();
    }

}
