package program;

import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;

/**
 * Classe responsável por exibir o menu na consola.
 */

public class MainMenu implements Display {
    @Override
    public void display() {
        System.out.println("\t===============================");
        System.out.println("\t--------- Main Menu ------");
        System.out.println("\t    1 - Admin              ");
        System.out.println("\t    2 - Student            ");
        System.out.println("\t    0 - Exit               ");
        System.out.println("\t===============================");
    }

    /**
     * Função responsável por exibir o menu principal do sistema e gerenciar as opções escolhidas pelo utilizador.
     * @param control .
     */
    public static void display(ControlPanel control) {
        Display menuStart = new MainMenu();
        boolean isRunning = true;
        String editionName, participantName, projectName;

        do {
            int choice = control.displayMenu(menuStart);

            switch (choice) {
                case 1:
                    AdminMenu.display(control);
                    break;
                case 2:
                    try {
                        participantName = control.getUserInputString("Enter name of Student to login: ");
                        Student student = control.getEditions().getStudent(participantName);

                        editionName = control.getUserInputString("Enter name of Edition: ");
                        Edition edition = control.getEditions().getEdition(editionName);

                        projectName = control.getUserInputString("Enter name of Project: ");
                        Project project = edition.getProject(projectName);

                        StudentMenu menu = new StudentMenu(student, edition, project);
                        menu.display(control);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (isRunning);
    }
}

