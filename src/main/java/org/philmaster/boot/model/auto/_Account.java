package org.philmaster.boot.model.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.model.Role;

/**
 * Class _Account was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Account extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ACCOUNT_ID_PK_COLUMN = "account_id";

    public static final Property<LocalDate> DATE_BIRTH = Property.create("dateBirth", LocalDate.class);
    public static final Property<Boolean> ENABLED = Property.create("enabled", Boolean.class);
    public static final Property<String> NAME_FIRST = Property.create("nameFirst", String.class);
    public static final Property<String> NAME_LAST = Property.create("nameLast", String.class);
    public static final Property<String> PASSWORD = Property.create("password", String.class);
    public static final Property<String> USERNAME = Property.create("username", String.class);
    public static final Property<Client> CLIENT = Property.create("client", Client.class);
    public static final Property<List<Questionnaire>> QUESTIONNAIRES = Property.create("questionnaires", List.class);
    public static final Property<List<Role>> ROLES = Property.create("roles", List.class);

    protected LocalDate dateBirth;
    protected Boolean enabled;
    protected String nameFirst;
    protected String nameLast;
    protected String password;
    protected String username;

    protected Object client;
    protected Object questionnaires;
    protected Object roles;

    public void setDateBirth(LocalDate dateBirth) {
        beforePropertyWrite("dateBirth", this.dateBirth, dateBirth);
        this.dateBirth = dateBirth;
    }

    public LocalDate getDateBirth() {
        beforePropertyRead("dateBirth");
        return this.dateBirth;
    }

    public void setEnabled(Boolean enabled) {
        beforePropertyWrite("enabled", this.enabled, enabled);
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        beforePropertyRead("enabled");
        return this.enabled;
    }

    public void setNameFirst(String nameFirst) {
        beforePropertyWrite("nameFirst", this.nameFirst, nameFirst);
        this.nameFirst = nameFirst;
    }

    public String getNameFirst() {
        beforePropertyRead("nameFirst");
        return this.nameFirst;
    }

    public void setNameLast(String nameLast) {
        beforePropertyWrite("nameLast", this.nameLast, nameLast);
        this.nameLast = nameLast;
    }

    public String getNameLast() {
        beforePropertyRead("nameLast");
        return this.nameLast;
    }

    public void setPassword(String password) {
        beforePropertyWrite("password", this.password, password);
        this.password = password;
    }

    public String getPassword() {
        beforePropertyRead("password");
        return this.password;
    }

    public void setUsername(String username) {
        beforePropertyWrite("username", this.username, username);
        this.username = username;
    }

    public String getUsername() {
        beforePropertyRead("username");
        return this.username;
    }

    public void setClient(Client client) {
        setToOneTarget("client", client, true);
    }

    public Client getClient() {
        return (Client)readProperty("client");
    }

    public void addToQuestionnaires(Questionnaire obj) {
        addToManyTarget("questionnaires", obj, true);
    }

    public void removeFromQuestionnaires(Questionnaire obj) {
        removeToManyTarget("questionnaires", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Questionnaire> getQuestionnaires() {
        return (List<Questionnaire>)readProperty("questionnaires");
    }

    public void addToRoles(Role obj) {
        addToManyTarget("roles", obj, true);
    }

    public void removeFromRoles(Role obj) {
        removeToManyTarget("roles", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        return (List<Role>)readProperty("roles");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "dateBirth":
                return this.dateBirth;
            case "enabled":
                return this.enabled;
            case "nameFirst":
                return this.nameFirst;
            case "nameLast":
                return this.nameLast;
            case "password":
                return this.password;
            case "username":
                return this.username;
            case "client":
                return this.client;
            case "questionnaires":
                return this.questionnaires;
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
            case "dateBirth":
                this.dateBirth = (LocalDate)val;
                break;
            case "enabled":
                this.enabled = (Boolean)val;
                break;
            case "nameFirst":
                this.nameFirst = (String)val;
                break;
            case "nameLast":
                this.nameLast = (String)val;
                break;
            case "password":
                this.password = (String)val;
                break;
            case "username":
                this.username = (String)val;
                break;
            case "client":
                this.client = val;
                break;
            case "questionnaires":
                this.questionnaires = val;
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
        out.writeObject(this.dateBirth);
        out.writeObject(this.enabled);
        out.writeObject(this.nameFirst);
        out.writeObject(this.nameLast);
        out.writeObject(this.password);
        out.writeObject(this.username);
        out.writeObject(this.client);
        out.writeObject(this.questionnaires);
        out.writeObject(this.roles);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.dateBirth = (LocalDate)in.readObject();
        this.enabled = (Boolean)in.readObject();
        this.nameFirst = (String)in.readObject();
        this.nameLast = (String)in.readObject();
        this.password = (String)in.readObject();
        this.username = (String)in.readObject();
        this.client = in.readObject();
        this.questionnaires = in.readObject();
        this.roles = in.readObject();
    }

}
