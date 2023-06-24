package program;

import ma02_resources.project.Edition;
import project.Edition_imp;

import java.time.LocalDate;

public class EditionMenu implements Display {

    @Override
    public void display() {
        System.out.println("\t===============================");
        System.out.println("\t--------- Edition Menu ------");
        System.out.println("\t    1 - Add Edition          ");
        System.out.println("\t    2 - Delete Edition       ");
        System.out.println("\t    3 - List Editions        ");
        System.out.println("\t    4 - Set Editon as Active ");
        System.out.println("\t    5 - Set Editon as Inactive ");
        System.out.println("\t    6 - Set Editon as Closed ");
        System.out.println("\t    7 - Set Editon as Canceled ");
        System.out.println("\t    8 - List Active Edition");
        System.out.println("\t    9 - List all Inactive Editions ");
        System.out.println("\t    10 - List all Closed Editions ");
        System.out.println("\t    11 - List all Canceled Editions ");
        System.out.println("\t    12 - Editions with projects with " +
                "missing submissions on tasks");
        System.out.println("\t    13 - Number of existing editions");
        System.out.println("\t    14 - Progress from edition");
        System.out.println("\t    15 - Exit                 ");
        System.out.println("\t===============================");
    }

    public static void display(ControlPanel control) {
        Display menuEdition = new EditionMenu();
        boolean isRunning = true;

        String name;
        String projectTemplate;
        LocalDate start;
        LocalDate end;
        Edition edition;


        do {
            int choice = control.displayMenu(menuEdition);
            switch (choice) {
                case 1: // add edition
                    try {
                        name = control.getUserInputString("Edition name: ");
                        projectTemplate = control.getUserInputString("Project template: ");
                        start = control.getUserInputLocalDate("Start date: ");
                        end = control.getUserInputLocalDate("End date: ");

                        control.getEditions().addEdition(new Edition_imp(name, projectTemplate, start, end));
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }
                    break;
                case 2: // delete edition
                    try {
                        name = control.getUserInputString("Name of edition to remove: ");

                        control.getEditions().removeEdition(name);
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Edition not found");
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }
                    break;
                case 3: // list editions
                    try {
                        System.out.println(control.getEditions().toString());

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 4: // set edition as active
                    try {
                        System.out.println("If exist any active edition it will be put as inactive, because only one edition" +
                                "can be active");

                        name = control.getUserInputString("Name of edition to become active: ");

                        control.getEditions().defineAsActive(name);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 5: // set edition as inactive
                    try {
                        name = control.getUserInputString("Name of edition to become inactive: ");

                        control.getEditions().defineAsInactive(name);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 6: // set edition as closed
                    try {
                        name = control.getUserInputString("Name of edition to become closed: ");

                        control.getEditions().defineAsClosed(name);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 7: // set edition as canceled
                    try {
                        name = control.getUserInputString("Name of edition to become canceled: ");

                        control.getEditions().defineAsCanceled(name);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }
                    break;
                case 8: // list active edition
                    try {
                        System.out.println(control.getEditions().getActiveEdition().toString());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 9: // list inactive editions
                    try {
                        System.out.println(control.getEditions().getAllInactiveEditions().toString());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }
                    break;
                case 10: // list closed editions
                    try {
                        System.out.println(control.getEditions().getAllClosedEditions().toString());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 11: // list canceled editions
                    try {
                        System.out.println(control.getEditions().getAllCanceledEditions().toString());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 12:// Editions with projects that have missing submissions
                    try {
                        System.out.println(control.getEditions().getProjectsWithMissingSubmissions().toString());
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Edition not found");
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 13: //List number of editions
                    try {
                        System.out.println("Number of editions: " + control.getEditions().getNumberOfEditions());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }

                    break;
                case 14: // Progress from edition
                    try {
                        name = control.getUserInputString("Name of edition you want details about progress: ");
                        edition = control.getEditions().getEdition(name);

                        System.out.println(control.getEditions().getEditionProgress(edition));
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Edition not found");
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }
                    break;
                case 15:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (isRunning);
    }
}
