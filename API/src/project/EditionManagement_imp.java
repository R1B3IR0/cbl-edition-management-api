package project;

import enumerations.RankType;
import interfaces.Event;
import interfaces.I_EditionManagement;
import ma02_resources.participants.*;
import ma02_resources.project.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import participants.*;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EditionManagement_imp implements I_EditionManagement {

    private Edition_imp[] editions;
    private int numberOfEditions;
    private final int MAXIMUM_NUMBER_OF_EDITIONS;

    public EditionManagement_imp() {
        this.MAXIMUM_NUMBER_OF_EDITIONS = 5;
        this.editions = new Edition_imp[MAXIMUM_NUMBER_OF_EDITIONS];
        this.numberOfEditions = 0;
    }

    /**
     * Method to add a new edition
     *
     * @param edition
     * @return
     */
    @Override
    public void addEdition(Edition edition) {
        if (edition == null) {
            System.out.println("Edition is null");

        }

        if (numberOfEditions >= MAXIMUM_NUMBER_OF_EDITIONS) {
            Edition_imp[] tmp = new Edition_imp[(numberOfEditions * 2)];
            for (int i = 0; i < numberOfEditions; i++) {
                tmp[i] = editions[i];
            }
            editions = tmp;
        }
        if (find(edition.getName()) != -1) {
            System.out.println("Edition doesn't exist");
        }
        editions[numberOfEditions++] = (Edition_imp) edition;
        System.out.println("Edition: " + edition.getName() + " added with success");
    }

    /**
     * Method to remove an edition by name
     *
     * @param name
     * @return
     */
    @Override
    public void removeEdition(String name) {
        int[] positions = new int[MAXIMUM_NUMBER_OF_EDITIONS];
        int found = 0;

        for (int i = 0; i < numberOfEditions; i++) {
            if (editions[i].getName() == name) {
                positions[i] = 1;
                found++;
            }
        }

        if (found > 0) {
            Edition_imp[] tmp = new Edition_imp[MAXIMUM_NUMBER_OF_EDITIONS - found];
            int tmpPosition = 0;

            for (int i = 0; i < positions.length; i++) {
                if (positions[i] == 1) {
                    tmp[tmpPosition] = editions[i];
                    tmpPosition++;
                }
            }
            editions = tmp;
            numberOfEditions--;
        } else {
            System.out.println("Edition doesn't exist");
        }
    }

    /**
     * Find edition by name
     *
     * @param name
     * @return
     */
    private int find(String name) {
        int pos = -1;
        int i = 0;

        while (pos == -1 && i < numberOfEditions) {
            if (name == editions[i].getName()) {
                pos = i;
            } else {
                i++;
            }
        }
        return pos;
    }

    /**
     * Method to define only one edition as active by a specific name
     *
     * @param name
     * @return
     */
    @Override
    public void defineAsActive(String name) {
        for (int i = 0; i < this.numberOfEditions; i++) {
            // Sets the status of other active editions as Inactive
            if (editions[i].getStatus().equals(Status.ACTIVE)) {
                editions[i].setStatus(Status.INACTIVE);
            }

            // Defines the edition with the specifided name as active.
            if (editions[i].getName().equals(name)) {
                editions[i].setStatus(Status.ACTIVE);
            }
        }

    }

    /**
     * Method to define an edition as inactive by a specific name
     *
     * @param name
     * @return
     */
    @Override
    public void defineAsInactive(String name) {
        if (name == null || find(name) == -1) {
            throw new IllegalArgumentException("Edition name is null or doesn't exist this edition in array");
        }
        for (int i = 0; i < this.numberOfEditions; i++) {
            if (editions[i].getName().equals(name)) {
                editions[i].setStatus(Status.INACTIVE);
            }
        }

    }

    /**
     * Method to define an edition as canceled by a specific name
     *
     * @param name
     * @return
     */
    @Override
    public void defineAsCanceled(String name) {
        if (name == null || find(name) == -1) {
            throw new IllegalArgumentException("Edition name is null or doesn't exist this edition in array");
        }
        for (int i = 0; i < this.numberOfEditions; i++) {
            if (editions[i].getName().equals(name)) {
                editions[i].setStatus(Status.CANCELED);
            }
        }
    }

    /**
     * Method to define an edition as closed by a specific name
     *
     * @param name
     * @return
     */
    @Override
    public void defineAsClosed(String name) {
        if (name == null || find(name) == -1) {
            throw new IllegalArgumentException("Edition name is null or doesn't exist this edition in array");
        }
        for (int i = 0; i < this.numberOfEditions; i++) {
            if (editions[i].getName().equals(name)) {
                editions[i].setStatus(Status.CLOSED);
            }
        }
    }

    /**
     * Get all editions
     *
     * @return
     */
    @Override
    public Edition[] getEditions() {
        return editions;
    }

    @Override
    public Edition_imp getEdition(String name) {
        for (int i = 0; i < numberOfEditions; i++) {
            if (editions[i].getName().equals(name)) {
                return editions[i];
            }
        }
        throw new NullPointerException("Edition doesn't exist");
    }

    /**
     * This method returns all editions that have projects with tasks that have missing submissions
     *
     * @return Edition[]
     */
    @Override
    public Edition[] getProjectsWithMissingSubmissions() {
        Edition[] editionsWithMissingSubmissions = new Edition[MAXIMUM_NUMBER_OF_EDITIONS];
        int editionsWithMissingSubmissionsIndex = 0;

        for (int i = 0; i < numberOfEditions; i++) {
            for (int j = 0; j < editions[i].getProjects().length; j++) {
                for (int k = 0; k < editions[i].getProjects()[j].getTasks().length; k++) {
                    if (editions[i].getProjects()[j].getTasks()[k].getNumberOfSubmissions() == 0) {
                        editionsWithMissingSubmissions[editionsWithMissingSubmissionsIndex] = editions[i];
                        editionsWithMissingSubmissionsIndex++;
                        break;
                    }
                }
            }
        }
        return editionsWithMissingSubmissions;
    }


    /**
     * Retrieves the progress of the edition as a percentage.
     * <p>
     * The progress is calculated based on the completion status of the projects
     * <p>
     * in the edition. The completed projects are counted, and the progress is
     * <p>
     * expressed as a percentage of the total number of projects.
     *
     * @param edition the edition for which the progress is calculated
     * @return a string representation of the edition progress in percentage
     */
    @Override
    public String getEditionProgress(Edition edition) {
        int completedProjects = 0;
        int totalProjects = edition.getNumberOfProjects();

        for (Project project : edition.getProjects()) {
            if (project.isCompleted()) {
                completedProjects++;
            }
        }

        double progressPercentage = (double) completedProjects / totalProjects * 100;
        String progress = String.format("%.2f", progressPercentage) + "%";

        return progress;
    }

    /**
     * Returns the number of editions
     *
     * @return
     */
    @Override
    public int getNumberOfEditions() {
        return numberOfEditions;
    }

    @Override
    public void saveEditionsToJsonFile(String filename) throws IOException {
        JSONArray arr = new JSONArray();
        for (Edition f : this.editions) {
            if (f != null) {
                JSONObject obj = new JSONObject();
                obj.put("Name", f.getName());
                obj.put("Project Template", f.getProjectTemplate());
                obj.put("Status", f.getStatus());
                obj.put("number of Projects", f.getNumberOfProjects());
                obj.put("start", f.getStart());
                obj.put("end", f.getEnd());
                obj.put("Project", f.getProjects());

                arr.add(obj);
            }
        }
        FileWriter out = new FileWriter(filename);
        // String ar = arr.toJSONString();
        out.write(arr.toJSONString());
        out.flush();
        out.close();
    }

    @Override
    public void readEditionsFromJsonFile(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader(filename)) {
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

            editions = new Edition_imp[jsonArray.size()];
            numberOfEditions = 0;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                String name = (String) jsonObject.get("Name");
                String projectTemplate = (String) jsonObject.get("Project Template");
                Status status = (Status) jsonObject.get("Status");
                LocalDate start = LocalDate.parse((String) jsonObject.get("start"));
                LocalDate end = LocalDate.parse((String) jsonObject.get("end"));

                // Projects
                JSONArray projectsArray = (JSONArray) jsonObject.get("Project");
                Project[] projects = new Project[projectsArray.size()];
                for (int j = 0; j < projectsArray.size(); j++) {
                    JSONObject projectObject = (JSONObject) projectsArray.get(j);
                    String projectName = (String) projectObject.get("Name");
                    String projectDescription = (String) projectObject.get("Description");
                    int numberOfParticipants = ((Long) projectObject.get("Number Of Participants")).intValue();
                    int numberOfPartners = ((Long) projectObject.get("Number Of Partners")).intValue();
                    int numberOfFacilitators = ((Long) projectObject.get("Number Of Facilitators")).intValue();
                    int numberOfStudents = ((Long) projectObject.get("Number Of Students")).intValue();
                    int numberOfTasks = ((Long) projectObject.get("Number Of Tasks")).intValue();

                    // read Participants inside Project
                    JSONArray participantsArray = (JSONArray) jsonObject.get("Participants");
                    Participant[] participants = new Participant[participantsArray.size()];
                    for (int k = 0; k < participantsArray.size(); k++) {
                        JSONObject participantObject = (JSONObject) participantsArray.get(k);
                        String participantName = (String) participantObject.get("Name");
                        String participantEmail = (String) participantObject.get("Email");
                        Contact participantContact = (Contact) participantObject.get("Contact");
                        Instituition participantInstitution = (Instituition) participantObject.get("Institution");

                        if (participantObject instanceof Partner) {
                            String partnerVat = (String) participantObject.get("Vat");
                            String partnerWebsite = (String) participantObject.get("Website");

                            participants[k] = new Partner_imp(participantName, participantEmail, (Contact_imp) participantContact,
                                    (Instituition_imp) participantInstitution, partnerVat, partnerWebsite);
                        } else if (participantObject instanceof Student) {
                            int studentNumber = ((Long) projectObject.get("Number")).intValue();

                            participants[k] = new Student_imp(participantName, participantEmail, (Contact_imp) participantContact,
                                    (Instituition_imp) participantInstitution, studentNumber);
                        } else if (participantObject instanceof Facilitator) {
                            String facilitatorAreaOfExpertise = (String) participantObject.get("Area of Expertise");

                            participants[k] = new Facilitator_imp(participantName, participantEmail, (Contact_imp) participantContact,
                                    (Instituition_imp) participantInstitution, facilitatorAreaOfExpertise);
                        }

                    }

                    // read tags inside Project
                    JSONArray tagsArray = (JSONArray) jsonObject.get("Tags");
                    String[] tags = new String[tagsArray.size()];
                    for (int l = 0; l < tagsArray.size(); l++) {
                        tags[l] = (String) tagsArray.get(l);
                    }

                    // read Tasks inside Project
                    JSONArray tasksArray = (JSONArray) jsonObject.get("Tasks");
                    Task[] tasks = new Task[tasksArray.size()];
                    for (int m = 0; m < tasksArray.size(); m++) {
                        JSONObject taskObject = (JSONObject) tasksArray.get(m);
                        LocalDate taskStart = LocalDate.parse((String) taskObject.get("Start"));
                        LocalDate taskEnd = LocalDate.parse((String) taskObject.get("End"));
                        int taskDuration = ((Long) taskObject.get("Duration")).intValue();
                        String taskTitle = (String) taskObject.get("Title");
                        String taskDescription = (String) taskObject.get("Description");

                        // read Submissions inside Task
                        JSONArray submissionsArray = (JSONArray) taskObject.get("Submissions");
                        Submission[] submissions = new Submission[submissionsArray.size()];
                        for (int n = 0; n < submissionsArray.size(); n++) {
                            JSONObject submissionObject = (JSONObject) submissionsArray.get(n);
                            int studentNumber = ((Long) submissionObject.get("Number")).intValue();
                            String studentName = (String) submissionObject.get("Name");
                            String studentEmail = (String) submissionObject.get("Email");
                            Contact studentContact = ((Contact) submissionObject.get("Contact"));
                            Instituition studentInstitution = ((Instituition) submissionObject.get("Institution"));

                            Student student = new Student_imp(studentName, studentEmail, (Contact_imp) studentContact,
                                    (Instituition_imp) studentInstitution, studentNumber);
                            LocalDateTime submissionDate = LocalDateTime.parse((String) submissionObject.get("Date"));
                            String submissionText = (String) submissionObject.get("Text");

                            Submission submission = new Submission_imp(submissionDate, (Student_imp) student, submissionText);
                            submissions[n] = submission;
                        }

                        Task task = new Task_imp(taskStart, taskEnd, taskDuration, taskTitle, taskDescription,
                                (Submission_imp[]) submissions);
                        tasks[m] = task;
                    }

                    Project project = new Project_imp(projectName, projectDescription, numberOfParticipants,
                            numberOfPartners, numberOfFacilitators, numberOfStudents, numberOfTasks, (Participant_imp[]) participants, tags,
                            (Task_imp[]) tasks);
                    projects[j] = project;
                }

                Edition edition = new Edition_imp(name, start, end, projectTemplate, status, (Project_imp[]) projects);
                editions[numberOfEditions] = (Edition_imp) edition;
                numberOfEditions++;
            }

        }
    }

    /**
     * This method return the active edition
     *
     * @return
     */
    @Override
    public Edition getActiveEdition() {
        for (Edition edition : editions) {
            if (edition.getStatus() == Status.ACTIVE) {
                return edition;
            }
        }

        throw new NullPointerException("No active editions");
    }

    /**
     * This method return all inactive editions
     *
     * @return
     */
    @Override
    public Edition[] getAllInactiveEditions() {
        Edition[] matchedEditions = new Edition[numberOfEditions];
        int numMatchedEditions = 0;

        if (numberOfEditions > 0) {
            for (Edition edition : editions) {
                if (edition.getStatus() == (Status.INACTIVE)) {
                    if (edition != null) {
                        matchedEditions[numMatchedEditions] = edition;
                        numMatchedEditions++;
                    }
                }
            }

        } else {
            throw new NullPointerException("There are no inactive editions");
        }

        Edition[] onlyEditions = new Edition[numMatchedEditions];
        System.arraycopy(matchedEditions, 0, onlyEditions, 0, numMatchedEditions);

        return onlyEditions;
    }

    /**
     * This method return all closed editions
     *
     * @return
     */
    @Override
    public Edition[] getAllClosedEditions() {
        Edition[] matchedEditions = new Edition[numberOfEditions];
        int numMatchedEditions = 0;

        if (numberOfEditions > 0) {
            for (Edition edition : editions) {
                if (edition.getStatus() == (Status.CLOSED)) {
                    if (edition != null) {
                        matchedEditions[numMatchedEditions] = edition;
                        numMatchedEditions++;
                    }
                }
            }

        } else {
            throw new NullPointerException("There are no closed editions");
        }

        Edition[] onlyEditions = new Edition[numMatchedEditions];
        System.arraycopy(matchedEditions, 0, onlyEditions, 0, numMatchedEditions);

        return onlyEditions;
    }

    /**
     * This method return all canceled editions
     *
     * @return
     */
    @Override
    public Edition[] getAllCanceledEditions() {
        Edition[] matchedEditions = new Edition[numberOfEditions];
        int numMatchedEditions = 0;

        if (numberOfEditions > 0) {
            for (Edition edition : editions) {
                if (edition.getStatus() == Status.CANCELED) {
                    if (edition != null) {
                        matchedEditions[numMatchedEditions] = edition;
                        numMatchedEditions++;
                    }
                }
            }

        } else {
            throw new NullPointerException("There are no canceled editions");
        }

        Edition[] onlyEditions = new Edition[numMatchedEditions];
        System.arraycopy(matchedEditions, 0, onlyEditions, 0, numMatchedEditions);

        return onlyEditions;
    }

    /**
     * This method return a participant by a specific name
     *
     * @param name
     */
    /*
    @Override
    public Participant getParticipant(String name) {
        for (int i = 0; i < numberOfEditions; i++) {
            for (int k = 0; k < editions[i].getNumberOfProjects(); k++) {
                if (editions[i].getProjects()[k].getParticipant(name) != null) {
                    return editions[i].getProjects()[k].getParticipant(name);
                }
            }
        }
        throw new NullPointerException("Participant doesn't exist");
    }
    */

    /**
     * This method return a student by a specific name
     *
     * @param name
     */
    @Override
    public Student getStudent(String name) {
        for (int i = 0; i < numberOfEditions; i++) {
            for (int k = 0; k < editions[i].getNumberOfProjects(); k++) {
                if (editions[i].getProjects()[k].getParticipant(name) instanceof Student) {
                    return (Student) editions[i].getProjects()[k].getParticipant(name);
                }
            }
        }
        throw new NullPointerException("Student doesn't exist");
    }

    /**
     * This method return a facilitator by a specific name
     *
     * @param name
     */
    @Override
    public Facilitator getFacilitator(String name) {
        for (int i = 0; i < numberOfEditions; i++) {
            for (int k = 0; k < editions[i].getNumberOfProjects(); k++) {
                if (editions[i].getProjects()[k].getParticipant(name) instanceof Facilitator) {
                    return (Facilitator) editions[i].getProjects()[k].getParticipant(name);
                }
            }
        }
        throw new NullPointerException("Facilitator doesn't exist");
    }

    /**
     * This method return a partner by a specific name
     *
     * @param name
     */
    @Override
    public Partner getPartner(String name) {
        for (int i = 0; i < numberOfEditions; i++) {
            for (int k = 0; k < editions[i].getNumberOfProjects(); k++) {
                if (editions[i].getProjects()[k].getParticipant(name) instanceof Partner) {
                    return (Partner) editions[i].getProjects()[k].getParticipant(name);
                }
            }
        }
        throw new NullPointerException("Partner doesn't exist");
    }

    /**
     * This method should be possible add submissions to a project of an active edition by a student.
     *
     * @param editionName
     * @param projectName
     * @param studentName
     * @param submission
     */
    @Override
    public void addSubmission(String editionName, String projectName, String taskName, String studentName, Submission submission) {
        if (editionName == null || projectName == null || studentName == null || submission == null) {
            throw new IllegalArgumentException("Edition name, project name, student name or submission is null");
        }
        if (find(editionName) == -1) {
            throw new IllegalArgumentException("Edition name doesn't exist");
        }
        if (getEdition(editionName).getStatus() != Status.ACTIVE) {
            throw new IllegalArgumentException("Edition is not active");
        }
        if (getEdition(editionName).getProject(projectName) == null) {
            throw new IllegalArgumentException("Project doesn't exist");
        }
        if (getEdition(editionName).getProject(projectName).getTask(taskName) == null) {
            throw new IllegalArgumentException("Task doesn't exist");
        }
        if (getEdition(editionName).getProject(projectName).getParticipant(studentName) == null) {
            throw new IllegalArgumentException("Student doesn't exist");
        }
        if (!(getEdition(editionName).getProject(projectName).getParticipant(studentName) instanceof Student)) {
            throw new IllegalArgumentException("Participant is not a student");
        }
        if (getEdition(editionName).getProject(projectName).getTask(taskName).getNumberOfSubmissions() == 6) {
            throw new IllegalArgumentException("Task is full");
        }
        getEdition(editionName).getProject(projectName).getTask(taskName).addSubmission(submission);
    }

    /**
     * Checks if the given student name already exists in the students array.
     *
     * @param students      The array of students' names.
     * @param name          The name to check for repetition.
     * @param studentsCount The current count of students in the array.
     * @return The index of the repeated student, or -1 if not found.
     */
    private int confereRepetitioninStudentsSubmissions(String[] students, String name, int studentsCount) {
        for (int i = 0; i < studentsCount; i++) {
            if (students[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method should be possible get the students with more submissions in a project.
     *
     * @param edition
     * @param project
     */
    @Override
    public String getSudentsWithMoreSubmissions(Edition edition, Project project) {
        String[] students = new String[project.getMaximumNumberOfStudents()];
        int[] studentSubmissions = new int[students.length];
        int studentsCount = 0;
        int indexOfStudent;

        for (Task task : project.getTasks()) {
            for (Submission submission : task.getSubmissions()) {
                indexOfStudent = confereRepetitioninStudentsSubmissions(students,
                        submission.getStudent().getName(), studentsCount);

                if (indexOfStudent != -1) {
                    // If the student is already in the array, increment their submission count
                    studentSubmissions[indexOfStudent]++;
                } else {
                    // Add the student to the array
                    students[studentsCount] = submission.getStudent().getName();
                    studentSubmissions[studentsCount]++;
                    studentsCount++;
                }
            }
        }

        // Sort students and studentSubmissions arrays based on submission count
        for (int i = 0; i < studentsCount - 1; i++) {
            for (int j = i + 1; j < studentsCount; j++) {
                if (studentSubmissions[i] > studentSubmissions[j]) {
                    // Swap students
                    String tempStudent = students[i];
                    students[i] = students[j];
                    students[j] = tempStudent;

                    // Swap submission counts
                    int tempSubmissions = studentSubmissions[i];
                    studentSubmissions[i] = studentSubmissions[j];
                    studentSubmissions[j] = tempSubmissions;
                }
            }
        }

        // Build the result string in ascending order
        String result = "\n";
        for (int i = 0; i < studentsCount; i++) {
            result += students[i] + " --> " + studentSubmissions[i] + " submissions \n";
        }

        return result;
    }

    /**
     * This method should be possible add projects to an active edition or inactive edition.
     *
     * @param editionName
     * @param projectName
     * @return
     */
    @Override
    public String addProjectToEdition(String editionName, String projectName, String description, String[] tags) {
        if (editionName == null || projectName == null) {
            throw new IllegalArgumentException("Edition name or project name is null");
        }
        if (find(editionName) == -1) {
            throw new IllegalArgumentException("Edition name doesn't exist");
        }
        if (getEdition(editionName).getStatus().equals(Status.CLOSED) || getEdition(editionName).getStatus().equals(Status.CANCELED)) {
            throw new IllegalArgumentException("Edition is closed or canceled");
        }
        if (getEdition(editionName).getNumberOfProjects() == getEdition(editionName).getMAXIMUM_NUMBER_OF_PROJECTS()) {
            throw new IllegalArgumentException("Edition is full");
        }
        if (getEdition(editionName).getProject(projectName) != null) {
            throw new IllegalArgumentException("Project name already exists");
        }
        try {
            getEdition(editionName).addProject(projectName, description, tags);
            return "Project added successfully";
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method returns all projects that have tasks with missing submissions in a specific edition and active edition.
     *
     * @param edition
     */
    @Override
    public Project[] getProjectsWithMissingSubmissions(Edition edition) {
        if (edition == null || find(edition.getName()) == -1) {
            throw new IllegalArgumentException("Edition is null");
        } else {
            Project[] projectsWithMissingSubmissions = new Project[edition.getNumberOfProjects()];
            int projectsWithMissingSubmissionsIndex = 0;

            for (int i = 0; i < numberOfEditions; i++) {
                for (int j = 0; j < editions[i].getProjects().length; j++) {
                    for (int k = 0; k < editions[i].getProjects()[j].getTasks().length; k++) {
                        if (editions[i].getProjects()[j].getTasks()[k].getNumberOfSubmissions() == 0) {
                            projectsWithMissingSubmissions[projectsWithMissingSubmissionsIndex] = editions[i].getProjects()[j];
                            projectsWithMissingSubmissionsIndex++;
                            break;
                        }
                    }
                }
            }
            return projectsWithMissingSubmissions;
        }
    }

    /**
     * This method returns all projects of a specific edition
     *
     * @param editionName
     */
    @Override
    public int getNumberOfProjects(String editionName) {
        if (editionName == null || editionName.isEmpty()) {
            throw new IllegalArgumentException("Edition name is null or empty");
        } else {
            return getEdition(editionName).getNumberOfProjects();
        }
    }

    /**
     * This method returns a project by name
     *
     * @param projectName
     */
    @Override
    public Project getProject(String projectName) {
        for (int i = 0; i < numberOfEditions; i++) {
            if (editions[i].getProject(projectName) != null) {
                return editions[i].getProject(projectName);
            }
        }

        throw new NullPointerException("Project not found!");

    }

    /**
     * This method should be possible to return the last three submissions of a task
     *
     * @param task
     */
    @Override
    public Submission[] getLastThreeSubmissions(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task is null");
        } else {
            Submission[] lastThreeSubmissions = new Submission[3];
            int lastThreeSubmissionsIndex = 0;

            for (int i = task.getNumberOfSubmissions() - 1; i >= 0; i--) {
                lastThreeSubmissions[lastThreeSubmissionsIndex] = task.getSubmissions()[i];
                lastThreeSubmissionsIndex++;
                if (lastThreeSubmissionsIndex == 3) {
                    break;
                }
            }
            return lastThreeSubmissions;
        }
    }

    /**
     * This method shoud return tasks from project that have the specified end date
     *
     * @param project
     * @param date
     * @return
     */
    @Override
    public Task[] getTasksByDate(Project project, LocalDate date) {
        if (project == null || date == null) {
            throw new IllegalArgumentException("Project or date is null");
        } else {
            Task[] tasksByDate = new Task[project.getMaximumNumberOfTasks()];
            int counter = 0;

            for (Task task : project.getTasks()) {
                if (task != null && task.getStart().equals(date)) {
                    tasksByDate[counter] = task;
                    counter++;
                }
            }

            Task[] result = new Task[counter];
            System.arraycopy(tasksByDate, 0, result, 0, counter);

            return result;
        }
    }


    @Override
    public String rankOfProjects() {
        for (int i = 0; i < numberOfEditions; i++) {
            if (editions[i].getNumberOfProjects() >= 1 && editions[i].getNumberOfProjects() < 10) {
                return RankType.ONE_STAR.toString();
            } else if (editions[i].getNumberOfProjects() >= 10 && editions[i].getNumberOfProjects() < 20) {
                return RankType.TWO_STARS.toString();
            } else if (editions[i].getNumberOfProjects() >= 20 && editions[i].getNumberOfProjects() < 30) {
                return RankType.THREE_STARS.toString();
            } else if (editions[i].getNumberOfProjects() >= 30 && editions[i].getNumberOfProjects() < 40) {
                return RankType.FOUR_STARS.toString();
            } else if (editions[i].getNumberOfProjects() >= 40) {
                return RankType.FIVE_STARS.toString();
            }
        }
        return null;
    }

    /**
     * Retrieves the progress of the project as a percentage.
     * <p>
     * The method calculates the progress of the project by counting the number of
     * completed tasks
     * <p>
     * and expressing it as a percentage of the total number of tasks in the
     * project. The progress
     * <p>
     * A task is marked as completed, if itthe number of submissions in equal to
     * the lenght of the submisisons array inside the tasks
     *
     * @param project the project for which the progress is calculated
     * @return a string representation of the project progress in percentage
     */
    @Override
    public String getProjectProgress(Project project) {
        int completedTasks = 0;
        int totalTasks = project.getTasks().length;

        // Iterate through each task in the project
        for (Task task : project.getTasks()) {
            // A task is considered completed if the number of submissions is equal to the
            // length of the submissions array inside the task
            if (task.getNumberOfSubmissions() == task.getSubmissions().length) {
                completedTasks++;
            }
        }

        // Calculate the progress percentage by dividing the completed tasks by the
        // total tasks
        double progressPercentage = (double) completedTasks / totalTasks * 100;

        // Format the progress percentage as a string with two decimal places
        String progress = String.format("%.2f", progressPercentage) + "%";

        return "The project is inm " + progress + " progress, with " + completedTasks + " tasks completed.";
    }

    @Override
    public String toString() {
        String text = "";

        for (int i = 0; i < numberOfEditions; i++) {
            text += editions[i].toString();

        }
        return text;
    }
}

