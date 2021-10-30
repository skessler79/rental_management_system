package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import main.classes.Address;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.classes.properties.PropertyBuilder;
import main.classes.users.Owner;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.views.AlertBoxView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPropertyController implements Initializable
{
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtRentalFee;

    @FXML
    private JFXTextField txtAddressRoad;

    @FXML
    private JFXTextField txtAddressProject;

    @FXML
    private JFXTextField txtAddressCity;

    @FXML
    private JFXTextField txtAddressState;

    @FXML
    private JFXTextField txtAddressCountry;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtRoomInfo;

    @FXML
    private JFXTextField txtBathrooms;

    @FXML
    private JFXTextField txtAddressPostcode;

    @FXML
    private JFXRadioButton radioTypeCondo;

    @FXML
    private JFXRadioButton radioTypeTownhouse;

    @FXML
    private JFXRadioButton radioTypeSingle;

    @FXML
    private JFXRadioButton radioTypeDouble;

    @FXML
    private JFXRadioButton radioTypeBungalow;

    @FXML
    private JFXRadioButton radioStatusActive;

    @FXML
    private JFXRadioButton radioStatusInactive;

    @FXML
    private JFXCheckBox checkFacilityPool;

    @FXML
    private JFXCheckBox checkFacilityWifi;

    @FXML
    private JFXCheckBox checkFacilityTv;

    @FXML
    private JFXCheckBox checkFacilityFridge;

    @FXML
    private JFXCheckBox checkFacilityAircond;

    @FXML
    private JFXCheckBox checkFacilityHeater;

    @FXML
    private JFXButton btnSubmit;

    private String name, description, roomInfo;
    private String addressRoad, addressProject, addressCity, addressState, addressCountry;
    private int addressPostcode, bathrooms;
    private PropertyType propertyType;
    private boolean isActive;
    private ArrayList<FacilityType> facilityTypes;
    private double rentalFee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // TODO : Handle Form

        // Grouping Type
        ToggleGroup typeGroup = new ToggleGroup();

        radioTypeCondo.setToggleGroup(typeGroup);
        radioTypeTownhouse.setToggleGroup(typeGroup);
        radioTypeSingle.setToggleGroup(typeGroup);
        radioTypeDouble.setToggleGroup(typeGroup);
        radioTypeBungalow.setToggleGroup(typeGroup);
        radioTypeCondo.setSelected(true);

        // Grouping Status
        ToggleGroup statusGroup = new ToggleGroup();

        radioStatusActive.setToggleGroup(statusGroup);
        radioStatusInactive.setToggleGroup(statusGroup);
        radioStatusActive.setSelected(true);

        facilityTypes = new ArrayList<>();

        btnSubmit.setOnAction(actionEvent ->
        {
            // Name
            name = txtName.getText();
            txtName.clear();

            // Type
            String typeStr = ((JFXRadioButton)typeGroup.getSelectedToggle()).getText();

            if(typeStr.equals("Condominium"))
            {
                propertyType = PropertyType.CONDO;
            }
            else if(typeStr.equals("Townhouse"))
            {
                propertyType = PropertyType.TOWNHOUSE;
            }
            else if(typeStr.equals("Single Story"))
            {
                propertyType = PropertyType.SINGLE_STORY;
            }
            else if(typeStr.equals("Double Story"))
            {
                propertyType = PropertyType.DOUBLE_STORY;
            }
            else
            {
                propertyType = PropertyType.BUNGALOW;
            }
            radioTypeCondo.setSelected(true);

            // Status
            String statusStr = ((JFXRadioButton)statusGroup.getSelectedToggle()).getText();

            if(statusStr.equals("Active"))
            {
                isActive = true;
            }
            else
            {
                isActive = false;
            }
            radioStatusActive.setSelected(true);

            // Rental Fee
            rentalFee = Double.valueOf(txtRentalFee.getText());
            txtRentalFee.clear();

            // Address
            addressRoad = txtAddressRoad.getText();
            addressProject = txtAddressProject.getText();
            addressPostcode = Integer.parseInt(txtAddressPostcode.getText());
            addressCity = txtAddressCity.getText();
            addressState = txtAddressState.getText();
            addressCountry = txtAddressCountry.getText();

            txtAddressRoad.clear();
            txtAddressProject.clear();
            txtAddressPostcode.clear();
            txtAddressCity.clear();
            txtAddressState.clear();
            txtAddressCountry.clear();

            // Description
            description = txtDescription.getText();
            txtDescription.clear();

            // Room Info
            roomInfo = txtRoomInfo.getText();
            txtRoomInfo.clear();

            // Bathrooms
            bathrooms = Integer.parseInt(txtBathrooms.getText());
            txtBathrooms.clear();

            // Facilities
            facilityTypes.clear();
            if(checkFacilityPool.isSelected())
            {
                facilityTypes.add(FacilityType.SWIMMING_POOL);
                checkFacilityPool.setSelected(false);
            }

            if(checkFacilityWifi.isSelected())
            {
                facilityTypes.add(FacilityType.WIFI);
                checkFacilityWifi.setSelected(false);
            }

            if(checkFacilityTv.isSelected())
            {
                facilityTypes.add(FacilityType.TV);
                checkFacilityTv.setSelected(false);
            }

            if(checkFacilityFridge.isSelected())
            {
                facilityTypes.add(FacilityType.FRIDGE);
                checkFacilityFridge.setSelected(false);
            }

            if(checkFacilityAircond.isSelected())
            {
                facilityTypes.add(FacilityType.AIRCOND);
                checkFacilityAircond.setSelected(false);
            }

            if(checkFacilityHeater.isSelected())
            {
                facilityTypes.add(FacilityType.WATER_HEATER);
                checkFacilityHeater.setSelected(false);
            }

            // Create new property
            Property newProperty = new PropertyBuilder(CurrentSession.currentUser, propertyType, name, addressProject)
                    .address(new Address(addressRoad, addressProject, addressCity, addressState, addressPostcode, addressCountry))
                    .isActive(isActive)
                    .rentalFee(rentalFee)
                    .description(description)
                    .roomInfo(roomInfo)
                    .bathRoomCount(bathrooms)
                    .facilityTypes(facilityTypes)
                    .buildProperty();

            CurrentSession.propertyDataModel.addProperty((Owner) CurrentSession.currentUser, newProperty);

            AlertBoxView.display("Add Property", "Property successfully added!");
        });
    }
}

