package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.TestData.TestPendingUsers;
import main.TestData.TestProperties;
import main.TestData.TestUsers;
import main.classes.properties.Property;
import main.classes.users.Owner;
import main.classes.users.User;
import main.classes.users.UserBuilder;
import main.enums.FacilityType;
import main.enums.UserType;
import main.models.PropertyDataModel;
import main.models.UserDataModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("SpellCheckingInspection")
public class TestCases extends Application {
    private PropertyDataModel propertyDataModel = new PropertyDataModel();
    private UserDataModel userDataModel = new UserDataModel();
    private User currentUser;
    private ArrayList<User> pendingUsers;
    private ArrayList<User> userData;
    private ArrayList<Property> propertyData;

    @SuppressWarnings("CommentedOutCode")
    @Override
    public void start(Stage stage) throws Exception {
        populateData();
//        testFilterProperties();
//        testEditProfile();
//        testGetUserByUserType();
        testAddTenant();

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
            if (file.isFile() && !file.getName().equals(".gitignore")) {
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

        userData = new TestPendingUsers().getTestPendingUsers();

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
        userData = new TestUsers().getTestUsers(admin0101);

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

    private void testAddTenant(){
        User tenant = userDataModel.loginUser("milo1234", "abc123");
        Property property = propertyDataModel.getPropertiesData().get(0);
        propertyDataModel.addTenant(tenant, property);
    }

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

    private void testGetUserByUserType(){
        ArrayList<User> userData = userDataModel.getUserDataByType(UserType.OWNER);
        for(User user:userData){
            System.out.println(user);
        }
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
