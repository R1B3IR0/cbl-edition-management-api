/*
import ma02_resources.participants.InstituitionType;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.ls.LSOutput;
import participants.Contact_imp;
import participants.Instituition_imp;
import participants.Student_imp;
import project.Project_imp;
import project.Submission_imp;
import project.Task_imp;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        //LocalDateTime dateTime = LocalDateTime.of(2023, 6, 15, 10, 30, 0);


        Contact_imp c1 = new Contact_imp("Rua dos caralhos", "Caralhos", "Amadora","4333-123","Portugal","123141");
        Instituition_imp i1 = new Instituition_imp("ESTG","estg@email.pt", InstituitionType.UNIVERSITY, c1, "Website", "yaya");
        Student_imp a1 = new Student_imp("Miguel", "m@m.pt",c1,i1,121);
        Submission_imp s1 = new Submission_imp(LocalDateTime.of(2023, 6, 15, 10, 30, 0), a1, "ola");
        Submission_imp s2 = new Submission_imp(LocalDateTime.of(2023, 6, 15, 10, 30, 0), a1, "ola2");
        Submission_imp s3 = new Submission_imp(LocalDateTime.of(2023, 6, 15, 10, 30, 0), a1, "ola2");

        Submission_imp[] submissions = new Submission_imp[5];
        //submissions[0] = s1;

        Task_imp t1 = new Task_imp(LocalDate.of(2002,2,12), LocalDate.of(2002,2,13), 1, "task1","descricao", submissions);
        Task_imp t2 = new Task_imp(LocalDate.of(2002,2,12), LocalDate.of(2002,2,13), 1, "task2","descricao", submissions);
        t1.addSubmission(s1);
        t2.addSubmission(s2);
        t2.addSubmission(s3);


        //Project_imp p1 = new Project_imp("projeto1", "descricao", );

        //System.out.println(s1.getStudent());
        //System.out.println(t1.getSubmissions()[0]);
        //System.out.println(s1.toString());
        //System.out.println();
        System.out.println(t1.toString());
        System.out.println(t2.toString());


    }
}
*/