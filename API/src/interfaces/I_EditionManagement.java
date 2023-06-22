package interfaces;

import project.Edition_imp;

import java.io.IOException;

public interface I_EditionManagement extends I_Edition, I_Event, I_Project, I_Task ,I_Participant{

    public void saveEditionsToJsonFile(String filename) throws IOException;

    public void readEditionsFromJsonFile(String filename) throws IOException, org.json.simple.parser.ParseException;

}
