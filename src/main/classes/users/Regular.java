package main.classes.users;

import main.enums.UserType;

public class Regular extends User{
    public Regular(String id, String firstName, String lastName, String username, String email, String password, String address) {
        super(id, UserType.REGULAR, firstName, lastName, username, email, password, address);
    }
}
