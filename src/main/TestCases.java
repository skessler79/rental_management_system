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
        testFilterProperties();
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
        User owner1 = new UserBuilder("Ali", "Tan", "ali0101", "ali@abc.com", "abc123", "19-01, Jln Padi Emas 3/1, Taman Abc, Johor Bahru")
                .ownerDetails("owner1")
                .buildUser(UserType.OWNER);
        User owner2 = new UserBuilder("Ee", "Ming", "ee0101", "ee@abc.com", "abc123", " Lorong Kota Permail 11, Taman kota Permai,")
                .ownerDetails("owner2")
                .buildUser(UserType.OWNER);
        User regular1 = new UserBuilder("Abu", "Lim", "abu0202", "abu@abc.com", "abc123", "No. 11 Jln Puah Jaya Taman Setapak Indah Jaya")
                .regularDetails("regular1")
                .buildUser(UserType.REGULAR);
        User admin1 = new UserBuilder("Aqel", "Kumar", "aqel0101", "abc2@abc.com", "abc123", "Jalan Permas 10, Bandar Baru Permas Jaya")
                .adminDetails("admin1")
                .buildUser(UserType.ADMIN);
        User agent1 = new UserBuilder("Johny ", "Shallow", "shallow123", "johny@abc.com", "abc123", "273B Kampar Road")
                .agentDetails("agent1")
                .buildUser(UserType.AGENT);
        User regular2 = new UserBuilder("Michael", "Nickson", "michael4321", "bilebile@abc.com", "abc123", "2 Jln Siu Nam, Johor Bahru")
                .regularDetails("regular2")
                .buildUser(UserType.REGULAR);

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
        User admin0101 = new UserBuilder("Admin0101", "Loo", "admin0101", "admin0101@abc.com", "abc123", "29 Lorong 3/1B Taman Intan Baiduri Kepong")
                .adminDetails("admin2")
                .buildUser(UserType.ADMIN);
        User owner3 = new UserBuilder("Cardi", "C", "owner3", "cardi@abc.com", "abc123", "8 Jln Hulu Batu 7 Hulu Ampang").
                ownerDetails("owner3")
                .buildUser(UserType.OWNER);
        User owner4 = new UserBuilder("Cola", "Sprite", "owner4", "cola@abc.com", "abc123", "36 Tingkat 3 Persiaran 65C Off Jalan Pahang Barat")
                .ownerDetails("owner4")
                .buildUser(UserType.OWNER);
        User regular3 = new UserBuilder("Pepsi", "Milo", "milo1234", "pepsi@abc.com", "abc123", "Jalan Pjs 11/20, Bandar Sunway,")
                .regularDetails("regular3")
                .buildUser(UserType.REGULAR);
        User admin2 = new UserBuilder("Lemon", "Lime", "lime0101", "lemon@abc.com", "abc123", "69, Taman Good, 69420")
                .adminDetails("admin2")
                .buildUser(UserType.ADMIN);
        User agent3 = new UserBuilder("Jamie", "Bonds", "agent007", "agent007@abc.com", "abc123", "19-01, Jln Padi Emas 3/1, Bahru")
                .agentDetails("agent3")
                .buildUser(UserType.AGENT);
        User regular4 = new UserBuilder("Elon", "Must", "mustelon", "abcg4@abc.com", "abc123", "11, Taman Very Ok, 14000 Pulau Pinang")
                .regularDetails("regular4")
                .buildUser(UserType.REGULAR);

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
        } catch (IllegalArgumentException e){
            System.out.println("The user do not exists!");
        } catch (IllegalAccessException){
            System.out.println("You must be admin to approve users!");
        }
    }


    private void getPropertiesByOwner(){
        User ownerUser = userDataModel.loginUser("owner1", "abc123");
        System.out.println(propertyDataModel.getPropertyByOwner(ownerUser));
    }

    private void addComments(){
        User adminUser1 = userDataModel.loginUser("admin0101", "abc123");
        User adminUser2 = userDataModel.loginUser("lime0101", "abc123");
        Property firstProperty = propertyDataModel.getPropertiesData().get(0);
        try {
            propertyDataModel.addComment(adminUser1, firstProperty, "A very interesting unit");
            propertyDataModel.addComment(adminUser2, firstProperty, "Looks cosy!");
        } catch (IllegalAccessException e){
            System.out.println("You must be admin to add comments!");
        }
    }

    private void changeStatus(){
        User adminUser = userDataModel.loginUser("admin0101", "abc123");
        Property firstProperty = propertyDataModel.getPropertiesData().get(0);
        try {
            propertyDataModel.setPropertyActive(adminUser, firstProperty, false);
        } catch (IllegalAccessException e) {
            System.out.println("You are not authorized to add comments!")
        }

    }

    private void searchByFacility(){
        propertyData = propertyDataModel.getPropertyByFacilityType(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER)));
        System.out.println(propertyData);
    }

    private void testFilterProperties(){
        propertyData = propertyDataModel.filterProperty(PropertyType.BUNGALOW, userDataModel.getUserByUsername("owner3"), null);
        for(Property property:propertyData){
            System.out.println(property.getName());
        }
    }

}
