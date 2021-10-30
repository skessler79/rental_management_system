package main.classes;

import main.classes.users.User;
import main.models.PropertyDataModel;
import main.models.UserDataModel;

public class CurrentSession
{
    public static User currentUser;
    public static UserDataModel userDataModel = new UserDataModel();
    public static PropertyDataModel propertyDataModel = new PropertyDataModel();
}