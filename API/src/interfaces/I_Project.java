package interfaces;

import ma02_resources.project.Edition;
import ma02_resources.project.Project;

public interface I_Project {

    //Add project to any edition
    public void addProjectToEdition(String editionName, String projectName);

    //Return the projects with missing submissions about a specific edition
    Project[] getProjectsWithMissingSubmissions(Edition edition);

    //Return the number of projects about a specific edition
    int getNumberOfProjects(Edition edition);

    //Return a textual representation of the progress of a specific project in a specific edition
    String getProjectProgress(Edition edition, Project project); //Verificar parametro depois talvez seja so Edition edition
}
