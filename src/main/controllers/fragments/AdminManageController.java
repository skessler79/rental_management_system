package main.controllers.fragments;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.classes.CurrentSession;
import main.classes.users.User;
import main.controllers.AddUserController;
import main.enums.UserType;
import main.models.UserDataModel;
import main.views.AlertBoxView;
import main.views.ConfirmBoxView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminManageController extends FragmentController implements Initializable {

    private UserDataModel userDataModel;
    private Stage window;
    FXMLLoader loader;
    private AnchorPane addUser;
    private AddUserController addUserController;


    @FXML
    private Label pending_users_count;

    @FXML
    private JFXButton btn_pendingUsers;

    @FXML
    private Label total_users_count;

    @FXML
    private JFXButton btn_totalUsers;

    @FXML
    private AnchorPane anchorPane_total_user;

    @FXML
    private AnchorPane anchorPane_pending_user;

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
    private TableColumn<User, UserType> col_total_user_userType;

    @FXML
    private TableColumn<User, String> col_total_user_firstname;

    @FXML
    private TableColumn<User, String> col_total_user_lastname;

    @FXML
    private JFXButton btn_add_user;

    @FXML
    private JFXButton btn_remove_user;

    @FXML
    private TableView<User> tableview_pending_user;

    @FXML
    private TableColumn<User, String> col_pending_user_id;

    @FXML
    private TableColumn<User, String> col_pending_user_username;

    @FXML
    private TableColumn<User, String> col_pending_user_email;

    @FXML
    private TableColumn<User, UserType> col_pending_user_userType;

    @FXML
    private TableColumn<User, String> col_pending_firstname;

    @FXML
    private TableColumn<User, String> col_pending_lastname;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setPendingUserNum();
        setTotalUserNum();

        loadTableViewPendingUser();
        loadTableViewTotalUser();

        btn_pendingUsers.setOnAction(actionEvent ->  {
            anchorPane_pending_user.toFront();
            loadTableViewPendingUser();
        });

        btn_totalUsers.setOnAction(actionEvent -> {
            anchorPane_total_user.toFront();
            loadTableViewTotalUser();
        });

        btn_reject.setOnAction(actionEvent -> rejectPendingUser());

        btn_accept.setOnAction(actionEvent -> acceptPendingUser());

        btn_add_user.setOnAction(actionEvent -> {

            window = new Stage();

            // Block user actions on previous window
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Add New User");

            loader = new FXMLLoader(getClass().getResource("../../views/AddUser.fxml"));
            try
            {
                addUser = loader.load();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            addUserController = loader.getController();

            Scene scene = new Scene(addUser);
            window.setScene(scene);
            window.showAndWait();
            setTotalUserNum();
        });

        btn_remove_user.setOnAction(actionEvent -> removeUser());

    }

    public void loadTableViewPendingUser() {
        tableview_pending_user.getItems().clear();
        userDataModel = new UserDataModel();
        ObservableList<User> pendingUser = FXCollections.observableArrayList(userDataModel.getPendingUserData());

        tableview_pending_user.getColumns().clear();
        col_pending_user_id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        col_pending_user_username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        col_pending_user_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_pending_firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        col_pending_lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        col_pending_user_userType.setCellValueFactory(new PropertyValueFactory<User, UserType>("userType"));

        for (User user:pendingUser) {
            tableview_pending_user.getItems().add(user);
        }

        tableview_pending_user.getColumns().addAll(col_pending_user_id, col_pending_user_username, col_pending_user_email
                                                ,col_pending_firstname ,col_pending_lastname ,col_pending_user_userType);
    }

    public void loadTableViewTotalUser() {
        tableview_total_user.getItems().clear();
        userDataModel = new UserDataModel();
        ObservableList<User> users = FXCollections.observableArrayList(userDataModel.getUserData());

        tableview_total_user.getColumns().clear();
        col_total_user_username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        col_total_user_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_total_user_userID.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        col_total_user_firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        col_total_user_lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        col_total_user_userType.setCellValueFactory(new PropertyValueFactory<User, UserType>("userType"));

        for (User user:users) {
            tableview_total_user.getItems().add(user);
        }

        tableview_total_user.getColumns().addAll(col_total_user_userID, col_total_user_username, col_total_user_email
                                            ,col_total_user_firstname ,col_total_user_lastname ,col_total_user_userType);
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
        alluser = tableview_pending_user.getItems();

        selectedRows = tableview_pending_user.getSelectionModel().getSelectedItems();
        String pendingId = selectedRows.get(0).getId();

        boolean result = ConfirmBoxView.display("Reject", "Are you sure you want to reject this User?");

        if(result) {


            System.out.println(pendingId);

            try {
                System.out.println(userDataModel.getUserById(pendingId, true));
                userDataModel.rejectUser(CurrentSession.currentUser, userDataModel.getUserById(pendingId, true));
            } catch (IllegalAccessException e) {
                System.out.println("Admin only!");
            } catch (IllegalArgumentException e) {
                AlertBoxView.display("Huh?", "You can't delete yourself buddy");
            }

            setPendingUserNum();
            loadTableViewPendingUser();
        }
    }

    public void acceptPendingUser() {
        ObservableList<User> selectedRows, allUser;
        allUser = tableview_pending_user.getItems();
        selectedRows = tableview_pending_user.getSelectionModel().getSelectedItems();
        String userID = selectedRows.get(0).getId();

        boolean result = ConfirmBoxView.display("Accept", "Are you sure you want to approve this User?");

        System.out.println(result);

        if (result) {

            System.out.println(userID);

            try {
                System.out.println(userDataModel.getUserById(userID, true));
                userDataModel.approveUser(CurrentSession.currentUser, userDataModel.getUserById(userID, true));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            setPendingUserNum();
            setTotalUserNum();
            loadTableViewPendingUser();
        }
    }


    public void removeUser() {
        ObservableList<User> selectedRows, alluser;
        alluser = tableview_total_user.getItems();
        selectedRows = tableview_total_user.getSelectionModel().getSelectedItems();
        String userID = selectedRows.get(0).getId();

        boolean result = ConfirmBoxView.display("Delete", "Are you sure you want to delete the User?");

        System.out.println(result);

        if (result) {

            System.out.println(userID);
            try {
                System.out.println(userDataModel.getUserById(userID ,false));
                userDataModel.deleteUser(CurrentSession.currentUser, userDataModel.getUserById(userID ,false));
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
