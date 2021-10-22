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
    private ArrayList<Property> data;
    private ArrayList<Property> properties;
    private JsonReader reader;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private final JSONParser parser = new JSONParser();

    public PropertyDataModel() {
        loadData();
    }

    public ArrayList<Property> getPropertiesData(){
        return data;
    }

    public void inputPropertyData(Property propertyObj){
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().serializeSpecialFloatingPointValues().create();
            fileWriter = new FileWriter(path);
            properties = data;
            properties.add(propertyObj);
            gson.toJson(properties, fileWriter);
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
                Gson gson = new Gson();
                reader = new JsonReader(fileReader);
                data = gson.fromJson(reader, PROPERTY_LIST_TYPE);
                if (data == null)
                    data = new ArrayList<Property>();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        public ArrayList<Property> getPropertyByName(String name){
            ArrayList<Property> match = new ArrayList<Property>();
        for(Property property : data) {
            if (property.getName() != null && property.getName().contains(name)){
                match.add(property);
            }
        }
        return match;
    }

    public Property getPropertyById(String id){
        for(Property property : data) {
            if (property.getPropertyId() != null && property.getPropertyId().equals(id)){
                return property;
            }
        }
        return null;
    }
}
