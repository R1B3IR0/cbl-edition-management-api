package program;

public class MainMenu implements Display {
    @Override
    public void display() {
        System.out.println("\t===============================");
        System.out.println("\t--------- Main Menu ------");
        System.out.println("\t    1 - Admin              ");
        System.out.println("\t    2 - Participant        ");
        System.out.println("\t    3 - Exit               ");
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

        do {
            int choice = control.displayMenu(menuStart);

            switch (choice) {
                case 1:
                    AdminMenu.display(control);
                    break;
                case 2:
                    //ParticipantMenu.display(menuManager);
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (isRunning);
    }
}

