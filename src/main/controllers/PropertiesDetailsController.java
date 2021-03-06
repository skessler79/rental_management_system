package main.controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.classes.Comment;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.classes.users.User;
import main.enums.FacilityType;
import main.enums.UserType;
import main.models.PropertyDataModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PropertiesDetailsController implements Initializable
{
    @FXML
    private Label labelId;

    @FXML
    private Label labelName;

    @FXML
    private Label labelOwner;

    @FXML
    private Label labelTenant;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelAddress;

    @FXML
    private Label labelProject;

    @FXML
    private Label labelRentalFee;

    @FXML
    private Label labelRooms;

    @FXML
    private Label labelBathrooms;

    @FXML
    private Label labelFacilities;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelTimeCreated;

    @FXML
    private JFXListView listComments;

    @FXML
    private Label labelComments;

    @FXML
    private Label labelColon;

    @FXML
    private JFXTextField txtAddComment;

    @FXML
    private Button btnAddComment, btnDeleteComment, btnConfirm, btnChangeStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // Let admin see and add comments
        if(CurrentSession.currentUser.getUserType() == UserType.ADMIN)
        {
            listComments.setVisible(true);
            labelComments.setVisible(true);
            labelColon.setVisible(true);
            txtAddComment.setVisible(true);
            btnAddComment.setVisible(true);
            btnDeleteComment.setVisible(true);
        }

        // Change status button
        if(CurrentSession.currentUser.getUserType() == UserType.ADMIN)
        {
            btnChangeStatus.setVisible(true);
        }
    }

    public void setDetails(Property property, Stage window)
    {
        // Confirm button
        btnConfirm.setOnAction(actionEvent -> window.close());

        // Tenants ID
        User tenant = property.getTenant();
        String tenantStr = new String();

        tenantStr = (tenant == null) ? "" : tenant.getId();

        if(tenantStr.length() > 0)
        {
            tenantStr = tenantStr.substring(2);
        }

        // Status
        String statusStr;
        if(property.getIsActive())
        {
            statusStr = "Active";
        }
        else
        {
            statusStr = "Inactive";
        }

        // Facility Types
        String facilityStr = new String();
        for(FacilityType facility : property.getFacilityTypes())
        {
            facilityStr += ", " + facility.toString();
        }
        if(facilityStr.length() > 0)
        {
            facilityStr = facilityStr.substring(2);
        }

        // Setting labels
        labelId.setText(property.getPropertyId());
        labelName.setText(property.getName());
        labelOwner.setText(property.getOwner().getId());
        labelTenant.setText(tenantStr);
        labelStatus.setText(statusStr);
        try
        {
            labelAddress.setText(property.getAddress().toString());
        }
        catch(NullPointerException e)
        {
            labelAddress.setText("");
        }
        labelProject.setText(property.getProject());
        labelRentalFee.setText(String.format("RM %.2f", property.getRentalFee()));
        labelRooms.setText(property.getRoomInfo());
        labelBathrooms.setText(String.valueOf(property.getBathRoomCount()));
        labelFacilities.setText(facilityStr);
        labelDescription.setText(property.getDescription());
        labelTimeCreated.setText(String.valueOf(property.getCreatedAt()));

        // Setting comments
        setComments(property);

        btnAddComment.setOnAction(actionEvent ->
        {
            PropertyDataModel propertyDataModel = new PropertyDataModel();
            try
            {
                propertyDataModel.addComment(CurrentSession.currentUser, property, txtAddComment.getText());
                setComments(property);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }

            txtAddComment.clear();
        });

        btnDeleteComment.setOnAction(actionEvent ->
        {
            CurrentSession.propertyDataModel.deleteComment(property, (Comment) listComments.getSelectionModel().getSelectedItem());
            setComments(property);
        });

        // Change status button
        btnChangeStatus.setOnAction(actionEvent ->
        {
            property.setActive(!property.getIsActive());
            if(property.getIsActive())
            {
                labelStatus.setText("Active");
            }
            else
            {
                labelStatus.setText("Inactive");
            }
        });
    }

    // Let admins add comments to properties
    private void setComments(Property property)
    {
        property = CurrentSession.propertyDataModel.getPropertyById(property.getPropertyId());
        ArrayList<Comment> comments = property.getComment();
        ObservableList<Comment> commentsObservable = FXCollections.observableArrayList(comments);
        listComments.setItems(commentsObservable);
    }
}

