package main.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.classes.Property;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class PropertyDataModel {
    private final String path = "resources/data/propertyData.json";
    private final Type PROPERTY_LIST_TYPE = new TypeToken<ArrayList<Property>>(){}.getType();
    private JsonReader reader;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private final JSONParser parser = new JSONParser();
    private Gson gson;

    private ArrayList<Property> propertyData;

    public ArrayList<Property> getPropertiesData(){
        return loadData();
    }

    public void registerProperty(Property property){
        propertyData = loadData();
        propertyData.add(property);
        inputData(propertyData);
    }

    public ArrayList<Property> getPropertyByName(String name){
        ArrayList<Property> match = new ArrayList<Property>();
        propertyData = loadData();
        for(Property property : propertyData) {
            if (property.getName() != null && property.getName().contains(name)){
                match.add(property);
            }
        }
        return match;
    }

    public Property getPropertyById(String id){
        propertyData = loadData();
        for(Property property : propertyData) {
            if (property.getPropertyId() != null && property.getPropertyId().equals(id)){
                return property;
            }
        }
        return null;
    }

    private void inputData(ArrayList<Property> properties){
        try{
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().serializeSpecialFloatingPointValues().create();
            fileWriter = new FileWriter(path);
            gson.toJson(properties, fileWriter);
        }
        catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Property> loadData(){
        try {
            fileReader = new FileReader(path);
            gson = new Gson();
            reader = new JsonReader(fileReader);
            propertyData = gson.fromJson(reader, PROPERTY_LIST_TYPE);
            if (propertyData == null)
                propertyData = new ArrayList<Property>();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return propertyData;
    }
}
