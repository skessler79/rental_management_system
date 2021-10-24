package main;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.classes.users.Admin;
import main.classes.users.User;
import main.controllers.*;

import org.json.simple.parser.JSONParser;

import main.enums.UserType;


import java.util.UUID;

public class App extends Application
{
    Stage window;
    Parent root;

    TestCases test = new TestCases();

    double x, y = 0;

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

        setDraggable();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.show();

//        test.startTest();
    }

    public void startApp(User currentUser) throws Exception
    {
        // TODO : Next Scene

        System.out.println(currentUser);
        // Closing login stage due to different StageStyle
        window.hide();

        // TODO : Load the right scene based on user type
        // Tenant Scene
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/TenantNavbarView.fxml"));
//        root = loader.load();
//        TenantNavbarController controller = loader.getController();
//        controller.setMain(this);

        // Owner Scene
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/OwnerNavbarView.fxml"));
//        root = loader.load();
//        OwnerNavbarController controller = loader.getController();
//        controller.setMain(this);

        // Admin Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/AdminNavbarView.fxml"));
        root = loader.load();
        AdminNavbarController controller = loader.getController();
        controller.setMain(this);

        // Setting a new stage
        Stage mainStage = new Stage();
        window = mainStage;
        window.initStyle(StageStyle.UNDECORATED);

        setDraggable();
        Scene scene = new Scene(root, 1200, 750);
        window.setScene(scene);
        window.show();
    }

    private void setDraggable()
    {
        root.setOnMousePressed(mouseEvent ->
        {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        root.setOnMouseDragged(mouseEvent ->
        {
            window.setX(mouseEvent.getScreenX() - x);
            window.setY(mouseEvent.getScreenY() - y);
        });
    }

    public void closeApp()
    {
        window.close();
    }
}

