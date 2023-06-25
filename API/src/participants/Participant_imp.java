package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;

/**
 * Classe abstrata que representa um participante.
 * Implementa a interface Participant.
 * Possui atributos como nome, email, contato e instituição.
 */
public abstract class Participant_imp implements Participant {
    private String name;
    private String email;
    private Contact_imp contact;
    private Instituition_imp instituition;

    /**
     * Constrói um participante com o nome, email, contato e instituição fornecidos.
     *
     * @param name         o nome do participante
     * @param email        o email do participante
     * @param contact      o contato do participante
     * @param instituition a instituição do participante
     */

    public Participant_imp(String name, String email, Contact_imp contact, Instituition_imp instituition) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.instituition = instituition;
    }

    /**
     * Constrói um participante com o nome, email e instituição fornecidos.
     *
     * @param name         o nome do participante
     * @param email        o email do participante
     * @param instituition a instituição do participante
     */

    public Participant_imp(String name, String email, Instituition_imp instituition) {
        this.name = name;
        this.email = email;
        this.instituition = instituition;
    }
    /**
     * Retorna o nome do participante.
     *
     * @return o nome do participante.
     */

    @Override
    public String getName() {
        return name;
    }

    /**
     * Retorna o email do participante.
     *
     * @return o email do participante.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Retorna o contato do participante.
     *
     * @return o contato do participante.
     */

    @Override
    public Contact getContact() {
        return contact;
    }

    /**
     * Retorna a instituição do participante.
     *
     * @return a instituição do participante.
     */

    @Override
    public Instituition_imp getInstituition() {
        return instituition;
    }

    /**
     * Define a instituição do participante.
     *
     * @param instituition a instituição do participante.
     */

    @Override
    public void setInstituition(Instituition instituition) {
        this.instituition = (Instituition_imp) instituition;
    }

    /**
     * Define o contato do participante.
     *
     * @param contact o contato do participante.
     */

    @Override
    public void setContact(Contact contact) {
        this.contact = (Contact_imp) contact;
    }

    /**
     * Verifica se o participante é igual a outro objeto.
     *
     * @param obj o objeto a ser comparado.
     * @return true se o participante for igual ao objeto, caso contrário retorna false.
     */

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

    /**
     * Retorna uma representação em string do participante.
     *
     * @return uma string contendo os detalhes do participante.
     */

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
