package main.models;

import main.classes.users.User;

public class UserNavbarModel
{
    protected User user;

    public UserNavbarModel()
    {
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
