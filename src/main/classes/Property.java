package main.classes;

import java.util.Date;

public class Property {
    private String propertyId;
    private String ownerId;
    private String agentId;
    private String type;
    private String name;
    private String address;
    private String size;
    private String description;
    private String project;
    private String state;
    private Date createdAt;
    private double rentalFee;
    private boolean active;

    public Property(String propertyId, String ownerId, String agentId, String type, String name, String address, String size, String description, String project, String state, double rentalFee, Date createdAt, boolean active) {
        this.propertyId = propertyId;
        this.ownerId = ownerId;
        this.agentId = agentId;
        this.type = type;
        this.name = name;
        this.address = address;
        this.size = size;
        this.description = description;
        this.project = project;
        this.state = state;
        this.rentalFee = rentalFee;
        this.createdAt = createdAt;
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }

    public String getProject() {
        return project;
    }

    public String getState() {
        return state;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId='" + propertyId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", project='" + project + '\'' +
                ", state='" + state + '\'' +
                ", rentalFee=" + rentalFee +
                '}';
    }
}
