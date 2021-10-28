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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import main.classes.Property;
import main.models.PropertyDataModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PropertiesController extends FragmentController implements Initializable
{
    @FXML
    ComboBox combo_state;

    @FXML
    JFXListView list_properties;

    PropertyDataModel propertyDataModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        propertyDataModel = new PropertyDataModel();

        combo_state.getItems().addAll(
                "All States",
                "Kuala Lumpur",
                "Selangor"
        );

        combo_state.setValue("All States");

        ObservableList<Property> data = FXCollections.observableArrayList();
        data.addAll(propertyDataModel.getPropertiesData());

        System.out.println(propertyDataModel.getPropertiesData());

        System.out.println("Shit : " + data.toString());

        final ListView<Property> listView = new ListView<>(data);

        listView.setCellFactory(new Callback<ListView<Property>, ListCell<Property>>()
        {
            @Override
            public ListCell<Property> call(ListView<Property> propertyListView)
            {
                return new PropertyCell();
            }
        });
    }

    private class PropertyCell extends ListCell<Property>
    {
        private HBox content;
        private Text name;
        private Text price;

        public PropertyCell()
        {
            super();
            name = new Text();
            price = new Text();
            VBox vBox = new VBox(name, price);
            content = new HBox(new Label("[Graphic]"), vBox);
            content.setSpacing(10);
        }

        @Override
        protected void updateItem(Property item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                name.setText(item.getName());
                price.setText(String.format("%d $", item.getRentalFee()));
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    }
}
