package main;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.classes.Admin;
import main.classes.User;
import main.controllers.LoginController;
import main.controllers.PropertyController;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.UUID;

public class App extends Application
{
    Stage window;
    Parent root;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        window = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/LoginView.fxml"));
        window.initStyle(StageStyle.TRANSPARENT);

        root = loader.load();
        LoginController controller = loader.getController();
        controller.setMain(this);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.show();
        Gson gson = new Gson();
//        User admin = new Admin(
//                UUID.randomUUID().toString(),
//                Type.UserType.ADMIN,
//
//
//        );
    }

    public void startApp() throws Exception
    {
        // TODO : Next Scene
        root = FXMLLoader.load(getClass().getResource("views/HomeView.fxml"));
        window.hide();
        Stage mainStage = new Stage();
        window = mainStage;
        window.setScene(new Scene(root, 420, 420));
        window.show();
        window.toFront();
    }

    public void closeApp()
    {
        window.close();
    }
}

