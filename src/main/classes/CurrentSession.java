package main.classes;

import javafx.stage.Stage;
import main.classes.users.User;
import main.models.PropertyDataModel;
import main.models.UserDataModel;

//this is a static class based on the singleton design pattern (only 1 instance is need always)
public class CurrentSession
{
    public static User currentUser;
    public static UserDataModel userDataModel = new UserDataModel();
    public static PropertyDataModel propertyDataModel = new PropertyDataModel();
}