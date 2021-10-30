package main.controllers.cells;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import main.classes.properties.Property;
import main.controllers.fragments.PropertyListItemController;

import java.io.IOException;

public class PropertyCell extends ListCell<Property>
{
    private PropertyListItemController propertyListItemController;
    private AnchorPane propertyListItem;
    private FXMLLoader loader;

    public PropertyCell(boolean editableDetails)
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
        propertyListItemController.setEditableDetails(editableDetails);
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

