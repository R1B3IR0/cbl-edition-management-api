package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Student;

public class Student_imp extends Participant_imp implements Student {
    private int number;

    public Student_imp(String name, String email, Contact contact, Instituition instituition, int number) {
        super(name, email, contact, instituition);
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        String text = "";

        text += "----Student----"
                + super.toString()
                +"\nNumber: " + number;

        return text;
    }
}
