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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.classes.CurrentSession;
import main.classes.properties.Property;
import main.classes.users.User;
import main.enums.FacilityType;
import main.enums.PropertyType;
import main.models.PropertyDataModel;
import main.models.UserDataModel;
import main.views.AlertBoxView;
import main.views.ConfirmBoxView;

import java.util.Collections;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminManageController extends FragmentController implements Initializable {

    private UserDataModel userDataModel;
    private AlertBoxView alertBox;

//    ArrayList<User> pendinguserlist, userlist;
//    ArrayList<String> usernamelist, pendingusernamelist, emaillist,pendingemailist;
//    ObservableList<String> username_info, email_info;

    @FXML
    private Label pending_users_count;

    @FXML
    private JFXButton btn_pendingUsers;

    @FXML
    private Label total_users_count;

    @FXML
    private JFXButton btn_totalUsers;

    @FXML
    private AnchorPane anchorpane_total_user;

    @FXML
    private AnchorPane anchorpane_pending_user;

    @FXML
    private JFXButton btn_reject;

    @FXML
    private JFXButton btn_accept;

    @FXML
    private TableView<User> tableview_total_user;

    @FXML
    private TableColumn<User, String> col_total_user_username;

    @FXML
    private TableColumn<User, String> col_total_user_email;

    @FXML
    private TableColumn<User, String> col_total_user_userID;

    @FXML
    private JFXButton btn_add_user;

    @FXML
    private JFXButton btn_remove_user;

    @FXML
    private TableView<User> tableview_pending_user;

    @FXML
    private TableColumn<User, String> col_pending_user_username;

    @FXML
    private TableColumn<User, String> col_pending_user_email;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        usernamelist = new ArrayList<>();
//        pendingusernamelist = new ArrayList<>();

        setPendingUserNum ();
        setTotalUserNum();

        loadTableViewPendingUser();
        loadTableViewTotalUser();

        btn_pendingUsers.setOnAction(actionEvent ->  anchorpane_pending_user.toFront());

        btn_totalUsers.setOnAction(actionEvent -> anchorpane_total_user.toFront());

        btn_reject.setOnAction(actionEvent -> rejectPendingUser());
        btn_accept.setOnAction(actionEvent -> acceptPendingUser());

        btn_add_user.setOnAction(actionEvent -> addNewUser());
        btn_remove_user.setOnAction(actionEvent -> removeUser());

    }


//    public void loadPendingUsersData () {
//
//        pendinguserlist = userDataModel.getPendingUserData();
//
//        for(User user: pendinguserlist) {
//            pendingusernamelist.add(user.getUsername());
//        }
//
//        for(User user: pendinguserlist) {
//            pendingemailist.add(user.getEmail());
//        }
//
//        ObservableList<String> pendinguserlist_observer = FXCollections.observableArrayList(pendingusernamelist);
//        listview_pending_user.getItems().addAll(pendingusernamelist);
//
//        listview_pending_user.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                username_info = listview_pending_user.getSelectionModel().getSelectedItems();
//                email_info = listview_pending_user.getSelectionModel().getSelectedItems();
//
//                txt_usernameAM1.setText(String.valueOf(username_info));
//                txt_emailAM1.setText(String.valueOf(email_info));
//
//            }
//        });
//    }

//    public void loadTotalUsersData () {
//        userlist = userDataModel.getUserData();
//
//        for(User user: userlist) {
//            usernamelist.add(user.getUsername());
//        }
//
//        for(User user: userlist) {
//            emaillist.add(user.getEmail());
//        }
//
//        ObservableList<String> usernamelist_observer, emaillist_observer;
//                //FXCollections.observableArrayList(usernamelist);
//        listview_total_user.getItems().addAll(usernamelist);
//
//        listview_total_user.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                username_info = listview_total_user.getSelectionModel().getSelectedItems();
//                email_info = listview_total_user.getSelectionModel().getSelectedItems();
//
//                txt_usernameAM.setText(String.valueOf(username_info));
//                txt_emailAM.setText(String.valueOf(email_info));
//
//            }
//        });
//    }

    public void loadTableViewPendingUser() {
        tableview_pending_user.getItems().clear();
        userDataModel = new UserDataModel();
        ObservableList<User> pendinguser = FXCollections.observableArrayList(userDataModel.getPendingUserData()); //will change to pending user data

        tableview_pending_user.getColumns().clear();
        col_pending_user_username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        col_pending_user_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        for (User user:pendinguser) {
            tableview_pending_user.getItems().add(user);
        }

        tableview_pending_user.getColumns().addAll(col_total_user_username, col_total_user_email);
    }

    public void loadTableViewTotalUser() {
        tableview_total_user.getItems().clear();
        userDataModel = new UserDataModel();
        ObservableList<User> users = FXCollections.observableArrayList(userDataModel.getUserData());

        tableview_total_user.getColumns().clear();
        col_total_user_username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        col_total_user_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_total_user_userID.setCellValueFactory(new PropertyValueFactory<User, String>("id"));

        for (User user:users) {
            tableview_total_user.getItems().add(user);
        }

        tableview_total_user.getColumns().addAll(col_total_user_userID, col_total_user_username, col_total_user_email);
    }

    public void setPendingUserNum () {
        userDataModel = new UserDataModel();
        int PUcount = userDataModel.getPendingUserData().size();
        String stringPUcount = ("" + PUcount);
        pending_users_count.setText(stringPUcount);
    }

    public void setTotalUserNum () {
        userDataModel = new UserDataModel();
        int TUcount = userDataModel.getUserData().size();
        String stringTUcout = ("" + TUcount);
        total_users_count.setText(stringTUcout);
    }

    public void rejectPendingUser() {
        ObservableList<User> selectedRows, alluser;
        alluser = tableview_total_user.getItems();

        selectedRows = tableview_total_user.getSelectionModel().getSelectedItems();
        String pendingusername = selectedRows.get(0).getUsername();

        boolean result = ConfirmBoxView.display("Reject", "Are you sure you want to reject this User?");

        if(result) {

            for (User user: selectedRows) {
                alluser.removeAll(selectedRows);
            }

            System.out.println(pendingusername);

            try {
                userDataModel.rejectUser(CurrentSession.currentUser, userDataModel.getUserById(pendingusername));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            setPendingUserNum();
            loadTableViewPendingUser();
        }



        // TODO reject pending users
    }

    public void acceptPendingUser() {

        ObservableList<User> selectedRows, alluser;
        alluser = tableview_total_user.getItems();
        selectedRows = tableview_total_user.getSelectionModel().getSelectedItems();
        //User userRow = selectedRows.get(0).getClass();

        boolean result = ConfirmBoxView.display("Reject", "Are you sure you want to reject this User?");

        if (result) {

            for (User user: selectedRows) {
                alluser.removeAll(selectedRows);
            }
        }


        //TODO accept pending users
    }


    public void addNewUser() {

    }

    public void removeUser() {
        ObservableList<User> selectedRows, alluser;
        alluser = tableview_total_user.getItems();
        selectedRows = tableview_total_user.getSelectionModel().getSelectedItems();
        String userID = selectedRows.get(0).getId();

        boolean result = ConfirmBoxView.display("Delete", "Are you sure you want to delete the User?");

        if (result) {

            for (User user: selectedRows) {
                alluser.removeAll(selectedRows);
            }

            System.out.println(userID);
            try {
                userDataModel.deleteUser(CurrentSession.currentUser, userDataModel.getUserById(userID));
            } catch (IllegalAccessException e) {
                System.out.println("Admin only!");
            } catch (IllegalArgumentException e) {
                AlertBoxView.display("Huh?", "You can't delete yourself buddy");
            };

            loadTableViewTotalUser();
            setTotalUserNum();
        }
    }
}
