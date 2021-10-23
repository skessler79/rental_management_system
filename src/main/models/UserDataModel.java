package main.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.classes.RuntimeTypeAdapterFactory;
import main.classes.users.*;
import main.enums.UserType;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            .registerSubtype(Admin.class)
            .registerSubtype(User.class, UserType.USER.name())
            .registerSubtype(Owner.class, UserType.OWNER.name())
            .registerSubtype(Agent.class, UserType.AGENT.name())
            .registerSubtype(Regular.class, UserType.REGULAR.name())
            .registerSubtype(Pending.class, UserType.PENDING.name());

    //private method to get path
    private String getPath(UserType loadDataType){
        switch (loadDataType){
            case USER:
                return "resources/data/userData.json";
            case ADMIN:
                return "resources/data/adminData.json";
            case OWNER:
                return "resources/data/ownerData.json";
            case AGENT:
                return "resources/data/agentData.json";
            case REGULAR:
                return "resources/data/regularData.json";
            case PENDING:
                return "resources/data/pendingRegisterData.json";
        }

        return null;
    }

    //fetch user from pending list and approve them based on their role
    public void approveUser(User currentUser, User pendingUser) throws IllegalArgumentException, IllegalAccessException{
        if (currentUser.getUserType() != UserType.ADMIN)
            throw new IllegalAccessException("Only admin can call this method");
        pendingData = loadData(UserType.PENDING);

        //delete user from pending list
        if(!pendingData.removeIf(user -> user.getId().equals(pendingUser.getId())))
            throw new IllegalArgumentException("Invalid User");
        inputData(UserType.PENDING, pendingData);

        ArrayList<User> tempData = loadData(pendingUser.getUserType());

        tempData.add(pendingUser);
        inputData(pendingUser.getUserType(), tempData);
    }

    //check if username is unique and add them to pending user
    public void registerUser(User registerUser) throws IllegalArgumentException{
        userData = loadData(UserType.USER);
        for(User user:userData){
            if (registerUser.getUsername().equals(user.getUsername())){
                throw new IllegalArgumentException("Username already exists!");
            }
        }

        ArrayList<User> pendingUserData = loadData(UserType.PENDING);
        pendingUserData.add(registerUser);
        inputData(UserType.PENDING, pendingUserData);

    }

    // perform login with provided username and password
    public User loginUser(String username, String password) throws IllegalArgumentException{
        userData = loadData(UserType.USER);
        boolean userExist = false;
        User loginUser = new User();
        for (User user:userData){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                loginUser = user;
                userExist = true;
            }
        }

        if (!userExist){
            throw new IllegalArgumentException("Credentials are incorrect");
        }

        data = loadData(loginUser.getUserType());
        for (User user:data){
            if (user.getId().equals(loginUser.getId())){
                System.out.println(user + "has logged in successfully");
                return user;
            }
        }
        return null;
    }


    //takes in UserType and arraylist of objects and store them as json file accordingly
    private void inputData(UserType loadDataType, ArrayList<User> inputData) {
        try {
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().serializeSpecialFloatingPointValues().create();
            fileWriter = new FileWriter(getPath(loadDataType));
            gson.toJson(inputData, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load from json file based on provided UserType
    private ArrayList<User> loadData(UserType loadDataType){
        try {
            fileReader = new FileReader(getPath(loadDataType));
            gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
            reader = new JsonReader(fileReader);
            data = gson.fromJson(reader, USER_LIST_TYPE);
            fileReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data == null){
            return new ArrayList<User>();
        }

        return data;
    }
}
