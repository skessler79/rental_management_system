package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.classes.Property;
import main.classes.PropertyBuilder;
import main.controllers.PropertyController;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.UUID;

public class App extends Application implements EventHandler<ActionEvent>
{
    Button button;

    FileWriter file;
    File fileList;
    JSONParser parser = new JSONParser();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Title of The Window");
        PropertyController propertyController = new PropertyController();
        Property property = new PropertyBuilder("12345", Type.PropertyType.BUNGALOW.toString(), "Happy house", "Project CCP").buildProperty();
        propertyController.setData(property);
        //        propertyController.setData(UUID.randomUUID().toString(), Type.PropertyType.BUNGALOW.toString(),
//                "Happy House", "69, Taman Nice, 42069, KL", "20x20 pixel",
//                "Just trust me its a good house", "CCP Corps", "Kuala Lumpur", 69420.69);



        propertyController.getPropertiesData();
//
//        // Button
//        button = new Button();
//        button.setText("YANG");
//        button.setOnAction(this);
//
//        // Image
//        Image image = new Image(new FileInputStream("resources/e_hackerman.jpeg"));
//        ImageView imageView = new ImageView(image);
//
//        VBox layout = new VBox();
//        layout.getChildren().addAll(button, imageView);
//
//        Scene scene = new Scene(layout);
//        stage.setScene(scene);
//        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == button)
        {
            System.out.println("Prepare to be probed by E!");
        }
    }
}

