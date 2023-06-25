package interfaces;

import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Partner;
import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface I_Edition {

    public void addEdition(Edition edition);

    public void removeEdition(String name);

    public void defineAsActive(String name);

    public void defineAsInactive(String name);

    public void defineAsCanceled(String name);

    public void defineAsClosed(String name);

    //Return all editions
    Edition[] getEditions();

    Edition getEdition(String name);

    //Return the edition with projects that have missing submissions
    Edition[] getProjectsWithMissingSubmissions();

    //Return the number of existing editions
    int getNumberOfEditions();

    //Return a textual representation of the progress of a specific edition
    String getEditionProgress(Edition edition);
    String getProjectProgress(Project project);

    public Edition getActiveEdition();

    public Edition[] getAllInactiveEditions();

    public Edition[] getAllClosedEditions();

    public Edition[] getAllCanceledEditions();

    String rankOfProjects();

    Student getStudent(String participantName);

    public Facilitator getFacilitator(String name);

    public Partner getPartner(String name);

    //Add submissions to a project of an active edition by a participant in specific a student
    public void addSubmission(String editionName, String projectName, String taskName, String studentName, Submission submission);

    public String getSudentsWithMoreSubmissions(Edition edition, Project project);

    //Add project to any edition
    String addProjectToEdition(String editionName, String projectName, String description, String[] tags);

    //Return the projects with missing submissions about a specific edition
    Project[] getProjectsWithMissingSubmissions(Edition edition);

    //Return the number of projects about a specific edition
    int getNumberOfProjects(String editionName);

    //Get Project by name
    Project getProject(String projectName);

    //Return last three submissions of a specific task
    public Submission[] getLastThreeSubmissions(Task task);

    Task[] getTasksByDate(Project project, LocalDate date);
}
