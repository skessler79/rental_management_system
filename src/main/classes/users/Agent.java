package main.classes.users;

import main.classes.properties.Property;
import main.enums.UserType;

import java.util.ArrayList;

public class Agent extends User{
    private ArrayList<String> propertyList;
    public Agent(String id, String firstName, String lastName, String username, String email, String password, String address, ArrayList<String> propertyList) {
        super(id, UserType.AGENT, firstName, lastName, username, email, password, address);
        this.propertyList = propertyList;
    }

    public void addPropertyList(Property property) {
        this.propertyList.add(property.getPropertyId());
    }
    public void setPropertyList(ArrayList<String> propertyList) {
        this.propertyList = propertyList;
    }

    public ArrayList getPropertyList() { return propertyList; }
}
