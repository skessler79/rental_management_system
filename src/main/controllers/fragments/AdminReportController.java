package main.controllers.fragments;

import com.google.gson.JsonParser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ar_choiceBox.setItems(ar_choiceBox_list);
        ar_choiceBox.setValue(PropertyType.CONDO);
        getTable();

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
        getTable();
    }

    // Pops up new window that displays properties according to user's criteria
    public void filter() {


        TableView <Property> tableView = new TableView();
//        TableColumn<Property, String> col_propertyID = new TableColumn<>();
        TableColumn <Property, PropertyType> col_propertyType = new TableColumn<>("PropertyType");
        col_propertyType.setCellValueFactory(c -> {
            ObservableValue<PropertyType> propertYType = new
            // Fxcollection shit google
            return ar_choiceBox.getValue();
        });
        tableView.getColumns().add(col_propertyType);

        System.out.println(ar_choiceBox.getValue());

        Stage stage = new Stage();
        stage.setTitle("Filtered Properties");

        HBox hBox = new HBox();
        hBox.getChildren().add(tableView);
        Scene scene = new Scene(hBox, 250,250);
        stage.setScene(scene);
        stage.show();
//        TableColumn<Property, String> col_ownerName = new TableColumn<>();
//        TableColumn<Property, Boolean> col_isActive = new TableColumn<>();
//        TableColumn<Property, Integer> col_comments = new TableColumn<>();
//        TableColumn<Property, ArrayList<FacilityType>> col_facilityTypes = new TableColumn<>();


    }


    // Returns the whole table
    public void getTable() {
        ar_table.getItems().clear();
        propertyDataModel = new PropertyDataModel();
        ObservableList<Property> properties = FXCollections.observableArrayList(propertyDataModel.getPropertiesData());

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
