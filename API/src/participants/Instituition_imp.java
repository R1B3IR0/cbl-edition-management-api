package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.InstituitionType;

public class Instituition_imp implements Instituition {
    private String name;
    private String email;
    private InstituitionType type;
    private Contact_imp contact;
    private String website;
    private String description;

    /**
     * Constructs a BaseInstitution object with the specified institution details.
     *
     * @param name
     * @param email
     * @param type
     * @param contact
     * @param website
     * @param description
     */
    public Instituition_imp(String name, String email, InstituitionType type, Contact_imp contact, String website, String description) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.contact = contact;
        this.website = website;
        this.description = description;
    }

    /**
     * Returns the name of the institution.
     *
     * @return the name of the institution
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Returns the email of the institution.
     *
     * @return the email of the institution
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Returns the type of the institution.
     * @return
     */
    @Override
    public InstituitionType getType() {
        return type;
    }
    /**
     * Returns the contact information of the institution.
     *
     * @return the contact information of the institution
     */
    @Override
    public Contact_imp getContact() {
        return contact;
    }
    /**
     * Returns the website of the institution.
     *
     * @return the website of the institution
     */
    @Override
    public String getWebsite() {
        return website;
    }
    /**
     * Returns the description of the institution.
     *
     * @return the description of the institution
     */
    @Override
    public String getDescription() {
        return description;
    }
    /**
     * Sets the name of the institution.
     *
     * @param s the name of the institution
     */
    @Override
    public void setWebsite(String s) {
        this.website = s;
    }
    /**
     * Sets the email of the institution.
     *
     * @param s the email of the institution
     */
    @Override
    public void setDescription(String s) {
        this.description = s;
    }
    /**
     * Sets the contact information of the institution.
     *
     * @param contact the contact information of the institution
     */
    @Override
    public void setContact(Contact contact) {
        this.contact = (Contact_imp) contact;
    }

    /**
     * Sets the type of the institution.
     *
     * @param instituitionType the type of the institution
     */
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
        String text = "";

        text += "Name: " + this.name + "\n"
                +"Email: " + this.email + "\n"
                + "Type: " + this.type + "\n"
                + "Contact:\n" + this.contact + "\n"
                + "Website: " + this.website + "\n"
                + "Description: " + this.description;
        return text;
    }
}
