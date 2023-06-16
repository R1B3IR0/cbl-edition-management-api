package project;


import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;


import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;


public class Edition_imp implements Edition {
    private String name;
    private LocalDate start;
    private LocalDate end;
    private String projectTemplate;
    private Status status;
    private Project_imp[] projects;
    private int numberOfProjects = 0;

    public Edition_imp(String name, LocalDate start, LocalDate end, String projectTemplate, Status status, Project_imp[] projects) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = status.INACTIVE;
        this.projects = projects;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public String getProjectTemplate() {
        return projectTemplate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        } else {
            this.status = status;
        }
    }

    /**
     * This method adds a project to the edition. The project is created from a template JSON.
     *
     * @param name        name of the project
     * @param description description of the project
     * @param tags        tags of the project
     * @throws IOException    - if the project template is not found.
     * @throws ParseException - if the project template is not valid
     * @theows IllegalArgumentException -  if the project name is null or empty, if the project already exists, if the description is null or empty, if the tags are null or empty.
     */
    @Override
    public void addProject(String name, String description, String[] tags) throws IOException, ParseException {


    }

    /**
     * This method removes a project from the edition. The project is identified by its name.
     *
     * @param name The name of the project
     * @throws IllegalArgumentException - if project name is null or empty, or project does not exist.
     */
    @Override
    public void removeProject(String name) {
        int[] positions = new int[numberOfProjects];
        int found = 0;

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Project name can't be null or empty");
        } else {
            for (int i = 0; i < numberOfProjects; i++) {
                if (projects[i].getName().equals(name)) {
                    positions[i] = 1;
                    found++;
                }
            }
            // Remove project
            if (found > 0) {
                Project_imp[] tmp = new Project_imp[numberOfProjects - found];
                int tmpPosition = 0;
                // Copy all projects that are not to be removed
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i] == 0) {
                        tmp[tmpPosition] = projects[i];
                        tmpPosition++;
                    }
                }
                projects = tmp;
                numberOfProjects--;
            } else {
                throw new IllegalArgumentException("Project does not exist");
            }
        }
    }

    /**
     * This method returns a project from the edition. The project is identified by its name.
     *
     * @param name The name of the project
     * @return The method returns a project of the edition.
     * @throws IllegalArgumentException - if project name is null or empty, or project does not exist.
     */
    @Override
    public Project getProject(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Project name can't be null or empty");
        } else {
            for (int i = 0; i < numberOfProjects; i++) {
                if (projects[i].getName().equals(name)) {
                    return projects[i];
                }
            }
            throw new IllegalArgumentException("Project does not exist");
        }
    }

    /**
     * This method returns all projects of the edition.
     *
     * @return The method returns an array of projects.
     */
    @Override
    public Project[] getProjects() {
        return projects;
    }

    /**
     * This method returns all projects of the edition that have a specific tag.
     *
     * @param tag The tag of the projects
     * @return The method returns an array of projects.
     */
    @Override
    public Project[] getProjectsByTag(String tag) {
        return new Project[0];
    }

    /**
     * This method returns all projects of the edition that have a specific participant.
     *
     * @param email
     * @return The method returns an array of projects.
     */
    @Override
    public Project[] getProjectsOf(String email) {
        if (projects == null) {
            throw new IllegalArgumentException("Projects can't be null");
        } else {
            for (int i = 0; i < numberOfProjects; i++) {
                for (int j = 0; i < projects[i].getNumberOfParticipants(); i++) {


                }
            }
        }
        return new Project[0];
    }


    /**
     * This method returns the number of projects in the edition.
     *
     * @return
     */
    @Override
    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    /**
     * This method returns the date of the last task ending date in the projects.
     *
     * @return The method returns the date of the last task ending date in the projects.
     */
    @Override
    public LocalDate getEnd() {
        LocalDate end = null;
        for (int i = 0; i < numberOfProjects; i++) {
            for (int j = 0; i < projects[i].getNumberOfTasks(); i++) {
                if (projects[i].getTasks()[j].getEnd().isAfter(end)) {
                    end = projects[i].getTasks()[j].getEnd();
                }
            }
        }
        return end;
    }
}
