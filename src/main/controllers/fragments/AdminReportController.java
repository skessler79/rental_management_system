package main.controllers.fragments;

import com.google.gson.JsonParser;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyDataModel propertyData = new PropertyDataModel();
        ObservableList<Property> properties = FXCollections.observableArrayList(propertyData.getPropertiesData());

        ar_table.getColumns().clear();
        ar_col_propertyID.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyId"));
        ar_col_propertyType.setCellValueFactory(new PropertyValueFactory<Property, PropertyType>("propertyType"));
        //ar_col_ownerName.setCellValueFactory(new PropertyValueFactory<Owner, String>("username"));
        ar_col_isActive.setCellValueFactory(new PropertyValueFactory<Property, Boolean>("isActive"));
        ar_col_facilityTypes.setCellValueFactory(new PropertyValueFactory<Property, ArrayList<FacilityType>>("facilityTypes"));
        ar_col_comments.setCellValueFactory(new PropertyValueFactory<Property, Integer>("commentCount"));

        ar_col_ownerName.setCellValueFactory(c -> {
            StringProperty var = new SimpleStringProperty((String) c.getValue().getOwner().getUsername());
            return var;
        });

//        TableColumn<RelationManager, String> firstNameColumn = new TableColumn<>("First Name");
//        firstNameColumn.setCellValueFactory(new Callback<CellDataFeatures<RelationManager,String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<RelationManager, String> data) {
//                return data.getValue() // the RelationManager
//                        .getPatient().firstNameProperty();
//            }
//        });

        for (Property property:properties) {
//            System.out.println("Test:"+ property);
            ar_table.getItems().add(property);
        }
// add/delete properties, sort properties according to propertType (choice box), owner (textfield), active/inactive (radio button)
// display enum values of propertyTypes and facilityTypes

        ar_table.getColumns().addAll(ar_col_propertyID, ar_col_propertyType, ar_col_ownerName, ar_col_isActive, ar_col_facilityTypes, ar_col_comments);
    }

//    public String deleteButtonClicked() {
//
//        return propertyID;
//    }
}
