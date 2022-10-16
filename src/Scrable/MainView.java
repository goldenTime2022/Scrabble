package Scrable;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class MainView extends Application {
    //private static String[] clargs;

    public static void main(String[] args) {
        //clargs = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        //////////////////////////////////////////////////////////////////////////////////////////////////
        // start scan the dictionary file and put it into Trie data structure
        //System.out.println("What is the file Name: ");
        File file = new File("C:\\Users\\yun\\IdeaProjects\\scrabblegame\\dictionaryFile\\sowpods.txt");
        try {
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                String s = scnr.nextLine();
                Trie.insert(s);
                //System.out.println(Trie.); // how to print out trie Data structure
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //////////////////////////////////////////////////////////////////////////////////////////////
        // scan the total tile into the tile_frequency_file and create the list of tiles
        File file_tile_frequency = new File("dictionaryFile/wordswithfriends_tiles.txt");
        int frequency;

        Tile tile = new Tile("0", 0);
        //hashmap store (tile, frequency)
        int tileCount = 0;
        HashMap<Integer, Tile> listOfTiles = new HashMap<Integer, Tile>();
        try {
            Scanner scnr_tile_frequency = new Scanner(file_tile_frequency);

            while (scnr_tile_frequency.hasNextLine()) {
                String[] values = scnr_tile_frequency.nextLine().split(" ");
                //System.out.println(values[0]);
                // System.out.println(values[1]);
                //System.out.println(values[2]);
                tile = new Tile(values[0], Integer.parseInt(values[1]));
                //System.out.println(tile);
                for (int i = 0; i < Integer.parseInt(values[2]); i++) {
                    listOfTiles.put(tileCount, tile);
                    tileCount++;
                }
                //System.out.println( "1st = "+ values[0].charAt(0)  +"; 2nd = " +Integer.parseInt(values[1]) +"; 3rd = " + Integer.parseInt(values[2]));
            }
            scnr_tile_frequency.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //catch(IOException e){
        //e.printStackTrace();
        //}
        //System.out.println();
        //System.out.println("scramble Bag: "+listOfTiles.entrySet());
        //System.out.println("----------------------------------------");


////////////////////////////////////////////////////////////////////////////////////////
        //start scan the board
        File fileBoard = new File("dictionaryFile/small_board.txt");
        try {
            Scanner scan_Board = new Scanner(fileBoard);
            String d = scan_Board.nextLine();
            int dimension = Integer.parseInt(d);
            scan_Board.useDelimiter(" ");
            String value;
            char[] charArr;
            Square[][] square = new Square[dimension][dimension];
            for (int i = 0; scan_Board.hasNextLine(); i++) {
                for (int j = 0; j<dimension; j++) {
                    if (scan_Board.hasNext()) {
                        value = scan_Board.next();// this is the one cause trouble???????????????????????? this scan the extra one
                        System.out.print("j = " + j + "; value = " + value + " // ");
                        charArr = value.toCharArray();
                        square[i][j] = new Square(0, 0, false, null);
                        //System.out.println("charArr[0] = " + charArr[0]);
                        //System.out.println("charArr[1] = " + charArr[1]);
                        if (charArr[0] == ' ') {
                            square[i][j].setOccupied(true);
                        } else if (charArr[0] == '.' && charArr[1] == '.') {
                            square[i][j].setWordMultiplier(0);
                            square[i][j].setLetterMultiplier(0);
                        } else if (charArr[0] == '.') {
                            square[i][j].setLetterMultiplier(charArr[1] - 48);
                        } else if (charArr[1] == '.') {
                            square[i][j].setWordMultiplier(charArr[0] - 48);
                        }
                        //System.out.println("square[" + i + "][" + j + "] = " + square[i][j]);
                    }
                    //System.out.print(square[i][j] +" ");
                }
                System.out.println();
                System.out.println("--------------------------------------------");
            }
            scan_Board.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        //start scan the board
      /*  File fileBoard = new File("dictionaryFile/small_board.txt");
        try {
            Scanner scan_Board = new Scanner(fileBoard);
            String d = scan_Board.nextLine();
            int dimension = Integer.parseInt(d);
            String value;
            String[] single_Value;
            scan_Board.useDelimiter(" ");
            Square[][] square = new Square[dimension][dimension];
                for (int row = 0; row< dimension; row++) {
                    for (int col = 0; col < dimension; col++) {
                        //for(int t=0, t<2; t++){
                            value = scan_Board.next();
                            System.out.println(value);
                        System.out.println("Value = "+ value + "; @ row = " + row + "; column = " + col );
                        single_Value = value.split("");
                        //System.out.println("single_Value[0] = " + single_Value[0]);
                        //System.out.println("single_Value[1] = " + single_Value[1]);
                        //System.out.println("----------------------------------------------------");
                        square[row][col] = new Square(0, 0, false, null);
                        if (single_Value[0].equals(".") && single_Value[1].equals(".")) {
                            square[row][col].setWordMultiplier(0);
                            square[row][col].setLetterMultiplier(0);
                        } else if (single_Value[0].equals(".")) {
                            square[row][col].setWordMultiplier(0);
                            square[row][col].setLetterMultiplier(Integer.parseInt(single_Value[1]));
                        } else if (single_Value[1].equals(".")) {
                            square[row][col].setLetterMultiplier(0);
                            square[row][col].setWordMultiplier(Integer.parseInt(single_Value[0]));
                        }
                        //System.out.println("square["+row+"]["+col+"] = "+square[row][col]);
                        //System.out.print(square[row][col] + " ");
                        scan_Boardscan_Board.nextLine();
                    }
                }
                scan_Board.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        ///////////////////////////////////////////////////////////////////////////////////
        /*
        //computer take up to 7 from scramble bag
        Map<Integer, Tile> computerPlayerMap = new HashMap<>();
        for(int i=0; i<7; i++){
            computerPlayer.take1(computerPlayerMap, listOfTiles);
        }
        System.out.println("computerplayer: "+ computerPlayerMap.entrySet());
        System.out.println("----------------------------------------");
        //System.out.println("after both player take, Now bag left: "+ listOfTiles.entrySet());

        //////////////////////////////////////////////////////////////////////////////////////////////
        //player take up to 7 scrambles from scramble bag
        Map<Integer, Tile> humanPlayerMap = new HashMap<>();
        for(int i=0; i<7; i++){
            humanPlayer.take1(humanPlayerMap, listOfTiles);
        }
        System.out.println("humanPlayer: "+ humanPlayerMap.entrySet());



        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //human player add the tile to board
        System.out.println("Please choose the key for the tile you want to add to the board: ");
        Scanner scan_tile_key = new Scanner(System.in);
        int tile_inputKey = scan_tile_key.nextInt();
        System.out.println("You choose : "+humanPlayerMap.get(tile_inputKey));
        scan_tile_key.close();


        System.out.println(" please choose the row you want to put: ");
        Scanner scan_row = new Scanner(System.in);
        int row_input =  scan_row.nextInt();
        System.out.println("scan_row = " + row_input);
        scan_row.close();

        System.out.println(" please choose the column you want to put: ");
        Scanner scan_column = new Scanner(System.in);
        int column_input = scan_column.nextInt();
        System.out.println("scan_column = " + column_input);
        scan_column.close();

        //add tile into the board
        square[row_input][column_input].setTile(humanPlayerMap.get(tile_inputKey));
        for(int i= 0; i< dimension; i++){
            for(int j=0; j< dimension; j++){
                System.out.print(square[i][j] +" ");
            }
            System.out.println();
        }


    }
    */

    }
}

