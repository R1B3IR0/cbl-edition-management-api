package project;

import enumerations.EventType;
import interfaces.Event;
import interfaces.Event_CRUD;
import ma02_resources.participants.Participant;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import participants.Facilitator_imp;
import participants.Participant_imp;
import participants.Partner_imp;
import participants.Student_imp;

import java.util.Arrays;


public class Project_imp implements Project, Event_CRUD {

    private String name;
    private String description;
    private int numberOfParticipants;
    private int numberOfStudents;
    private int numberOfPartners;
    private int numberOfFacilitators;
    private int numberOfTasks;
    private final int MAXIMUM_NUMBER_OF_TASKS = 10;
    private final long MAXIMUM_NUMBER_OF_PARTICIPANTS = 15; // 5 students, 5 partners, 5 facilitators
    private final int MAXIMUM_NUMBER_OF_STUDENTS = 5;
    private final int MAXIMUM_NUMBER_OF_PARTNERS = 5;
    private final int MAXIMUM_NUMBER_OF_FACILITATORS = 5;
    private Participant_imp[] participants;
    private String[] tags;
    private Task_imp[] tasks;
    private boolean hasTags;
    private Event_imp[] events;
    private EventType type;
    private int numberOfEvents;

    private final int MAXIMUM_NUMBER_OF_EVENTS = 5;

    /**
     * Constructor of the class Project_imp
     * Used to create a project form the given parameters or other files
     *
     * @param name                 the name of the project
     * @param description          the description of the project
     * @param numberOfParticipants the number of participants in the project
     * @param numberOfStudents     the number of students in the project
     * @param numberOfPartners     the number of partners in the project
     * @param numberOfFacilitators the number of facilitators in the project
     * @param numberOfTasks        the number of tasks in the project
     * @param participants         the array of participants in the project
     * @param tags                 the array of tags associated with the project
     * @param tasks                the array of tasks in the project
     */
    public Project_imp(String name, String description, int numberOfParticipants, int numberOfStudents,
                       int numberOfPartners, int numberOfFacilitators, int numberOfTasks, Participant_imp[] participants,
                       String[] tags, Task_imp[] tasks) {
        this.name = name;
        this.description = description;
        // check if the number of participants does not exceed the maximum number of participants
        this.numberOfParticipants = (int) Math.min(numberOfParticipants, MAXIMUM_NUMBER_OF_PARTICIPANTS);
        this.numberOfStudents = Math.min(numberOfStudents, MAXIMUM_NUMBER_OF_STUDENTS);
        this.numberOfPartners = Math.min(numberOfPartners, MAXIMUM_NUMBER_OF_PARTNERS);
        this.numberOfFacilitators = Math.min(numberOfFacilitators, MAXIMUM_NUMBER_OF_FACILITATORS);
        this.participants = participants;
        this.tags = tags;
        this.tasks = tasks;
        this.hasTags = (tags == null) ? false : true; // if tags is null then hasTags is false othwerwise true if have tags associeated with the project
    }

    /**
     * Constructor of the class Project_imp
     *
     * @param name        the name of the project
     * @param description the description of the project
     * @param tags        the array of tags associated with the project
     */
    public Project_imp(String name, String description, String[] tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.numberOfTasks = 0;
        this.numberOfParticipants = 0;
        this.numberOfStudents = 0;
        this.numberOfPartners = 0;
        this.numberOfFacilitators = 0;
        this.hasTags = (tags == null) ? false : true; // if tags is null then hasTags is false othwerwise true if have tags associeated with the project
    }

    /**
     * This constructor is used in the {@code addProject} method in the class {@code Edition_imp}
     *
     * @param name
     * @param description
     * @param numberOfParticipants
     * @param numberOfPartners
     * @param numberOfFacilitators
     * @param numberOfStudents
     * @param numberOfTasks
     * @param tags
     * @param tasks
     */
    public Project_imp(String name, String description, int numberOfParticipants, int numberOfPartners,
                       int numberOfFacilitators, int numberOfStudents, int numberOfTasks ,String[] tags, Task_imp[] tasks) {
        this.name = name;
        this.description = description;
        this.numberOfParticipants = (int) Math.min(numberOfParticipants, MAXIMUM_NUMBER_OF_PARTICIPANTS);
        this.numberOfStudents = Math.min(numberOfStudents, MAXIMUM_NUMBER_OF_STUDENTS);
        this.numberOfPartners = Math.min(numberOfPartners, MAXIMUM_NUMBER_OF_PARTNERS);
        this.numberOfFacilitators = Math.min(numberOfFacilitators, MAXIMUM_NUMBER_OF_FACILITATORS);
        this.numberOfTasks = Math.min(numberOfTasks, MAXIMUM_NUMBER_OF_TASKS);
        this.tags = tags;
        this.tasks = tasks;
        this.participants = new Participant_imp[numberOfParticipants];
        this.hasTags = (tags == null) ? false : true;
    }

    public Project_imp(String name, String description, int numberOfStudents, int numberOfPartners,
                       int numberOfFacilitators, Participant_imp[] participants, String[] tags, Task_imp[] tasks, Event_imp[] events, EventType type,
                       int numberOfEvents) {
        this.name = name;
        this.description = description;
        this.numberOfStudents = numberOfStudents;
        this.numberOfPartners = numberOfPartners;
        this.numberOfFacilitators = numberOfFacilitators;
        this.participants = participants;
        this.tags = tags;
        this.tasks = tasks;
        this.numberOfParticipants = participants.length;
        this.numberOfTasks = tasks.length;
        this.hasTags = (tags == null) ? false : true;
        this.events = events;
        this.type = type;
        this.numberOfEvents = numberOfEvents;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    @Override
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    @Override
    public int getNumberOfPartners() {
        return numberOfPartners;
    }

    @Override
    public int getNumberOfFacilitators() {
        return numberOfFacilitators;
    }

    @Override
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    @Override
    public int getMaximumNumberOfTasks() {
        return MAXIMUM_NUMBER_OF_TASKS;
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        return MAXIMUM_NUMBER_OF_PARTICIPANTS;
    }

    @Override
    public int getMaximumNumberOfStudents() {
        return MAXIMUM_NUMBER_OF_STUDENTS;
    }

    @Override
    public int getMaximumNumberOfPartners() {
        return MAXIMUM_NUMBER_OF_PARTNERS;
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        return MAXIMUM_NUMBER_OF_FACILITATORS;
    }

    /***
     * This method adds a participant to the project  and if array is full it doubles the size of the array
     * @param participant
     * @throws IllegalNumberOfParticipantType
     * @throws ParticipantAlreadyInProject
     */
    @Override
    public void addParticipant(Participant participant) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        if (participant == null) {
            throw new NullPointerException("Participant is null");
        }
        if (numberOfParticipants == MAXIMUM_NUMBER_OF_PARTICIPANTS) {
            throw new IllegalNumberOfParticipantType("Maximum number of participants reached");
        }
        if (participant instanceof Student_imp) {
            if (numberOfStudents == MAXIMUM_NUMBER_OF_STUDENTS) {
                throw new IllegalNumberOfParticipantType("Maximum number of students reached");
            }
            for (int i = 0; i < numberOfParticipants; i++) {
                if (participants[i].getEmail().equals(participant.getEmail())) {
                    throw new ParticipantAlreadyInProject("Participant already in project");
                }
            }
            participants[numberOfParticipants] = (Participant_imp) participant;
            numberOfParticipants++;
            numberOfStudents++;
        } else if (participant instanceof Partner_imp) {
            if (numberOfPartners == MAXIMUM_NUMBER_OF_PARTNERS) {
                throw new IllegalNumberOfParticipantType("Maximum number of partners reached");
            }
            for (int i = 0; i < numberOfParticipants; i++) {
                if (participants[i].getEmail().equals(participant.getEmail())) {
                    throw new ParticipantAlreadyInProject("Participant already in project");
                }
            }
            participants[numberOfParticipants] = (Participant_imp) participant;
            numberOfParticipants++;
            numberOfPartners++;
        } else if (participant instanceof Facilitator_imp) {
            if (numberOfFacilitators == MAXIMUM_NUMBER_OF_FACILITATORS) {
                throw new IllegalNumberOfParticipantType("Maximum number of facilitators reached");
            }
            for (int i = 0; i < numberOfParticipants; i++) {
                if (participants[i].getEmail().equals(participant.getEmail())) {
                    throw new ParticipantAlreadyInProject("Participant already in project");
                }
            }
            participants[numberOfParticipants] = (Participant_imp) participant;
            numberOfParticipants++;
            numberOfFacilitators++;
        } else {
            throw new IllegalNumberOfParticipantType("Participant type not recognized");
        }
    }

    @Override
    public Participant removeParticipant(String s) {
        int[] positions = new int[numberOfParticipants];
        int found = 0;

        // Find the participant
        for (int i = 0; i < numberOfParticipants; i++) {
            if (participants[i].getEmail().equals(s)) {
                positions[i] = 1;
                found++;
            }
        }
        // Remove the participant
        if (found > 0) {
            Participant_imp[] tmp = new Participant_imp[numberOfParticipants - found];
            int tmpPosition = 0;
            // Copy all participants that are not to be removed
            for (int i = 0; i < positions.length; i++) {
                if (positions[i] == 0) {
                    tmp[tmpPosition] = participants[i];
                    tmpPosition++;
                }
            }
            participants = tmp;

            // Update the number of participants
            if(participants[numberOfParticipants] instanceof Student_imp){
                numberOfStudents--;
            } else if(participants[numberOfParticipants] instanceof Partner_imp){
                numberOfPartners--;
            } else if(participants[numberOfParticipants] instanceof Facilitator_imp){
                numberOfFacilitators--;
            }

            numberOfParticipants--;
            return participants[numberOfParticipants];
        } else {
            throw new IllegalArgumentException("Participant not found");
        }
    }

    @Override
    public Participant getParticipant(String s) {
        if (participants == null) {
            throw new NullPointerException("Participant list is null");
        }

        for (int i = 0; i < numberOfParticipants; i++) {
            if (participants[i].getEmail().equals(s)) {
                return participants[i];
            }
        }
        throw new IllegalArgumentException("Participant not found");
    }

    public Participant_imp[] getParticipants() {
        return participants;
    }
    @Override
    public String[] getTags() {
        if (hasTags != false) {
            return tags;
        } else {
            throw new NullPointerException("Tag list is null");
        }
    }

    @Override
    public boolean hasTag(String s) {
        if (tags != null) {
            for (int i = 0; i < tags.length; i++) {
                if (tags[i] == s) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * This method adds a task to the project and if array is full it doubles the size of the array
     * @param task
     * @throws IllegalNumberOfTasks
     * @throws TaskAlreadyInProject
     */
    @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
        if (task == null) {
            throw new NullPointerException("Task is null");
        }

        /*
        // Check if array is full and double the size of the array
        if (numberOfTasks >= MAXIMUM_NUMBER_OF_TASKS) {
            Task_imp[] tmp = new Task_imp[(int) (MAXIMUM_NUMBER_OF_TASKS * 2)];

            for (int i = 0; i < numberOfTasks; i++) {
                tmp[i] = tasks[i];
            }
            tasks = tmp;
        }
        */

        // Check if task is already in the project
        if (getTask(task.getTitle()) != null) {
            throw new TaskAlreadyInProject("Task is already in the project");
        } else {
            System.out.println("Task is not in the project");
        }
        // Add task to project
        if (numberOfTasks < MAXIMUM_NUMBER_OF_TASKS) {
            tasks[numberOfTasks++] = (Task_imp) task;
            System.out.println("Task added");
        } else {
            throw new IllegalNumberOfTasks("The maximum number of tasks has been reached");
        }
    }

    @Override
    public Task getTask(String task) {
        if (tasks == null) {
            throw new NullPointerException("Task list is null");
        }

        for (int i = 0; i < numberOfTasks; i++) {
            if (tasks[i].getTitle().equals(task)) {
                return tasks[i];
            }
        }
        throw new IllegalArgumentException("Task not found");
    }

    @Override
    public Task[] getTasks() {
        return tasks;
    }

    /***
     * The method checks if all tasks have at least one submission.
     * If the number of tasks inserted is not equal to the number specified upon creation it must return false.
     * @return It returns true if all tasks have at least one submission, and false otherwise
     */
    @Override
    public boolean isCompleted() {
        if (numberOfTasks != MAXIMUM_NUMBER_OF_TASKS) {
            return false;
        }

        for (int i = 0; i < numberOfTasks; i++) {
            if (tasks[i].getNumberOfSubmissions() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method returns the number of array tags
     * @return
     */
    public int getNumberOfTags() {
        int numberOfTags = 0;
        for (int i = 0; i < tags.length; i++) {
            if (tags[i] != null) {
                numberOfTags++;
            }
        }
        return numberOfTags;
    }

    public EventType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }
        /*
        if (obj instanceof Project_imp) {
            return false;
        }
        */
        boolean equal = false;
        Project_imp tmp = (Project_imp) obj;

        if (tmp.getName().equals(name)) {
            return true;
        }

        return equal;
    }

    @Override
    public String toString() {
        String text = "";

        text += "Project name: " + name + "\n"
                + "Description: " + description + "\n"
                + "Number of participants: " + numberOfParticipants + "\n"
                + "Number of students: " + numberOfStudents + "\n"
                + "Number of partners: " + numberOfPartners + "\n"
                + "Number of facilitators: " + numberOfFacilitators + "\n"
                + "Number of tasks: " + numberOfTasks + "\n"
                + "Maximum number of tasks: " + MAXIMUM_NUMBER_OF_TASKS + "\n"
                + "Maximum number of participants: " + MAXIMUM_NUMBER_OF_PARTICIPANTS + "\n"
                + "Maximum number of students: " + MAXIMUM_NUMBER_OF_STUDENTS + "\n"
                + "Maximum number of partners: " + MAXIMUM_NUMBER_OF_PARTNERS + "\n"
                + "Maximum number of facilitators: " + MAXIMUM_NUMBER_OF_FACILITATORS + "\n"
                + "Participants: " + Arrays.toString(participants) + "\n"
                + "Tags: " + Arrays.toString(tags) + "\n"
                + "Tasks: " + Arrays.toString(tasks) + "\n";

        return text;
    }

    /**
     * This method adds an event to the edition.
     *
     * @return
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


