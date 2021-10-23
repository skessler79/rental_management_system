package main;

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
import main.controllers.LoginController;
import main.controllers.PropertyController;
import main.controllers.TenantNavbarController;
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
    }

    public void startApp() throws Exception
    {
        // TODO : Next Scene

        // Tenant Scene
        window.hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/TenantNavbarView.fxml"));
        Stage mainStage = new Stage();
        window = mainStage;
        window.initStyle(StageStyle.UNDECORATED);

        root = loader.load();
        TenantNavbarController controller = loader.getController();
        controller.setMain(this);

        Scene scene = new Scene(root, 800, 500);
        window.setScene(scene);
        window.show();
    }

    public void closeApp()
    {
        window.close();
    }
}

