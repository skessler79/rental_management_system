package main.classes;

import main.enums.UserType;

public class User {
    private String id;
    private UserType userType;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String address;

    public User(String id, UserType userType, String firstName, String lastName, String username, String email, String password, String address) {
        this.id = id;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }

}
