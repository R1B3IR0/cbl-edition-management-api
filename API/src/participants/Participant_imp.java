package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;
import ma02_resources.participants.InstituitionType;

public abstract class Participant_imp implements Participant {
    private String name;
    private String email;
    private Contact contact;
    private Instituition instituition;

    public Participant_imp(String name, String email, Contact contact, Instituition instituition) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.instituition = instituition;
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
    public Contact getContact() {
        return contact;
    }

    @Override
    public Instituition getInstituition() {
        return instituition;
    }

    @Override
    public void setInstituition(Instituition instituition) {
        this.instituition = instituition;
    }

    @Override
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Participant_imp)) {
            return false;
        }

        boolean equal = false;
        Participant_imp tmp = (Participant_imp) obj;

        if (tmp.getEmail() == this.email) {
            equal = true;
        }

        return equal;
    }

    @Override
    public String toString() {
        String text = "";

        text += "----Info Participant----"
                + "\nName: " + name
                + "\nEmail: " + email
                + "\n--Contact--\n" + contact.toString()
                + "\n--Instituition-- " + instituition;

        return text;
    }

}
