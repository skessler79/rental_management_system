package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.App;
import main.models.LoginModel;

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

    private LoginModel loginModel;
    private App main;

    @FXML
    public void login(ActionEvent actionEvent)
    {
        String username = txt_username_signin.getText();
        String password = pwd_signin.getText();

        System.out.println(username);
        System.out.println(password);

        // TODO : Check Credentials

        try
        {
            main.startApp();
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        actionEvent.consume();
        main.closeApp();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loginModel = new LoginModel();
        System.out.println("Initialize");
    }

    public void setMain(App app)
    {
        main = app;
    }
}
