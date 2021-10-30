package main.controllers.fragments;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.classes.CurrentSession;
import main.classes.users.User;
import main.views.AlertBoxView;
import main.views.ConfirmBoxView;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends FragmentController implements Initializable
{
    @FXML
    private Label txt_usertype;

    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_firstname;

    @FXML
    private JFXTextField txt_lastname;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private Button btn_update, btn_cancel;

    private String username, email, firstname, lastname, address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        setDetails(CurrentSession.currentUser);

        btn_update.setOnAction(actionEvent ->
        {
            boolean confirm = ConfirmBoxView.display("Edit Profile", "Are you sure you want to edit your profile?");

            if(confirm)
            {
                username = txt_username.getText();
                email = txt_email.getText();
                firstname = txt_firstname.getText();
                lastname = txt_lastname.getText();
                address = txt_address.getText();

                if(username.isEmpty() || email.isEmpty())
                {
                    AlertBoxView.display("Edit Error", "The username and email field cannot be empty!");
                }
                else
                {
                    CurrentSession.currentUser.setUsername(username);
                    CurrentSession.currentUser.setEmail(email);
                    CurrentSession.currentUser.setFirstName(firstname);
                    CurrentSession.currentUser.setLastName(lastname);
                    CurrentSession.currentUser.setAddress(address);

                    CurrentSession.userDataModel.editUserProfile(CurrentSession.currentUser);
                }
            }
        });

        btn_cancel.setOnAction(actionEvent ->
        {
            setDetails(CurrentSession.currentUser);
        });
    }

    @Override
    public void setDetails(User user)
    {
        this.txt_usertype.setText(user.getUserType().toString());
        this.txt_username.setText(user.getUsername());
        this.txt_email.setText(user.getEmail());
        this.txt_firstname.setText(user.getFirstName());
        this.txt_lastname.setText(user.getLastName());
        this.txt_address.setText(user.getAddress());
    }
}

