package main.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.classes.users.User;
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

    private final Type USER_LIST_TYPE = new TypeToken<ArrayList<User>>(){}.getType();
//    private final Type ADMIN_LIST_TYPE = new TypeToken<ArrayList<User>>(){}.getType();
//    private final Type REGULAR_LIST_TYPE = new TypeToken<ArrayList<User>>(){}.getType();
//    private final Type OWNER_LIST_TYPE = new TypeToken<ArrayList<User>>(){}.getType();
//    private final Type AGENT_LIST_TYPE = new TypeToken<ArrayList<User>>(){}.getType();
//    private final Type PENDING_LIST_TYPE = new TypeToken<ArrayList<User>>(){}.getType();

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

    //check if username is unique and add them to pending user
    public void registerUser(User registerUser) throws IllegalArgumentException{
        ArrayList<User> userData = loadData(UserType.USER);
        for(User user:userData){
            if (registerUser.getUsername().equals(user.getUsername())){
                throw new IllegalArgumentException("Username already exists!");
            }
        }

        ArrayList<User> pendingUserData = loadData(UserType.PENDING);
        pendingUserData.add(registerUser);
        inputData(UserType.PENDING, pendingUserData);

    }

    // perform login
    public User loginUser(String username, String password) throws IllegalArgumentException{
        ArrayList<User> userData = loadData(UserType.USER);
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
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().serializeSpecialFloatingPointValues().create();
            fileWriter = new FileWriter(getPath(loadDataType));
            gson.toJson(inputData, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //load from json file based on provided UserType
    private ArrayList<User> loadData(UserType loadDataType){
        try {
            fileReader = new FileReader(getPath(loadDataType));
            Gson gson = new Gson();
            reader = new JsonReader(fileReader);
            data = gson.fromJson(reader, USER_LIST_TYPE);
            fileReader.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data == null){
            return new ArrayList<User>();
        }

        return data;
    }
}
