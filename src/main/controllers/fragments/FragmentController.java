package main.controllers.fragments;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.classes.users.User;

// TODO : use model
public class FragmentController
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


    // Setters
    public void setTxt_usertype(String usertype)
    {
        this.txt_usertype.setText(usertype);
    }

    public void setTxt_username(String username)
    {
        this.txt_username.setText(username);
    }

    public void setTxt_email(String email)
    {
        this.txt_email.setText(email);
    }

    public void setTxt_firstname(String firstname)
    {
        this.txt_firstname.setText(firstname);
    }

    public void setTxt_lastname(String lastname)
    {
        this.txt_lastname.setText(lastname);
    }

    public void setTxt_address(String address)
    {
        this.txt_address.setText(address);
    }


    public void setDetails(User user)
    {
        // TODO : Handle setDetails for fragments without all the fields...
        System.out.println(user.toString());
        try
        {
            this.txt_usertype.setText(user.getUserType().toString());
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }

//        this.txt_username.setText(user.getUsername());
//        this.txt_email.setText(user.getEmail());
//        this.txt_firstname.setText(user.getFirstName());
//        this.txt_lastname.setText(user.getLastName());
//        this.txt_address.setText(user.getAddress());
    }
}
