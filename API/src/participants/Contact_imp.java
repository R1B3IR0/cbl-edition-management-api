package participants;

import ma02_resources.participants.Contact;

public class Contact_imp implements Contact {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phone;

    /**
     * Constructor for Contact_imp
     * @param street
     * @param city
     * @param state
     * @param zipCode
     * @param country
     * @param phone
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
     * returns the {@code street} of the Contact
     * @return
     */
    @Override
    public String getStreet() {
        return street;
    }

    /**
     * returns the {@code city} of the Contact
     * @return
     */
    @Override
    public String getCity() {
        return city;
    }

    /**
     * returns the {@code state} of the Contact
     * @return
     */
    @Override
    public String getState() {
        return state;
    }

    /**
     * returns the {@code zipCode} of the Contact
     * @return
     */
    @Override
    public String getZipCode() {
        return zipCode;
    }

    /**
     * return the {@code country} of the Contact
     * @return
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * return the {@code phone} of the Contact
     * @return
     */
    @Override
    public String getPhone() {
        return phone;
    }

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
