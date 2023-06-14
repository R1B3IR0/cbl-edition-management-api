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
    public int compareTo(Submission var1) {
        if (this.getDate().isBefore(var1.getDate())) {
            return -1;
        } else if (this.getDate().equals(var1.getDate())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        String text = "";

        text += "Submission: " + date + "\n"
                + "Student: " + student + "\n"
                + "Text: " + text + "\n";
        return text;
    }
}
