package main.controllers.cells;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import main.classes.properties.Property;
import main.controllers.fragments.PropertyListItemController;

import java.io.IOException;

public class PropertyCell extends ListCell<Property>
{
    private HBox content;
    private Text name;
    private Text price;

    private PropertyListItemController propertyListItemController;
    private AnchorPane propertyListItem;
    private FXMLLoader loader;

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
