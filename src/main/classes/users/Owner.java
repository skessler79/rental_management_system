package main.classes.users;

import main.enums.UserType;

public class Owner extends User{
    private String ownerDetails;
    public Owner(String id, String firstName, String lastName, String username, String email, String password, String address, String ownerDetails) {
        super(id, UserType.OWNER, firstName, lastName, username, email, password, address);
        this.ownerDetails = ownerDetails;
    }
}
