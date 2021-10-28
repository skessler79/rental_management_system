package main.classes.properties;

import main.classes.Address;
import main.classes.Comment;
import main.classes.users.Owner;
import main.classes.users.Regular;
import main.classes.users.User;
import main.enums.FacilityType;
import main.enums.PropertyType;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

// This class is used for builder design pattern (reference to https://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java/222295#222295)
public class PropertyBuilder {

    //required fields
    private String propertyId = UUID.randomUUID().toString();
    private String ownerId = null;
    private PropertyType propertyType = null;
    private String name = null;
    private String project = null;
    private Date createdAt = new Date();
    private ArrayList<Regular> tenant = new ArrayList<>();
    private ArrayList<Comment> comment = new ArrayList<>();
    private int commentCount = 0;

    //optional fields
    private String agentId = null;
    private Address address = null;
    private String size = null;
    private String description = null;
    private double rentalFee = -1;
    private double rentalRate = -1;
    private boolean isActive = false;
    private ArrayList<FacilityType> facilityTypes = new ArrayList<>();
    private String roomInfo = null;
    private int bathRoomCount = -1;

    public PropertyBuilder(User owner, PropertyType propertyType, String name, String project){
        this.ownerId = owner.getId();
        this.propertyType = propertyType;
        this.name = name;
        this.project = project;
    }

    public Property buildProperty(){
        return new Property(
                propertyId,
                ownerId,
                agentId,
                propertyType,
                name,
                address,
                size,
                description,
                project,
                rentalFee,
                rentalRate,
                createdAt,
                facilityTypes,
                isActive,
                tenant,
                roomInfo,
                bathRoomCount,
                comment,
                commentCount);
    }

    public PropertyBuilder agentId(String agentId){
        this.agentId = agentId;
        return this;
    }

    public PropertyBuilder address(Address address){
        this.address = address;
        return this;
    }

    public PropertyBuilder size(String size){
        this.size = size;
        return this;
    }

    public PropertyBuilder description(String description){
        this.description = description;
        return this;
    }

    public PropertyBuilder rentalFee(double rentalFee){
        this.rentalFee = rentalFee;
        return this;
    }

    public PropertyBuilder rentalRate(double rentalRate){
        this.rentalRate = rentalRate;
        return this;
    }

    public PropertyBuilder isActive(boolean isActive){
        this.isActive = isActive;
        return this;
    }

    public PropertyBuilder facilityTypes(ArrayList<FacilityType> facilityTypes) {
        this.facilityTypes = facilityTypes;
        return this;
    }

    public PropertyBuilder roomInfo(String roomInfo){
        this.roomInfo = roomInfo;
        return this;
    }

    public PropertyBuilder bathRoomCount(int bathRoomCount){
        this.bathRoomCount = bathRoomCount;
        return this;
    }
}
