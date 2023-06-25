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

    /**
     * Construtor da classe ControlPanel.
     * Inicializa o objeto Scanner para leitura de entrada do utilizador
     * e o objeto editions para gerenciamento das edições.
     */

    public ControlPanel() {
        scanner = new Scanner(System.in);
        editions = new EditionManagement_imp();
    }

    /**
     * Mostra um menu para obter a escolha do utilizador.
     *
     * @param menu O objeto de menu a ser exibido.
     * @return A escolha do utilizador.
     */

    public int displayMenu(Display menu) {
        int choice;
        menu.display();
        choice = getUserChoice();
        return choice;
    }

    /**
     * Obtém uma entrada de string do utilizador.
     *
     * @param msg A mensagem a ser exibida para solicitar a entrada do utilizador.
     * @return A string inserida pelo utilizador.
     */

    public String getUserInputString(String msg) {
        System.out.print(msg);

        String input = scanner.nextLine();

        return input;
    }

    /**
     * Obtém uma entrada numérica do utilizador dentro de um intervalo específico.
     *
     * @param msg A mensagem a ser exibida para solicitar a entrada do utilizador.
     * @param max O valor máximo permitido para a entrada.
     * @return O número inserido pelo utilizador.
     */

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

    /**
     * Obtém uma data inserida pelo utilizador no formato "YYYY-MM-DD".
     *
     * @param msg A mensagem a ser exibida para solicitar a entrada do utilizador.
     * @return A data inserida pelo utilizador no formato LocalDate.
     * @throws DateTimeParseException Se a data inserida não estiver no formato válido.
     */

    public LocalDate getUserInputLocalDate(String msg) {
        System.out.print(msg + " in the format (YYYY-MM-DD): ");

        String input = scanner.nextLine();

        LocalDate date = LocalDate.parse(input);

        return date;
    }

    /**
     * Obtém a escolha do utilizador a partir da entrada da consola.
     *
     * @return A escolha do utlizador como um número inteiro.
     * @throws InputMismatchException Se a entrada do utilizador não for um número inteiro válido.
     */

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

    /**
     * Obtém a instância de edições.
     *
     * @return A instância de edições.
     */

    public I_Edition getEditions() {
        return editions;
    }

    /**
     * Obtém as informações de contato inseridas pelo utilizador.
     *
     * @return Uma instância de contato com as informações inseridas.
     */

    public Contact getInfoContact() {
        String street = getUserInputString("Name of street: ");
        String city = getUserInputString("Name of City: ");
        String state = getUserInputString("Name of State: ");
        String zipCode = getUserInputString("Name of Zipcode: ");
        String country = getUserInputString("Name of Country: ");
        String phone = getUserInputString("Name of Phone: ");


        return new Contact_imp(street, city, state, zipCode, country, phone);
    }

    /**
     * Obtém o tipo de instituição inserido pelo utilizador.
     *
     * @return o tipo de instituição selecionado pelo utilizador.
     */

    public InstituitionType getInfoInstituitionType() {
        System.out.println("Instituition Types:");

        for (InstituitionType instituitionType : InstituitionType.values()) {
            System.out.println(instituitionType.ordinal() + " - " + instituitionType.name());
        }

        int choice = getUserInputInt("Enter the instituition type: ", InstituitionType.values().length);
        return InstituitionType.values()[choice];
    }

    /**
     * Obtém as informações da instituição inseridas pelo utilizador.
     *
     * @return A instância de Instituition contendo as informações inseridas pelo utilizador.
     */

    public Instituition getInfoInstituition() {
        String name = getUserInputString("Enter the instituition name: ");
        String email = getUserInputString("Enter the instituition email: ");
        InstituitionType instituitionType = getInfoInstituitionType();
        Contact contact = getInfoContact();
        String website = getUserInputString("Enter the instituition website: ");
        String description = getUserInputString("Enter the instituition description: ");

        return new Instituition_imp(name, email, instituitionType, (Contact_imp) contact, website, description);
    }


    /**
     * Obtém as informações do estudante inseridas pelo utilizador.
     *
     * @return A instância de Student contendo as informações inseridas pelo utilizador.
     */

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

    /**
     * Obtém as informações da tarefa inseridas pelo utilizador.
     *
     * @return A instância de Task contendo as informações inseridas pelo utilizador.
     */

    public Task getInfoTask() {
        String title = getUserInputString("Enter the task title: ");
        String description = getUserInputString("Enter the task description: ");
        int start = getUserInputInt("Enter the task start date (format YYYYMMDD): ", Integer.MAX_VALUE);
        int duration = getUserInputInt("Enter the task duration (days): ", Integer.MAX_VALUE);
        int maxSubmissions = getUserInputInt("Enter the task max number of submissions: ", Integer.MAX_VALUE);

        return new Task_imp(title, description, start, duration, maxSubmissions);
    }


    /**
     * Obtém as informações do evento inseridas pelo utilizador.
     *
     * @return A instância de Event contendo as informações inseridas pelo utilizador.
     */
    public Event getInfoEvent() {
        String name = getUserInputString("Enter the event name: ");
        String start = String.valueOf(getUserInputLocalDate("Enter the event start date: "));
        String end = String.valueOf(getUserInputLocalDate("Enter the event end date: "));

        return new Event_imp(name, start, end);
    }


}
