package project;

public class EditionManagement_imp {

    private String name;
    private Edition_imp[] editions;
    private int numberOfEditions;
    private final int MAXIMUM_NUMBER_OF_EDITIONS;

    public EditionManagement_imp() {
        this.MAXIMUM_NUMBER_OF_EDITIONS = 5;
        this.editions = new Edition_imp[MAXIMUM_NUMBER_OF_EDITIONS];
        this.numberOfEditions = 0;

    }

    /**
     * Add edition
     *
     * @param edition
     */
    public void addEdition(Edition_imp edition) {
        if (edition == null) {
            System.out.println("Edition is null");

        }

        if (numberOfEditions >= MAXIMUM_NUMBER_OF_EDITIONS) {
            Edition_imp[] tmp = new Edition_imp[(numberOfEditions * 2)];
            for (int i = 0; i < numberOfEditions; i++) {
                tmp[i] = editions[i];
            }
            editions = tmp;
        }
        if (find(edition.getName()) != -1) {
            System.out.println("Edition doesn't exist");
        }
        editions[numberOfEditions++] = edition;
        System.out.println("Edition: " + edition.getName() + " added with success");
    }

    /**
     * Find edition by name
     *
     * @param name
     * @return
     */
    private int find(String name) {
        int pos = -1;
        int i = 0;

        while (pos == -1 && i < numberOfEditions) {
            if (name == editions[i].getName()) {
                pos = i;
            } else {
                i++;
            }
        }
        return pos;
    }

    /**
     * Remove edition by name
     *
     * @param name
     */
    public void removeEdition(String name) {
        int[] positions = new int[MAXIMUM_NUMBER_OF_EDITIONS];
        int found = 0;

        for (int i = 0; i < numberOfEditions; i++) {
            if (editions[i].getName() == name) {
                positions[i] = 1;
                found++;
            }
        }

        if (found > 0) {
            Edition_imp[] tmp = new Edition_imp[MAXIMUM_NUMBER_OF_EDITIONS - found];
            int tmpPosition = 0;

            for (int i = 0; i < positions.length; i++) {
                if (positions[i] == 1) {
                    tmp[tmpPosition] = editions[i];
                    tmpPosition++;
                }
            }
            editions = tmp;
            numberOfEditions--;
        } else {
            System.out.println("Edition doesn't exist");
        }
    }

    /**
     * Get edition by name
     *
     * @param name
     * @return
     */
    public Edition_imp getEdition(String name) {
        Edition_imp edition = null;
        for (int i = 0; i < numberOfEditions; i++) {
            if (editions[i].getName().equals(name)) {
                edition = editions[i];
            }
        }
        return edition;
    }

    public Edition_imp[] getEditions() {
        return editions;
    }

    public int getNumberOfEditions() {
        return numberOfEditions;
    }

    /**
     * Define which edition
     * active. There should only be one currently active edition.
     */
    public void setActiveEdition() {
        int activeEdition = 0;
        for (int i = 0; i < numberOfEditions; i++) {
            if (editions[i].isActive()) {
                activeEdition++;
            }
        }
        if (activeEdition > 1) {
            System.out.println("There should only be one currently active edition");
        }
    }
}
