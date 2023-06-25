package participants;

import ma02_resources.participants.Contact;

/**
 * Implementação da interface Contact que representa um contato com as seguintes informações:
 * - Rua
 * - Cidade
 * - Estado
 * - Código Postal
 * - País
 * - Número de telefone
 */
public class Contact_imp implements Contact {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phone;

    /**
     * Cria um novo objeto de contato com as informações fornecidas.
     *
     * @param street   o nome da rua do contato
     * @param city     a cidade do contato
     * @param state    o estado do contato
     * @param zipCode  o código postal do contato
     * @param country  o país do contato
     * @param phone    o número de telefone do contato
     */
    public Contact_imp(String street, String city, String state, String zipCode, String country, String phone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
    }

    /**
     * Retorna o nome da rua do contato.
     * @return o nome da rua do contato
     */
    @Override
    public String getStreet() {
        return street;
    }

    /**
     * Retorna a cidade do contato.
     * @return a cidade do contato
     */
    @Override
    public String getCity() {
        return city;
    }

    /**
     * Retorna o estado do contato.
     * @return o estado do contato
     */
    @Override
    public String getState() {
        return state;
    }

    /**
     * Retorna o código postal do contato.
     * @return o código postal do contato
     */
    @Override
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Retorna o país do contato.
     * @return o país do contato
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * Retorna o número de telefone associado a este contato.
     * @return o número de telefone do contato
     */
    @Override
    public String getPhone() {
        return phone;
    }

    /**
     *
     /**
     * Verifica se o objeto atual é igual ao objeto fornecido como argumento.
     * Retorna true se os objetos forem iguais em termos de seus atributos de contato
     * (rua, cidade, estado, código postal, país e telefone), caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Contact_imp)) {
            return false;
        }

        boolean equal = false;
        Contact_imp tmp = (Contact_imp) obj;
        if(tmp.getStreet() == this.getStreet()){
            if(tmp.getCity() == this.getCity()){
                if(tmp.getState() == this.getState()){
                    if(tmp.getZipCode() == this.getZipCode()){
                        if(tmp.getCountry() == this.getCountry()){
                            if(tmp.getPhone() == this.getPhone()){
                                equal = true;
                            }
                        }
                    }
                }
            }
        }
        return equal;
    }

    /**
     * Retorna uma representação em formato de texto deste contato.
     * O texto contém as informações sobre o endereço e o número de telefone do contato.
     * @return uma string contendo as informações do contato
     */

    @Override
    public String toString() {

        String text = "";

        text += "Street: " + street
                + "\nCity: " + city
                + "\nState: " + state
                + "\nZip Code: " + zipCode
                + "\nCountry: " + country
                + "\nPhone: " + phone;
        return text;
    }
}
