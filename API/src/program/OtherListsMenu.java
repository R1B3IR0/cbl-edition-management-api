package program;

import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Task;

import java.time.LocalDate;

public class OtherListsMenu implements Display{
    @Override
    public void display() {
        System.out.println("\t================================================");
        System.out.println("\t||-------------- Distinct Lists --------------||");
        System.out.println("\t||      1 - Get Sudents With More Submissions ||");
        System.out.println("\t||      2 - Get Last Three Submissions        ||");
        System.out.println("\t||      3 - Get Tasks By Date                 ||");
        System.out.println("\t||      0 - Back                              ||");
        System.out.println("\t================================================");
    }

    public  static void display(ControlPanel control) {
        Display menuOtherLists = new OtherListsMenu();
        boolean isRunning = true;


        String editionName, projectName, taskName;
        Edition edition;
        Project project;
        Task task;
        LocalDate date;

        do {
            int choice = control.displayMenu(menuOtherLists);
            switch (choice) {
                case 1: // Get Sudents With More Submissions
                    try {
                        editionName = control.getUserInputString("Enter the name of the edition: ");

                        edition = control.getEditions().getEdition(editionName);

                        projectName = control.getUserInputString("Enter the name of the project: ");

                        project = edition.getProject(projectName);

                        System.out.println(control.getEditions().getSudentsWithMoreSubmissions(edition, project));
                    } catch (NullPointerException e) {
                        System.out.println("Name not found: " + e.getMessage());
                    }catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }
                    break;
                case 2: // Get last Three submissions of a task
                    try {
                        editionName = control.getUserInputString("Enter the name of the edition: ");

                        edition = control.getEditions().getEdition(editionName);

                        projectName = control.getUserInputString("Enter the name of the project: ");

                        taskName = control.getUserInputString("Enter the name of the task: ");

                        task = control.getEditions().getEdition(editionName).getProject(projectName).getTask(taskName);

                        System.out.println(control.getEditions().getLastThreeSubmissions(task));
                    } catch (NullPointerException e) {
                        System.out.println("Name not found: " + e.getMessage());
                    }catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
                    }
                    break;
                case 3: // Get Tasks By Date
                    try {
                        projectName = control.getUserInputString("Enter the name of the project: ");

                        project = control.getEditions().getProject(projectName);

                        date = control.getUserInputLocalDate("Enter the date: ");

                        System.out.println("\n---------------------");
                        for(Task t : control.getEditions().getTasksByDate(project, date))
                            System.out.println("\t" + t.toString() + "\n");
                    } catch (NullPointerException e) {
                        System.out.println("Name not found: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occurred: " + e.getMessage());
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
