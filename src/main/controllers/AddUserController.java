package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import main.classes.CurrentSession;
import main.classes.users.Admin;
import main.classes.users.User;
import main.classes.users.UserBuilder;
import main.enums.UserType;
import main.views.AlertBoxView;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable
{
    @FXML
    private JFXTextField txt_first_name;

    @FXML
    private JFXTextField txt_password;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private JFXRadioButton radioType_admin;

    @FXML
    private JFXRadioButton radioType_owner;

    @FXML
    private JFXRadioButton radioType_agent;

    @FXML
    private JFXRadioButton radioType_regular;

    @FXML
    private JFXTextField txt_last_name;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXButton btn_add_user;

    private String username, first_name, last_name, password, email, address;
    private UserType userType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Grouping type
        ToggleGroup typeGroup = new ToggleGroup();

        radioType_admin.setToggleGroup(typeGroup);
        radioType_agent.setToggleGroup(typeGroup);
        radioType_owner.setToggleGroup(typeGroup);
        radioType_regular.setToggleGroup(typeGroup);
        radioType_admin.setSelected(true);

        btn_add_user.setOnAction(actionEvent ->
        {

            //First Name
            first_name = txt_first_name.getText();
            txt_first_name.clear();

            //Last Name
            last_name = txt_last_name.getText();
            txt_last_name.clear();

            // Type
            String typeStr = ((JFXRadioButton) typeGroup.getSelectedToggle()).getText();

            switch (typeStr)
            {
                case "Admin":
                    userType = UserType.ADMIN;
                    break;
                case "Owner":
                    userType = UserType.OWNER;
                    break;
                case "Agent":
                    userType = UserType.AGENT;
                    break;
                default:
                    userType = UserType.REGULAR;
                    break;
            }
            radioType_admin.setSelected(true);

            //Username
            username = txt_username.getText();
            txt_username.clear();

            //Password
            password = txt_password.getText();
            txt_password.clear();

            //Email
            email = txt_email.getText();
            txt_email.clear();

            //Address
            address = txt_address.getText();
            txt_address.clear();

            User newUser = new UserBuilder(username, email, password)
                    .firstName(first_name)
                    .lastName(last_name)
                    .address(address)
                    .buildUser(userType);

            System.out.println(newUser);

            try
            {
                CurrentSession.userDataModel.adminCreateUser(CurrentSession.currentUser, newUser);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }

            AlertBoxView.display("Add User", "User successfully added!");
        });
    }
}
