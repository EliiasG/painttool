package me.eliasg.painttool.popups;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TextWindow
{
    private String result = null;

    public TextWindow(String title, String prompt)
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setResizable(false);

        //Label label = new Label(title);

        TextField textField = new TextField();
        textField.setMaxWidth(200);
        Label promptLabel = new Label(prompt);


        Button confirm = new Button("Confirm");
        Button cancel = new Button("Cancel");

        HBox buttons = new HBox(confirm, cancel);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(promptLabel, textField, buttons);

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Scene scene = new Scene(layout);

        scene.setOnKeyPressed(e ->
        {
            if(e.getCode() == KeyCode.ENTER) confirm.fire();
        });

        stage.setScene(scene);
        stage.setMinWidth(250);
        stage.setMinHeight(150);

        confirm.setOnAction(e ->
        {
            if(textField.getText().length() == 0) return;
            result = textField.getText();
            stage.close();
        });

        scene.getStylesheets().add("Default.css");
        layout.setId("main-pane");

        cancel.setOnAction(e -> stage.close());

        stage.showAndWait();
    }

    public String getResult()
    {
        return result;
    }
}
