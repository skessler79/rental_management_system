package main.controllers.fragments;

import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.controllers.cells.PropertyCell;
import main.enums.FacilityType;
import main.enums.PropertyType;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO : Search by Facilities
public class PropertiesController extends FragmentController implements Initializable
{
    @FXML
    TextField txtPropertyName, txtProjectName;

    @FXML
    ComboBox comboType;

    @FXML
    JFXCheckBox checkPool, checkWifi, checkTv, checkFridge, checkAircond, checkHeater;

    @FXML
    Button btnSearch;

    @FXML
    AnchorPane anchorPropertyList;

    private ArrayList<FacilityType> facilityTypes;
    private ArrayList<Property> propertyArrayList;
    private ListView<Property> listView;
    private String propertyName, projectName, propertyTypeStr;
    private PropertyType propertyType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        facilityTypes = new ArrayList<>();

        btnSearch.setOnAction(actionEvent ->
        {
            handleSearch();
        });

        // Properly capitalize PropertyType enum and changes underscore to space
        List<String> propertyTypeList = Stream.of(PropertyType.values()).map(PropertyType::name).collect(Collectors.toList());
        for(int i = 0; i < propertyTypeList.size(); ++i)
        {
            String str = propertyTypeList.get(i).substring(0, 1).toUpperCase() + propertyTypeList.get(i).substring(1).toLowerCase();
            str = str.replace('_', ' ');
            propertyTypeList.set(i, str);
        }

        // Setting house type selection
        comboType.getItems().add("Any");
        comboType.getItems().addAll(propertyTypeList);
        comboType.setValue("Any");

        // Get list of all active properties
        ObservableList<Property> data = FXCollections.observableArrayList();
        propertyArrayList = CurrentSession.propertyDataModel.getPropertyByActive(true);
        Collections.sort(propertyArrayList);
        data.addAll(propertyArrayList);
        displayList(data);
    }

    private void displayList(ObservableList<Property> data)
    {
        // Create dynamic ListView of fragments
        listView = new ListView<>(data);
        listView.setMinWidth(690);

        listView.setCellFactory(new Callback<ListView<Property>, ListCell<Property>>()
        {
            @Override
            public ListCell<Property> call(ListView<Property> propertyListView)
            {
                return new PropertyCell(false);
            }
        });

        anchorPropertyList.getChildren().add(listView);
    }

    private void handleSearch()
    {
        // Property Name
        propertyName = txtPropertyName.getText();

        // Project Name
        projectName = txtProjectName.getText();

        // Property Type
        propertyTypeStr = (String) comboType.getValue();
        if(propertyTypeStr.equals("Condo"))
        {
            propertyType = PropertyType.CONDO;
        }
        else if(propertyTypeStr.equals("Townhouse"))
        {
            propertyType = PropertyType.TOWNHOUSE;
        }
        else if(propertyTypeStr.equals("Single story"))
        {
            propertyType = PropertyType.SINGLE_STORY;
        }
        else if(propertyTypeStr.equals("Double story"))
        {
            propertyType = PropertyType.DOUBLE_STORY;
        }
        else if(propertyTypeStr.equals("Bungalow"))
        {
            propertyType = PropertyType.BUNGALOW;
        }

        // Facilities
        facilityTypes.clear();
        if(checkPool.isSelected())
        {
            facilityTypes.add(FacilityType.SWIMMING_POOL);
        }

        if(checkWifi.isSelected())
        {
            facilityTypes.add(FacilityType.WIFI);
        }

        if(checkTv.isSelected())
        {
            facilityTypes.add(FacilityType.TV);
        }

        if(checkFridge.isSelected())
        {
            facilityTypes.add(FacilityType.FRIDGE);
        }

        if(checkAircond.isSelected())
        {
            facilityTypes.add(FacilityType.AIRCOND);
        }

        if(checkHeater.isSelected())
        {
            facilityTypes.add(FacilityType.WATER_HEATER);
        }

        ObservableList<Property> data = FXCollections.observableArrayList();
        propertyArrayList = CurrentSession.propertyDataModel.filterProperty(propertyType, facilityTypes, projectName, propertyName);
        Collections.sort(propertyArrayList);
        data.addAll(propertyArrayList);
        displayList(data);
    }
}
