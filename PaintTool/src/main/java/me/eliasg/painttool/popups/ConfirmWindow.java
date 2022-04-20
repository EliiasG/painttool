package me.eliasg.painttool.popups;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmWindow
{
    private boolean confirmed = false;

    public ConfirmWindow(String windowTitle, String message)
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(windowTitle);
        stage.setResizable(false);

        Label label = new Label(message);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        HBox buttonBox = new HBox(10, yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER);

        yesButton.setOnAction(event ->
        {
            confirmed = true;
            stage.close();
        });

        noButton.setOnAction(event -> stage.close() );

        VBox layout = new VBox(10 ,label, buttonBox);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);

        scene.getStylesheets().add("Default.css");
        layout.setId("main-pane");

        stage.setScene(scene);
        stage.setMinWidth(350);
        stage.setMinHeight(200);

        stage.showAndWait();
    }

    public boolean isConfirmed()
    {
        return confirmed;
    }
}
