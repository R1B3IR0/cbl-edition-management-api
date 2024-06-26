package interfaces;

import java.io.IOException;

/**
 * Interface I_EditionManagement
 */
public interface I_EditionManagement extends I_Edition {

    public void saveEditionsToJsonFile(String filename) throws IOException;

    public void readEditionsFromJsonFile(String filename) throws IOException, org.json.simple.parser.ParseException;

}
