package main.classes.users;

import main.enums.UserType;

public class Regular extends User{
    private String regularDetails;
    public Regular(String id, String firstName, String lastName, String username, String email, String password, String address, String regularDetails) {
        super(id, UserType.REGULAR, firstName, lastName, username, email, password, address);
        this.regularDetails = regularDetails;
    }
}
