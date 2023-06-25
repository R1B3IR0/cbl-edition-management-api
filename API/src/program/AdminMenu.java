package program;

public class AdminMenu implements Display {

    @Override
    public void display() {
        System.out.println("\t===============================");
        System.out.println("\t--------- Admin Menu ------");
        System.out.println("\t    1 - Edition Management  ");
        System.out.println("\t    2 - Project Management  ");
        System.out.println("\t    3 - Lists               ");
        System.out.println("\t    4 - Exit                ");
        System.out.println("\t===============================");

    }

    public static void display(ControlPanel control) {
        Display menuAdmin = new AdminMenu();
        boolean isRunning = true;

        String editionName;

        do {
            int choice = control.displayMenu(menuAdmin);
            switch (choice) {
                case 1:
                    EditionMenu.display(control);
                    break;
                case 2:
                    try {
                        editionName = control.getUserInputString("Enter the name of the edition: ");

                        ProjectMenu projectMenu = new ProjectMenu(control.getEditions().getEdition(editionName));

                        projectMenu.display(control);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    OtherListsMenu.display(control);
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (isRunning);
    }

}
