package main.controllers.fragments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.classes.properties.Property;
import main.controllers.EditPropertyController;
import main.controllers.PropertiesDetailsController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PropertyListItemController implements Initializable
{
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

    private FXMLLoader loader;
    private AnchorPane anchorPropertyDetails;
    private PropertiesDetailsController propertiesDetailsController;
    private EditPropertyController editPropertyController;
    private Stage window;
    private Property property;
    private boolean editableDetails = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        btnDetails.setOnAction(actionEvent ->
        {
            window = new Stage();

            // Block user actions on previous window
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Property details");

            if(editableDetails)
            {
                loader = new FXMLLoader(getClass().getResource("../../views/EditProperty.fxml"));
                try
                {
                    anchorPropertyDetails = loader.load();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                editPropertyController = loader.getController();

                editPropertyController.setWindow(window);
                editPropertyController.setDetails(property);

                Scene scene = new Scene(anchorPropertyDetails);
                window.setScene(scene);
                window.showAndWait();
            }
            else
            {
                loader = new FXMLLoader(getClass().getResource("../../views/PropertiesDetails.fxml"));
                try
                {
                    anchorPropertyDetails = loader.load();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                propertiesDetailsController = loader.getController();

                propertiesDetailsController.setDetails(property, window);

                Scene scene = new Scene(anchorPropertyDetails);
                window.setScene(scene);
                window.showAndWait();
            }
        });
    }

    public void setDetails(Property property)
    {
        this.property = property;

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

    public void setEditableDetails(boolean editableDetails)
    {
        this.editableDetails = editableDetails;
    }
}

