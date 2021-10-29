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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
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
    private ChoiceBox<PropertyType> ar_choiceBox;
    // List of enums from PropertyType
    ObservableList<PropertyType> ar_choiceBox_list = FXCollections.observableArrayList(PropertyType.values());

    @FXML
    private JFXTextField ar_textField;

    @FXML
    private JFXRadioButton ar_radio_active;

    @FXML
    private JFXRadioButton ar_radio_inactive;

    @FXML
    private JFXButton ar_btn_facilities;

    @FXML
    private JFXButton ar_btn_project;

    @FXML
    private JFXButton ar_btn_del;

    @FXML
    private JFXButton ar_btn_show;

    private PropertyDataModel propertyDataModel;

    ObservableList<Property> properties;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ar_choiceBox.setItems(ar_choiceBox_list);
        ar_choiceBox.setValue(PropertyType.CONDO);
        getTable(CurrentSession.propertyDataModel.getPropertiesData());

// view properties according to propertType (choice box), owner (textfield), active/inactive (radio button)
// display enum values of propertyTypes and facilityTypes
        ar_btn_del.setOnAction(e -> deleteButtonClicked());
        ar_btn_show.setOnAction(e -> filter());
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

    // Pops up new window that displays properties according to user's criteria
    public void filter() {
//        getTable(CurrentSession.propertyDataModel.getPropertiesData());

       // getTable(CurrentSession.propertyDataModel.getPropertyByOwner());

    }


    // Returns the whole table
    public void getTable(ArrayList<Property> propertiesList) {
        ar_table.getItems().clear();
        propertyDataModel = new PropertyDataModel();
        properties = FXCollections.observableArrayList(propertiesList);
       // properties = FXCollections.observableArrayList(propertyDataModel.getPropertiesData());

        ar_table.getColumns().clear();
        ar_col_propertyID.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyId"));
        ar_col_propertyType.setCellValueFactory(new PropertyValueFactory<Property, PropertyType>("propertyType"));
        ar_col_isActive.setCellValueFactory(new PropertyValueFactory<Property, Boolean>("isActive"));
        ar_col_facilityTypes.setCellValueFactory(new PropertyValueFactory<Property, ArrayList<FacilityType>>("facilityTypes"));
        ar_col_comments.setCellValueFactory(new PropertyValueFactory<Property, Integer>("commentCount"));

        ar_col_ownerName.setCellValueFactory(c -> {
            StringProperty var = new SimpleStringProperty((String) c.getValue().getOwner().getUsername());
            return var;
        });

        for (Property property:properties) {
            ar_table.getItems().add(property);
        }
        ar_table.getColumns().addAll(ar_col_propertyID, ar_col_propertyType, ar_col_ownerName, ar_col_isActive, ar_col_facilityTypes, ar_col_comments);
    }
}
