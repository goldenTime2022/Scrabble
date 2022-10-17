package Scrable;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

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

        //HashMap<Integer, Tile> listOfTiles = new HashMap<Integer, Tile>();
        List<Tile> listOfTiles = new ArrayList<>();
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
                    listOfTiles.add(tile);
                }
                //System.out.println( "1st = "+ values[0].charAt(0)  +"; 2nd = " +Integer.parseInt(values[1]) +"; 3rd = " + Integer.parseInt(values[2]));
            }
            System.out.println(listOfTiles.size());
            scnr_tile_frequency.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println();
        //System.out.println("scramble Bag: "+listOfTiles.entrySet());
        //System.out.println("----------------------------------------");


        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        //start scan the board
        File fileBoard = new File("dictionaryFile/small_board.txt");
        try {
            Scanner scan_Board = new Scanner(fileBoard);
            String d = scan_Board.nextLine();
            int dimension = Integer.parseInt(d);
            String[] value;
            char[] charArr;
            Square[][] square = new Square[dimension][dimension];
            for (int row = 0; row < dimension; row++) {
                String line = scan_Board.nextLine();
                value = line.split(" ");
                for (int col = 0; col < dimension; col++) {
                    charArr = value[col].toCharArray();
                    //System.out.println("@ row = " + row + "; column = " + col +"; value = " + value[col] + "; charArr[0] = " + charArr[0] + "; charArr[1] = " + charArr[1] );
                    square[row][col] = new Square(0, 0, false, null);
                    if (charArr[0] == ' ') {
                        square[row][col].setOccupied(true);
                    } else if (charArr[0] == '.' && charArr[1] == '.') {
                        square[row][col].setWordMultiplier(0);
                        square[row][col].setLetterMultiplier(0);
                    } else if (charArr[0] == '.') {
                        square[row][col].setLetterMultiplier(charArr[1] - 48);
                    } else if (charArr[1] == '.') {
                        square[row][col].setWordMultiplier(charArr[0] - 48);
                    }
                    System.out.print(square[row][col] + " ");
                }
                System.out.println();
            }
            scan_Board.close();

            ///////////////////////////////////////////////////////////////////////////////////
            //computer take up to 7 from scramble bag
            List<Tile> computerPlayerTray = new ArrayList<>();
            System.out.println("Computer tray: ");
            for (int i = 0; i < 7; i++) {
                computerPlayer.take1(computerPlayerTray, listOfTiles);
                System.out.print(computerPlayerTray.get(i).getLetter() +" ");
            }
            System.out.println();

            //////////////////////////////////////////////////////////////////////////////////////////////
            //player take up to 7 scrambles from scramble bag
            //Map<Integer, Tile> humanPlayerMap = new HashMap<>();
            List<Tile> humanPlayerTray = new ArrayList<>();
            System.out.println("Human tray: ");
            for (int i = 0; i < 7; i++) {
                humanPlayer.take1(humanPlayerTray, listOfTiles);
                System.out.print( humanPlayerTray.get(i).getLetter() +" ");
            }
            System.out.println();
            ///////////////////////////////////////////////////////////////////////////////////////////////////
            //human player add the tile to board
            int human_point=0;
            System.out.println("Please choose the tile you want to add to the board: ");
            Scanner scan_input = new Scanner(System.in);
            String tile_input = scan_input.next();
            char[] humanInputCharArr = tile_input.toCharArray();
            System.out.println(" tile_input = "+ tile_input);
            List<Tile> human_tryList = new ArrayList<>();
            for(int j=0; j< humanInputCharArr.length; j++){
                for(int i=0; i< humanPlayerTray.size(); i++) {
                    //System.out.println(humanPlayerTray.get(i).getLetter().equals(tile_input));
                    //System.out.println(humanPlayerTray.get(i).equalsTile(String.valueOf(humanInputCharArr[0])));
                    if (humanPlayerTray.get(i).equalsTile(String.valueOf(humanInputCharArr[j]))) {
                        human_tryList.add(humanPlayerTray.get(i));
                        human_point += humanPlayerTray.get(i).getValue();
                        System.out.println("humanPlayerTray.get(i) = " + humanPlayerTray.get(i));
                        humanPlayerTray.remove(i);
                        break;

                    }
                }
            }
            System.out.println("human_tryList stream: "+human_tryList.stream().toList());
            System.out.println("1. human player's point = "+ human_point);
            System.out.println("humanPlayerTray stream: "+humanPlayerTray.stream().toList());


            System.out.println(" please choose the row you want to put: (the range is between 1 to "+ dimension + ")");
            int row_input = scan_input.nextInt();
            System.out.println("scan_row = " + row_input);

            System.out.println(" please choose the column you want to put: (the range is between 1 to "+ dimension + ")");
            int column_input = scan_input.nextInt();
            System.out.println("scan_column = " + column_input);

            System.out.println(" please choose the direction you want to put: (right for 'r'; down for 'd') " );
            String direction_human = scan_input.next();
            System.out.println("direction_human = " + direction_human);

            //add scramble tiles into the board
            int wordX=1;
            int letterX=1;
            int rightAdjust=0;
            int downAdjust = 0;
            String direction;
            for(int i=0; i< human_tryList.size(); i++) {
                if(direction_human.equals("r")){
                    downAdjust=i;
                    square[row_input+rightAdjust-1][column_input+downAdjust-1].setTile(human_tryList.get(i));
                    square[row_input+rightAdjust-1][column_input+downAdjust-1].setOccupied(true);
                    if(square[row_input+rightAdjust-1][column_input+downAdjust-1].getWordMultiplier() != 0){
                        System.out.println("human_tryList.get(i) = ("+ human_tryList.get(i)+") wordX: "+square[row_input+rightAdjust-1][column_input+downAdjust-1].getWordMultiplier());
                        wordX= square[row_input+rightAdjust-1][column_input+downAdjust-1].getWordMultiplier();
                        human_point *= (wordX-1);
                    }else if (square[row_input+rightAdjust-1][column_input+downAdjust-1].getLetterMultiplier() !=0){
                        System.out.println("human_tryList.get(i) = ("+ human_tryList.get(i)+") has letterX: "+ square[row_input+rightAdjust-1][column_input+downAdjust-1].getLetterMultiplier() );
                        letterX = square[row_input+rightAdjust-1][column_input+downAdjust-1].getLetterMultiplier();
                        human_point += (letterX-1) * (human_tryList.get(i).getValue()); // letterX only for individual letter
                    }

                }else if(direction_human.equals("d")) {
                    rightAdjust = i;
                    square[row_input + rightAdjust-1][column_input + downAdjust-1].setTile(human_tryList.get(i));
                    square[row_input + rightAdjust-1][column_input + downAdjust-1].setOccupied(true);
                    if(square[row_input+rightAdjust-1][column_input+downAdjust-1].getWordMultiplier() != 0){
                        System.out.println("human_tryList.get(i) = ("+ human_tryList.get(i)+") wordX: "+square[row_input+rightAdjust-1][column_input+downAdjust-1].getWordMultiplier());
                        wordX= square[row_input+rightAdjust-1][column_input+downAdjust-1].getWordMultiplier();
                        human_point *= (wordX-1);
                    }else if (square[row_input+rightAdjust-1][column_input+downAdjust-1].getLetterMultiplier() !=0){
                        System.out.println("human_tryList.get(i) = ("+ human_tryList.get(i)+") has  letterX: "+ square[row_input+rightAdjust-1][column_input+downAdjust-1].getLetterMultiplier() );
                        letterX = square[row_input+rightAdjust-1][column_input+downAdjust-1].getLetterMultiplier();
                        human_point += (letterX-1) * (human_tryList.get(i).getValue()); // letterX only for individual letter
                    }
                }
            }

            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    System.out.print(square[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("2. human player's point = "+ human_point);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

