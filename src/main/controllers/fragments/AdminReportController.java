package main.controllers.fragments;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.classes.Comment;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.classes.users.Owner;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.models.LoginModel;
import main.models.PropertyDataModel;
import main.views.ConfirmBoxView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Stream;

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
    ObservableList<PropertyType> ar_choiceBox_list = FXCollections.observableArrayList(PropertyType.values());
    @FXML
    private ChoiceBox<Boolean> ar_choiceBox_activity;
    @FXML
    private ChoiceBox<String> ar_choiceBox_facility;

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
        ar_choiceBox_property.setItems(ar_choiceBox_list);
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
                list = CurrentSession.propertyDataModel.filterProperty(ar_choiceBox_property.getValue(), null, ar_choiceBox_activity.getValue());
                getTable(list);
            }
            else {
                list = CurrentSession.propertyDataModel.filterProperty(ar_choiceBox_property.getValue(), CurrentSession.userDataModel.getUserByUsername(ar_textField_user.getText().trim()), ar_choiceBox_activity.getValue());
                getTable(list);
            }
        });

        // Clears the filters
        ar_btn_clear.setOnAction(e -> {
            getTable(CurrentSession.propertyDataModel.getPropertiesData());
            ar_textField_user.clear();
            ar_choiceBox_activity.valueProperty().set(null);
            ar_choiceBox_property.valueProperty().set(null);
        });



        // For displaying facilityTypes and Projects into a new window
//        Stage stage = new Stage();
//        stage.setWidth(250);
//
//        ListView listView = new ListView();
//
//        // Shows a list of Facilities in a new window
//        ar_btn_facilities.setOnAction(e -> {
//            List<FacilityType> list = Arrays.asList(FacilityType.values());
//
//            for (FacilityType facilityType: list) {
//                listView.getItems().add(facilityType);
//            }
//
//            VBox vBox = new VBox();
//            vBox.getChildren().add(listView);
//            final Scene scene = new Scene(vBox);
//            stage.setTitle("Facilities");
//            stage.setScene(scene);
//            stage.show();
//        });
//
//        ar_btn_project.setOnAction(e -> {
//
//        });
    }

    // Deletes the selected property in the table
    public void deleteButtonClicked() {
        ObservableList<Property> propertySelected, allProperty;
        allProperty = ar_table.getItems();
        propertySelected = ar_table.getSelectionModel().getSelectedItems();
        String propertyId = propertySelected.get(0).getPropertyId();

        propertySelected.forEach(allProperty::remove);

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
