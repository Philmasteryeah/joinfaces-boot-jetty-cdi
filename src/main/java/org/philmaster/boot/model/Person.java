package org.philmaster.boot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Person implements Serializable {  
	  
    private static final long serialVersionUID = 20111128L;  
  
    private String id;  
    private String name;  
    private int taxClass;  
    private Date birthDate;  
    private Set<String> languageSkills = new HashSet<String>();  
  
    public Person(String id, String name, int taxClass, Date birthDate) {  
        this.id = id;  
        this.name = name;  
        this.taxClass = taxClass;  
        this.birthDate = birthDate;  
    }  
  
    public String getId() {  
        return id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public int getTaxClass() {  
        return taxClass;  
    }  
  
    public void setTaxClass(int taxClass) {  
        this.taxClass = taxClass;  
    }  
  
    public Date getBirthDate() {  
        return birthDate;  
    }  
  
    public void setBirthDate(Date birthDate) {  
        this.birthDate = birthDate;  
    }  
  
    public String[] getLanguageSkills() {  
        return languageSkills.toArray(new String[languageSkills.size()]);  
    }  
  
    public void addLanguageSkill(String languageSkill) {  
        languageSkills.add(languageSkill);  
    }  
} 
