package model;

import java.util.ArrayList;

public class Owner extends User
{
    ArrayList<Property> propertyList;

    public Owner(String username, String fullName)
    {
        super(username, fullName);
        propertyList = new ArrayList<>();
    }
}
