package Scrable;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class MainView extends Application {
    private static String[] clargs;

    public static void main(String[] args) {
        clargs = args;
        launch(args);
        // BufferedReader br = null;
        //System.out.println("Which dictionary files you want to load?");
        /*try{
            //use "sowpods.txt"
            br= new BufferedReader(new FileReader(args[1]));
            String line;
        }catch(IOException e){
            e.printStackTrace();
        }*/
    }

    @Override
    public void start(Stage primaryStage) {
        // set up GUI here
        BufferedReader br = null;
        //System.out.println("Which dictionary files you want to load?");
        try {
            //use "sowpods.txt"
            if (clargs.length == 0) {
                System.out.println("missing commandline.");
                display("Missing commandline argument - dictionary file name.");
                return;
            }

            br = new BufferedReader(new FileReader("dictionaryFile/"+clargs[0]));
            // write code to read
            String line;
            //while loop here to read line
            while((line= br.readLine())!=null){
                line=line.trim();
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            display("Unable to open file: "+ clargs[0]);
            //e.printStackTrace();
        }
    }
    private void display(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}