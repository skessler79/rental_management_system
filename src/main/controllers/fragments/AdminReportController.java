package main.controllers.fragments;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.models.PropertyDataModel;
import main.views.ConfirmBoxView;
import java.net.URL;
import java.util.*;

public class AdminReportController implements Initializable {

    @FXML
    private TableView<Property> ar_table;

    @FXML
    private TableColumn<Property, String> ar_col_propertyID;
    @FXML
    private TableColumn<Property, PropertyType> ar_col_propertyType;
    @FXML
    private TableColumn<Property, String> ar_col_ownerName;
    @FXML
    private TableColumn<Property, Boolean> ar_col_isActive;
    @FXML
    private TableColumn<Property, Integer> ar_col_comments;
    @FXML
    private TableColumn<Property, ArrayList<FacilityType>> ar_col_facilityTypes;
    @FXML
    private TableColumn<Property, String> ar_col_project;

    @FXML
    private ChoiceBox<PropertyType> ar_choiceBox_property;
    // List of enums from PropertyType
    ObservableList<PropertyType> ar_choiceBox_propertyList = FXCollections.observableArrayList(PropertyType.values());
    @FXML
    private ChoiceBox<Boolean> ar_choiceBox_activity;
    @FXML
    private ChoiceBox<FacilityType> ar_choiceBox_facility;
    ObservableList<FacilityType> ar_choiceBox_facilityList = FXCollections.observableArrayList(FacilityType.values());

//    @FXML
//    private JFXButton ar_btn_facilities;
//    @FXML
//    private JFXButton ar_btn_project;
    @FXML
    private JFXButton ar_btn_del;
    @FXML
    private JFXButton ar_btn_filter;
    @FXML
    private JFXButton ar_btn_clear;

    @FXML
    private JFXTextField ar_textField_user;
    @FXML
    private JFXTextField ar_textField_project;

    private PropertyDataModel propertyDataModel;

    ObservableList<Property> properties;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ar_choiceBox_property.setItems(ar_choiceBox_propertyList);
        ar_choiceBox_facility.setItems(ar_choiceBox_facilityList);
        ar_choiceBox_activity.getItems().addAll(Boolean.TRUE, Boolean.FALSE);
        getTable(CurrentSession.propertyDataModel.getPropertiesData());

        // Button for deleting a property
        ar_btn_del.setOnAction(e -> {
            Boolean ans = ConfirmBoxView.display("Delete Property", "Are you sure you want to delete this property?");
            if (ans) {
                deleteButtonClicked();
            }
        });

        // Button for filtering by propertyType, owner username, active/inactive
        ar_btn_filter.setOnAction(e -> {
            ArrayList<Property> list;

            if (ar_textField_user.getText().trim().isEmpty()) {
                if(ar_textField_project.getText().trim().isEmpty()) {
                    list = CurrentSession.propertyDataModel.filterProperty(
                            ar_choiceBox_property.getValue(),
                            null,
                            ar_choiceBox_activity.getValue(),
                            CurrentSession.propertyDataModel.getPropertyByFacilityType(ar_choiceBox_facility.getValue()),
                            null);
                }
                else {
                    list = CurrentSession.propertyDataModel.filterProperty(
                            ar_choiceBox_property.getValue(),
                            null,
                            ar_choiceBox_activity.getValue(),
                            CurrentSession.propertyDataModel.getPropertyByFacilityType(ar_choiceBox_facility.getValue()),
                            ar_textField_project.getText().trim());
                }
                getTable(list);
            }
            else {
                if(ar_textField_project.getText().trim().isEmpty()) {
                    list = CurrentSession.propertyDataModel.filterProperty(
                            ar_choiceBox_property.getValue(),
                            CurrentSession.userDataModel.getUserByUsername(ar_textField_user.getText().trim()),
                            ar_choiceBox_activity.getValue(),
                            CurrentSession.propertyDataModel.getPropertyByFacilityType(ar_choiceBox_facility.getValue()),
                            null);
                }
                else {
                    list = CurrentSession.propertyDataModel.filterProperty(
                            ar_choiceBox_property.getValue(),
                            CurrentSession.userDataModel.getUserByUsername(ar_textField_user.getText().trim()),
                            ar_choiceBox_activity.getValue(),
                            CurrentSession.propertyDataModel.getPropertyByFacilityType(ar_choiceBox_facility.getValue()),
                            ar_textField_project.getText().trim());
                }
                getTable(list);
            }
        });

        // Clears the filters
        ar_btn_clear.setOnAction(e -> {
            getTable(CurrentSession.propertyDataModel.getPropertiesData());
            ar_textField_user.clear();
            ar_textField_project.clear();
            ar_choiceBox_activity.valueProperty().set(null);
            ar_choiceBox_property.valueProperty().set(null);
            ar_choiceBox_facility.valueProperty().set(null);
        });

    }

    // Deletes the selected property in the table
    public void deleteButtonClicked() {
        ObservableList<Property> propertySelected;
        propertySelected = ar_table.getSelectionModel().getSelectedItems();
        String propertyId = propertySelected.get(0).getPropertyId();
        ar_table.getItems().remove(propertySelected);

        System.out.println(propertyId);
        propertyDataModel.deleteProperty(propertyId);
        getTable(CurrentSession.propertyDataModel.getPropertiesData());
    }

    // Returns the whole table
    public void getTable(ArrayList<Property> propertiesList) {
        ar_table.getItems().clear();
        propertyDataModel = new PropertyDataModel();
        properties = FXCollections.observableArrayList(propertiesList);

        ar_table.getColumns().clear();

        ar_col_propertyID.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyId"));
        ar_col_propertyType.setCellValueFactory(new PropertyValueFactory<Property, PropertyType>("propertyType"));
        ar_col_isActive.setCellValueFactory(new PropertyValueFactory<Property, Boolean>("isActive"));
        ar_col_facilityTypes.setCellValueFactory(new PropertyValueFactory<Property, ArrayList<FacilityType>>("facilityTypes"));
        ar_col_comments.setCellValueFactory(new PropertyValueFactory<Property, Integer>("commentCount"));
        ar_col_project.setCellValueFactory(new PropertyValueFactory<Property, String>("project"));

        ar_col_ownerName.setCellValueFactory(c -> {
            StringProperty var = new SimpleStringProperty((String) c.getValue().getOwner().getUsername());
            return var;
        });

        for (Property property:properties) {
            ar_table.getItems().add(property);
        }
        ar_table.getColumns().addAll(ar_col_propertyID, ar_col_project, ar_col_propertyType, ar_col_ownerName, ar_col_isActive, ar_col_facilityTypes, ar_col_comments);
    }
}
