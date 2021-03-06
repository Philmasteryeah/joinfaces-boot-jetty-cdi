package org.philmaster.boot.model.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;
import org.philmaster.boot.model.Privilege;
import org.philmaster.boot.model.Role;

/**
 * Class _Access was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Access extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ACCESS_ID_PK_COLUMN = "access_id";

    public static final Property<Integer> FLAGS = Property.create("flags", Integer.class);
    public static final Property<Privilege> PRIVILEGE = Property.create("privilege", Privilege.class);
    public static final Property<Role> ROLE = Property.create("role", Role.class);

    protected Integer flags;

    protected Object privilege;
    protected Object role;

    public void setFlags(int flags) {
        beforePropertyWrite("flags", this.flags, flags);
        this.flags = flags;
    }

    public int getFlags() {
        beforePropertyRead("flags");
        if(this.flags == null) {
            return 0;
        }
        return this.flags;
    }

    public void setPrivilege(Privilege privilege) {
        setToOneTarget("privilege", privilege, true);
    }

    public Privilege getPrivilege() {
        return (Privilege)readProperty("privilege");
    }

    public void setRole(Role role) {
        setToOneTarget("role", role, true);
    }

    public Role getRole() {
        return (Role)readProperty("role");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "flags":
                return this.flags;
            case "privilege":
                return this.privilege;
            case "role":
                return this.role;
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
            case "flags":
                this.flags = (Integer)val;
                break;
            case "privilege":
                this.privilege = val;
                break;
            case "role":
                this.role = val;
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
        out.writeObject(this.flags);
        out.writeObject(this.privilege);
        out.writeObject(this.role);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.flags = (Integer)in.readObject();
        this.privilege = in.readObject();
        this.role = in.readObject();
    }

}
