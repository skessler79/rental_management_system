package main.controllers.fragments;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import main.classes.properties.Property;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.models.PropertyDataModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO : Search by Facilities
public class PropertiesController extends FragmentController implements Initializable
{
    @FXML
    ComboBox combo_state;

    @FXML
    JFXCheckBox checkPool, checkWifi, checkTv, checkFridge, checkAircond, checkHeater;

    @FXML
    Button btnSearch;

    @FXML
    AnchorPane anchorPropertyList;

    private PropertyListItemController propertyListItemController;
    private PropertyDataModel propertyDataModel;
    private FXMLLoader loader;
    private ArrayList<FacilityType> facilityTypes;
    private ListView<Property> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        propertyDataModel = new PropertyDataModel();
        facilityTypes = new ArrayList<>();

        btnSearch.setOnAction(actionEvent -> handleFacilities());

        // Properly capitalize PropertyType enum and changes underscore to space
        List<String> propertyTypeList = Stream.of(PropertyType.values()).map(PropertyType::name).collect(Collectors.toList());
        for(int i = 0; i < propertyTypeList.size(); ++i)
        {
            String str = propertyTypeList.get(i).substring(0, 1).toUpperCase() + propertyTypeList.get(i).substring(1).toLowerCase();
            str = str.replace('_', ' ');
            propertyTypeList.set(i, str);
        }

        // Setting house type selection
        combo_state.getItems().add("Any");
        combo_state.getItems().addAll(propertyTypeList);
        combo_state.setValue("Any");

        // Get list of all active properties
        ObservableList<Property> data = FXCollections.observableArrayList();
        data.addAll(propertyDataModel.getPropertyByActive(true));
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

        anchorPropertyList.getChildren().add(listView);
    }

    private void handleFacilities()
    {
        facilityTypes.clear();

        if(checkPool.isSelected())
        {
            facilityTypes.add(FacilityType.SWIMMING_POOL);
        }

        if(checkWifi.isSelected())
        {
            facilityTypes.add(FacilityType.WIFI);
        }

        if(checkTv.isSelected())
        {
            facilityTypes.add(FacilityType.TV);
        }

        if(checkFridge.isSelected())
        {
            facilityTypes.add(FacilityType.FRIDGE);
        }

        if(checkAircond.isSelected())
        {
            facilityTypes.add(FacilityType.AIRCOND);
        }

        if(checkHeater.isSelected())
        {
            facilityTypes.add(FacilityType.WATER_HEATER);
        }

        ObservableList<Property> data = FXCollections.observableArrayList();
        data.addAll(propertyDataModel.getPropertyByFacilityType(facilityTypes));
        displayList(data);
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
