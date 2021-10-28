package main.controllers.fragments;

import com.google.gson.JsonParser;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.classes.Comment;
import main.classes.properties.Property;
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
    private TableColumn<Property, String> ar_col_ownerId;

    @FXML
    private TableColumn<Property, Boolean> ar_col_isActive;

    @FXML
    private TableColumn<Property, ArrayList<Comment>> ar_col_comments;

    @FXML
    private TableColumn<Property, ArrayList<FacilityType>> ar_col_facilityTypes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyDataModel propertyData = new PropertyDataModel();
        ObservableList<Property> properties = FXCollections.observableArrayList(propertyData.getPropertiesData());
//        boolean isActive =

        ar_table.getColumns().clear();
        ar_col_propertyID.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyId"));
//        ar_col_propertyID.setCellValueFactory(c -> {
//            StringProperty var = new SimpleStringProperty((String) properties.get(0).getPropertyId());
//            return var;
//        });

        ar_col_propertyType.setCellValueFactory(new PropertyValueFactory<Property, PropertyType>("propertyType"));
        ar_col_ownerId.setCellValueFactory(new PropertyValueFactory<Property, String>("ownerId"));
        ar_col_isActive.setCellValueFactory(new PropertyValueFactory<Property, Boolean>("isActive"));
        ar_col_facilityTypes.setCellValueFactory(new PropertyValueFactory<Property, ArrayList<FacilityType>>("facilityTypes"));
        ar_col_comments.setCellValueFactory(new PropertyValueFactory<Property, ArrayList<Comment>>("comment"));

        for (Property property:properties) {

//            System.out.println("Test:"+ property);
            ar_table.getItems().add(property);
        }


        ar_table.getColumns().addAll(ar_col_propertyID, ar_col_propertyType, ar_col_ownerId, ar_col_isActive, ar_col_facilityTypes, ar_col_comments);
    }
}
