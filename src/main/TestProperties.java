package main;

import main.classes.Address;
import main.classes.properties.Property;
import main.classes.properties.PropertyBuilder;
import main.classes.users.User;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.models.UserDataModel;

import java.util.ArrayList;
import java.util.Arrays;

public class TestProperties {
    private UserDataModel userDataModel;
    private ArrayList<Property> propertyObjectData;

    public ArrayList<Property> getTestProperties(){
        userDataModel = new UserDataModel();
        User ownerUser1 = userDataModel.loginUser("owner3", "abc123");
        User ownerUser2 = userDataModel.loginUser("owner4", "abc123");

        propertyObjectData = new ArrayList<>(Arrays.asList(
                new PropertyBuilder(ownerUser1, PropertyType.CONDO, "Happy house", "Taman 69")
                        .address(new Address("69, Lorong Six Nine", "Taman 69", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                        .isActive(true)
                        .description("A nice house with a cheap rental beside the greatest uni MMU")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.SWIMMING_POOL, FacilityType.FRIDGE, FacilityType.WIFI)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.BUNGALOW, "Sad house", "Taman 420")
                        .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                        .isActive(true)
                        .description("A bad house with a cheap rental beside the greatest uni MMU")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.BUNGALOW, "Average house", "Taman 789")
                        .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                        .isActive(true)
                        .description("A bad house with a cheap rental beside the greatest uni MMU")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.SWIMMING_POOL, FacilityType.WIFI)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.BUNGALOW, "Average house", "Taman 789")
                        .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                        .isActive(true)
                        .description("A bad house with a cheap rental beside the greatest uni MMU")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.SWIMMING_POOL, FacilityType.WIFI)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser1, PropertyType.BUNGALOW, "Haunted house", "Taman 456")
                        .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                        .isActive(true)
                        .description("A bad house with a cheap rental beside the greatest uni MMU")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.SWIMMING_POOL, FacilityType.TV, FacilityType.AIRCOND)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser2, PropertyType.BUNGALOW, "Temple", "Taman 123")
                        .address(new Address("420, Lorong Weed", "Taman 420", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                        .isActive(true)
                        .description("A bad house with a cheap rental beside the greatest uni MMU")
                        .roomInfo("1 master room and 2 bed room with 1 kitchen and 1 living room")
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.TV, FacilityType.WATER_HEATER, FacilityType.FRIDGE, FacilityType.SWIMMING_POOL)))
                        .buildProperty(),
                new PropertyBuilder(ownerUser2, PropertyType.DOUBLE_STORY, "Temple", "Taman 123")
                        .address(new Address("666, lorong DN", "Taman Malay", "Cyberjaya", "Selangor", 14000, "Malaysia"))
                        .isActive(false)
                        .roomInfo("6 bathrooms, no bed")
                        .facilityTypes(new ArrayList<>(Arrays.asList(FacilityType.AIRCOND, FacilityType.TV, FacilityType.WATER_HEATER, FacilityType.SWIMMING_POOL)))
                        .buildProperty()
        ));

        return propertyObjectData;
    }
}
