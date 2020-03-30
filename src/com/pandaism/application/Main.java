package com.pandaism.application;

import com.pandaism.application.reference.Messages;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    public static Application instance;
    public static final File BIN_FOLDER = new File("./bin");

    public static final long MAX_BYTE = 36686478 + 39147198;

    @Override
    public void start(Stage primaryStage) throws Exception{
        if(BIN_FOLDER.exists()) {
            Parent root = FXMLLoader.load(getClass().getResource("application.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 350, 270));
            primaryStage.show();

            instance = this;
        } else {
            new Alert(Alert.AlertType.ERROR, Messages.NO_BIN, ButtonType.CLOSE).show();
        }
    }

//75,833,676
    public static void main(String[] args) {
        launch(args);
    }
}
