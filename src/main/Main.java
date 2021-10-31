package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.classes.users.User;
import main.controllers.LoginController;
import main.controllers.UserNavbarController;

import java.io.IOException;

public class Main extends Application
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

    }

    public void startApp(User user) throws Exception
    {
        // Closing login stage due to different StageStyle
        window.hide();

        FXMLLoader loader;
        UserNavbarController controller;

        switch(user.getUserType())
        {
            case ADMIN:
                loader = new FXMLLoader(getClass().getResource("views/AdminNavbarView.fxml"));
                break;
            case REGULAR:
                loader = new FXMLLoader(getClass().getResource("views/TenantNavbarView.fxml"));
                break;
            case OWNER:
            case AGENT:
                loader = new FXMLLoader(getClass().getResource("views/OwnerNavbarView.fxml"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getUserType());
        }

        root = loader.load();
        controller = loader.getController();
        controller.setMain(this);
        controller.setUser(user);

        // Setting a new stage
        Stage mainStage = new Stage();
        window = mainStage;
        window.initStyle(StageStyle.UNDECORATED);

        setDraggable();
        Scene scene = new Scene(root, 1200, 750);
        window.setScene(scene);
        window.show();
    }

    public void loginScreen() throws IOException
    {
        window.hide();
        Stage stage = new Stage();
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

