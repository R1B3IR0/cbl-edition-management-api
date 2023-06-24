package program;

import interfaces.Event;
import ma02_resources.participants.Participant;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import project.EditionManagement_imp;
import project.Edition_imp;
import project.Project_imp;

public class ProjectMenu implements Display {

    private Edition edition;

    private Project_imp project;
    private EditionManagement_imp editionManagement;

    public ProjectMenu(Edition edition) {
        this.edition = edition;
    }

    public static void display(ControlPanel control, ProjectMenu projectMenu) {

    }


    @Override
    public void display() {
        System.out.println("\t===============================");
        System.out.println("\t--------- Project Menu --------");
        System.out.println("\t    1 - Add Project            ");
        System.out.println("\t    2 - Add Project to Edition");
        System.out.println("\t    3 - Add Participant to Project");
        System.out.println("\t    4 - Add Task to Project    ");
        System.out.println("\t    5 - Add Event to Project   ");
        System.out.println("\t    6 - Delete Project         ");
        System.out.println("\t    7 - Delete Participant from Project");
        System.out.println("\t    8 - Delete Event from Project");
        System.out.println("\t    9 - Get Progress from Project");
        System.out.println("\t    10 - List Projects          ");
        System.out.println("\t    11 - List Participants      ");
        System.out.println("\t    12 - List Tasks            ");
        System.out.println("\t    13 - List Events           ");
        System.out.println("\t    14 - Exit                  ");
        System.out.println("\t===============================");
    }

    public void display(ControlPanel control) {
        boolean isRunning = true;

        String projectTitle, projectDescription, participantName, projectName;
        String[] projectTags;
        String editionTitle;

        do {
            int choice = control.displayMenu(this);
            switch (choice) {
                case 1: // Add Project
                    try {
                        System.out.println("Most of a project's attributes are passed from a json file, which has to be " +
                                "at the root of the project folder.\n");
                        System.out.println("The json file must be named 'project.json'.\n");

                        projectTitle = control.getUserInputString("Enter the title of the project: ");
                        projectDescription = control.getUserInputString("Enter the description of the project: ");
                        projectTags = control.getUserInputString("Enter the tags of the project: ").split(",");

                        control.getEditions().getEdition(edition.getName()).addProject(projectTitle, projectDescription, projectTags);

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2: // Add Project to Edition //Testar depois
                    try {
                        editionTitle = control.getUserInputString("Enter the name edition: ");
                        projectTitle = control.getUserInputString("Enter the title of the project you want to add in edition selected: ");
                        projectDescription = control.getUserInputString("Enter the description of the project: ");
                        projectTags = control.getUserInputString("Enter the tags of the project: ").split(",");


                        control.getEditions().getEdition(editionManagement.addProjectToEdition(editionTitle, projectTitle, projectDescription, projectTags));
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 3: // Add Participant to Project
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to add:" +
                                "participant to: ");

                        control.getEditions().getEdition(edition.getName()).getProject(projectTitle).addParticipant(control.getInfoStudent());

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 4: // Add Task to Project
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to add:" +
                                "task: ");
                        Task task = control.getInfoTask();

                        control.getEditions().getEdition(edition.getName()).getProject(projectTitle).addTask(task);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 5: // Add Event to Project //Testar depois
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to add:" +
                                "event: ");

                        Event event = control.getInfoEvent();

                        control.getEditions().getEdition(edition.getName()).getProject(project.addEvent(event));

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 6: // Delete Project
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you to delete: ");

                        control.getEditions().getEdition(edition.getName()).removeProject(projectTitle);
                    } catch (NullPointerException e) {
                        System.out.println("Edition not found");
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 7: // Delete Participant from Project
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to delete:" +
                                "participant from: ");
                        participantName = control.getUserInputString("Enter the name of the student you want to delete: ");

                        control.getEditions().getEdition(edition.getName()).getProject(projectTitle).removeParticipant(participantName);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 8: // Delete Event from Project // Ver depois
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to delete:" +
                                "event from: ");
                        String eventTitle = control.getUserInputString("Enter the title of the event you want to delete: ");

                       // control.getEditions().getEdition(edition.getName()).getProject(projectTitle).removeEvent(eventTitle);
                       // control.getEditions().getEdition(edition.getName()).getProject(project.addEvent(eventTitle));
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 9: // Get Progress from Project
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to get the progress: ");

                        Project searchedProject = control.getEditions().getEdition(edition.getName()).getProject(projectTitle);

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 10: // List Projects
                    try {
                        for(Project list : control.getEditions().getEdition(edition.getName()).getProjects()) {
                            System.out.println(list.toString() + "\n");
                        }
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 11: // List Participants from Project
                    try {

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 12: //List tasks
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to list the tasks: ");

                        for(Task list : control.getEditions().getEdition(edition.getName()).getProject(projectTitle).getTasks()) {
                            System.out.println(list.toString() + "\n");
                        }
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 13: //List events // Ver depois
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to list the events: ");

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 14:
                    isRunning = false;
                    break;
            }

        } while (isRunning);

    }
}