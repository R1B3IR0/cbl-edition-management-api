package program;

import interfaces.Event;
import ma02_resources.participants.Participant;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import project.EditionManagement_imp;
import project.Edition_imp;
import project.Project_imp;

/**
 * Classe responsável por exibir o menu na consola.
 */

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
        System.out.println("\t    1 - Add Project            ");    //OK
        System.out.println("\t    2 - Add Project to Edition");     //OK
        System.out.println("\t    3 - Add Participant to Project"); //OK
        System.out.println("\t    4 - Add Task to Project    ");    //OK
        System.out.println("\t    5 - Add Event to Edition   ");
        System.out.println("\t    6 - Delete Project         ");    //OK
        System.out.println("\t    7 - Delete Participant from Project");//OK
        System.out.println("\t    8 - Delete Event from Edition");
        System.out.println("\t    9 - Get Progress from Project");  //OK
        System.out.println("\t    10 - List Projects          ");   //OK
        System.out.println("\t    11 - List Participants      ");
        System.out.println("\t    12 - List Tasks            ");     //OK
        System.out.println("\t    13 - List Events           ");
        System.out.println("\t    0 - Exit                  ");
        System.out.println("\t===============================");
    }


    /**
     * Função responsável por exibir o menu do projeto e gerenciar as opções escolhidas pelo utilizador.
     * @param control .
     */

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
                case 2: // Add Project to Edition
                    try {
                        editionTitle = control.getUserInputString("Enter the name edition: ");
                        projectTitle = control.getUserInputString("Enter the title of the project you want to add in edition selected: ");
                        projectDescription = control.getUserInputString("Enter the description of the project: ");
                        projectTags = control.getUserInputString("Enter the tags of the project: ").split(",");

                        control.getEditions().addProjectToEdition(editionTitle, projectTitle, projectDescription, projectTags);
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
                case 5: // Add Event to Edition
                    try {
                        editionTitle = control.getUserInputString("Enter the name edition: ");
                        Event event = control.getInfoEvent();

                        control.getEditions();
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
                case 8: // Delete Event from Edition // Ver depois
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to delete:" +
                                "event from: ");
                        String eventTitle = control.getUserInputString("Enter the title of the event you want to delete: ");

                        //control.getEditions().getEdition(edition.getName()).getProject(project.removeEvent(eventTitle));
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
                        for (Project list : control.getEditions().getEdition(edition.getName()).getProjects()) {
                            System.out.println(list.toString() + "\n");
                        }
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 11: // List Participants from Project
                    break;
                case 12: //List tasks
                    try {
                        projectTitle = control.getUserInputString("Enter the title of the project you want to list the tasks: ");

                        for (Task list : control.getEditions().getEdition(edition.getName()).getProject(projectTitle).getTasks()) {
                            System.out.println(list.toString() + "\n");
                        }
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has ocurred!: " + e.getMessage());
                    }
                    break;
                case 13: //List events // Rever

                    break;
                case 0:
                    isRunning = false;
                    break;
            }

        } while (isRunning);

    }
}
