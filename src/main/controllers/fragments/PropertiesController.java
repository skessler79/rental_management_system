package main.controllers.fragments;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import main.classes.properties.Property;
import main.enums.PropertyType;
import main.models.PropertyDataModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PropertiesController extends FragmentController implements Initializable
{
    @FXML
    ComboBox combo_state;

//    @FXML
//    JFXListView list_properties;

    @FXML
    AnchorPane anchorPropertyList;

    private PropertyListItemController propertyListItemController;
    private PropertyDataModel propertyDataModel;
    private FXMLLoader loader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        propertyDataModel = new PropertyDataModel();

        // Properly capitalize PropertyType enum and changes underscore to space
        List<String> propertyTypeList = Stream.of(PropertyType.values()).map(PropertyType::name).collect(Collectors.toList());
        for(int i = 0; i < propertyTypeList.size(); ++i)
        {
            String str = propertyTypeList.get(i).substring(0, 1).toUpperCase() + propertyTypeList.get(i).substring(1).toLowerCase();
            str = str.replace('_', ' ');
            propertyTypeList.set(i, str);
        }

        combo_state.getItems().add("Any");
        combo_state.getItems().addAll(propertyTypeList);

        combo_state.setValue("Any");

        ObservableList<Property> data = FXCollections.observableArrayList();
        data.addAll(propertyDataModel.getPropertiesData());

        final ListView<Property> listView = new ListView<>(data);
        listView.setMinWidth(690);

        listView.setCellFactory(new Callback<ListView<Property>, ListCell<Property>>()
        {
            @Override
            public ListCell<Property> call(ListView<Property> propertyListView)
            {
                return new PropertyCell();
            }
        });

        anchorPropertyList.getChildren().add(listView);
    }

    private class PropertyCell extends ListCell<Property>
    {
        private HBox content;
        private Text name;
        private Text price;

        private AnchorPane propertyListItem;

        public PropertyCell()
        {
            super();
            loader = new FXMLLoader(getClass().getResource("../../views/fragments/PropertiesListItem.fxml"));
            try
            {
                propertyListItem = loader.load();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            propertyListItemController = loader.getController();

        }

        @Override
        protected void updateItem(Property item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                propertyListItemController.setDetails(item);
                setGraphic(propertyListItem);
            } else {
                setGraphic(null);
            }
        }
    }
}
