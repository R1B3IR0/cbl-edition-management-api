package project;

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


public class Project_imp implements Project {

    private String name;
    private String description;
    private int numberOfParticipants = 0;
    private int numberOfStudents = 0;
    private int numberOfPartners = 0;
    private int numberOfFacilitators = 0;
    private int numberOfTasks = 0;

    private int numberOfTags = 0;

    private int maximumNumberOfTags;
    private int maximumNumberOfTasks;
    private long maximumNumberOfParticipants;
    private int maximumNumberOfStudents;
    private int maximumNumberOfPartners;
    private int maximumNumberOfFacilitators;
    private Participant_imp[] participants;
    private String[] tags;
    private Task_imp[] tasks;

    public Project_imp(String name, String description, int maximumNumberOfTags, int maximumNumberOfTasks,
                       long maximumNumberOfParticipants, int maximumNumberOfStudents, int maximumNumberOfPartners,
                       int maximumNumberOfFacilitators, Participant_imp[] participants, String[] tags, Task_imp[] tasks) {
        this.name = name;
        this.description = description;
        this.maximumNumberOfTags = maximumNumberOfTags;
        this.maximumNumberOfTasks = maximumNumberOfTasks;
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
        this.maximumNumberOfStudents = maximumNumberOfStudents;
        this.maximumNumberOfPartners = maximumNumberOfPartners;
        this.maximumNumberOfFacilitators = maximumNumberOfFacilitators;
        this.participants = participants;
        this.tags = new String[maximumNumberOfTags];
        this.tasks = tasks;
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
        return maximumNumberOfTasks;
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        return maximumNumberOfParticipants;
    }

    @Override
    public int getMaximumNumberOfStudents() {
        return maximumNumberOfStudents;
    }

    @Override
    public int getMaximumNumberOfPartners() {
        return maximumNumberOfPartners;
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        return maximumNumberOfFacilitators;
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
        if (numberOfParticipants == maximumNumberOfParticipants) {
            Participant_imp[] tmp = new Participant_imp[(numberOfParticipants * 2)];
            for (int i = 0; i < numberOfParticipants; i++) {
                tmp[i] = participants[i];
            }
            participants = tmp;
        }
        if (participant instanceof Student_imp) {
            if (numberOfStudents == maximumNumberOfStudents) {
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
            if (numberOfPartners == maximumNumberOfPartners) {
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
            if (numberOfFacilitators == maximumNumberOfFacilitators) {
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

    private int find(String tag) {
        int pos = -1;
        int i = 0;

        while (pos == -1 && i < tags.length) {
            if (tags[i].equals(tag)) {
                pos = i;
            }
            i++;
        }
        return pos;
    }

    public boolean addTag(String tag) {
        if(tag == null){
            return false;
        }

        if(numberOfTags == maximumNumberOfTags){
            return false;
        }

        this.tags[numberOfTags++] = tag;

        return true;
    }

    @Override
    public String[] getTags() {
        if (tags != null) {
            return tags;
        } else {
            throw new NullPointerException("Tag list is null");
        }
    }

    @Override
    public boolean hasTag(String s) {
        if (tags != null) {
            for (int i = 0; i < tags.length; i++) {
                if (tags[i].equals(s)) {
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

        if (numberOfTasks >= maximumNumberOfTasks) {
            Task_imp[] tmp = new Task_imp[(int) (maximumNumberOfTasks * 2)];

            for (int i = 0; i < numberOfTasks; i++) {
                tmp[i] = tasks[i];
            }
            tasks = tmp;
        }

        if (getTask(task.getTitle()) != null) {
            throw new TaskAlreadyInProject("Task is already in the project");
        } else {
            System.out.println("Task is not in the project");
        }

        if (numberOfTasks < maximumNumberOfTasks) {
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

    @Override
    public boolean isCompleted() {
        if (tasks != null) {
            for (int i = 0; i < numberOfTasks; i++) {
                if (tasks[i].getNumberOfSubmissions() > 0) {
                    return true;
                }
            }
        }
        return false;
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
}


