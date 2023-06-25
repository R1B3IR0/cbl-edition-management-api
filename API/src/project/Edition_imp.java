package project;


import enumerations.EventType;
import enumerations.RankType;
import interfaces.Event;
import interfaces.Event_CRUD;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;


public class Edition_imp implements Edition, Event_CRUD {
    private String name;
    private LocalDate start;
    private LocalDate end;
    private String projectTemplate;
    private Status status;
    private Project_imp[] projects;
    private int numberOfProjects;
    private final int MAXIMUM_NUMBER_OF_PROJECTS = 50;
    private RankType rankType;

    private Event_imp[] events;

    private EventType type;

    private int numberOfEvents;

    private final int MAXIMUM_NUMBER_OF_EVENTS = 5;

    /**
     * Construtor da classe Edition_imp.
     * @param name O nome da edição.
     * @param start A data de início da edição.
     * @param end A data de término da edição.
     * @param projectTemplate O template do projeto.
     * @param status O status da edição.
     * @param projects Os projetos da edição.
     */

    public Edition_imp(String name, LocalDate start, LocalDate end, String projectTemplate, Status status,
                       Project_imp[] projects) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = status;
        this.projects = projects;
        this.numberOfProjects = projects.length;
    }

    /**
     * Construtor da classe Edition_imp.
     * @param name O nome da edição.
     * @param start A data de início da edição.
     * @param end A data de término da edição.
     * @param projectTemplate O template do projeto.
     * @param projects Os projetos da edição.
     * @param numberOfProjects O número de projetos da edição.
     */

    public Edition_imp(String name, LocalDate start, LocalDate end, String projectTemplate, Project_imp[] projects,
                       int numberOfProjects) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = Status.INACTIVE;
        this.projects = new Project_imp[numberOfProjects];
        this.numberOfProjects = numberOfProjects;
    }

    /**
     * Construtor da classe Edition_imp.
     * @param name O nome da edição.
     * @param start A data de início da edição.
     * @param end A data de término da edição.
     * @param projectTemplate O template do projeto.
     * @param rankType O tipo de classificação da edição.
     */

    public Edition_imp(String name, LocalDate start, LocalDate end, String projectTemplate, RankType rankType) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = Status.INACTIVE;
        this.projects = new Project_imp[MAXIMUM_NUMBER_OF_PROJECTS];
        this.numberOfProjects = 0;
        this.rankType = rankType;
    }

    /**
     * Construtor da classe Edition_imp.
     * @param name O nome da edição.
     * @param projectTemplate O template do projeto.
     * @param start A data de início da edição.
     * @param end A data de término da edição.
     */

    public Edition_imp(String name, String projectTemplate, LocalDate start, LocalDate end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = Status.INACTIVE;
        this.projects = new Project_imp[MAXIMUM_NUMBER_OF_PROJECTS];
        this.numberOfProjects = 0;

    }

    public RankType getRank() {
        return rankType;
    }

    public EventType getType() {
        return type;
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public int getMAXIMUM_NUMBER_OF_EVENTS() {
        return MAXIMUM_NUMBER_OF_EVENTS;
    }


    /**
     * Retorna o nome do objeto.
     * @return O nome do objeto.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retorna a data de início do objeto.
     * @return A data de início do objeto.
     */

    @Override
    public LocalDate getStart() {
        return start;
    }

    /**
     * Retorna o modelo de projeto do objeto.
     * @return O modelo de projeto do objeto.
     */

    @Override
    public String getProjectTemplate() {
        return projectTemplate;
    }

    /**
     * Retorna o status do objeto.
     * @return O status do objeto.
     */

    @Override
    public Status getStatus() {
        return status;
    }

    /**
     * Define o status do objeto.
     * @param status O status a ser definido.
     * @throws IllegalArgumentException se o status fornecido for nulo.
     */

    @Override
    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        } else {
            this.status = status;
        }
    }

    /**
     * Retorna o número máximo de projetos permitidos.
     * @return O número máximo de projetos permitidos.
     */

    public int getMAXIMUM_NUMBER_OF_PROJECTS() {
        return MAXIMUM_NUMBER_OF_PROJECTS;
    }

    /**
     * Adiciona um novo projeto.
     * @param name O nome do projeto.
     * @param description A descrição do projeto.
     * @param tags As tags do projeto.
     * @throws IOException Se ocorrer um erro de leitura ou se os parâmetros forem inválidos.
     * @throws ParseException Se ocorrer um erro ao analisar o modelo do projeto.
     */
    @Override
    public void addProject(String name, String description, String[] tags) throws IOException, ParseException {
        FileReader read = null;
        try {
            if (name == null || name.isEmpty()) {
                throw new IOException("Project name can't be null or empty");
            } else if (description == null || description.isEmpty()) {
                throw new IOException("Project description can't be null or empty");
            } else if (tags == null || tags.length == 0) {
                throw new IOException("Project tags can't be null or empty");
            } else {
                if (numberOfProjects == MAXIMUM_NUMBER_OF_PROJECTS) {
                    Project_imp[] tmp = new Project_imp[(numberOfProjects * 2)];
                    for (int i = 0; i < numberOfProjects; i++) {
                        tmp[i] = projects[i];
                    }
                    projects = tmp;
                }

            }

            JSONParser parser = new JSONParser();
            //Read the JSON file
            read = new FileReader("../libs/project_template.json");
            //Parse the JSON file
            JSONObject jsonObject = (JSONObject) parser.parse(read);

            //Get data from JSON file
            int numberOfFacilitators = ((Long) jsonObject.get("number_of_facilitators")).intValue();
            int numberOfStudents = ((Long) jsonObject.get("number_of_students")).intValue();
            int numberOfPartners = ((Long) jsonObject.get("number_of_partners")).intValue();
            int numberOfParticipants = ((Long) jsonObject.get("number_of_participants")).intValue();

            //Get data of tasks array from JSON file
            JSONArray jsonArrayTask = (JSONArray) jsonObject.get("tasks");

            // Determine the number of tasks
            int numberOfTasks = jsonArrayTask.size();

            Task_imp[] tasks = getTasksFromJSON(jsonObject, jsonArrayTask);

            projects[numberOfProjects++] = new Project_imp(name, description, numberOfParticipants, numberOfPartners,
                    numberOfFacilitators, numberOfStudents, numberOfTasks, tags, tasks);

        } catch (IOException e) {
            throw new IOException("Project template not found", e);
        } catch (org.json.simple.parser.ParseException e) {
            throw new ParseException("Project template not valid", 0);
        } finally {
            if (read != null) {
                read.close();
            }
        }
    }

    /**
     * Obtém as tarefas a partir do objeto JSON.
     * @param jsonObject O objeto JSON contendo os dados das tarefas.
     * @param jsonArrayTask O array JSON contendo as tarefas.
     * @return Um array de tarefas.
     */

    private Task_imp[] getTasksFromJSON(JSONObject jsonObject, JSONArray jsonArrayTask) {
        // Create an array of tasks
        Task_imp[] tasks = new Task_imp[jsonArrayTask.size()];
        for (int i = 0; i < jsonArrayTask.size(); i++) {

            JSONObject taskObject1 = (JSONObject) jsonArrayTask.get(i);

            // Get data from taskObject1
            String title = (String) taskObject1.get("title");
            String description = (String) taskObject1.get("description");
            int StartAt = ((Long) taskObject1.get("start_at")).intValue();
            int duration = ((Long) taskObject1.get("duration")).intValue();

            // Create a task with the data
            Task_imp taskTemplate = new Task_imp(title, description, StartAt, duration, start);

            //Add the task to the array
            tasks[i++] = taskTemplate;

        }
        return tasks;
    }

    /**
     * Remove um projeto com o nome especificado.
     * @param name O nome do projeto a ser removido.
     */

    @Override
    public void removeProject(String name) {
        int[] positions = new int[numberOfProjects];
        int found = 0;

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Project name can't be null or empty");
        } else {
            for (int i = 0; i < numberOfProjects; i++) {
                if (projects[i].getName().equals(name)) {
                    positions[i] = 1;
                    found++;
                }
            }
            // Remove project
            if (found > 0) {
                Project_imp[] tmp = new Project_imp[numberOfProjects - found];
                int tmpPosition = 0;
                // Copy all projects that are not to be removed
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i] == 0) {
                        tmp[tmpPosition] = projects[i];
                        tmpPosition++;
                    }
                }
                projects = tmp;
                numberOfProjects--;
            } else {
                throw new IllegalArgumentException("Project does not exist");
            }
        }
    }

    /**
     * Retorna o projeto com o nome especificado.
     * @param name O nome do projeto a ser retornado.
     * @return O projeto com o nome especificado.
     * @throws IllegalArgumentException Se o nome do projeto for nulo, vazio ou se o projeto não existir.
     */
    @Override
    public Project getProject(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Project name can't be null or empty");
        } else {
            for (int i = 0; i < numberOfProjects; i++) {
                if (projects[i].getName().equals(name)) {
                    return projects[i];
                }
            }
            throw new IllegalArgumentException("Project does not exist");
        }
    }

    /**
     * Retorna um array contendo todos os projetos desta edição.
     * @return Um array contendo todos os projetos desta edição.
     */
    @Override
    public Project[] getProjects() {
        return projects;
    }

    /**
     * Retorna um array contendo todos os projetos desta edição que possuem a tag especificada.
     * @param tag A tag para filtrar os projetos.
     * @return Um array contendo todos os projetos desta edição que possuem a tag especificada.
     */
    @Override
    public Project[] getProjectsByTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("Tag can't be null or empty");
        } else {
            Project_imp[] tmp = new Project_imp[numberOfProjects];
            int tmpPosition = 0;
            for (int i = 0; i < numberOfProjects; i++) {
                for (int j = 0; j < projects[i].getNumberOfTags(); j++) {
                    if (projects[i].getTags()[j].equals(tag)) {
                        tmp[tmpPosition] = projects[i];
                        tmpPosition++;
                    }
                }
            }
            Project_imp[] result = new Project_imp[tmpPosition];
            for (int i = 0; i < tmpPosition; i++) {
                result[i] = tmp[i];
            }
            return result;
        }
    }

    /**
     * Retorna um array contendo todos os projetos desta edição em que o participante com o email especificado está envolvido.
     * @param email O email do participante para filtrar os projetos.
     * @return Um array contendo todos os projetos desta edição em que o participante com o email especificado está envolvido.
     */
    @Override
    public Project[] getProjectsOf(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email can't be null or empty");
        } else {
            Project_imp[] tmp = new Project_imp[numberOfProjects];
            int tmpPosition = 0;
            for (int i = 0; i < numberOfProjects; i++) {
                for (int j = 0; j < projects[i].getNumberOfParticipants(); j++) {
                    if (projects[i].getParticipants()[j].equals(email)) {
                        tmp[tmpPosition] = projects[i];
                        tmpPosition++;
                    }
                }
            }
            Project_imp[] result = new Project_imp[tmpPosition];
            for (int i = 0; i < tmpPosition; i++) {
                result[i] = tmp[i];
            }
            return result;
        }
    }

    /**
     * Retorna o número de projetos nesta edição.
     * @return O número de projetos nesta edição.
     */

    @Override
    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    /**
     * Retorna a data em que termina mais recente entre todas as tarefas dos projetos nesta edição.
     * @return A data em que termina mais recente.
     */
    @Override
    public LocalDate getEnd() {
        LocalDate end = null;
        for (int i = 0; i < numberOfProjects; i++) {
            for (int j = 0; i < projects[i].getNumberOfTasks(); i++) {
                if (projects[i].getTasks()[j].getEnd().isAfter(end)) {
                    end = projects[i].getTasks()[j].getEnd();
                }
            }
        }
        return end;
    }

    /**
     * Retorna uma representação em formato de texto da edição, incluindo seus atributos e os projetos contidos nela.
     * @return Uma string que representa a edição.
     */

    @Override
    public String toString() {
        String text = "";

        text += "Edition: " + name + "\n"
                + "Start: " + start + "\n"
                + "End: " + end + "\n"
                + "ProjectTemplate: " + projectTemplate + "\n"
                + "Status: " + status + "\n"
                + "Number of projects: " + numberOfProjects + "\n"
                + "Rank: " + rankType + "\n";
        for (int i = 0; i < numberOfProjects; i++) {
            text += projects[i].toString();
        }
        text += "\n\n";

        return text;
    }

    /**
     * Adiciona um evento à edição.
     * @param event O evento a ser adicionado.
     * @return Uma mensagem indicando que o evento foi adicionado.
     * @throws IllegalArgumentException Se o evento for nulo.
     */

    @Override
    public String addEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event can't be null");
        } else {
            events[numberOfEvents++] = (Event_imp) event;
            return "Event added";
        }

    }

    /**
     * Remove um evento da edição.
     * @param var1 O nome do evento a ser removido.
     * @return Uma mensagem indicando que o evento foi removido.
     * @throws IllegalArgumentException Se o nome do evento for nulo ou vazio.
     * @throws IllegalArgumentException Se o evento não existir.
     */

    @Override
    public String removeEvent(String var1) {
        if (var1 == null || var1.isEmpty()) {
            throw new IllegalArgumentException("Event name can't be null or empty");
        } else {
            int[] positions = new int[numberOfEvents];
            int found = 0;
            for (int i = 0; i < numberOfEvents; i++) {
                if (events[i].getName().equals(var1)) {
                    positions[i] = 1;
                    found++;
                }
            }
            // Remove event
            if (found > 0) {
                Event_imp[] tmp = new Event_imp[numberOfEvents - found];
                int tmpPosition = 0;
                // Copy all events that are not to be removed
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i] == 0) {
                        tmp[tmpPosition] = events[i];
                        tmpPosition++;
                    }
                }
                events = tmp;
                numberOfEvents--;
                return "Event removed";
            } else {
                throw new IllegalArgumentException("Event does not exist");
            }
        }
    }

    /**
     * Obtém um evento com base no nome fornecido.
     * @param var1 O nome do evento a ser obtido.
     * @return O evento correspondente.
     * @throws IllegalArgumentException Se o nome do evento for nulo ou vazio.
     * @throws IllegalArgumentException Se o evento não existir.
     */

    @Override
    public Event getEvent(String var1) {
        if (var1 == null || var1.isEmpty()) {
            throw new IllegalArgumentException("Event name can't be null or empty");
        } else {
            for (int i = 0; i < numberOfEvents; i++) {
                if (events[i].getName().equals(var1)) {
                    return events[i];
                }
            }
            throw new IllegalArgumentException("Event does not exist");
        }
    }

    /**
     * Obtém todos os eventos.
     * @return Um array contendo todos os eventos.
     */

    @Override
    public Event[] getEvents() {
        return events;
    }
}
