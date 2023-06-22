package interfaces;

import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Partner;
import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Submission;

public interface I_Participant {

    public Student getStudent(String name);

    public Facilitator getFacilitator(String name);

    public Partner getPartner(String name);

    //Add submissions to a project of an active edition by a participant in specific a student
    public void addSubmission(String editionName, String projectName, String taskName, String studentName, Submission submission);

    public String getSudentsWithMoreSubmissions(Edition edition, Project project);
}
