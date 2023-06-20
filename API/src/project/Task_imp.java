package project;

import ma02_resources.project.Submission;
import ma02_resources.project.Task;

import java.time.LocalDate;


public class Task_imp implements Task {
    private final int MAXIMUM_NUMBER_OF_SUBMISSIONS = 6;
    private LocalDate start;
    private LocalDate end;
    private int duration;
    private String title;
    private String description;
    private Submission_imp[] submissions;
    private int numberOfSubmissions;

    /**
     * Constructor of the class Task_imp
     *
     * @param start       the start date of the task
     * @param end         the end date of the task
     * @param duration    the duration of the task in days
     * @param title       the title of the task
     * @param description the description of the task
     * @param submissions the array of submissions for the task
     */
    public Task_imp(LocalDate start, LocalDate end, int duration, String title, String description, Submission_imp[] submissions) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.title = title;
        this.description = description;
        this.submissions = submissions;
        this.numberOfSubmissions = 0;
    }
    /**
     * Constructor of the class Task_imp is used in addProject in the class Edition_imp
     *
     *
     *
     * @param title       the title of the task
     * @param description the description of the task
     * @param startAt     the start date of the task
     * @param duration    the duration of the task in days
     * @param editionStart the start date of the edition of the task
     */
    public Task_imp(String title, String description, int startAt, int duration, LocalDate editionStart) {
        this.title = title;
        this.description = description;
        this.start = editionStart.plusDays(startAt);
        this.end = start.plusDays(duration);
        this.duration = duration;
        this.submissions = new Submission_imp[MAXIMUM_NUMBER_OF_SUBMISSIONS];
        this.numberOfSubmissions = 0;
    }
    //Miss

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Submission_imp[] getSubmissions() {
        return submissions;
    }

    @Override
    public int getNumberOfSubmissions() {
        return numberOfSubmissions;
    }

    @Override
    public void addSubmission(Submission submission) {
        if (submission == null) {
            throw new NullPointerException("Submission is null");
        }
        /*
        if (numberOfSubmissions >= MAXIMUM_NUMBER_OF_SUBMISSIONS) {
            Submission_imp[] tmp = new Submission_imp[(MAXIMUM_NUMBER_OF_SUBMISSIONS * 2)];

            for (int i = 0; i < numberOfSubmissions; i++) {
                tmp[i] = submissions[i];
            }
            submissions = tmp;
        }
        if (find(submission) != -1) {
            System.out.println("Submission doesn't exist");
        }*/

        submissions[numberOfSubmissions++] = (Submission_imp) submission;
        System.out.println("Submission: " + submission.getText() + " added with success");

    }

    private int find(Submission submission) {
        int pos = -1;
        int i = 0;
        while (i < numberOfSubmissions && pos == -1) {
            if (submission.equals(submissions[i])) {
                pos = i;
            } else {
                i++;
            }
        }
        return pos;
    }

    public void removeSubmission(Submission submission) {
        int position = find(submission);

        if (position != -1) {
            for (int i = position; i < numberOfSubmissions; i++) {
                submissions[i] = submissions[i + 1];
            }
            submissions[--numberOfSubmissions] = null;
        }
    }

    @Override
    public void extendDeadline(int i) {
        if (i < 0) {
            //throw new IllegalArgumentException("i is negative");
            System.out.println("i is negative");
        }
        this.end.plusDays(i);
    }

    /**
     * //a negative integer, zero, or a positive integer as this task is less than, equal to, or greater than the specified task.
     * @param task the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Task task) {
        if (this.getStart().isBefore(task.getStart())) {
            return -1;
        } else if (this.getStart().isAfter(task.getStart())) {
            return 1;
        } else {
            return 0;

        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Task_imp)) {
            return false;
        }

        boolean equal = false;
        Task_imp tmp = (Task_imp) obj;

        if (tmp.getTitle() == this.title) {
            equal = true;
        }

        return equal;
    }

    @Override
    public String toString() {
        String text = "";

        text += "Task: " + title + "\n"
                + "Start: " + start + "\n"
                + "End: " + end + "\n"
                + "Duration: " + duration + "\n"
                + "Description: " + description + "\n"
                + "Submissions: \n"
                + "Number of submissions: " + numberOfSubmissions + "\n";

        for (int i = 0; i < numberOfSubmissions; i++) {
            text += submissions[i].toString() + "\n";
        }

        return text;
    }
}
