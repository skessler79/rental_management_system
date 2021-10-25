package main.classes.properties;

import main.classes.users.Regular;
import main.enums.FacilityType;
import main.enums.PropertyType;

import java.util.ArrayList;
import java.util.Date;

public class Property implements Comparable<Property>{
    private String propertyId;
    private String ownerId;
    private String agentId;
    private PropertyType propertyType;
    private String name;
    private String address;
    private String size;
    private String description;
    private String project;
    private double rentalFee;
    private double rentalRate;
    private Date createdAt;
    private ArrayList<FacilityType> facilityTypes;
    private boolean isActive;
    private ArrayList<Regular> tenant;
    private String roomInfo;
    private int bathRoomCount;

    public Property(String propertyId, String ownerId, String agentId, PropertyType propertyType, String name, String address, String size, String description, String project, double rentalFee, double rentalRate, Date createdAt, ArrayList<FacilityType> facilityTypes, boolean isActive, ArrayList<Regular> tenant, String roomInfo, int bathRoomCount) {
        this.propertyId = propertyId;
        this.ownerId = ownerId;
        this.agentId = agentId;
        this.propertyType = propertyType;
        this.name = name;
        this.address = address;
        this.size = size;
        this.description = description;
        this.project = project;
        this.rentalFee = rentalFee;
        this.rentalRate = rentalRate;
        this.createdAt = createdAt;
        this.facilityTypes = facilityTypes;
        this.isActive = isActive;
        this.tenant = tenant;
        this.roomInfo = roomInfo;
        this.bathRoomCount = bathRoomCount;
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

    public PropertyType getPropertyType() {
        return propertyType;
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

    public double getRentalRate() {
        return rentalRate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ArrayList<FacilityType> getFacilityTypes() {
        return facilityTypes;
    }

    public boolean isActive() {
        return isActive;
    }

    public ArrayList<Regular> getTenant() {
        return tenant;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public int getBathRoomCount() {
        return bathRoomCount;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId='" + propertyId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", propertyType=" + propertyType +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", project='" + project + '\'' +
                ", state='" + state + '\'' +
                ", rentalFee=" + rentalFee +
                ", rentalRate=" + rentalRate +
                ", createdAt=" + createdAt +
                ", facilityTypes=" + facilityTypes +
                ", isActive=" + isActive +
                ", tenant=" + tenant +
                ", roomInfo='" + roomInfo + '\'' +
                ", bathRoomCount=" + bathRoomCount +
                '}';
    }

    @Override
    public int compareTo(Property property) {
        return (int) (this.rentalFee - property.getRentalFee());
    }
}
