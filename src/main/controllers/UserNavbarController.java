package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.App;
import main.classes.users.User;
import main.controllers.fragments.DashboardController;
import main.controllers.fragments.FragmentController;
import main.models.UserNavbarModel;
import main.views.ConfirmBoxView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserNavbarController implements Initializable
{
    @FXML
    private JFXButton btn_exit, btn_hamburger;

    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    protected AnchorPane contentPane;

    private AnchorPane fragmentPane;

    private App main;
    private FXMLLoader loader;
    private FragmentController fragmentController;

    // Model
    private UserNavbarModel model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        model = new UserNavbarModel();

        try
        {
            loader = new FXMLLoader(getClass().getResource("../views/fragments/Dashboard.fxml"));
            fragmentPane = loader.load();
            fragmentController = loader.getController();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        contentPane.getChildren().setAll(fragmentPane);

        pane1.setVisible(false);

        // Setting up animations
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition.setByX(-600);
        translateTransition.play();

        btn_hamburger.setOnAction(actionEvent ->
        {
            if(pane2.getTranslateX() < -550.0)
            {
                openSidebar();
            }
            else
            {
                closeSidebar();
            }
        });

        pane1.setOnMouseClicked(mouseEvent -> closeSidebar());
        btn_exit.setOnAction(actionEvent ->
        {
            boolean result = ConfirmBoxView.display("Exit", "Are you sure you want to exit?");

            if(result)
            {
                main.closeApp();
            }
        });
    }

    public void setMain(App app)
    {
        main = app;
    }

    public void setUser(User user)
    {
        model.setUser(user);
        fragmentController.setDetails(user);
    }

    // Open sidebar animation
    public void openSidebar()
    {
        btn_hamburger.setDisable(true);
        pane1.setVisible(true);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(0.15);
        fadeTransition1.play();

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(+600);
        translateTransition1.play();

        translateTransition1.setOnFinished(actionEvent1 -> btn_hamburger.setDisable(false));
    }

    // Close sidebar animation
    public void closeSidebar()
    {
        btn_hamburger.setDisable(true);
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(actionEvent1 -> pane1.setVisible(false));

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setToX(-600);
        translateTransition1.play();

        translateTransition1.setOnFinished(actionEvent1 -> btn_hamburger.setDisable(false));
    }

    /// UI sidebar buttons

    @FXML
    private void handleOpenDashboard(ActionEvent event) throws IOException
    {
        loader = new FXMLLoader(getClass().getResource("../views/fragments/Dashboard.fxml"));
        setNewFragment();
    }

    @FXML
    private void handleOpenProperties(ActionEvent event) throws IOException
    {
        loader = new FXMLLoader(getClass().getResource("../views/fragments/Properties.fxml"));
        setNewFragment();
    }

    @FXML
    private void handleOpenSettings(ActionEvent event) throws IOException
    {
        loader = new FXMLLoader(getClass().getResource("../views/fragments/Settings.fxml"));
        setNewFragment();
    }

    @FXML
    private void handleOpenLogout(ActionEvent event) throws IOException
    {
        boolean result = ConfirmBoxView.display("Logout", "Are you sure you want to logout?");

        if(result)
        {
            main.loginScreen();
        }
    }

    protected void setNewFragment() throws IOException
    {
        fragmentPane = loader.load();
        contentPane.getChildren().setAll(fragmentPane);
    }
}

