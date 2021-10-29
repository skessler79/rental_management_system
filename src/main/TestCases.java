package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.classes.Address;
import main.classes.properties.Property;
import main.classes.properties.PropertyBuilder;
import main.classes.users.Owner;
import main.classes.users.User;
import main.classes.users.UserBuilder;
import main.controllers.UserController;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.enums.UserType;
import main.models.PropertyDataModel;
import main.models.UserDataModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class TestCases extends Application {
    private PropertyDataModel propertyDataModel = new PropertyDataModel();
    private UserDataModel userDataModel = new UserDataModel();
    private User currentUser;
    private ArrayList<User> pendingUsers;
    private ArrayList<User> userData;
    private ArrayList<Property> propertyData;

    @Override
    public void start(Stage stage) throws Exception {
        populateData();
    }

    private void startTest(){
//        registerUsers();
//        loginUser();
//        approveUsers();
//        registerProperties();
//        getPropertiesByOwner();
//        addComments();
//        changeStatus();
//        searchByFacility();
    }

    private void populateData() throws IOException, IllegalAccessException {
        emptyJsonFiles();
        registerPendingUsers();
        registerActualUsers();
        registerProperties();
    }

    private void emptyJsonFiles() throws IOException {
        String path = "resources/data";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                FileWriter fwOb = new FileWriter(path + "/" + file.getName(), false);
                PrintWriter pwOb = new PrintWriter(fwOb, false);
                pwOb.flush();
                pwOb.close();
                fwOb.close();
            }
        }
    }

    private void registerPendingUsers(){

        //goes to pending list
        User owner1 = new UserBuilder("Winson1", "Loo1", "owner1", "abc1@abc.com", "abc123", "69, Taman Nice, 69420").ownerDetails("owner1").buildUser(UserType.OWNER);
        User owner2 = new UserBuilder("Winson2", "Loo2", "owner2", "abc2@abc.com", "abc123", "69, Taman Bad, 69420").ownerDetails("owner2").buildUser(UserType.OWNER);
        User regular1 = new UserBuilder("Winson1", "Loo1", "regular1", "abc1@abc.com", "abc123", "69, Taman Nice, 69420").regularDetails("regular1").buildUser(UserType.REGULAR);
        User admin1 = new UserBuilder("Winson2", "Loo2", "admin1", "abc2@abc.com", "abc123", "69, Taman Nice, 69420").adminDetails("admin1").buildUser(UserType.ADMIN);
        User agent1 = new UserBuilder("Winson3", "Loo3", "agent1", "abc3@abc.com", "abc123", "69, Taman Nice, 69420").agentDetails("agent1").buildUser(UserType.AGENT);
        User regular2 = new UserBuilder("Winson4", "Loo4", "regular2", "abc4@abc.com", "abc123", "69, Taman Nice, 69420").regularDetails("regular2").buildUser(UserType.REGULAR);

       try{
            userDataModel.registerUser(owner1);
            userDataModel.registerUser(regular1);
            userDataModel.registerUser(admin1);
            userDataModel.registerUser(agent1);
            userDataModel.registerUser(regular2);
            userDataModel.registerUser(owner2);
        }catch (IllegalArgumentException e){
            System.out.println("Username already exists");
        }
    }

    private void registerActualUsers() throws IllegalAccessException {
        User admin0101 = new UserBuilder("Admin0101", "Loo", "admin0101", "admin0101@abc.com", "abc123", "69, Taman Good, 69420").adminDetails("admin2").buildUser(UserType.ADMIN);
        User owner3 = new UserBuilder("Winson3", "Loo1", "owner3", "abc1@abc.com", "abc123", "69, Taman Nice, 69420").ownerDetails("owner3").buildUser(UserType.OWNER);
        User owner4 = new UserBuilder("Winson4", "Loo2", "owner4", "abc2@abc.com", "abc123", "69, Taman Bad, 69420").ownerDetails("owner4").buildUser(UserType.OWNER);
        User regular3 = new UserBuilder("Winson3", "Loo1", "regular3", "abcd1@abc.com", "abc123", "69, Taman Ok, 69420").regularDetails("regular3").buildUser(UserType.REGULAR);
        User admin2 = new UserBuilder("WinsonAdmin2", "Loo2", "admin2", "abce2@abc.com", "abc123", "69, Taman Good, 69420").adminDetails("admin2").buildUser(UserType.ADMIN);
        User agent3 = new UserBuilder("WinsonAgent07", "Loo3", "agent3", "abcf3@abc.com", "abc123", "69, Taman Excellent, 69420").agentDetails("agent3").buildUser(UserType.AGENT);
        User regular4 = new UserBuilder("WinsonRegular4", "Loo4", "regular4", "abcg4@abc.com", "abc123", "69, Taman Very Ok, 69420").regularDetails("regular4").buildUser(UserType.REGULAR);

        userDataModel.adminCreateUser(admin0101, admin0101);
        userDataModel.adminCreateUser(admin0101, owner3);
        userDataModel.adminCreateUser(admin0101, owner4);
        userDataModel.adminCreateUser(admin0101, regular3);
        userDataModel.adminCreateUser(admin0101, admin2);
        userDataModel.adminCreateUser(admin0101, agent3);
        userDataModel.adminCreateUser(admin0101, regular4);
    }

    private void registerProperties(){
        propertyData = new TestProperties().getTestProperties();
        for (Property property:propertyData){
            propertyDataModel.addProperty((Owner) property.getOwner(), property);

        }
    }


    private void loginUser(){
        currentUser = userDataModel.loginUser("admin0101", "abc123");
        System.out.println(currentUser);
    }

    private void approveUsers(){
        pendingUsers = userDataModel.getPendingUserData();
        try{
            for (User user:pendingUsers){
                userDataModel.approveUser(currentUser, user);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void getPropertiesByOwner(){
        User ownerUser = userDataModel.loginUser("owner1", "abc123");
        System.out.println(propertyDataModel.getPropertyByOwner(ownerUser));
    }

    private void addComments(){
        User adminUser = userDataModel.loginUser("admin0101", "abc123");
        Property firstProperty = propertyDataModel.getPropertiesData().get(0);
        try {
            propertyDataModel.addComment(adminUser, firstProperty, "This unit is shit af man dont rent it");
            propertyDataModel.addComment(adminUser, firstProperty, "This unit is good someone hacked my account, dont trust above comment");
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

    private void changeStatus(){
        User adminUser = userDataModel.loginUser("admin0101", "abc123");
        Property firstProperty = propertyDataModel.getPropertiesData().get(0);
        try {
            propertyDataModel.setPropertyActive(adminUser, firstProperty, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void searchByFacility(){
        propertyData = propertyDataModel.getPropertyByFacilityType(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER)));
        System.out.println(propertyData);
    }

}
