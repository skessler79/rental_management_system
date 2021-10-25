package main.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.classes.properties.Property;
import main.classes.users.Owner;
import main.classes.users.User;
import main.enums.UserType;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;

public class PropertyDataModel {
    private final String path = "resources/data/propertyData.json";
    private final Type PROPERTY_LIST_TYPE = new TypeToken<ArrayList<Property>>(){}.getType();
    private JsonReader reader;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private final JSONParser parser = new JSONParser();
    private Gson gson;
    private UserDataModel userDataModel = new UserDataModel();

    private ArrayList<Property> propertyData;

    public ArrayList<Property> getPropertiesData(){
        return loadData();
    }

    //register property with input of current user object and property object
    public void addProperty(Owner currentUser, Property property){
        propertyData = loadData();
        propertyData.add(property);
        currentUser.addPropertyList(property);
        inputData(propertyData);
        userDataModel.editProperty(currentUser);
    }

    public void editProperty(Property targetProperty) throws IllegalArgumentException{
        propertyData = loadData();
        boolean exist = false;
        for (Property property:propertyData){
            if (property.getPropertyId().equals(targetProperty.getPropertyId())){
                propertyData.remove(property);
                exist = true;
            }
        }

        if (!exist){
            throw new IllegalArgumentException("property do not exist!");
        }

        propertyData.add(targetProperty);
        inputData(propertyData);
    }

    public void removeProperty(Property targetProperty) throws IllegalArgumentException{
        propertyData = loadData();
        boolean exist = false;
        for (Property property:propertyData){
            if (property.getPropertyId().equals(targetProperty.getPropertyId())){
                propertyData.remove(property);
                exist = true;
            }
        }
        if (!exist){
            throw new IllegalArgumentException("property do not exist!");
        }

        inputData(propertyData);
    }

    public ArrayList<Property> getPropertyByName(String name){
        ArrayList<Property> match = new ArrayList<>();
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

    public ArrayList<Property> getPropertyByOwner(User user){
        String id = user.getId();
        propertyData = loadData();
        ArrayList<Property> output = new ArrayList<>();
        for(Property property : propertyData) {
            if (property.getOwnerId() != null && property.getOwnerId().equals(id)){
                output.add(property);
            }
        }
        return output;
    }

    private void inputData(ArrayList<Property> properties){
        try{
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").serializeSpecialFloatingPointValues().create();
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
            reader = new JsonReader(fileReader);
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
            propertyData = gson.fromJson(reader,PROPERTY_LIST_TYPE);
            fileReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (propertyData == null){
            return new ArrayList<>();
        }

        return propertyData;
    }
}
