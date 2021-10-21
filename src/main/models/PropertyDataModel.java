package main.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import main.classes.Property;
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
    private final String path = "resources/data/propertyData.json";
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

    public void inputPropertyData(Property propertyObj){
        JSONArray properties = data;
        property = new JSONObject();
        property.put("ownerId", propertyObj.getOwnerId());
        property.put("agentId", propertyObj.getAgentId());
        property.put("id", propertyObj.getPropertyId());
        property.put("name", propertyObj.getName());
        property.put("type", propertyObj.getType());
        property.put("address", propertyObj.getAddress());
        property.put("state", propertyObj.getState());
        property.put("size", propertyObj.getSize());
        property.put("description", propertyObj.getDescription());
        property.put("project", propertyObj.getProject());
        property.put("rentalFee", propertyObj.getRentalFee());
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
            // if error then assign data to empty array
            data = new JSONArray();
        }
    }
}
