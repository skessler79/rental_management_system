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


}
