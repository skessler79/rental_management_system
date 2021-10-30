package main.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.classes.Comment;
import main.classes.properties.Property;
import main.classes.users.Admin;
import main.classes.users.Owner;
import main.classes.users.User;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.enums.UserType;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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

    //get property based on projects
    public ArrayList<Property> getPropertyByProject(String project){
        ArrayList<Property> output = new ArrayList<>();
        propertyData = loadData();
        for (Property property:propertyData){
            if (property.getProject().toLowerCase().contains(project.toLowerCase())){
                output.add(property);
            }
        }

        return  output;
    }

    //register property with input of current user object and property object
    public ArrayList<Property> getPropertyByFacilityType(ArrayList<FacilityType> targetFacilityTypes) {
        ArrayList<Property> output = new ArrayList<>();
        propertyData = getPropertyByActive(true);
        if (targetFacilityTypes.size() == 0){
            return propertyData;
        }
        int containFlag;
        for (Property property:propertyData){
            //remove different element
            containFlag = 0;

            for (FacilityType facility:targetFacilityTypes){
                if (property.getFacilityTypes().contains(facility)) {
                    containFlag += 1;
                }
            }
            if (containFlag == targetFacilityTypes.size())
                output.add(property);
        }

        return output;

    }

    //private method to provide filterProperty a more cleaner implementation
    private boolean checkFilter(Property property, ArrayList<FacilityType> facilityTypes){
        int containFlag = 0;
        for (FacilityType facility:facilityTypes){
            if (facility == null) {
                return true;
            }
            if (property.getFacilityTypes().contains(facility)) {
                containFlag += 1;
            }
        }
        if (containFlag == facilityTypes.size())
            return true;
        return false;
    }

    public ArrayList<Property> filterProperty(PropertyType propertyType,  ArrayList<FacilityType> facilityTypes, String project, String name){
        ArrayList<Property> output = new ArrayList<>();

        propertyData = getPropertyByActive(true);

        for (Property property:propertyData){
            int addCounter = 0;

            //check propertType conditions
            if (propertyType == null)
                addCounter += 1;
            else if(property.getPropertyType() == propertyType)
                addCounter += 1;
            else
                continue;

            //check facilityType conditions
            if (facilityTypes == null)
                addCounter+=1;
            else if(checkFilter(property, facilityTypes))
                addCounter+=1;
            else
                continue;

            //check project conditions
            if (project == null || project == "")
                addCounter+=1;
            else if(property.getProject().toLowerCase().contains(project.toLowerCase()))
                addCounter+=1;
            else
                continue;

            //check for name conditions
            if (name == null || name == "")
                addCounter+=1;
            else if(property.getName().toLowerCase().contains(name.toLowerCase()))
                addCounter+=1;
            else
                continue;

            //if both condition met
            if (addCounter == 4)
                output.add(property);
        }
        return output;
    }


    //Overloaded function of above property with additional owner object and doesnt have property name
    public ArrayList<Property> filterProperty(PropertyType propertyType, User owner, Boolean isActive, ArrayList<FacilityType> facilityTypes, String project){
        ArrayList<Property> output = new ArrayList<>();
        if (isActive == null)
            propertyData = loadData();
        else
            propertyData = getPropertyByActive(isActive);

        for (Property property:propertyData){
            int addCounter = 0;
            //check propertType conditions
            if (propertyType == null)
                addCounter += 1;
            else if(property.getPropertyType() == propertyType)
                addCounter += 1;
            else
                continue;

            //check owner conditions
            if (owner == null)
                addCounter+=1;
            else if(owner.getId().equals(property.getOwner().getId()))
                addCounter+=1;
            else
                continue;

            //check facilityType conditions
            if (facilityTypes == null)
                addCounter+=1;
            else if(checkFilter(property, facilityTypes))
                addCounter+=1;
            else
                continue;

            //check project conditions
            if (project == null || project == "")
                addCounter+=1;
            else if(property.getProject().toLowerCase().contains(project.toLowerCase()))
                addCounter+=1;
            else
                continue;

            //if both condition met
            if (addCounter == 4)
                output.add(property);
        }
        return output;
    }

    public void deleteProperty(String propertyId){
        propertyData = loadData();
        for (Property property:propertyData){
            if (property.getPropertyId().equals(propertyId)){
                propertyData.remove(property);
                break;
            }
        }

        inputData(propertyData);
    }

    //register property with input of current user object and property object
    public void addComment(User currentUser, Property targetProperty, String comment) throws IllegalAccessException{
        if (currentUser.getUserType() != UserType.ADMIN){
            throw new IllegalAccessException("Only admin can add comments!");
        }
        propertyData = loadData();
        for (Property property:propertyData){
            if (property.getPropertyId().equals(targetProperty.getPropertyId())){
                property.addComment(new Comment(UUID.randomUUID().toString(), currentUser.getId(), comment, new Date()));
                property.setCommentCount(property.getCommentCount()+1);
                break;
            }
        }
        inputData(propertyData);
    }

    //get property based on active or not
    public ArrayList<Property> getPropertyByActive(boolean active){
        propertyData = loadData();
        ArrayList<Property> output = new ArrayList<>();
        for(Property property:propertyData){
            if (property.getIsActive() == active){
                output.add(property);
            }
        }
        return output;
    }

    //get property based on property type
    public ArrayList<Property> getPropertyByType(PropertyType propertyType){
        propertyData = loadData();
        ArrayList<Property> output = new ArrayList<>();
        for(Property property:propertyData){
            if (property.getPropertyType() == propertyType){
                output.add(property);
            }
        }
        return output;
    }

    public void setPropertyActive (User currentUser, Property targetProperty, boolean active) throws IllegalAccessException {
        if (currentUser.getUserType() != UserType.OWNER && currentUser.getUserType() != UserType.AGENT && currentUser.getUserType() != UserType.ADMIN)
            throw new IllegalAccessException("Unauthorized access!");
        propertyData = loadData();
        for (Property property:propertyData){
            if (property.getPropertyId().equals(targetProperty.getPropertyId())){
                propertyData.remove(property);
                targetProperty.setActive(active);
                propertyData.add(targetProperty);
                break;
            }
        }
        inputData(propertyData);
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
                break;
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
        User agent = targetProperty.getAgent();
        User owner = targetProperty.getOwner();
        boolean exist = false;
        for (Property property:propertyData){
            if (property.getPropertyId().equals(targetProperty.getPropertyId())){
                propertyData.remove(property);
                exist = true;
                break;
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
            if (property.getOwner().getId() != null && property.getOwner().getId().equals(id)){
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
