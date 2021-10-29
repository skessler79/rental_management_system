package main.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBoxView
{
    static boolean answer;

    public static boolean display(String title, String message)
    {
        Stage window = new Stage();

        // Block user actions on previous window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(100);

        Label label = new Label();
        label.setText(message);

        // Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");



        yesButton.setOnAction(actionEvent ->
        {
            answer = true;
            window.close();
        });

        noButton.setOnAction(actionEvent ->
        {
            answer = false;
            window.close();
        });

        window.setOnCloseRequest(actionEvent ->
        {
            answer = false;
            actionEvent.consume();
            window.close();

        });

        VBox layout = new VBox(10);
        HBox buttons = new HBox(10);

        buttons.getChildren().addAll(yesButton, noButton);
        buttons.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
