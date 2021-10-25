package main.classes;

public class Address {
    private String roadName;
    private String project;
    private String state;
    private String country;
    private String city;
    private int postcode;

    public Address(String roadName, String project, String city, String state, int postcode, String country) {
        this.roadName = roadName;
        this.project = project;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
        this.city = city;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getProject() {
        return project;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return roadName + ", " + project + ", " + postcode + ", " + city + ", " + state + " " + country;
    }
}
