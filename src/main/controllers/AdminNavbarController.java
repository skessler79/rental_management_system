package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminNavbarController extends UserNavbarController
{
    @FXML
    void handleOpenAdminManage(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/AdminManage.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }

    @FXML
    void handleOpenAdminReport(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/AdminReport.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }
}


