package main.models;

import javafx.event.ActionEvent;
import main.classes.users.User;

public class LoginModel
{
    public LoginModel()
    {

    }

    // TODO : Return user object
    public User login(String username, String password) throws IllegalArgumentException
    {
        UserDataModel userDataModel = new UserDataModel();
        User user;
        try {
            user = userDataModel.loginUser(username, password);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Login credential invalid");
        }

        return user;
    }
}
