package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Partner;

public class Partner_imp extends Participant_imp implements Partner {
    private String vat;
    private String website;

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
