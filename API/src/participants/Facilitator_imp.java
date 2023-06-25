package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Instituition;
/**
 * Retorna a área de expertise do facilitador.
 * @return a área de expertise do facilitador
 */
public class Facilitator_imp extends Participant_imp  implements Facilitator {
    private String areaOfExpertise;

    /**
     * Cria uma instância de Facilitator_imp com o nome, email, informações de contato, instituição e área de expertise especificados.
     *
     * @param name            nome do facilitador
     * @param email           email do facilitador
     * @param contact         informações de contato do facilitador
     * @param instituition    instituição do facilitador
     * @param areaOfExpertise área de expertise do facilitador
     */
    public Facilitator_imp(String name, String email, Contact_imp contact, Instituition_imp instituition, String areaOfExpertise) {
        super(name, email, contact, instituition);
        this.areaOfExpertise = areaOfExpertise;
    }


    /**
     * Retorna a área de expertise do facilitador.
     * @return a área de expertise do facilitador
     */
    @Override
    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    /**
     * Define a área de expertise do facilitador.
     *
     * @param s a área de expertise a ser definida
     */
    @Override
    public void setAreaOfExpertise(String s) {
        this.areaOfExpertise = s;
    }

    /**
     * Retorna uma representação em formato de string do facilitador.
     * @return uma string que representa o facilitador
     */
    @Override
    public String toString() {
        String text = "";

        text += "----Facilitator----"
                + super.toString()
                +"\nArea of Expertise: " + areaOfExpertise;

        return text;
    }
}
