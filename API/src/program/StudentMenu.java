package program;

import ma02_resources.participants.Student;
import ma02_resources.project.*;
import participants.Student_imp;
import project.Submission_imp;

/**
 * Classe responsável por exibir o menu na consola.
 */

public class StudentMenu implements Display {
    private Student student;

    private Edition edition;

    private Project project;

    public StudentMenu(Student student, Edition edition, Project project) {
        this.student = student;
        this.edition = edition;
        this.project = project;
    }

    @Override
    public void display() {
        System.out.println("\t==============================================================");
        System.out.println("\t                 Hello " + student.getName() + "");
        System.out.println("\t||                1 - Add Submission                        ||");
        System.out.println("\t||                2 - Get Task                              ||");
        System.out.println("\t||                3 - List all Tasks                        ||");
        System.out.println("\t||                4 - Get progress from Project             ||");
        System.out.println("\t||                5 - Get all Submissions                   ||");
        System.out.println("\t||                0 - Back                                  ||");
        System.out.println("\t==============================================================");
    }

    /**
     * Função responsável por exibir o menu do projeto e gerenciar as opções escolhidas pelo utilizador.
     * @param control .
     */

    public void display(ControlPanel control) {
        boolean isRunning = true;

        String submissionText, taskTitle; // submission text

        do {
            switch (control.displayMenu(this)) {

                case 1: // add Submission
                    // A student can acces all editions, but can only add submissions to the active
                    // one
                    if (edition.getStatus() != Status.ACTIVE) {
                        System.out.println("This edition is not active, you can't add any submissions");
                        break;
                    }

                    try {

                        taskTitle = control
                                .getUserInputString("Insert the title of the task you want to make a submission: ");
                        submissionText = control.getUserInputString("Insert a text in your submission: ");

                        control.getEditions().getEdition(edition.getName()).getProject(project.getName())
                                .getTask(taskTitle)
                                .addSubmission(new Submission_imp((Student_imp) student, submissionText));
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occured: " + e.getMessage());
                    }

                    break;
                case 2: // Get Task
                    try {
                        taskTitle = control
                                .getUserInputString("Insert the title of the task you are looking for: ");

                        System.out.println(control.getEditions().getEdition(edition.getName())
                                .getProject(project.getName()).getTask(taskTitle).toString());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occured: " + e.getMessage());
                    }
                    break;
                case 3: // List all Tasks
                    try {
                        for (Task task : project.getTasks()) {
                            System.out.println(task.toString());
                        }
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occured: " + e.getMessage());
                    }

                    break;
                case 4: // Get progress from Project
                    try {
                        System.out.println(control.getEditions().getProjectProgress(project));
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occured: " + e.getMessage());
                    }

                    break;
                case 5: // get all Submissions
                    try {
                        for (Task task : project.getTasks()) {
                            System.out.println("Task: " + task.getTitle());
                            for (Submission submission : task.getSubmissions()) {
                                System.out.println(submission.toString());
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error has occured: " + e.getMessage());
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
