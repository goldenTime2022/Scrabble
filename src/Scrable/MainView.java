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
        //////////////////////////////////////////////////////////////////////////////////////////////

        File file_tile_frequency = new File("dictionaryFile/wordswithfriends_tiles.txt");
        int frequency;

        Tile tile = new Tile('0',0);
        //hashmap store (tile, frequency)
        int tileCount=0;
        HashMap<Integer,Tile> listOfTiles = new HashMap<Integer,Tile>();
        try{
            Scanner scnr_tile_frequency = new Scanner(file_tile_frequency);

            while(scnr_tile_frequency.hasNextLine()){
                String[] values = scnr_tile_frequency.nextLine().split(" ");
                //System.out.println(values[0]);
               // System.out.println(values[1]);
                //System.out.println(values[2]);
                tile =new Tile(values[0].charAt(0), Integer.parseInt(values[1]));
                //System.out.println(tile);
                for(int i=0; i<Integer.parseInt(values[2]); i++) {
                    listOfTiles.put(tileCount, tile);
                    tileCount++;
                }
                //System.out.println( "1st = "+ values[0].charAt(0)  +"; 2nd = " +Integer.parseInt(values[1]) +"; 3rd = " + Integer.parseInt(values[2]));
               }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        //System.out.println();
        //System.out.println("scramble Bag: "+listOfTiles.entrySet());
        //System.out.println("----------------------------------------");
        //////////////////////////////////////////////////////////////////////////////////////////////
        //player take up to 7 scrambles from scramble bag
        Map<Integer, Tile> humanPlayerMap = new HashMap<>();
        for(int i=0; i<7; i++){
            humanPlayer.take1(humanPlayerMap, listOfTiles);
        }
        //System.out.println("humanPlayer: "+ humanPlayerMap.entrySet());
        //System.out.println("----------------------------------------");

        ///////////////////////////////////////////////////////////////////////////////////
        //computer take up to 7 from scramble bag
        Map<Integer, Tile> computerPlayerMap = new HashMap<>();
        for(int i=0; i<7; i++){
            computerPlayer.take1(computerPlayerMap, listOfTiles);
        }
        System.out.println("computerplayer: "+ computerPlayerMap.entrySet());
        System.out.println("----------------------------------------");
        //System.out.println("after both player take, Now bag left: "+ listOfTiles.entrySet());

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //humanPlayer.chooseKey(humanPlayerMap);// not sure if I need this method?????????????

////////////////////////////////////////////////////////////////////////////////////////
        //start scan the board
       /* File fileBoard = new File("dictionaryFile/scrabble_board.txt");
        try {
            Scanner scan_Board = new Scanner(fileBoard);
            String d = scan_Board.nextLine();
            int dimension = Integer.parseInt(d);
            String value;
            char[] charArr;
            scan_Board.useDelimiter(" ");
            Square[][] square = new Square[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if(scan_Board.hasNext()) {
                        value = scan_Board.next();
                        System.out.println("scan value: " + value);
                        charArr = value.toCharArray();
                        square[i][j] = new Square(0, 0, false);
                        System.out.println("charArr[0] = " + charArr[0]);
                        System.out.println("charArr[1] = " + charArr[1]);
                        if (charArr[0] == ' ') {
                            square[i][j].setOccupied(true);
                            //Tile tile = new Tile(charArr[1]);//  how to add tile into the board?????????????????
                            //System.out.println("tile = " + tile );
                        }else if(charArr[0] =='.' && charArr[1] =='.'){
                            square[i][j].setWordMultiplier(0);
                            square[i][j].setLetterMultiplier(0);
                        }else if(charArr[0] =='.'){
                            square[i][j].setWordMultiplier(charArr[1]-48);
                        }else if(charArr[1] == '.'){
                            square[i][j].setLetterMultiplier(charArr[0]-48);
                        }
                        System.out.println("square["+i+"]["+j+"] = "+square[i][j]);
                    }
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      */
      ///////////////////////////////////////////////////////////////////////////////////////////////

    }
}

