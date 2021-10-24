package main.classes.users;

import main.enums.UserType;

public class Agent extends User{
    private String agentDetails;
    public Agent(String id, String firstName, String lastName, String username, String email, String password, String address, String agentDetails) {
        super(id, UserType.AGENT, firstName, lastName, username, email, password, address);
        this.agentDetails = agentDetails;
    }
}
