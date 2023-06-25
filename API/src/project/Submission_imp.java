package project;

import ma02_resources.project.Submission;
import participants.Student_imp;

import java.time.LocalDateTime;

public class Submission_imp implements Submission {
    private LocalDateTime date;
    private Student_imp student;
    private String text;

    /**
     * Retorna uma representação em formato de string da submissão.
     * @return A representação em formato de string da submissão.
     */

    public Submission_imp(LocalDateTime date, Student_imp student, String text) {
        this.date = date;
        this.student = student;
        this.text = text;
    }

    /**
     * Retorna uma representação em formato de string da submissão.
     * @return A representação em formato de string da submissão.
     */

    public Submission_imp(Student_imp student, String text) {
        this.date = LocalDateTime.now();
        this.student = student;
        this.text = text;
    }

    /**
     * Retorna a data da submissão.
     * @return A data da submissão.
     */

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Retorna o estudante responsável pela submissão.
     * @return O estudante responsável pela submissão.
     */

    @Override
    public Student_imp getStudent() {
        return student;
    }


    /**
     * Retorna o texto da submissão.
     * @return O texto da submissão.
     */

    @Override
    public String getText() {
        return text;
    }

    /**
     * Compara duas submissões com base em suas datas.
     * @param submission A submissão a ser comparada.
     * @return Um valor negativo se esta submissão for anterior à submissão passada, um valor positivo se for posterior,
     *         ou 0 se as datas forem iguais.
     */

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

    /**
     * Retorna uma representação em string desta submissão.
     * @return Uma string contendo informações sobre a submissão.
     */

    @Override
    public String toString() {
        String text = "";

        text += "\tSubmission: " + date + "\n"
                + "\tStudent: " + student + "\n"
                + "\tText: " + this.text + "\n";
        return text;
    }
}
