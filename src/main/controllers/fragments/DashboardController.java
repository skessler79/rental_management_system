package main.controllers.fragments;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.classes.CurrentSession;
import main.classes.users.User;
import main.enums.UserType;
import main.views.AlertBoxView;
import main.views.ConfirmBoxView;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends FragmentController implements Initializable
{
    @FXML
    private Label txtUsertype;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtFirstname;

    @FXML
    private JFXTextField txtLastname;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private Button btnUpdate, btnCancel, btnMyProperty;

    private String username, email, firstname, lastname, address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        setDetails(CurrentSession.currentUser);

        btnUpdate.setOnAction(actionEvent ->
        {
            boolean confirm = ConfirmBoxView.display("Edit Profile", "Are you sure you want to edit your profile?");

            if(confirm)
            {
                username = txtUsername.getText();
                email = txtEmail.getText();
                firstname = txtFirstname.getText();
                lastname = txtLastname.getText();
                address = txtAddress.getText();

                CurrentSession.currentUser.setUsername(username);
                CurrentSession.currentUser.setEmail(email);
                CurrentSession.currentUser.setFirstName(firstname);
                CurrentSession.currentUser.setLastName(lastname);
                CurrentSession.currentUser.setAddress(address);

                CurrentSession.userDataModel.editUserProfile(CurrentSession.currentUser);
            }
        });

        btnCancel.setOnAction(actionEvent ->
        {
            setDetails(CurrentSession.currentUser);
        });

        if(CurrentSession.currentUser.getUserType() == UserType.REGULAR)
        {
            btnMyProperty.setVisible(true);
        }
    }

    @Override
    public void setDetails(User user)
    {
        this.txtUsertype.setText(user.getUserType().toString());
        this.txtUsername.setText(user.getUsername());
        this.txtEmail.setText(user.getEmail());
        this.txtFirstname.setText(user.getFirstName());
        this.txtLastname.setText(user.getLastName());
        this.txtAddress.setText(user.getAddress());
    }
}

