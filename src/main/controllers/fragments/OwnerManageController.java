package main.controllers.fragments;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.controllers.AddPropertyController;
import main.controllers.cells.PropertyCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerManageController extends FragmentController implements Initializable
{
    @FXML
    private Label txt_usertype;

    @FXML
    private AnchorPane anchorFragment, anchorPropertyList;

    @FXML
    private JFXButton btnYourProperties, btnAddProperties;

    private ListView<Property> listView;
    private FXMLLoader loader;
    private Stage window;
    private AnchorPane anchorAddProperties;
    private AddPropertyController addPropertyController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        txt_usertype.setText(CurrentSession.currentUser.getUserType().toString());

        displayList();
        btnYourProperties.setOnAction(actionEvent -> displayList());
        btnAddProperties.setOnAction(actionEvent ->
        {
            window = new Stage();

            // Block user actions on previous window
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Property details");

            loader = new FXMLLoader(getClass().getResource("../../views/AddProperty.fxml"));
            try
            {
                anchorAddProperties = loader.load();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            addPropertyController = loader.getController();

            Scene scene = new Scene(anchorAddProperties);
            window.setScene(scene);
            window.showAndWait();
            displayList();
        });
    }

    private void displayList()
    {
        // Get list of all properties belonging to current user
        ObservableList<Property> data = FXCollections.observableArrayList();
        data.addAll(CurrentSession.propertyDataModel.getPropertyByOwner(CurrentSession.currentUser));

        // Create dynamic ListView of fragments
        listView = new ListView<>(data);
        listView.setMinWidth(690);

        listView.setCellFactory(new Callback<ListView<Property>, ListCell<Property>>()
        {
            @Override
            public ListCell<Property> call(ListView<Property> propertyListView)
            {
                return new PropertyCell();
            }
        });
        anchorPropertyList.getChildren().clear();
        anchorPropertyList.getChildren().add(listView);
    }
}

