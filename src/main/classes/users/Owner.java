package main.classes.users;

import main.classes.properties.Property;
import main.enums.UserType;

import java.util.ArrayList;

public class Owner extends User{
    private ArrayList<String> propertyList;
    public Owner(String id, String firstName, String lastName, String username, String email, String password, String address,  ArrayList<String> propertyList) {
        super(id, UserType.OWNER, firstName, lastName, username, email, password, address);
        this.propertyList = propertyList;
    }


    public void addPropertyList(Property property) {
        this.propertyList.add(property.getPropertyId());
    }

    public ArrayList getPropertyList() { return propertyList; }
    public void setPropertyList(ArrayList<String> propertyList) {
        this.propertyList = propertyList;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "propertyList=" + propertyList +
                '}';
    }
}
