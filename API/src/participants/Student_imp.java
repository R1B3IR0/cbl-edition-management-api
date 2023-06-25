package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Student;

/**
 * Implementação da interface Student que representa um student.
 * Esta classe estende a classe Participant_imp e adiciona os atributos específicos de um student.
 */

public class Student_imp extends Participant_imp implements Student {
    private int number;

    /**
     * Constrói um objeto Student_imp com as informações fornecidas.
     *
     * @param name       O nome do estudante.
     * @param email      O email do estudante.
     * @param contact    As informações de contato do estudante.
     * @param instituition    A instituição do estudante.
     * @param number     O número do estudante.
     */
    public Student_imp(String name, String email, Contact_imp contact, Instituition_imp instituition, int number) {
        super(name, email, contact, instituition);
        this.number = number;
    }

    /**
     * Retorna o número do estudante.
     *
     * @return O número do estudante.
     */

    @Override
    public int getNumber() {
        return number;
    }

    /**
     * Retorna uma representação em formato de string do objeto Student_imp.
     *
     * @return A representação em formato de string do objeto Student_imp.
     */

    @Override
    public String toString() {
        String text = "";

        text += super.toString()
                +"\nNumber: " + number;

        return text;
    }
}
