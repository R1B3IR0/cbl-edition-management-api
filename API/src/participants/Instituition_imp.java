package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.InstituitionType;

public class Instituition_imp implements Instituition {
    private String name;
    private String email;
    private InstituitionType type;
    private Contact contact;
    private String website;
    private String description;

    public Instituition_imp(String name, String email, InstituitionType type, Contact contact, String website, String description) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.contact = contact;
        this.website = website;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public InstituitionType getType() {
        return type;
    }

    @Override
    public Contact getContact() {
        return contact;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setWebsite(String s) {
        this.website = s;
    }

    @Override
    public void setDescription(String s) {
        this.description = s;
    }

    @Override
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void setType(InstituitionType instituitionType) {
        this.type = instituitionType;
    }

    @Override
    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        }
        if(var1 == null) {
            return false;
        }
        if(!(var1 instanceof Instituition_imp)) {
            return false;
        }

        boolean equal = false;
        Instituition_imp tmp = (Instituition_imp) var1;

        if(tmp.getName() == this.name) {
            equal = true;
        }

        return equal;
    }

    @Override
    public String toString() {
        return "Instituition_imp{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", contact=" + contact +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
