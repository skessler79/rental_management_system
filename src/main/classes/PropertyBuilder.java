package main.classes;

import main.enums.PropertyType;

import java.util.Date;
import java.util.UUID;

// This class is used for builder design pattern (reference to https://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java/222295#222295)
public class PropertyBuilder {
    private String propertyId = UUID.randomUUID().toString();
    private Date createdAt = new Date();
    private String ownerId = null;
    private String agentId = null;
    private PropertyType propertyType = null;
    private String name = null;
    private String address = null;
    private String size = null;
    private String description = null;
    private String project = null;
    private String state = null;
    private double rentalFee = Double.NaN;
    private boolean active = false;

    public PropertyBuilder(String ownerId, PropertyType propertyType, String name, String project){
        this.ownerId = ownerId;
        this.propertyType = propertyType;
        this.name = name;
        this.project = project;
    }

    public Property buildProperty(){
        return new Property (
                propertyId,
                ownerId,
                agentId,
                propertyType,
                name,
                address,
                size,
                description,
                project,
                state,
                rentalFee,
                createdAt,
                active
                );
    }

    public PropertyBuilder agentId(String agentId){
        this.agentId = agentId;
        return this;
    }

    public PropertyBuilder address(String address){
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

    public PropertyBuilder state(String state){
        this.state = state;
        return this;
    }

    public PropertyBuilder rentalFee(double rentalFee){
        this.rentalFee = rentalFee;
        return this;
    }
    public PropertyBuilder active(boolean active){
        this.active = active;
        return this;
    }


}