package project;

import ma02_resources.project.Submission;
import ma02_resources.project.Task;

import java.time.LocalDate;
import java.util.Arrays;


public class Task_imp implements Task {
    private static final int MAX = 20;

    private LocalDate start;
    private LocalDate end;
    private int duration;
    private String title;
    private String description;
    private Submission_imp[] submissions;
    private int numberOfSubmissions = 0;

    public Task_imp(LocalDate start, LocalDate end, int duration, String title, String description, Submission_imp[] submissions, int numberOfSubmissions) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.title = title;
        this.description = description;
        this.submissions = submissions;
        this.numberOfSubmissions = numberOfSubmissions;
    }

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
        return (Submission_imp[]) submissions;
    }

    @Override
    public int getNumberOfSubmissions() {
        return numberOfSubmissions;
    }

    @Override
    public void addSubmission(Submission submission) {
        if(submission == null) {
            //throw new NullPointerException("Submission is null");
            System.out.println("Submission is null");
        }
        if(numberOfSubmissions >= MAX) {
            Submission_imp[] tmp = new Submission_imp[(MAX * 2)];

            for (int i = 0; i < this.submissions.length; i++) {
                tmp[i] = submissions[i];
            }
            submissions = tmp;
        }
    }

    @Override
    public void extendDeadline(int i) {
        if(i < 0) {
            //throw new IllegalArgumentException("i is negative");
            System.out.println("i is negative");
        }
        this.end.plusDays(i);
    }

    @Override
    public int compareTo(Task task) {
        if (this.getStart().isBefore(task.getStart())) {
            return -1;
        } else if (this.getStart().equals(task.getStart())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Task_imp)) {
            return false;
        }

        boolean equal = false;
        Task_imp tmp = (Task_imp) obj;

        if(tmp.getTitle() == this.title) {
            equal = true;
        }

        return equal;
    }
}
