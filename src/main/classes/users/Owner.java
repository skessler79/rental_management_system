package main.classes.users;

import main.enums.UserType;

public class Owner extends User{
    public Owner(String id, String firstName, String lastName, String username, String email, String password, String address) {
        super(id, UserType.OWNER, firstName, lastName, username, email, password, address);
    }
}
