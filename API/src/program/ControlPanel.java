package program;

import interfaces.Event;
import interfaces.I_Edition;
import interfaces.I_EditionManagement;
import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.InstituitionType;
import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.Task;
import participants.Contact_imp;
import participants.Instituition_imp;
import participants.Student_imp;
import project.EditionManagement_imp;
import project.Edition_imp;
import project.Event_imp;
import project.Task_imp;

import java.time.LocalDate;
import java.util.Scanner;

public class ControlPanel {

    private Scanner scanner;

    private I_EditionManagement editions;





    public ControlPanel() {
        scanner = new Scanner(System.in);
        editions = new EditionManagement_imp();
    }

    public int displayMenu(Display menu) {
        int choice;
        menu.display();
        choice = getUserChoice();
        return choice;
    }

    public String getUserInputString(String msg) {
        System.out.print(msg);

        String input = scanner.nextLine();

        return input;
    }

    public int getUserInputInt(String msg, int max) {
        int choice = -1;
        while (choice < 0 || choice >= max) {
            System.out.print(msg);
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            if (choice < 0 || choice >= max) {
                System.out.println("Invalid choice.");
            }
        }
        return choice;
    }

    public LocalDate getUserInputLocalDate(String msg) {
        System.out.print(msg + " in the format (YYYY-MM-DD): ");

        String input = scanner.nextLine();

        LocalDate date = LocalDate.parse(input);

        return date;
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");

        int choice = -1;

        try {
            choice = scanner.nextInt();

        } catch (Exception e) {
            System.out.println("Invalid Option");
        } finally {
            // clear the buffer
            scanner.nextLine();
        }

        return choice;
    }

    public I_Edition getEditions() {
        return editions;
    }

    public Contact getInfoContact() {
        String street = getUserInputString("Name of street: ");
        String city = getUserInputString("Name of City: ");
        String state = getUserInputString("Name of State: ");
        String zipCode = getUserInputString("Name of Zipcode: ");
        String country = getUserInputString("Name of Country: ");
        String phone = getUserInputString("Name of Phone: ");


        return new Contact_imp(street, city, state, zipCode, country, phone);
    }

    public InstituitionType getInfoInstituitionType() {
        System.out.println("Instituition Types:");

        for (InstituitionType instituitionType : InstituitionType.values()) {
            System.out.println(instituitionType.ordinal() + " - " + instituitionType.name());
        }

        int choice = getUserInputInt("Enter the instituition type: ", InstituitionType.values().length);
        return InstituitionType.values()[choice];
    }

    public Instituition getInfoInstituition() {
        String name = getUserInputString("Enter the instituition name: ");
        String email = getUserInputString("Enter the instituition email: ");
        InstituitionType instituitionType = getInfoInstituitionType();
        Contact contact = getInfoContact();
        String website = getUserInputString("Enter the instituition website: ");
        String description = getUserInputString("Enter the instituition description: ");

        return new Instituition_imp(name, email, instituitionType, (Contact_imp) contact, website, description);
    }


    public Student getInfoStudent() {
        int number = getUserInputInt("Enter the student number: ", Integer.MAX_VALUE);
        String name = getUserInputString("Enter the student name: ");
        String email = getUserInputString("Enter the student email: ");
        System.out.println("Info about Contact");
        Contact contact = getInfoContact();
        System.out.println("Info about Instituition");
        Instituition instituition = getInfoInstituition();

        return new Student_imp(name, email, (Contact_imp) contact, (Instituition_imp) instituition, number);
    }

    public Task getInfoTask() {
        String title = getUserInputString("Enter the task title: ");
        String description = getUserInputString("Enter the task description: ");
        int start = getUserInputInt("Enter the task start date (format YYYYMMDD): ", Integer.MAX_VALUE);
        int duration = getUserInputInt("Enter the task duration (days): ", Integer.MAX_VALUE);
        int maxSubmissions = getUserInputInt("Enter the task max number of submissions: ", Integer.MAX_VALUE);

        return new Task_imp(title, description, start, duration, maxSubmissions);
    }

    public Event getInfoEvent() {
        String name = getUserInputString("Enter the event name: ");
        String start = String.valueOf(getUserInputLocalDate("Enter the event start date: "));
        String end = String.valueOf(getUserInputLocalDate("Enter the event end date: "));

        return new Event_imp(name, start, end);
    }


}
