package main.models;

import javafx.event.ActionEvent;
import main.classes.CurrentSession;
import main.classes.users.User;

public class LoginModel
{
    public LoginModel()
    {

    }

    // TODO : Return user object
    public User login(String username, String password) throws IllegalArgumentException
    {
        User user;
        try {
            user = CurrentSession.userDataModel.loginUser(username, password);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Login credential invalid");
        }

        return user;
    }
}
