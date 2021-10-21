package main.classes;

import java.util.UUID;

// This class is used for builder design pattern (reference to https://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java/222295#222295)
public class PropertyBuilder {
    private String propertyId = UUID.randomUUID().toString();
    private String ownerId = "";
    private String agentId = "";
    private String type = "";
    private String name = "";
    private String address = "";
    private String size = "";
    private String description = "";
    private String project = "";
    private String state = "";
    private double rentalFee = Double.NaN;

    public PropertyBuilder(String ownerId, String type, String name, String project){
        this.ownerId = ownerId;
        this.type = type;
        this.name = name;
        this.project = project;
    }

    public Property buildProperty(){
        return new Property (propertyId, ownerId, agentId, type, name, address, size, description, project, state, rentalFee);
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


}