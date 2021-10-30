package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.App;
import main.classes.CurrentSession;
import main.classes.users.User;
import main.models.LoginModel;
import main.views.AlertBoxView;
import main.views.ConfirmBoxView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private JFXButton btn_signin, btn_signup, btn_close_signin, btn_close_signup, btn_submit_signin;

    @FXML
    private AnchorPane pn_signin, pn_signup;

    @FXML
    private JFXTextField txt_username_signin, txt_username_signup, txt_email_signup;

    @FXML
    private JFXPasswordField pwd_signin, pwd_signup;

    private App main;

    @FXML
    public void login(ActionEvent actionEvent)
    {
        String username = txt_username_signin.getText();
        String password = pwd_signin.getText();
        User user = null;

        try{
            user = CurrentSession.userDataModel.loginUser(username, password);
        } catch (IllegalArgumentException e){

            AlertBoxView.display("Login Error", "Your login credentials are incorrect!");
        }

        if( user != null)
        {
            try
            {
                main.startApp(user);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == btn_signin)
        {
            pn_signin.toFront();
        }
        else if(actionEvent.getSource() == btn_signup)
        {
            pn_signup.toFront();
        }
    }

    @FXML
    public void handleCloseAction(ActionEvent actionEvent)
    {
        boolean result = ConfirmBoxView.display("Exit", "Are you sure you want to exit?");

        if(result)
        {
            main.closeApp();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        btn_submit_signin.setDefaultButton(true);
    }

    public void setMain(App app)
    {
        main = app;
    }
}
