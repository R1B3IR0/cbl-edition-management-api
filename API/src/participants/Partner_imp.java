package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Partner;

public class Partner_imp extends Participant_imp implements Partner {
    private String vat;
    private String website;
    /**
     * Constructor of the Partner class.
     *
     * @param name         the name of the Partner
     * @param email        the email of the Partner
     * @param contact      the contact of the Partner
     * @param instituition the instituition of the Partner
     * @param vat          the vat number of the Partner
     * @param website      the website of the Partner
     */
    public Partner_imp(String name, String email, Contact_imp contact, Instituition_imp instituition, String vat, String website) {
        super(name, email, contact, instituition);
        this.vat = vat;
        this.website = website;
    }


    @Override
    public String getVat() {
        return vat;
    }

    @Override
    public String getWebsite() {
        return website;
    }


    @Override
    public String toString() {
        String text = "";

        text += "----Partner----"
                + super.toString()
                +"\nVAT: " + vat
                +"\nWebsite: " + website;

        return text;
    }
}
