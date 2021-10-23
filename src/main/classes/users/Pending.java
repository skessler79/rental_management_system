package main.classes.users;

import main.enums.UserType;

public class Pending extends User{
    public Pending(String id, String firstName, String lastName, String username, String email, String password, String address) {
        super(id, UserType.PENDING, firstName, lastName, username, email, password, address);
    }
}
