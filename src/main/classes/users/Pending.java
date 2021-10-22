package main.classes.users;

import main.enums.UserType;

public class Pending extends User{
    public Pending(String id, UserType userType, String firstName, String lastName, String username, String email, String password, String address) {
        super(id, userType, firstName, lastName, username, email, password, address);
    }
}
