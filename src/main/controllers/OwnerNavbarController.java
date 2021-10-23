package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import main.App;
import main.views.ConfirmBoxView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerNavbarController implements Initializable
{
    @FXML
    private JFXButton btn_exit, btn_hamburger;

    @FXML
    private AnchorPane pane1, pane2, contentPane;

    private App main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        AnchorPane anchorPane = null;
        try
        {
            anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/Dashboard.fxml"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        contentPane.getChildren().setAll(anchorPane);


        pane1.setVisible(false);

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

    @FXML
    void handleOpenDashboard(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/Dashboard.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }

    @FXML
    void handleOpenProperties(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/Properties.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }

    @FXML
    void handleOpenProjects(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/Projects.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }

    @FXML
    void handleOpenSearch(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/Search.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }

    @FXML
    void handleOpenOwnerManage(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/OwnerManage.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }

    @FXML
    void handleOpenSettings(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/Settings.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }

    @FXML
    void handleOpenLogout(ActionEvent event) throws IOException
    {
        main.loginScreen();
    }

}
