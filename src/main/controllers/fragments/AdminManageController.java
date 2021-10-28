package main.controllers.fragments;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.classes.users.User;
import main.models.UserDataModel;
import java.util.Collections;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminManageController extends FragmentController implements Initializable {

    UserDataModel userDataModel = new UserDataModel();

    ArrayList<User> pendinguserlist, userlist;
    ArrayList<String> usernamelist, pendingusernamelist, emaillist,pendingemailist;
    ObservableList<String> username_info, email_info;


    @FXML
    private Label pending_users_count;

    @FXML
    private JFXButton btn_pendingUsers;

    @FXML
    private Label total_users_count;

    @FXML
    private JFXButton btn_totalUsers;

    @FXML
    private AnchorPane btm_anchorpane1;

    @FXML
    private JFXListView<String> listview_total_user;

    @FXML
    private AnchorPane btm_anchorpane;

    @FXML
    private JFXButton btn_reject;

    @FXML
    private JFXButton btn_accept;

    @FXML
    private TextField txt_usernameAM1;

    @FXML
    private TextField txt_emailAM1;

    @FXML
    private JFXListView<String> listview_pending_user;

    @FXML
    private JFXButton btn_remove;

    @FXML
    private TextField txt_usernameAM;

    @FXML
    private TextField txt_emailAM;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamelist = new ArrayList<>();
        pendingusernamelist = new ArrayList<>();

        btn_pendingUsers.setOnAction(actionEvent ->  btm_anchorpane1.toFront());

        btn_totalUsers.setOnAction(actionEvent -> btm_anchorpane.toFront());

        setPendingUserNum ();
        setTotalUserNum();

        loadPendingUsersData();
        loadTotalUsersData();

    }


    public void loadPendingUsersData () {

        pendinguserlist = userDataModel.getPendingUserData();

        for(User user: pendinguserlist) {
            pendingusernamelist.add(user.getUsername());
        }

        for(User user: pendinguserlist) {
            pendingemailist.add(user.getEmail());
        }

        ObservableList<String> PendingUserList= FXCollections.observableArrayList(pendingusernamelist);
        listview_pending_user.getItems().addAll(PendingUserList);

        listview_pending_user.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                username_info = listview_pending_user.getSelectionModel().getSelectedItems();
                email_info = listview_pending_user.getSelectionModel().getSelectedItems();

                txt_usernameAM1.setText(String.valueOf(username_info));
                txt_emailAM1.setText(String.valueOf(email_info));

            }
        });


//
//        listview_pending_user.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
//            @Override
//            public void changed(ObservableValue<? extends User> observableValue, User user, User t1) {
//                username_info = listview_pending_user.getSelectionModel().getSelectedItems().toString();
//                email_info = listview_pending_user.getSelectionModel().getSelectedItems().toString();
//                txt_usernameAM.setText(username_info);
//                txt_emailAM.setText(email_info);
//            }
//        });
    }

    public void loadTotalUsersData () {
        userlist = userDataModel.getUserData();

        for(User user: userlist) {
            usernamelist.add(user.getUsername());
        }

        for(User user: userlist) {
            emaillist.add(user.getEmail());
        }

        ObservableList<String> UserList= FXCollections.observableArrayList(usernamelist);
        listview_total_user.getItems().addAll(UserList);

        listview_total_user.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                username_info = listview_total_user.getSelectionModel().getSelectedItems();
                email_info = listview_total_user.getSelectionModel().getSelectedItems();

                txt_usernameAM.setText(String.valueOf(username_info));
                txt_emailAM.setText(String.valueOf(email_info));

            }
        });

/*
        listview_pending_user.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User user, User t1) {
                username_info = listview_pending_user.getSelectionModel().getSelectedItems().toString();
                email_info = listview_pending_user.getSelectionModel().getSelectedItems().toString();
                txt_usernameAM.setText(username_info);
                txt_emailAM.setText(email_info);
            }
        }); */
    }

    public void setPendingUserNum () {
        int PUcount = userDataModel.getPendingUserData().size();
        String stringPUcount = ("" + PUcount);
        pending_users_count.setText(stringPUcount);
    }

    public void setTotalUserNum () {
        int TUcount = userDataModel.getUserData().size();
        String stringTUcout = ("" + TUcount);
        total_users_count.setText(stringTUcout);
    }

}
