package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.classes.Address;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.classes.users.User;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.views.AlertBoxView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPropertyController implements Initializable
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
    private JFXTextField txtTenant;

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
    private JFXButton btnSave, btnCancel, btnReturn;

    private String name, description, roomInfo;
    private String addressRoad, addressProject, addressCity, addressState, addressCountry;
    private int addressPostcode, bathrooms;
    private PropertyType propertyType;
    private boolean isActive;
    private ArrayList<FacilityType> facilityTypes;
    private double rentalFee;

    private Property property;
    private User tenant;
    private Address address;
    private Stage window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
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

        btnSave.setOnAction(actionEvent ->
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

            // Tenant
            tenant = CurrentSession.userDataModel.getUserByUsername(txtTenant.getText());

            // Edit current property
            property.setName(name);
            property.setPropertyType(propertyType);
            property.setActive(isActive);
            property.setRentalFee(rentalFee);
            property.setAddress(new Address(addressRoad, addressProject, addressCity, addressState, addressPostcode, addressCountry));
            property.setProject(addressProject);
            property.setDescription(description);
            property.setRoomInfo(roomInfo);
            property.setBathRoomCount(bathrooms);
            property.setFacilityTypes(facilityTypes);
            if(tenant != null)
            {
                CurrentSession.propertyDataModel.addTenant(tenant, property);
            }

            CurrentSession.propertyDataModel.editProperty(property);

            setDetails(property);
            AlertBoxView.display("Edit Property", "Property successfully edited!");
        });

        btnCancel.setOnAction(actionEvent -> setDetails(property));
        btnReturn.setOnAction(actionEvent -> window.close());
    }

    public void setDetails(Property property)
    {
        this.property = property;

        // Setting forms
        // Name
        txtName.setText(property.getName());

        // Type
        propertyType = property.getPropertyType();

        if(propertyType == PropertyType.CONDO)
        {
            radioTypeCondo.setSelected(true);
        }
        else if(propertyType == PropertyType.TOWNHOUSE)
        {
            radioTypeTownhouse.setSelected(true);
        }
        else if(propertyType == PropertyType.SINGLE_STORY)
        {
            radioTypeSingle.setSelected(true);
        }
        else if(propertyType == PropertyType.DOUBLE_STORY)
        {
            radioTypeDouble.setSelected(true);
        }
        else
        {
            radioTypeBungalow.setSelected(true);
        }

        // Status
        isActive = property.getIsActive();

        if(isActive)
        {
            radioStatusActive.setSelected(true);
        }
        else
        {
            radioStatusInactive.setSelected(true);
        }

        // Rental Fee
        txtRentalFee.setText(String.valueOf(property.getRentalFee()));

        // Address
        address = property.getAddress();

        txtAddressRoad.setText(address.getRoadName());
        txtAddressProject.setText(address.getProject());
        txtAddressPostcode.setText(Integer.toString(address.getPostcode()));
        txtAddressCity.setText(address.getCity());
        txtAddressState.setText(address.getState());
        txtAddressCountry.setText(address.getCountry());

        // Description
        txtDescription.setText(property.getDescription());

        // Room Info
        txtRoomInfo.setText(property.getRoomInfo());

        // Bathrooms
        txtBathrooms.setText(Integer.toString(property.getBathRoomCount()));

        // Facilities
        facilityTypes = property.getFacilityTypes();

        if(facilityTypes.contains(FacilityType.SWIMMING_POOL))
        {
            checkFacilityPool.setSelected(true);
        }

        if(facilityTypes.contains(FacilityType.WIFI))
        {
            checkFacilityWifi.setSelected(true);
        }

        if(facilityTypes.contains(FacilityType.TV))
        {
            checkFacilityTv.setSelected(true);
        }

        if(facilityTypes.contains(FacilityType.FRIDGE))
        {
            checkFacilityFridge.setSelected(true);
        }

        if(facilityTypes.contains(FacilityType.AIRCOND))
        {
            checkFacilityAircond.setSelected(true);
        }

        if(facilityTypes.contains(FacilityType.WATER_HEATER))
        {
            checkFacilityHeater.setSelected(true);
        }

        // Tenant
        try
        {
            txtTenant.setText(property.getTenant().getUsername());
        }
        catch (NullPointerException e)
        {
            txtTenant.setText("");
        }
    }

    public void setWindow(Stage window)
    {
        this.window = window;
    }
}

