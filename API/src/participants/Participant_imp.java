package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;


public abstract class Participant_imp implements Participant {
    private String name;
    private String email;
    private Contact_imp contact;
    private Instituition_imp instituition;

    public Participant_imp(String name, String email, Contact_imp contact, Instituition_imp instituition) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.instituition = instituition;
    }
    public Participant_imp(String name, String email, Instituition_imp instituition) {
        this.name = name;
        this.email = email;
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
    public Instituition_imp getInstituition() {
        return instituition;
    }

    @Override
    public void setInstituition(Instituition instituition) {
        this.instituition = (Instituition_imp) instituition;
    }

    @Override
    public void setContact(Contact contact) {
        this.contact = (Contact_imp) contact;
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

        text += "\nName: " + name
                + "\nEmail: " + email
                + "\n--Contact--\n" + contact
                + "\n--Instituition--\n" + instituition;
        return text;
    }

}
