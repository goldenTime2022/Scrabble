package Scrable;


import javafx.application.Application;
import javafx.stage.Stage;

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainView extends Application {
    //public static String rack = new String();
    public static int board_size=0;
    public static StringBuffer rack = new StringBuffer();
    public static Square[][] squares;

    //public static char[] charArr_rack;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        //////////////////////////////////////////////////////////////////////////////////////////////////
        // start scan the dictionary file and put it into Trie data structure
        //TrieNode root;
        File file = new File("C:\\Users\\yun\\IdeaProjects\\scrabblegame\\dictionaryFile\\sowpods.txt");
        try {
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                String s = scnr.nextLine();
                //root = new TrieNode();
                Trie.insert(s); // build up trie from dictionary
                //System.out.println(Trie.); // how to print out trie Data structure
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        //computer find out all possible words
//
//        rack.append('p');
//
//        rack.append('d');
//        rack.append('t');
//        rack.append('b');
//        rack.append('e');
//        rack.append('a');
//        solveState.all_possible_word();




        //////////////////////////////////////////////////////////////////////////////////////////////
        // scan the total tile into the tile_frequency_file and create the list of tiles
        File file_tile_frequency = new File("dictionaryFile/wordswithfriends_tiles.txt");
        int frequency;

        Tile tile = new Tile("0", 0);
        List<Tile> listOfTiles = new ArrayList<>();
        try {
            Scanner scnr_tile_frequency = new Scanner(file_tile_frequency);

            while (scnr_tile_frequency.hasNextLine()) {
                String[] values = scnr_tile_frequency.nextLine().split(" ");
                tile = new Tile(values[0], Integer.parseInt(values[1]));
                for (int i = 0; i < Integer.parseInt(values[2]); i++) {
                    listOfTiles.add(tile);
                }
            }
            scnr_tile_frequency.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        //start scan the board
        File fileBoard = new File("dictionaryFile/small_board.txt");
        try

    {
        Scanner scan_Board = new Scanner(fileBoard);
        String d = scan_Board.nextLine();
        int board_size = Integer.parseInt(d);
        String[] value;
        char[] charArr;
        Position position;
        squares = new Square[board_size][board_size];
        for (int row = 0; row < board_size; row++) {
            String line = scan_Board.nextLine();
            value = line.split(" ");
            for (int col = 0; col < board_size; col++) {
                charArr = value[col].toCharArray();
                //System.out.println("@ row = " + row + "; column = " + col +"; value = " + value[col] + "; charArr[0] = " + charArr[0] + "; charArr[1] = " + charArr[1] );
                position = new Position(row, col);
                squares[row][col] = new Square(position, 0, 0, false, null);
                if (charArr[0] == ' ') {
                    squares[row][col].setOccupied(true);
                } else if (charArr[0] == '.' && charArr[1] == '.') {
                    squares[row][col].setWordMultiplier(0);
                    squares[row][col].setLetterMultiplier(0);
                } else if (charArr[0] == '.') {
                    squares[row][col].setLetterMultiplier(charArr[1] - 48);
                } else if (charArr[1] == '.') {
                    squares[row][col].setWordMultiplier(charArr[0] - 48);
                }
                System.out.print(squares[row][col] + " ");
            }
            System.out.println();
        }
        scan_Board.close();


        //////////////////////////////////////////////////////////////////////////////////////////////
        //human player take up to 7 scrambles from scramble bag
        //Map<Integer, Tile> humanPlayerMap = new HashMap<>();
        List<Tile> humanPlayerTray = new ArrayList<>();
        System.out.println("Human tray: ");
        for (int i = 0; i < 7; i++) {
            HumanPlayer.take1(humanPlayerTray, listOfTiles);
            System.out.print(humanPlayerTray.get(i).getLetter() + " ");
        }
        System.out.println();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //human first add tiles to the board
        int humanSingleWordPoint = 0;
        int humanTotalPoint = 0;
        List<Tile> human_tryList = new ArrayList<>();
        System.out.println("Please choose 2 or more tiles you want to add to the board: ");
        Scanner scan_input = new Scanner(System.in);
        String tile_input = scan_input.next();
        char[] humanInputCharArr = tile_input.toCharArray();
        System.out.println(" tile_input = " + tile_input);

        //loop through the entail tray to find the match tile' letters and add to human_tryList and also delete from humanPlayerTray
        for (int j = 0; j < humanInputCharArr.length; j++) {
            for (int i = 0; i < humanPlayerTray.size(); i++) {
                if (humanPlayerTray.get(i).equalsTile(String.valueOf(humanInputCharArr[j]))) {
                    human_tryList.add(humanPlayerTray.get(i));
                    humanSingleWordPoint += humanPlayerTray.get(i).getValue();
                    //System.out.println("humanPlayerTray.get(i) = " + humanPlayerTray.get(i));
                    humanPlayerTray.remove(i);
                    break;
                }
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //human player's word is it legal
        System.out.println("Option -- human player's word legal check. Is it a legal word? -- " + Trie.trieSearch(tile_input));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //System.out.println("main---human_tryList stream: " + human_tryList.stream().toList());
        //System.out.println("main---humanPlayerTray stream: " + humanPlayerTray.stream().toList());
        //System.out.println("at beginning of human player's single word points = " + humanSingleWordPoint);
        System.out.println(" please choose the direction you want to put: (right for 'r'; down for 'd') ");
        String direction_human = scan_input.next();
        System.out.println("direction_human = " + direction_human);
        ///////////////////////////////////////////////////////////////////////
        //add scramble tiles into the board
        int wordX = 1;
        int letterX = 1;
        int rightAdjust = 0;
        int downAdjust = 0;
        int row_input = board_size / 2;
        int column_input = board_size / 2;
        //int row_input = 0;
        //int column_input = 0;
        //loop every single letter and put it on the board
        for (int i = 0; i < human_tryList.size(); i++) {
            if (direction_human.equals("r")) { // direction to "right"
                downAdjust = i;
                squares[row_input + rightAdjust][column_input + downAdjust].setTile(human_tryList.get(i));
                squares[row_input + rightAdjust][column_input + downAdjust].setOccupied(true);
                wordX = squares[row_input + rightAdjust][column_input + downAdjust].getWordMultiplier();
                letterX = squares[row_input + rightAdjust][column_input + downAdjust].getLetterMultiplier();
                if (wordX != 0) {
                    //System.out.println("human_tryList.get(i) = (" + human_tryList.get(i) + ") wordX: " + wordX);
                    humanSingleWordPoint *= wordX;
                } else if (letterX != 0) {
                    //System.out.println("human_tryList.get(i) = (" + human_tryList.get(i) + ") has letterX: " + letterX);
                    humanSingleWordPoint += (letterX - 1) * (human_tryList.get(i).getValue()); // letterX only for individual letter
                }
            } else if (direction_human.equals("d")) { // direction to "down"
                rightAdjust = i;
                squares[row_input + rightAdjust][column_input + downAdjust].setTile(human_tryList.get(i));
                squares[row_input + rightAdjust][column_input + downAdjust].setOccupied(true);
                wordX = squares[row_input + rightAdjust][column_input + downAdjust].getWordMultiplier();
                letterX = squares[row_input + rightAdjust][column_input + downAdjust].getLetterMultiplier();
                if (wordX != 0) {
                    //System.out.println("human_tryList.get(i) = (" + human_tryList.get(i) + ") wordX: " + wordX);
                    humanSingleWordPoint *= wordX;
                } else if (letterX != 0) {
                    //System.out.println("human_tryList.get(i) = (" + human_tryList.get(i) + ") has letterX: " + letterX);
                    humanSingleWordPoint += (letterX - 1) * (human_tryList.get(i).getValue()); // letterX only for individual letter
                }
            }
        }

        //empty the human_tryList
        human_tryList.clear();
        humanTotalPoint += humanSingleWordPoint;
        System.out.println("human player's single word points = " + humanSingleWordPoint);
        System.out.println("human player's points = " + humanTotalPoint);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // print out the board
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                System.out.print(squares[i][j] + " ");
            }
            System.out.println();
        }

        //////////////////////////////////////////////////////////////////////////////////////////////
        //computer's turn
        // whole game loop start from here
        //game stop when scrabble bag run out of scrabble, and one of player run out of scrabble
        List<Tile> computerPlayerTray = new ArrayList<>();
        while(listOfTiles.size()!=0 && (computerPlayerTray.size()!=0 || humanPlayerTray.size()!=0)) {
            ///////////////////////////////////////////////////////////////////////////////////
            //computer take up to 7 from scramble bag
            //static String rack = new String();
            System.out.println("Computer tray: ");
            for (int i = 0; computerPlayerTray.size() < 7; i++) {
                ComputerPlayer.take1(computerPlayerTray, listOfTiles);
                rack.append(computerPlayerTray.get(i).getLetter());
                System.out.print(computerPlayerTray.get(i).getLetter() + " ");
            }
            System.out.println();
            System.out.println(" --------------------------------------------------");
            System.out.println("computer rack :" + rack);
            System.out.println(" --------------------------------------------------");

            //////////////////////////////////////////////////////////////////////////////////////
            //computer find out all possible words
            String partial_word = new String();

            //SolveState.find_anchor(board_size, square, anchors);
            SolveState.all_possible_word(board_size, squares, partial_word);

            /////////////////////////////////////////////////////////////////////////////////
            //computer find all anchors
            //List<Position> anchors = new ArrayList<>();
            //solveState.find_anchor(board_size, square, anchors);


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //human player take up to 7 scrambles from scramble bag
            for (int i = humanPlayerTray.size(); i < 7; i++) {
                HumanPlayer.take1(humanPlayerTray, listOfTiles);
                //System.out.print(humanPlayerTray.get(i).getLetter() + " ");
            }
            System.out.println("Human tray has: ");
            for (int i = 0; i < 7; i++) {
                System.out.print(humanPlayerTray.get(i).getLetter() + " ");
            }
            System.out.println();

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            //human player add the tile to board
            humanSingleWordPoint = 0;
            //List<Tile> human_tryList = new ArrayList<>();
            System.out.println("Please choose 2 or more tiles you want to add to the board: ");
            tile_input = scan_input.next();
            humanInputCharArr = tile_input.toCharArray();
            System.out.println(" tile_input = " + tile_input);

            //loop through the entail tray to find the match tile' letters and add to human_tryList and also delete from humanPlayerTray
            for (int j = 0; j < humanInputCharArr.length; j++) {
                for (int i = 0; i < humanPlayerTray.size(); i++) {
                    if (humanPlayerTray.get(i).equalsTile(String.valueOf(humanInputCharArr[j]))) {
                        human_tryList.add(humanPlayerTray.get(i));
                        humanSingleWordPoint += humanPlayerTray.get(i).getValue();
                        //System.out.println("humanPlayerTray.get(i) = " + humanPlayerTray.get(i));
                        humanPlayerTray.remove(i);
                        break;
                    }

                }
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //human player's word is it legal
            System.out.println("Option---human player's word legal check: " + Trie.trieSearch(tile_input));
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //System.out.println("main---human_tryList stream: "+human_tryList.stream().toList());
            //System.out.println("main---humanPlayerTray stream: "+humanPlayerTray.stream().toList());

            //Scanner scan_input = new Scanner(System.in);
            System.out.println(" please choose the row you want to put: (the range is between 1 to " + board_size + ")");
            row_input = scan_input.nextInt();
            System.out.println("scan_row = " + row_input);

            System.out.println(" please choose the column you want to put: (the range is between 1 to " + board_size + ")");
            column_input = scan_input.nextInt();
            System.out.println("scan_column = " + column_input);


            System.out.println(" please choose the direction you want to put: (right for 'r'; down for 'd') ");
            direction_human = scan_input.next();
            System.out.println("direction_human = " + direction_human);

            //add scramble tiles into the board
            wordX = 1;
            letterX = 1;
            rightAdjust = 0;
            downAdjust = 0;
            for (int i = 0; i < human_tryList.size(); i++) {
                if (direction_human.equals("r")) {
                    downAdjust = i;
                    squares[row_input + rightAdjust - 1][column_input + downAdjust - 1].setTile(human_tryList.get(i));
                    squares[row_input + rightAdjust - 1][column_input + downAdjust - 1].setOccupied(true);
                    wordX = squares[row_input + rightAdjust - 1][column_input + downAdjust - 1].getWordMultiplier();
                    letterX = squares[row_input + rightAdjust - 1][column_input + downAdjust - 1].getLetterMultiplier();
                    if (wordX != 0) {
                        //System.out.println("human_tryList.get(i) = ("+ human_tryList.get(i)+") wordX: "+wordX);
                        humanSingleWordPoint *= wordX;
                    } else if (letterX != 0) {
                        //System.out.println("human_tryList.get(i) = ("+ human_tryList.get(i)+") has letterX: "+ letterX );
                        humanSingleWordPoint += (letterX - 1) * (human_tryList.get(i).getValue()); // letterX only for individual letter
                    }

                } else if (direction_human.equals("d")) {
                    rightAdjust = i;
                    squares[row_input + rightAdjust - 1][column_input + downAdjust - 1].setTile(human_tryList.get(i));
                    squares[row_input + rightAdjust - 1][column_input + downAdjust - 1].setOccupied(true);
                    wordX = squares[row_input + rightAdjust - 1][column_input + downAdjust - 1].getWordMultiplier();
                    letterX = squares[row_input + rightAdjust - 1][column_input + downAdjust - 1].getLetterMultiplier();
                    if (wordX != 0) {
                        //System.out.println("human_tryList.get(i) = ("+ human_tryList.get(i)+") wordX: "+wordX);
                        humanSingleWordPoint *= wordX;
                    } else if (letterX != 0) {
                        //System.out.println("human_tryList.get(i) = ("+ human_tryList.get(i)+") has letterX: "+ letterX );
                        humanSingleWordPoint += (letterX - 1) * (human_tryList.get(i).getValue()); // letterX only for individual letter
                    }
                }
            }
            humanTotalPoint += humanSingleWordPoint;
            System.out.println("human player's single word points = " + humanSingleWordPoint);
            System.out.println("human player's points = " + humanTotalPoint);
            //empty the human_tryList
            human_tryList.clear();

            for (int i = 0; i < board_size; i++) {
                for (int j = 0; j < board_size; j++) {
                    System.out.print(squares[i][j] + " ");
                }
                System.out.println();
            }
        }

        } catch(FileNotFoundException e){
                e.printStackTrace();
        }
    }
}



