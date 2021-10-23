package main.classes.users;

import main.enums.UserType;

public class Agent extends User{
    public Agent(String id, String firstName, String lastName, String username, String email, String password, String address) {
        super(id, UserType.AGENT, firstName, lastName, username, email, password, address);
    }
}
