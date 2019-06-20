package org.philmaster.boot.model.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;
import org.philmaster.boot.model.RolePrivilege;

/**
 * Class _Privilege was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Privilege extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String PRIVILEGE_ID_PK_COLUMN = "privilege_id";

    public static final Property<String> NAME = Property.create("name", String.class);
    public static final Property<List<RolePrivilege>> ROLES = Property.create("roles", List.class);

    protected String name;

    protected Object roles;

    public void setName(String name) {
        beforePropertyWrite("name", this.name, name);
        this.name = name;
    }

    public String getName() {
        beforePropertyRead("name");
        return this.name;
    }

    public void addToRoles(RolePrivilege obj) {
        addToManyTarget("roles", obj, true);
    }

    public void removeFromRoles(RolePrivilege obj) {
        removeToManyTarget("roles", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<RolePrivilege> getRoles() {
        return (List<RolePrivilege>)readProperty("roles");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "name":
                return this.name;
            case "roles":
                return this.roles;
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
            case "roles":
                this.roles = val;
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
        out.writeObject(this.roles);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.name = (String)in.readObject();
        this.roles = in.readObject();
    }

}
