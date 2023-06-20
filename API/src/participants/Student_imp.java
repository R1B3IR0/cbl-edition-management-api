package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Student;

public class Student_imp extends Participant_imp implements Student {
    private int number;

    /**
     * Constructs a Student object with the specified name, email, contact,
     * instituition, and number.
     *
     * @param name         the name of the student
     * @param email        the email of the student
     * @param contact      the contact of the student
     * @param instituition the instituition of the student
     * @param number       the number of the student
     */
    public Student_imp(String name, String email, Contact_imp contact, Instituition_imp instituition, int number) {
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

        text += super.toString()
                +"\nNumber: " + number;

        return text;
    }
}
