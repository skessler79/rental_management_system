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
import main.controllers.LoginController;

import main.controllers.OwnerNavbarController;
import main.controllers.PropertyController;
import main.controllers.TenantNavbarController;
import org.json.simple.parser.JSONParser;

import main.enums.UserType;


import java.util.UUID;

public class App extends Application
{
    Stage window;
    Parent root;

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
        Gson gson = new Gson();
        User admin = new Admin(
                UUID.randomUUID().toString(),
                "Winson",
                "Loo",
                "weixiong0404",
                "abc@abc.com",
                "abc123",
                "69, Taman Nice, 69420 Nice City",
                "abc"
        );
        String gsonTest = gson.toJson(admin);

        User user = gson.fromJson(gsonTest, User.class);
        System.out.println(admin instanceof User);
    }

    public void startApp() throws Exception
    {
        // TODO : Next Scene

        // Closing login stage due to different StageStyle
        window.hide();

        // TODO : Load the right scene based on user type
        // Tenant Scene
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/TenantNavbarView.fxml"));
//        root = loader.load();
//        TenantNavbarController controller = loader.getController();
//        controller.setMain(this);

        // Owner Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/OwnerNavbarView.fxml"));
        root = loader.load();
        OwnerNavbarController controller = loader.getController();
        controller.setMain(this);


        // Setting a new stage
        Stage mainStage = new Stage();
        window = mainStage;
        window.initStyle(StageStyle.UNDECORATED);

        setDraggable();
        Scene scene = new Scene(root, 800, 500);
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

