package Scrable;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class MainView extends Application {
    //private static String[] clargs;

    public static void main(String[] args) {
        //clargs = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // set up GUI here
        System.out.println("What is the file Name: ");
        File file = new File("C:\\Users\\yun\\IdeaProjects\\scrabblegame\\dictionaryFile\\sowpods.txt");
        try{
            Scanner scnr = new Scanner(file);
            while(scnr.hasNextLine()){
                String s= scnr.nextLine();
                System.out.println(s);
            }
            scnr.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

