package program;

import ma02_resources.participants.Student;

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
     * Displays the start menu and handles user input.*
     *
     * @param control the menu manager
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

