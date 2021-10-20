package main.controllers;

import javafx.fxml.Initializable;
import main.models.PropertyDataModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class PropertyController implements Initializable {
    private JSONArray data;
    private PropertyDataModel propertyData = new PropertyDataModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = propertyData.getPropertiesData();

    }

    public JSONArray getProperties(){
        return data;
    }

    public void setData(String ownerId, String type, String name, String address, String size, String description,  String project, String state, double rentalFee){
        propertyData.inputPropertyData(ownerId, type, name, address, size, description, project, state, rentalFee);
    }


}
