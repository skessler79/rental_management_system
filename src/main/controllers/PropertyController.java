package main.controllers;

import javafx.fxml.Initializable;
import main.classes.properties.Property;
import main.models.PropertyDataModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PropertyController implements Initializable {
    private PropertyDataModel propertyData = new PropertyDataModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ArrayList<Property> getProperties(){
        return propertyData.getPropertiesData();
    }

    public void setData(Property property){
        propertyData.registerProperty(property);
    }

    public void getPropertiesData(){
        //TODO: change to return instead
        System.out.println(propertyData.getPropertiesData());
    }


}
