package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Partner;

/**
 * Implementação da interface Partner que representa um parceiro.
 * Esta classe estende a classe Participant_imp e adiciona os atributos específicos de um parceiro.
 */

public class Partner_imp extends Participant_imp implements Partner {
    private String vat;
    private String website;

    /**
     * Cria um novo parceiro com o nome, email, contato, instituição, NIF e website fornecidos.
     *
     * @param name       o nome do parceiro
     * @param email      o email do parceiro
     * @param contact    as informações de contato do parceiro
     * @param instituition a instituição à qual o parceiro está associado
     * @param vat        o número de identificação fiscal (NIF) do parceiro
     * @param website    o website do parceiro
     */

    public Partner_imp(String name, String email, Contact_imp contact, Instituition_imp instituition, String vat, String website) {
        super(name, email, contact, instituition);
        this.vat = vat;
        this.website = website;
    }

    /**
     * Retorna o número de identificação fiscal (NIF) do parceiro.
     *
     * @return O número de identificação fiscal (NIF) do parceiro.
     */
    @Override
    public String getVat() {
        return vat;
    }

    /**
     * Retorna o website do parceiro.
     *
     * @return O website do parceiro.
     */

    @Override
    public String getWebsite() {
        return website;
    }


    /**
     * Retorna uma representação em forma de texto do parceiro.
     *
     * @return Uma representação em forma de texto do parceiro.
     */

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
