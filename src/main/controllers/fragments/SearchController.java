package main.controllers.fragments;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends FragmentController implements Initializable
{
    @FXML
    AnchorPane anchor_content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            FileInputStream input = new FileInputStream("resources/images/properties/house4.jpg");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);

            anchor_content.getChildren().add(imageView);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


    }
}
