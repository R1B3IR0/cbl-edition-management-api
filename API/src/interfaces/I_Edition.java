package interfaces;

import ma02_resources.project.Edition;

public interface I_Edition {

    public void addEdition(Edition edition);

    public void removeEdition(String name);

    public void defineAsActive(String name);

    public void defineAsInactive(String name);

    public void defineAsCanceled(String name);

    public void defineAsClosed(String name);

    //Return all editions
    Edition[] getEditions();

    //Edition getEdition(String name);

    //Return the edition with projects that have missing submissions
    Edition[] getProjectsWithMissingSubmissions();

    //Return the number of existing editions
    int getNumberOfEditions();

    //Return a textual representation of the progress of a specific edition
    String getEditionProgress(Edition edition);

    public Edition getActiveEdition();

    public Edition[] getAllInactiveEditions();

    public Edition[] getAllClosedEditions();

    public Edition[] getAllCanceledEditions();

}
