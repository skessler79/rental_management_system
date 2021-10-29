package main;

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

import java.util.ArrayList;
import java.util.Arrays;

public class TestCases {
    private PropertyDataModel propertyDataModel = new PropertyDataModel();
    private UserDataModel userDataModel = new UserDataModel();
    private User currentUser;
    private ArrayList<User> pendingUsers;
    private ArrayList<User> userData;
    private ArrayList<Property> propertyData;

    public void startTest(){
        registerUsers();
        loginUser();
        approveUsers();
        registerProperties();
        getPropertiesByOwner();
        addComments();
        changeStatus();
        searchByFacility();
    }
    private void registerUsers(){
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
            e.printStackTrace();
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

    private void registerProperties(){
        User ownerUser1 = userDataModel.loginUser("owner1", "abc123");
        User ownerUser2 = userDataModel.loginUser("owner2", "abc123");

        Property property1 = new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Happy house", "Taman 69")
                .address(new Address("69, Lorong Six Nine", "Taman 69", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                .isActive(true)
                .description("A nice house with a cheap rental beside the greatest uni MMU")
                .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE, FacilityType.WIFI)))
                .buildProperty();

        Property property2 = new PropertyBuilder(ownerUser1, PropertyType.BUNGALOW, "Sad house", "Taman 420")
                .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                .isActive(true)
                .description("A bad house with a cheap rental beside the greatest uni MMU")
                .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND)))
                .buildProperty();

        Property property3 = new PropertyBuilder(ownerUser1, PropertyType.BUNGALOW, "Average house", "Taman 789")
                .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                .isActive(true)
                .description("A bad house with a cheap rental beside the greatest uni MMU")
                .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.SWIMMING_POOL, FacilityType.WIFI)))
                .buildProperty();
        Property property4 = new PropertyBuilder(ownerUser1, PropertyType.BUNGALOW, "Haunted house", "Taman 456")
                .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                .isActive(true)
                .description("A bad house with a cheap rental beside the greatest uni MMU")
                .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.SWIMMING_POOL, FacilityType.TV, FacilityType.AIRCOND)))
                .buildProperty();

        Property property5 = new PropertyBuilder(ownerUser2, PropertyType.BUNGALOW, "Temple", "Taman 123")
                .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                .isActive(true)
                .description("A bad house with a cheap rental beside the greatest uni MMU")
                .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.TV, FacilityType.WATER_HEATER, FacilityType.FRIDGE, FacilityType.SWIMMING_POOL)))
                .buildProperty();

        Property property6 = new PropertyBuilder(ownerUser2, PropertyType.DOUBLE_STORY, "Temple", "Taman 123")
                .address(new Address("666, lorong DN", "Taman Malay", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                .isActive(false)
                .roomInfo("6 bathrooms, no bed")
                .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.TV, FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL)))
                .buildProperty();

        propertyDataModel.addProperty((Owner) ownerUser1, property1);
        propertyDataModel.addProperty((Owner) ownerUser1, property2);
        propertyDataModel.addProperty((Owner) ownerUser1, property3);
        propertyDataModel.addProperty((Owner) ownerUser1, property4);
        propertyDataModel.addProperty((Owner) ownerUser2, property5);
        propertyDataModel.addProperty((Owner) ownerUser2, property6);
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
