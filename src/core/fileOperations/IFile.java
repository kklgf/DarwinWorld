package core.fileOperations;

import core.Simulation;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;

public interface IFile {
    public static Simulation loadSimulationFromJson(String file_path) throws FileNotFoundException {
//
//        InputStream is =
//                JsonParsing.class.getResourceAsStream( "sample-json.txt");
//        String jsonTxt = IOUtils.toString( is );
//
//        JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt );
//        double coolness = json.getDouble( "coolness" );
//        int altitude = json.getInt( "altitude" );
//        JSONObject pilot = json.getJSONObject("pilot");
//        String firstName = pilot.getString("firstName");
//        String lastName = pilot.getString("lastName");
//

//        Gson gson = new Gson();
//        JsonReader reader = new JsonReader(new FileReader(file_path));
//        JsonObject data = reader.readObject();
//        return null;
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        File jsonInputFile = new File(file_path);
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
            // Get the JsonObject structure from JsonReader.
            JsonObject empObj = reader.readObject();
            reader.close();
            // read string data
            System.out.println("Emp Name: " + empObj.getString("bat"));
            // read integer data
            System.out.println("Emp Id: " + empObj.getInt("altitude"));
            // read inner json element
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
