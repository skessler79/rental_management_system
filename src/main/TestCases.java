package main;

import main.classes.users.Owner;
import main.classes.users.User;
import main.classes.users.UserBuilder;
import main.controllers.UserController;
import main.enums.UserType;
import main.models.PropertyDataModel;
import main.models.UserDataModel;

public class TestCases {
    private PropertyDataModel propertyDataModel = new PropertyDataModel();
    private UserDataModel userDataModel = new UserDataModel();
    public void registerOwner(){
        User owner1 = new UserBuilder("Winson1", "Loo1", "weixiong04041", "abc1@abc.com", "abc123", "69, Taman Nice, 69420").ownerDetails("owner1").buildUser(UserType.OWNER);
        User owner2 = new UserBuilder("Winson2", "Loo2", "weixiong04042", "abc2@abc.com", "abc123", "69, Taman Nice, 69420").ownerDetails("owner2").buildUser(UserType.OWNER);
        User owner3 = new UserBuilder("Winson3", "Loo3", "weixiong04043", "abc3@abc.com", "abc123", "69, Taman Nice, 69420").ownerDetails("owner3").buildUser(UserType.OWNER);
        User owner4 = new UserBuilder("Winson4", "Loo4", "weixiong04044", "abc4@abc.com", "abc123", "69, Taman Nice, 69420").ownerDetails("owner4").buildUser(UserType.OWNER);
        userDataModel.registerUser(owner2);
//        userDataModel.registerUser(owner2);
//        userDataModel.registerUser(owner3);
//        userDataModel.registerUser(owner4);
    }
}
