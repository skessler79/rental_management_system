package controller;

import model.Properties;
import model.Property;

import java.util.ArrayList;

public class PropertiesController
{
    private static ArrayList<Property> viewPropertyList()
    {
        return Properties.getPropertyArrayList();
    }

    private static ArrayList<Property> viewPropertyByType(String type)
    {
        // TODO : Filter properties by type and return;
        return Properties.getPropertyArrayList();
    }

}
