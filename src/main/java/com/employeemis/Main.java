package com.employeemis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    // Load main layout from fxml
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
    // Create main layout instance
    Scene root = new Scene(fxmlLoader.load(), 320, 240);

    // Load global css styles
    root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("global.css")).toExternalForm());
    // Set main app window title
    stage.setTitle("Employee Management System");
    // Assign main layout to applet window
    stage.setScene(root);
    // Display app
    stage.show();
  }

  public static void main(String[] args) {
        launch();
    }
}