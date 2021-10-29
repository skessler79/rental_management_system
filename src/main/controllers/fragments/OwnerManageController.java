package main.controllers.fragments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.controllers.cells.PropertyCell;

import java.net.URL;
import java.util.ResourceBundle;

public class OwnerManageController extends FragmentController implements Initializable
{
    @FXML
    private Label txt_usertype;

    @FXML
    private AnchorPane anchorFragment, anchorPropertyList;

    private ListView<Property> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        txt_usertype.setText(CurrentSession.currentUser.getUserType().toString());

        // Get list of all properties belonging to current user
        ObservableList<Property> data = FXCollections.observableArrayList();
        data.addAll(CurrentSession.propertyDataModel.getPropertyByOwner(CurrentSession.currentUser));
        displayList(data);
    }

    private void displayList(ObservableList<Property> data)
    {
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


