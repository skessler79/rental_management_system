package main.controllers.fragments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.classes.properties.Property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PropertyListItemController {

    @FXML
    private ImageView imageProperty;

    @FXML
    private Label txtPropertyName;

    @FXML
    private Label txtPropertyType;

    @FXML
    private Label txtDescription;

    @FXML
    private Label txtPrice;

    @FXML
    private Button btnDetails;

    public void setDetails(Property property)
    {
        FileInputStream input = null;
        try
        {
            input = new FileInputStream("resources/images/properties/house4.jpg");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        Image image = new Image(input);
        imageProperty.setImage(image);

        txtPropertyName.setText(property.getName());

        String propertyType = property.getPropertyType().toString();
        propertyType = propertyType.substring(0, 1) + propertyType.substring(1).toLowerCase();

        txtPropertyType.setText(propertyType);
        txtDescription.setText(property.getDescription());

        String rentalFee = String.format("RM %.2f", property.getRentalFee());
        txtPrice.setText(rentalFee);
    }
}

