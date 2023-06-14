package project;

import exceptions.IllegalNumberOfParticipantType_imp;
import ma02_resources.participants.Participant;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import participants.Participant_imp;

public class Project_imp implements Project {
    private String name;
    private String description;
    private int numberOfParticipants = 0;
    private int numberOfStudents;
    private int numberOfPartners;
    private int numberOfFacilitators;
    private int numberOfTasks;
    private int maximumNumberOfTasks;
    private long maximumNumberOfParticipants;
    private int maximumNumberOfStudents;
    private int maximumNumberOfPartners;
    private int maximumNumberOfFacilitators;
    private Participant_imp[] participants;
    private Task_imp[] tasks;
    private String[] tags;

    public Project_imp(String name, String description, int numberOfParticipants, int numberOfStudents, int numberOfPartners, int numberOfFacilitators, int numberOfTasks, int maximumNumberOfTasks, long maximumNumberOfParticipants, int maximumNumberOfStudents, int maximumNumberOfPartners, int maximumNumberOfFacilitators, Participant_imp[] participants, Task_imp[] tasks, String[] tags) {
        this.name = name;
        this.description = description;
        this.numberOfParticipants = numberOfParticipants;
        this.numberOfStudents = numberOfStudents;
        this.numberOfPartners = numberOfPartners;
        this.numberOfFacilitators = numberOfFacilitators;
        this.numberOfTasks = numberOfTasks;
        this.maximumNumberOfTasks = maximumNumberOfTasks;
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
        this.maximumNumberOfStudents = maximumNumberOfStudents;
        this.maximumNumberOfPartners = maximumNumberOfPartners;
        this.maximumNumberOfFacilitators = maximumNumberOfFacilitators;
        this.participants = participants;
        this.tasks = tasks;
        this.tags = tags;
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

    @Override
    public void addParticipant(Participant participant) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        if(numberOfParticipants >= maximumNumberOfParticipants) {
            throw new IllegalNumberOfParticipantType_imp("Maximum number of participants reached");
        }
    }

    @Override
    public Participant removeParticipant(String s) {
        return null;
    }

    @Override
    public Participant getParticipant(String s) {
        return null;
    }

    @Override
    public String[] getTags() {
        return new String[0];
    }

    @Override
    public boolean hasTag(String s) {
        return false;
    }

    @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
    }

    @Override
    public Task getTask(String s) {
        return null;
    }

    @Override
    public Task[] getTasks() {
        return new Task[0];
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

}
