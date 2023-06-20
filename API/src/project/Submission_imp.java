package project;

import ma02_resources.project.Submission;
import participants.Student_imp;

import java.time.LocalDateTime;

public class Submission_imp implements Submission {
    private LocalDateTime date;
    private Student_imp student;
    private String text;

    public Submission_imp(LocalDateTime date, Student_imp student, String text) {
        this.date = date;
        this.student = student;
        this.text = text;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public Student_imp getStudent() {
        return student;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int compareTo(Submission submission) {
        if (this.getDate().isBefore(submission.getDate())) {
            return -1;
        } else if (this.getDate().isAfter(submission.getDate())) {
            return 1;
        } else {
            // If the dates are equal, compare by the name of the student
            //return this.getStudent().getName().compareTo(submission.getStudent().getName());
            return 0;

        }
    }

    @Override
    public String toString() {
        String text = "";

        text += "Submission: " + date + "\n"
                + "Student: " + student + "\n"
                + "Text: " + this.text + "\n";
        return text;
    }
}
