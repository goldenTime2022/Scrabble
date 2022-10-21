package Scrable;

import java.util.ArrayList;
import java.util.List;
public class solveState {
    Trie dictionary;
    //Position position =new Position(0,0);

    /////////////////////////////////////////////////////////////////////////////////////
    public static Position left(Position position ){
        position.column -= 1;
        return position;
    }
    public static Position right(Position position ){
        position.column += 1;
        return position;
    }
    public static Position up(Position position ){
        position.row -= 1;
        return position;
    }
    public static Position down(Position position ){
        position.row += 1;
        return position;
    }

    ////////////////////////////////////////////////////////////////////////
    //find anchors
    public static List<Position> find_anchor(int boardSize, Square[][] squares, List<Position> anchors){
        Position positions;
        for(int row=0; row< boardSize; row++){
            for(int col=0; col< boardSize; col++){
                positions=new Position(row, col);
                if(squares[row][col].is_empty(row, col, boardSize)) {  // if this square is empty
                    // left square
                    if(positions.column-1 >=0  && positions.column-1 <boardSize){
                        if (squares[positions.row][positions.column-1].is_filled(positions.row, positions.column-1, boardSize)){
                            anchors.add(positions); // add this position into anchors' list
                            System.out.println("Find anchor @ position = ("+ positions.getRow() +", "+ positions.getColumn()+") ");
                        }
                    }
                    // right square
                    if(positions.column+1 >=0 && positions.column+1 <boardSize){
                        if (squares[positions.row][positions.column+1].is_filled(positions.row, positions.column+1, boardSize)){
                            anchors.add(positions); // add this position into anchors' list
                            System.out.println("Find anchor @ position = ("+ positions.getRow() +", "+ positions.getColumn()+") ");
                        }
                    }
                    // up square
                    if(positions.row-1 >=0 && positions.row-1< boardSize){
                        if (squares[positions.row-1][positions.column].is_filled(positions.row-1, positions.column, boardSize)){
                            anchors.add(positions); // add this position into anchors' list
                            System.out.println("Find anchor @ position = ("+ positions.getRow() +", "+ positions.getColumn()+") ");
                        }
                    }
                    // down square
                    if(positions.row+1 >=0 && positions.row+1 < boardSize){
                        if (squares[positions.row+1][positions.column].is_filled(positions.row+1, positions.column, boardSize)){
                            anchors.add(positions); // add this position into anchors' list
                            System.out.println("Find anchor @ position = ("+ positions.getRow() +", "+ positions.getColumn()+") ");
                        }
                    }
                }
            }
        }
        return anchors;
    }
    /////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void legal_move(String word){
        System.out.println("Found a letter combination: "+ word);
    }

    ///////////////////////////////////////////////////////////////
    // find all possible letters combination --- back tracking
    public static void all_words(String partial_word, TrieNode current_node, String rack){
       if(current_node.isWord ){
           legal_move(partial_word);
       }
        char[] charArr = rack.toCharArray();
            for(char key: current_node.children.keySet()) { //loop through current's children
                for (char curChar : charArr) { // loop through rack
                    if(key == curChar) {
                        partial_word+=curChar;
                        rack = rack.replace(String.valueOf(curChar), ""); // delete curChar from rack, because after next recursion, curChar need to add back to rack at the previous position, so just make a sub_rack put into recursion call
                        System.out.println("rack = "+ rack);
                        System.out.println();
                        all_words(partial_word, current_node.children.get(key), rack);
                        System.out.println("--------------------------------------");
                        rack += curChar;
                    }

                }
            }
       }

       /*
       for(int index: current_node.children.){
           if(rack.indexOf(nextLetter.toString()) != -1){
               rack.deleteCharAt(rack.indexOf(Character.toString(nextLetter)));
               possible_combination(partial_word, current_node, rack);
           }
           rack.append(nextLetter);
       }
    */

    public static void all_possible_word(String rack){
        all_words("", Trie.root, rack);
    }

}
