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
//        testFilterProperties();
//        testEditProfile();

    }


    //clear all the file and generate data
    private void populateData() throws IOException, IllegalAccessException {
        emptyJsonFiles();
        registerPendingUsers();
        registerActualUsers();
        registerProperties();
        System.out.println("\nAll data generated successfully!");
    }

    //used to empty all the json files
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

    //create dummy users into pending list data
    private void registerPendingUsers(){

        userData = new ArrayList<>(Arrays.asList(
                //admin
                new UserBuilder(  "aqel0101", "abc2@abc.com", "abc123")
                        .firstName("Aqel")
                        .lastName("Kumar")
                        .address("Jalan Permas 10, Bandar Baru Permas Jaya")
                        .buildUser(UserType.ADMIN),
                //owner
                new UserBuilder("ali0101", "ali@abc.com", "abc123")
                        .firstName("Ali")
                        .lastName("Tan")
                        .address("19-01, Jln Padi Emas 3/1, Taman Abc, Johor Bahru")
                        .buildUser(UserType.OWNER),
                new UserBuilder(  "ee0101", "ee@abc.com", "abc123")
                        .firstName("Ee")
                        .lastName("Ming")
                        .address(" Lorong Kota Permail 11, Taman kota Permai,")
                        .buildUser(UserType.OWNER),
                //agent
                new UserBuilder("shallow123", "johny@abc.com", "abc123")
                        .firstName("Johny")
                        .lastName("Shallow")
                        .address("273B Kampar Road")
                        .buildUser(UserType.AGENT),
                //regular
                new UserBuilder("abu0202", "abu@abc.com", "abc123")
                        .firstName("Abu")
                        .lastName("Lim")
                        .address("No. 11 Jln Puah Jaya Taman Setapak Indah Jaya")
                        .buildUser(UserType.REGULAR),
                new UserBuilder("michael4321", "bilebile@abc.com", "abc123")
                        .firstName("Michael")
                        .lastName("Nickson")
                        .address("2 Jln Siu Nam, Johor Bahru")
                        .buildUser(UserType.REGULAR)

        ));

        try{
            for(User user:userData){
                userDataModel.registerUser(user);
            }
            System.out.println("Total pending user generated = " + userData.size());
        }catch (IllegalArgumentException e){
            System.out.println("Username already exists");
        }
    }

    //create dummy users into actual user data
    private void registerActualUsers() throws IllegalAccessException {
        //superAdmin
        User admin0101 = new UserBuilder("admin0101", "admin0101@abc.com", "abc123")
                .firstName("Admin0101")
                .lastName("Loo")
                .address("29 Lorong 3/1B Taman Intan Baiduri Kepong")
                .buildUser(UserType.ADMIN);

        userData = new ArrayList<>(Arrays.asList(
                //admin
                admin0101,
                new UserBuilder("lime0101", "lemon@abc.com", "abc123")
                        .firstName("Lemon")
                        .lastName("Lime")
                        .address("69, Taman Good, 69420")
                        .buildUser(UserType.ADMIN),
                //owner
                new UserBuilder("owner3", "cardi@abc.com", "abc123")
                        .firstName("Cardi")
                        .lastName("C")
                        .address("8 Jln Hulu Batu 7 Hulu Ampang")
                        .buildUser(UserType.OWNER),
                new UserBuilder("owner4", "cola@abc.com", "abc123")
                        .firstName("Cola")
                        .lastName("Sprite")
                        .address("36 Tingkat 3 Persiaran 65C Off Jalan Pahang Barat")
                        .buildUser(UserType.OWNER),
                new UserBuilder("owner5", "selwyn@abc.com", "abc123")
                        .firstName("Selwyn")
                        .lastName("Ping")
                        .address("9 Jln Hulu Batu 10 Hulu Ampang")
                        .buildUser(UserType.OWNER),
                new UserBuilder("owner6", "affendi@abc.com", "abc123")
                        .firstName("Nafis")
                        .lastName("Affendi")
                        .address("37 Tingkat 4 Persiaran 65C Off Jalan Pahang Barat")
                        .buildUser(UserType.OWNER),
                //regular user
                new UserBuilder("milo1234", "pepsi@abc.com", "abc123")
                        .firstName("Pepsi")
                        .lastName("Milo")
                        .address("Jalan Pjs 11/20, Bandar Sunway,")
                        .buildUser(UserType.REGULAR),
                new UserBuilder("mustelon", "abcg4@abc.com", "abc123")
                        .firstName("Elon")
                        .lastName("Must")
                        .address("11, Taman Very Ok, 14000 Pulau Pinang")
                        .buildUser(UserType.REGULAR),
                new UserBuilder( "adam0101", "adam@abc.com", "abc123")
                        .firstName("Adam")
                        .lastName("Tan")
                        .address("11 Jalan Juru, Bukit Mertajam,")
                        .buildUser(UserType.REGULAR),
                new UserBuilder("markkepen", "mark123@abc.com", "abc123")
                        .firstName("Mark")
                        .lastName("Kepen")
                        .address("55, Taman Very Very Ok, 14000 Pulau Pinang")
                        .buildUser(UserType.REGULAR),
                new UserBuilder( "peterspark0101", "ironspider@abc.com", "abc123")
                        .firstName("Peter")
                        .lastName("Spark")
                        .address("22, Taman Very Very Very Ok, 14000 Pulau Pinang")
                        .buildUser(UserType.REGULAR),
                //agent
                new UserBuilder("agent007", "agent007@abc.com", "abc123")
                        .firstName("Jamie")
                        .lastName("Bonds")
                        .address("19-01, Jln Padi Emas 3/1, Bahru")
                        .buildUser(UserType.AGENT),
                new UserBuilder("river123", "dog4life@abc.com", "abc123")
                        .firstName("Keanu")
                        .lastName("River")
                        .address("19-01, Jln Padi Silver 4/5")
                        .buildUser(UserType.AGENT),
                new UserBuilder("warrenbezos", "agent007@abc.com", "abc123")
                        .firstName("Jeff")
                        .lastName("Buffet")
                        .address("19-01, Jln Padi Emas 3/1, Bahru")
                        .buildUser(UserType.AGENT)

        ));

        for(User user:userData){
            userDataModel.adminCreateUser(admin0101, user);
        }
        System.out.println("Total user generated = " + userData.size());
    }

    //fetch Arraylist<Property> from TestProperties and register them into propertyData.json
    private void registerProperties(){
        propertyData = new TestProperties().getTestProperties();
        for (Property property:propertyData){
            propertyDataModel.addProperty((Owner) property.getOwner(), property);
        }
        System.out.println("Total properties generated = " + propertyData.size());
    }

    //=========below functions are used to simulate and test functionalities of the models

    private void testLoginUser(){
        currentUser = userDataModel.loginUser("admin0101", "abc123");
        System.out.println(currentUser);
    }

    private void testApproveUsers(){
        pendingUsers = userDataModel.getPendingUserData();
        try{
            for (User user:pendingUsers){
                userDataModel.approveUser(currentUser, user);
            }
        } catch (IllegalArgumentException e){
            System.out.println("The user do not exists!");
        } catch (IllegalAccessException e){
            System.out.println("You must be admin to approve users!");
        }
    }


    private void testGetPropertiesByOwner(){
        User ownerUser = userDataModel.loginUser("owner1", "abc123");
        System.out.println(propertyDataModel.getPropertyByOwner(ownerUser));
    }

    private void testAddComments(){
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

    private void testChangeStatus(){
        User adminUser = userDataModel.loginUser("admin0101", "abc123");
        Property firstProperty = propertyDataModel.getPropertiesData().get(0);
        try {
            propertyDataModel.setPropertyActive(adminUser, firstProperty, false);
        } catch (IllegalAccessException e) {
            System.out.println("You are not authorized to add comments!");
        }

    }

    private void testSearchByFacility(){
        propertyData = propertyDataModel.getPropertyByFacilityType(new ArrayList<>(Arrays.asList(FacilityType.WATER_HEATER)));
        System.out.println(propertyData);
    }

    private void testFilterProperties(){
        propertyData = propertyDataModel.filterProperty(null, null, null, new ArrayList<>(Arrays.asList(FacilityType.WIFI, FacilityType.FRIDGE)), null);
        for(Property property:propertyData){
            System.out.println(property.getFacilityTypes());
        }
    }

    private void testEditProfile(){
        User currentUser = userDataModel.loginUser("agent007", "abc123");
        currentUser.setEmail("imagent007@gmail.com");
        currentUser.setLastName("bondny");
        userDataModel.editUserProfile(currentUser);
    }

}
