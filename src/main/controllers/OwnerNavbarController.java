package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OwnerNavbarController extends UserNavbarController
{
    @FXML
    void handleOpenOwnerManage(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../views/fragments/OwnerManage.fxml"));
        contentPane.getChildren().setAll(anchorPane);
    }
}
