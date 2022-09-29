package Scrable;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.nio.Buffer;

public class MainView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Button btn;
    @Override
    public void start(Stage primaryStage) {
        // set up GUI here
        btn = new Button();
        btn.setText("Click Me");
        BorderPane pane= new BorderPane();
        pane.setCenter(btn);
        Scene scene= new Scene(pane,300,200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}