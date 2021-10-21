package main.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class PropertyDataModel {
    private final String path = "src/main/data/propertyData.json";
    private JSONArray data;
    private JSONArray properties;
    private JSONObject property;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private final JSONParser parser = new JSONParser();

    public PropertyDataModel() {
        loadData();
    }

    public JSONArray getPropertiesData(){
        return data;
    }

    public void inputPropertyData(String ownerId, String type, String name, String address, String size, String description,  String project, String state, double rentalFee){
        JSONArray properties = data;
        property = new JSONObject();
        property.put("ownerId", ownerId);
        property.put("id", UUID.randomUUID().toString());
        property.put("name", name);
        property.put("type", type);
        property.put("address", address);
        property.put("state", state);
        property.put("size", size);;
        property.put("description", description);
        property.put("project", project);
        property.put("rentalFee", rentalFee);
        properties.add(property);
        try{
            fileWriter = new FileWriter(path);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(properties.toJSONString());
            String prettyJsonString = gson.toJson(je);
            fileWriter.write(prettyJsonString);
            //refetch data
        }
        catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
                loadData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadData(){
        try {
            fileReader = new FileReader(path);

            Object obj = parser.parse(fileReader);
            // A JSON array. JSONObject supports java.util.List interface.
            data = (JSONArray) obj;
            //way to iterate through all data
//          Iterator<JSONObject> iterator = data.iterator();
//          while (iterator.hasNext()) {
//              // get each property
//              JSONObject jsonObject = (JSONObject) iterator.next();
//
//              // display via key
//              System.out.println(jsonObject.get("owner_name"));
//          }


        } catch (Exception e) {
            data = new JSONArray();
        }
    }


}
