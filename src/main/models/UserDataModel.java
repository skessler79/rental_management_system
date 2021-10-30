package main.models;

//json dependencies libraries

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.classes.CurrentSession;
import main.classes.RuntimeTypeAdapterFactory;
import main.classes.properties.Property;
import main.classes.users.*;
import main.enums.UserType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserDataModel {

    private ArrayList<User> data;
    private Gson gson;

    private ArrayList<User> userData;
    private ArrayList<User> pendingData;

    //used for deserialization of User derived from RuntimeTypeAdapterFactory.java
    RuntimeTypeAdapterFactory<User> adapter = RuntimeTypeAdapterFactory.of(User.class, "userType")
            .registerSubtype(Admin.class, UserType.ADMIN.name())
            .registerSubtype(Owner.class, UserType.OWNER.name())
            .registerSubtype(Agent.class, UserType.AGENT.name())
            .registerSubtype(Regular.class, UserType.REGULAR.name());

    //get TypeToken based on userType
    @SuppressWarnings("DuplicateBranchesInSwitch")
    private Type getTypeToken(UserType userType){
        switch (userType){
            case ADMIN:
                return new TypeToken<ArrayList<Admin>>(){}.getType();
            case OWNER:
                return new TypeToken<ArrayList<Owner>>(){}.getType();
            case AGENT:
                return new TypeToken<ArrayList<Agent>>(){}.getType();
            case REGULAR:
                return new TypeToken<ArrayList<Regular>>(){}.getType();
        }
        return null;
    }

    //private method to get path of the json files
    private String getPath(UserType userType , boolean isPending){
        switch (userType){
            case ADMIN:
                return isPending?"resources/data/pendingAdminData.json":"resources/data/adminData.json";
            case OWNER:
                return isPending?"resources/data/pendingOwnerData.json":"resources/data/ownerData.json";
            case AGENT:
                return isPending?"resources/data/pendingAgentData.json":"resources/data/agentData.json";
            case REGULAR:
                return isPending?"resources/data/pendingRegularData.json":"resources/data/regularData.json";
        }

        return null;
    }

    //takes in UserType and arraylist of objects and store them into json file according to userType
    private void inputData(UserType userType, ArrayList<User> inputData, boolean isPending) {
        try {
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().registerTypeAdapterFactory(adapter).serializeSpecialFloatingPointValues().create();
            FileWriter fileWriter = new FileWriter(getPath(userType, isPending));
            gson.toJson(inputData, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load from json file based on provided UserType
    private ArrayList<User> loadData(UserType userType, boolean isPending){
        try {
            FileReader fileReader = new FileReader(getPath(userType, isPending));
            JsonReader reader = new JsonReader(fileReader);
            gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
            data = gson.fromJson(reader, getTypeToken(userType));
            fileReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data == null){
            return new ArrayList<>();
        }

        return data;
    }

    // removes propertyId from respective user when a property is removed
    public void userRemoveSelfPropertyListing(User targetUser, String propertyId){
        userData = loadData(targetUser.getUserType(), false);
        ArrayList<String> userPropertyList;

        for(User user:userData){
            if(user.getId().equals(targetUser.getId())){
                if (targetUser.getUserType() == UserType.OWNER){
                    Owner ownerUser = (Owner) user;
                    userPropertyList = ownerUser.getPropertyList();
                    userPropertyList.removeIf(id -> id.equals(propertyId) );
                    ownerUser.setPropertyList(userPropertyList);
                    CurrentSession.userDataModel.editUserProfile(ownerUser);
                }
                else if (targetUser.getUserType() == UserType.AGENT){

                    Agent agentUser = (Agent) user;
                    userPropertyList = agentUser.getPropertyList();
                    userPropertyList.removeIf(id -> id.equals(propertyId) );
                    agentUser.setPropertyList(userPropertyList);
                    CurrentSession.userDataModel.editUserProfile(agentUser);
                }
            }
        }

    }

    //allow users to update their profile details
    public void editUserProfile(User currentUser){
        userData = loadData(currentUser.getUserType(), false);
        for(User user:userData){
            if (user.getId().equals(currentUser.getId())){
                userData.remove(user);
                userData.add(currentUser);
                break;
            }
        }
        inputData(currentUser.getUserType(), userData, false);
    }

    //allow admin to create user directly
    public void adminCreateUser(User currentUser, User registerUser) throws IllegalAccessException, IllegalArgumentException{
        if (currentUser.getUserType() != UserType.ADMIN)
            throw new IllegalAccessException("Only admin can call this!");
        userData = getUserData();
        // check if there are the same usernames
        for(User user:userData){
            if (registerUser.getUsername().equals(user.getUsername())){
                throw new IllegalArgumentException("Username already exists!");
            }
        }

        ArrayList<User> userTypeData = loadData(registerUser.getUserType(), false);
        userTypeData.add(registerUser);
        inputData(registerUser.getUserType(), userTypeData, false);
    }

    //allow admin to delete user directly
    public void deleteUser(User currentUser, User targetUser) throws IllegalAccessException, IllegalArgumentException{
        if (currentUser.getUserType() != UserType.ADMIN)
            throw new IllegalAccessException("Only admin can call this!");
        if (currentUser.getId().equals(targetUser.getId()))
            throw new IllegalArgumentException("You cannot delete yourself!");
        userData = loadData(targetUser.getUserType(),false);
        userData.removeIf(user -> user.getId().equals(targetUser.getId()));

        //remove property that the owner owns
        ArrayList<Property> propertyData = CurrentSession.propertyDataModel.getPropertyByOwner(targetUser);
        for (Property property:propertyData){
            CurrentSession.propertyDataModel.removeProperty(property);
        }

        inputData(targetUser.getUserType(),userData,false);
    }

    //allow admin to reject user in the pending list
    public void rejectUser(User currentUser, User targetUser) throws IllegalAccessException{
        if (currentUser.getUserType() != UserType.ADMIN)
            throw new IllegalAccessException("Only admin can call this!");
        pendingData = loadData(targetUser.getUserType(), true);
        pendingData.removeIf(user -> user.getId().equals(targetUser.getId()));
        inputData(targetUser.getUserType(),pendingData,true);
    }

    //after owner registered a property, this function will add in the id of the property to propertyList in owner or agent class
    public void editProperty(User targetUser) throws IllegalArgumentException{
        userData = loadData(targetUser.getUserType(), false);
        boolean exist = false;
        for (User user:new ArrayList<>(userData)){
            if (user.getId().equals(targetUser.getId())){
                userData.remove(user);
                exist = true;
            }
        }

        if (!exist){
            throw new IllegalArgumentException("property do not exist!");
        }

        userData.add(targetUser);
        inputData(targetUser.getUserType(), userData, false);
    }

    //fetch user from pending list and approve them based on their role
    public void approveUser(User currentUser, User pendingUser) throws IllegalArgumentException, IllegalAccessException{
        if (currentUser.getUserType() != UserType.ADMIN)
            throw new IllegalAccessException("Only admin can call this method");
        pendingData = loadData(pendingUser.getUserType(), true);

        //delete user from pending list
        if(!pendingData.removeIf(user -> user.getId().equals(pendingUser.getId())))
            throw new IllegalArgumentException("Invalid User");
        inputData(pendingUser.getUserType(), pendingData, true);

        ArrayList<User> tempData = loadData(pendingUser.getUserType(), false );

        tempData.add(pendingUser);
        inputData(pendingUser.getUserType() , tempData, false);
    }

    //check if username is unique and add them to pending user
    public void registerUser(User registerUser) throws IllegalArgumentException{
        userData = getUserData();
        // check if there are the same usernames
        for(User user:userData){
            if (registerUser.getUsername().equals(user.getUsername())){
                throw new IllegalArgumentException("Username already exists!");
            }
        }

        ArrayList<User> pendingUserData = loadData(registerUser.getUserType(), true);
        pendingUserData.add(registerUser);
        inputData(registerUser.getUserType(), pendingUserData, true);

    }


    // perform login with provided username and password
    public User loginUser(String username, String password) throws IllegalArgumentException{
        userData = getUserData();
        boolean userExist = false;
        for (User user:userData){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                CurrentSession.currentUser = user;
                return user;
            }
        }

        if (!userExist){
            throw new IllegalArgumentException("Credentials are incorrect");
        }

        return null;
    }

    //public method to get user data by userType
    public ArrayList<User> getUserDataByType(UserType userType){
        return loadData(userType, false);
    }

    //public method to get user data by username
    public User getUserByUsername(String username){
        userData = getUserData();
        for (User user:userData){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    //public method to get user data by id
    public User getUserById(String id, boolean isPending){
        userData = isPending?getPendingUserData():getUserData();
        for (User user:userData){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    //public method to get user data
    public ArrayList<User> getUserData(){
        ArrayList<User> output = new ArrayList<>();
        output.addAll(loadData(UserType.ADMIN, false));
        output.addAll(loadData(UserType.OWNER, false));
        output.addAll(loadData(UserType.AGENT, false));
        output.addAll(loadData(UserType.REGULAR, false));
        return output;
    }

    //public method to get all pending user data
    public ArrayList<User> getPendingUserData(){
        ArrayList<User> output = new ArrayList<>();
        output.addAll(loadData(UserType.ADMIN, true));
        output.addAll(loadData(UserType.OWNER, true));
        output.addAll(loadData(UserType.AGENT, true));
        output.addAll(loadData(UserType.REGULAR, true));
        return output;
    }
}
