package project;


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
    private final int MAXIMUM_NUMBER_OF_PROJECTS = 3;
    private Event_imp[] events;
    private int numberOfEvents;
    private final int MAXIMUM_NUMBER_OF_EVENTS = 3;


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

    public Edition_imp(String name, LocalDate start, LocalDate end, String projectTemplate) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = Status.INACTIVE;
        this.projects = new Project_imp[MAXIMUM_NUMBER_OF_PROJECTS];
        this.numberOfProjects = 0;
    }
    /**
     * Constructor of the class Edition_imp with events included
     *
     * @param name            the name of the edition
     * @param start           the start date of the edition
     * @param end             the end date of the edition
     * @param projectTemplate the project template of the edition
     * @param status          the status of the edition
     */
    public Edition_imp(String name, LocalDate start, LocalDate end, String projectTemplate, Status status) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = status;
        this.projects = new Project_imp[MAXIMUM_NUMBER_OF_PROJECTS];
        this.numberOfProjects = 0;
        this.events = new Event_imp[MAXIMUM_NUMBER_OF_EVENTS];
        this.numberOfEvents = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public String getProjectTemplate() {
        return projectTemplate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        } else {
            this.status = status;
        }
    }

    public int getMAXIMUM_NUMBER_OF_PROJECTS() {
        return MAXIMUM_NUMBER_OF_PROJECTS;
    }

    /**
     * This method adds a project to the edition. The project is created from a projectTemplate.
     *
     * @param name        name of the project
     * @param description description of the project
     * @param tags        tags of the project
     * @throws IOException    - if the project template is not found.
     * @throws ParseException - if the project template is not valid
     * @theows IllegalArgumentException -  if the project name is null or empty, if the project already exists, if the description is null or empty, if the tags are null or empty.
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
     * This method removes a project from the edition. The project is identified by its name.
     *
     * @param name The name of the project
     * @throws IllegalArgumentException - if project name is null or empty, or project does not exist.
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
     * This method returns a project from the edition. The project is identified by its name.
     *
     * @param name The name of the project
     * @return The method returns a project of the edition.
     * @throws IllegalArgumentException - if project name is null or empty, or project does not exist.
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
     * This method returns all projects of the edition.
     *
     * @return The method returns an array of projects.
     */
    @Override
    public Project[] getProjects() {
        return projects;
    }

    /**
     * This method returns all projects of the edition that have a specific tag.
     *
     * @param tag The tag of the projects
     * @return The method returns an array of projects.
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
     * This method returns all projects of the edition that have a specific participant.
     *
     * @param email
     * @return The method returns an array of projects.
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
     * This method returns the number of projects in the edition.
     *
     * @return
     */
    @Override
    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    /**
     * This method returns the date of the last task ending date in the projects.
     *
     * @return The method returns the date of the last task ending date in the projects.
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
     * This method adds an event to the edition.
     *
     * @return
     */
    @Override
    public void addEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event can't be null");
        } else {
            events[numberOfEvents++] = (Event_imp) event;
        }
    }

    /**
     * This method removes an event from the edition. The event is identified by its name.
     *
     * @param var1 The name of the event
     * @throws IllegalArgumentException - if event name is null or empty, or event does not exist.
     */
    @Override
    public void removeEvent(String var1) {
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
            } else {
                throw new IllegalArgumentException("Event does not exist");
            }
        }
    }
    /**
     * This method returns an event from the edition. The event is identified by its name.
     * @param var1 The name of the event
     * @return The method returns an event of the edition.
     * @throws IllegalArgumentException - if event name is null or empty, or event does not exist.
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
     *  This method returns all events of the edition.
     * @return
     */
    @Override
    public Event[] getEvents() {
        return events;
    }
}
