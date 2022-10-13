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
import java.util.Collection;
import java.util.Scanner;

public class MainView extends Application {
    //private static String[] clargs;

    public static void main(String[] args) {
        //clargs = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // start scan the dictionary file and put it into Trie data structure
        //System.out.println("What is the file Name: ");
        File file = new File("C:\\Users\\yun\\IdeaProjects\\scrabblegame\\dictionaryFile\\sowpods.txt");
        try{
            Scanner scnr = new Scanner(file);
            while(scnr.hasNextLine()){
                String s= scnr.nextLine();
                Trie.insert(s);
                //System.out.println(Trie.); // how to print out trie Data structure
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //start scan the board
        File fileBoard = new File("C:\\Users\\yun\\IdeaProjects\\scrabblegame\\dictionaryFile\\small_board.txt");
        try {
            Scanner scan_Board = new Scanner(fileBoard);

            String d = scan_Board.nextLine();
            int dimension = Integer.parseInt(d);
            String each_square;
            char[] charArr;
            scan_Board.useDelimiter(" ");
            String[][] twoD_board = new String[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if(scan_Board.hasNext()){
                        each_square = scan_Board.next();
                        charArr = each_square.toCharArray();
                        for(int k=0; k<2; k++){
                            if(charArr[0]!=null){
                                square.
                            }
                        }
                        System.out.println(each_square[i][j]);
                        twoD_board[i][j]= square[];
                    }
                }
                //put b into each tile by calling making board
                // Board??
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

