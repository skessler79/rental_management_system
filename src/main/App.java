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

import java.io.FileInputStream;

public class App extends Application implements EventHandler<ActionEvent>
{
    Button button;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Title of The Window");

        // Button
        button = new Button();
        button.setText("Click me");
        button.setOnAction(this);

        // Image
        Image image = new Image(new FileInputStream("resources/e_hackerman.jpeg"));
        ImageView imageView = new ImageView(image);

        VBox layout = new VBox();
        layout.getChildren().addAll(button, imageView);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
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

