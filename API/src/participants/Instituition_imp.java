package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.InstituitionType;

/**
 * Implementação da interface Instituition que representa uma instituição.
 * Esta classe contém informações sobre o nome, email, tipo, contato, website e descrição da instituição.
 */
public class Instituition_imp implements Instituition {
    private String name;
    private String email;
    private InstituitionType type;
    private Contact_imp contact;
    private String website;
    private String description;

    /**
     * Cria uma nova instância da classe Instituition_imp.
     *
     * @param name        o nome da instituição
     * @param email       o email da instituição
     * @param type        o tipo de instituição
     * @param contact     os dados de contato da instituição
     * @param website     o website da instituição
     * @param description a descrição da instituição
     */
    public Instituition_imp(String name, String email, InstituitionType type, Contact_imp contact, String website, String description) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.contact = contact;
        this.website = website;
        this.description = description;
    }

    /**
     * Retorna o nome da instituição.
     *
     * @return o nome da instituição
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Retorna o email da instituição.
     *
     * @return o email da instituição
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Retorna o type da instituição.
     *
     * @return o type da instituição
     */
    @Override
    public InstituitionType getType() {
        return type;
    }
    /**
     * Retorna o contato da instituição.
     *
     * @return o contato da instituição
     */
    @Override
    public Contact_imp getContact() {
        return contact;
    }
    /**
     * Retorna o website da instituição.
     *
     * @return o website da instituição
     */
    @Override
    public String getWebsite() {
        return website;
    }
    /**
     * Retorna a descrição da instituição.
     *
     * @return a descrição da instituição
     */
    @Override
    public String getDescription() {
        return description;
    }
    /**
     * Define o website da instituição.
     *
     * @param s o website da instituição
     */
    @Override
    public void setWebsite(String s) {
        this.website = s;
    }
    /**
     /**
     * Define a descrição da instituição.
     *
     * @param s a descrição da instituição
     */
    @Override
    public void setDescription(String s) {
        this.description = s;
    }
    /**
     * Define o contato da instituição.
     *
     * @param contact o contato da instituição
     */
    @Override
    public void setContact(Contact contact) {
        this.contact = (Contact_imp) contact;
    }

    /**
     * Define o tipo de instituição.
     *
     * @param instituitionType o tipo de instituição
     */
    @Override
    public void setType(InstituitionType instituitionType) {
        this.type = instituitionType;
    }

    /**
     * Verifica se a instituição é igual a outro objeto.
     *
     * @param var1 o objeto a ser comparado
     * @return true se a instituição for igual ao objeto, caso contrário false
     */

    @Override
    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        }
        if(var1 == null) {
            return false;
        }
        if(!(var1 instanceof Instituition_imp)) {
            return false;
        }

        boolean equal = false;
        Instituition_imp tmp = (Instituition_imp) var1;

        if(tmp.getName() == this.name) {
            equal = true;
        }

        return equal;
    }

    /**
     * Retorna uma representação em formato de string da instituição.
     *
     * @return uma string que representa a instituição
     */

    @Override
    public String toString() {
        String text = "";

        text += "Name: " + this.name + "\n"
                +"Email: " + this.email + "\n"
                + "Type: " + this.type + "\n"
                + "Contact:\n" + this.contact + "\n"
                + "Website: " + this.website + "\n"
                + "Description: " + this.description;
        return text;
    }
}
