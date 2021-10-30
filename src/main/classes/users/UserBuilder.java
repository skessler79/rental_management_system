package main.classes.users;


import main.enums.UserType;

import java.util.ArrayList;
import java.util.UUID;

// This class is designed and modified based on builder design pattern
public class UserBuilder {
    //users
    private final String id = UUID.randomUUID().toString();
    private String firstName = "";
    private String lastName = "";
    private String username = null;
    private String email = null;
    private String password = null;
    private String address = "";
    // owner & agent
    private final ArrayList<String> propertyList = new ArrayList<>();
    // regular
    private final String tenantPropertyId = null;

    public UserBuilder(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User buildUser(UserType userType) {
        switch (userType){
            case ADMIN:

                return new Admin(id,firstName,lastName,username,email,password,address);
            case OWNER:

                return new Owner(id,firstName,lastName,username,email,password,address,  propertyList);
            case AGENT:

                return new Agent(id,firstName,lastName,username,email,password,address, propertyList);
            case REGULAR:

                return new Regular(id,firstName,lastName,username,email,password,address, tenantPropertyId);
        }

        return null;
    }

    public UserBuilder address (String address){
        this.address = address;
        return this;
    }

    public UserBuilder firstName (String firstName){
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName (String lastName){
        this.lastName = lastName;
        return this;
    }
}
