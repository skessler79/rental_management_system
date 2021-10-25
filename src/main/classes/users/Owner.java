package main.classes.users;

import main.classes.properties.Property;
import main.enums.UserType;

import java.util.ArrayList;

public class Owner extends User{
    private String ownerDetails;
    private ArrayList<String> propertyList;
    public Owner(String id, String firstName, String lastName, String username, String email, String password, String address, String ownerDetails, ArrayList<String> propertyList) {
        super(id, UserType.OWNER, firstName, lastName, username, email, password, address);
        this.ownerDetails = ownerDetails;
        this.propertyList = propertyList;
    }

    public String getOwnerDetails() {
        return ownerDetails;
    }

    public void setOwnerDetails(String ownerDetails) {
        this.ownerDetails = ownerDetails;
    }

    public void addPropertyList(Property property) {
        this.propertyList.add(property.getPropertyId());
    }
}
