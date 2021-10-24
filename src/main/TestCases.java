package main;

import main.classes.users.Owner;
import main.classes.users.User;
import main.classes.users.UserBuilder;
import main.controllers.UserController;
import main.enums.UserType;
import main.models.PropertyDataModel;
import main.models.UserDataModel;

import java.util.ArrayList;

public class TestCases {
    private PropertyDataModel propertyDataModel = new PropertyDataModel();
    private UserDataModel userDataModel = new UserDataModel();
    private User currentUser;
    private ArrayList<User> pendingUsers;

    public void startTest(){
//        registerUsers();
        loginUser();
        approveUsers();
    }
    private void registerUsers(){
        User owner1 = new UserBuilder("Winson1", "Loo1", "owner1", "abc1@abc.com", "abc123", "69, Taman Nice, 69420").ownerDetails("owner1").buildUser(UserType.OWNER);
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
}
