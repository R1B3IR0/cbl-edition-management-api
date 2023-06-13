package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Instituition;

public class Facilitator_imp extends Participant_imp  implements Facilitator {
    private String areaOfExpertise;

    public Facilitator_imp(String name, String email, Contact contact, Instituition instituition, String areaOfExpertise) {
        super(name, email, contact, instituition);
        this.areaOfExpertise = areaOfExpertise;
    }


    @Override
    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    @Override
    public void setAreaOfExpertise(String s) {
        this.areaOfExpertise = s;
    }

    @Override
    public String toString() {
        String text = "";

        text += "----Facilitator----"
                + super.toString()
                +"\nArea of Expertise: " + areaOfExpertise;

        return text;
    }
}
