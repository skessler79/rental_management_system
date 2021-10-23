package main.classes.users;

import main.enums.UserType;

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

    public UserBuilder(String firstName, String lastName, String username, String email, String password, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User buildUser(UserType userType) {
        switch (userType){
            case ADMIN:
                if (adminDetails == null){
                    return null;
                }
                return new Admin(id,firstName,lastName,username,email,password,address, adminDetails);
            case OWNER:
                if (ownerDetails == null){
                    return null;
                }
                return new Owner(id,firstName,lastName,username,email,password,address, ownerDetails);
            case AGENT:
                if (agentDetails == null){
                    return null;
                }
                return new Agent(id,firstName,lastName,username,email,password,address, agentDetails);
            case REGULAR:
                if (regularDetails == null){
                    return null;
                }
                return new Regular(id,firstName,lastName,username,email,password,address, regularDetails);
        }

        return null;
    }

    public UserBuilder address (String address){
        this.address = address;
        return this;
    }

    public UserBuilder adminDetails (String adminDetails){
        this.adminDetails = adminDetails;
        return this;
    }

    public UserBuilder ownerDetails (String ownerDetails){
        this.ownerDetails = ownerDetails;
        return this;
    }

    public UserBuilder agentDetails (String agentDetails){
        this.agentDetails = agentDetails;
        return this;
    }
}
