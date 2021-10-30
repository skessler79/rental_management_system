package main.classes.properties;

import main.classes.Address;
import main.classes.Comment;
import main.classes.users.Agent;
import main.classes.users.Owner;
import main.classes.users.Regular;
import main.classes.users.User;
import main.enums.FacilityType;
import main.enums.PropertyType;

import java.util.ArrayList;
import java.util.Date;

public class Property implements Comparable<Property>{
    private String propertyId;
    private User owner;
    private User agent;
    private PropertyType propertyType;
    private String name;
    private Address address;
    private String size;
    private String description;
    private String project;
    private double rentalFee;
    private double rentalRate;
    private Date createdAt;
    private ArrayList<FacilityType> facilityTypes;
    private boolean isActive;
    private ArrayList<User> tenant;
    private String roomInfo;
    private int bathRoomCount;
    private ArrayList<Comment> comment;
    private int commentCount;

    public Property(String propertyId, User owner, User agent, PropertyType propertyType, String name, Address address, String size, String description, String project, double rentalFee, double rentalRate, Date createdAt, ArrayList<FacilityType> facilityTypes, boolean isActive, ArrayList<User> tenant, String roomInfo, int bathRoomCount, ArrayList<Comment> comment, int commentCount)  {
        this.propertyId = propertyId;
        this.owner = owner;
        this.agent = agent;
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
        this.comment = comment;
        this.commentCount = commentCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setComment(ArrayList<Comment> comment) {
        this.comment = comment;
    }

    public void addTenant(User regular){
        this.tenant.add(regular);
    }

    public ArrayList<Comment> getComment() { return comment; }

    public void addComment(Comment comment){
        this.comment.add(comment);
    }

    public String getPropertyId() {
        return propertyId;
    }

    public User getOwner() {
        return owner;
    }

    public User getAgent() {
        return agent;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
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

    public Double getRentalFee() {
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

    public boolean getIsActive() {
        return isActive;
    }

    public ArrayList<User> getTenant() {
        return tenant;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public Integer getBathRoomCount() {
        return bathRoomCount;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public void setOwnerId(Owner owner) {
        this.owner = owner;
    }

    public void setAgentId(Agent agent) {
        this.agent = agent;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
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

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setFacilityTypes(ArrayList<FacilityType> facilityTypes) {
        this.facilityTypes = facilityTypes;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setTenant(ArrayList<User> tenant) {
        this.tenant = tenant;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public void setBathRoomCount(int bathRoomCount) {
        this.bathRoomCount = bathRoomCount;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId='" + propertyId + '\'' +
                ", owner='" + owner + '\'' +
                ", agent='" + agent + '\'' +
                ", propertyType=" + propertyType +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", project='" + project + '\'' +
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
