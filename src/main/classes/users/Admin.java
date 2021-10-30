package main.classes.users;

import main.enums.UserType;

public class Admin extends User{
    public Admin(String id, String firstName, String lastName, String username, String email, String password, String address) {
        super(id, UserType.ADMIN, firstName, lastName, username, email, password, address);
    }
}
