package main.classes.users;

import main.classes.properties.Property;
import main.enums.UserType;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.UUID;

public class UserBuilder {
    private String id = UUID.randomUUID().toString();
    private String firstName = null;
    private String lastName = null;
    private String username = null;
    private String email = null;
    private String password = null;
    private String address = null;
    private String adminDetails = null;
    private String ownerDetails = null;
    private String agentDetails = null;
    private String regularDetails = null;
    // owner
    private ArrayList<String> propertyList = new ArrayList<>();

    public UserBuilder(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User buildUser(UserType userType) {
        switch (userType){
            case ADMIN:

                return new Admin(id,firstName,lastName,username,email,password,address, adminDetails);
            case OWNER:

                return new Owner(id,firstName,lastName,username,email,password,address, ownerDetails, propertyList);
            case AGENT:

                return new Agent(id,firstName,lastName,username,email,password,address, agentDetails);
            case REGULAR:

                return new Regular(id,firstName,lastName,username,email,password,address, regularDetails);
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
