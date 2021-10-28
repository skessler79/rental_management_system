package main.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.classes.CurrentSession;
import main.classes.RuntimeTypeAdapterFactory;
import main.classes.properties.Property;
import main.classes.users.*;
import main.enums.UserType;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserDataModel {

    private JsonReader reader;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private ArrayList<User> data;
    private final JSONParser parser = new JSONParser();
    private Gson gson;

    private ArrayList<User> userData;
    private ArrayList<User> pendingData;
    private ArrayList<User> adminData;
    private ArrayList<User> ownerData;
    private ArrayList<User> agentData;
    private ArrayList<User> regularData;

    private final Type USER_LIST_TYPE = new TypeToken<ArrayList<User>>(){}.getType();

    //used for deserialization of User
    RuntimeTypeAdapterFactory<User> adapter = RuntimeTypeAdapterFactory.of(User.class, "userType")
            .registerSubtype(Admin.class, UserType.ADMIN.name())
            .registerSubtype(Owner.class, UserType.OWNER.name())
            .registerSubtype(Agent.class, UserType.AGENT.name())
            .registerSubtype(Regular.class, UserType.REGULAR.name())
            .registerSubtype(Pending.class, UserType.PENDING.name());

    //get TypeToken
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

    //private method to get path
    private String getPath(UserType loadDataType , boolean isPending){
        switch (loadDataType){
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

    //takes in UserType and arraylist of objects and store them as json file accordingly
    private void inputData(UserType loadDataType, ArrayList<User> inputData, boolean isPending) {
        try {
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().registerTypeAdapterFactory(adapter).serializeSpecialFloatingPointValues().create();
            fileWriter = new FileWriter(getPath(loadDataType, isPending));
            gson.toJson(inputData, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load from json file based on provided UserType
    private ArrayList<User> loadData(UserType loadDataType, boolean isPending){
        try {
            fileReader = new FileReader(getPath(loadDataType, isPending));
            reader = new JsonReader(fileReader);
            gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
            data = gson.fromJson(reader, getTypeToken(loadDataType));
            fileReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data == null){
            return new ArrayList<>();
        }

        return data;
    }

    //edit user based on given user object
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

    //public method to get user data
    public User getUserById(String id){
        userData = getUserData();
        for (User user:userData){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    //public method to get pending user data by type
    public ArrayList<User> getPendingUserDataByType(UserType userType){
        return loadData(userType, true);
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
