package Scrable;

import java.util.List;

import static Scrable.MainView.*;

public class solveState {
    Trie dictionary;
    //Position position =new Position(0,0);
   // static String rack = new String();

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
    //cross check: every time put down a tile on a position on the board, look up and down, collect all letters from up and down.
    // combine this letter you want to put on the board. put them into dictionary to check if it is a word or not
    public static char[] cross_check(Position position){
        int row = position.row;
        int column = position.column;
        String try_word;
        char[] legal_here;
        String letters_up="";
        String letters_down = "";
        for(int i=1; squares[row-i][column].is_filled(row, column,board_size); i++){
            letters_up = squares[row-i][column].getTile().getLetter() + letters_up;
        }
        for(int i=1; squares[row+i][column].is_filled(row, column,board_size); i++){
            letters_down = squares[row+i][column].getTile().getLetter() + letters_down;
        }
        if(letters_up.equals("") && letters_down.equals("")){
            legal_here = 'abcdefghijklmnopqrstuvwxyz';
        }else{
            legal_here ='';
            String s = "abcdefghijklmnopqrstuvwxyz";
            char[] letter_charArr = s.toCharArray();
            for(char letter: letter_charArr){
                try_word = letters_up + letter + letters_down;
                if(Trie.trieSearch(try_word)){
                    legal_here.append(letter);
                }
            }
        }
        return legal_here;

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //find anchors. if a empty spot's  one of the neighbors is_filled, then this is a anchor
    public static List<Position> find_anchor(int board_size, Square[][] squares, List<Position> anchors, String partial_word){
        Position scan_pos = new Position(0,0);
        Position positions;
        //String scan_prefix= new String();
        for(int row=0; row< board_size; row++){
            for(int col=0; col< board_size; col++){
                positions=new Position(row, col);
                if(squares[row][col].is_empty(row, col, board_size)) {  // if this square is empty
                    // left square
                    if(positions.column-1 >=0  && positions.column-1 <board_size){
                        if (squares[positions.row][positions.column-1].is_filled(positions.row, positions.column-1, board_size)){
                            anchors.add(positions); // add this position into anchors' list
                            System.out.println("Find anchor @ position = ("+ positions.getRow() +", "+ positions.getColumn()+") ");
                        }
                    }
                    // right square
                    if(positions.column+1 >=0 && positions.column+1 <board_size){
                        if (squares[positions.row][positions.column+1].is_filled(positions.row, positions.column+1, board_size)){
                            anchors.add(positions); // add this position into anchors' list
                            System.out.println("Find anchor @ position = ("+ positions.getRow() +", "+ positions.getColumn()+") ");
                        }
                    }
                    // up square
                    if(positions.row-1 >=0 && positions.row-1< board_size){
                        if (squares[positions.row-1][positions.column].is_filled(positions.row-1, positions.column, board_size)){
                            anchors.add(positions); // add this position into anchors' list
                            System.out.println("Find anchor @ position = ("+ positions.getRow() +", "+ positions.getColumn()+") ");
                        }
                    }
                    // down square
                    if(positions.row+1 >=0 && positions.row+1 < board_size){
                        if (squares[positions.row+1][positions.column].is_filled(positions.row+1, positions.column, board_size)){
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
    // find all possible letters combination ---
    public static void left_part(String partial_word, TrieNode current_node, Position anchor_position){
        // computer rack--- stringBuffer : can add remove char easily. but for_each loop doesn't appy to stringBuffer
        // computer rack string  turn into charArr[] can not add and remove char easily because it has to create a new arraylist everytime
        // this function has find the possible words which is not from dictionary. it seems like not working. also rack.append(key) this does rotate the letter in the stringBuffer
       extend_right(partial_word,  current_node , anchor_position);
        if(current_node.isWord && current_node != Trie.root){ // what can we put before the anchor
           legal_move(partial_word);
       }
        for(char key: current_node.children.keySet()) {
            for(int i=0; i< rack.length(); i++){
                if(key==rack.charAt(i)){
                    rack.deleteCharAt(i);
                    left_part(partial_word+=key, current_node.children.get(key), anchor_position);

                }
            }
            //how to go to sibling node
        }
    }
    ///////////////////////////////////////////////////////////////
    // find all possible letters combination -- from a letter one the left and we extend to the right
    public static void extend_right(String partial_word, TrieNode current_node , Position next_position) {
        // computer rack--- stringBuffer : can add remove char easily. but for_each loop doesn't appy to stringBuffer
        // computer rack string  turn into charArr[] can not add and remove char easily because it has to create a new arraylist everytime
        // this function has find the possible words which is not from dictionary. it seems like not working. also rack.append(key) this does rotate the letter in the stringBuffer
        double[][] square;
        if (current_node.isWord && current_node != Trie.root && !square[next_position.row][next_position.column].is_fill()) { // make sure there is no more tile come after
            legal_move(partial_word);
        }

        if (next_position.row >= 0 && next_position.row < board_size && next_position.column >= 0 && next_position.column < board_size) {// make sure it is in bound
            for (char key : current_node.children.keySet()) {
                if (square[next_position.row][next_position.column].is_empty()) { // if this position is empty, put tile on it
                    for (int i = 0; i < rack.length(); i++) {
                        if (key == rack.charAt(i)) {
                            int row = next_position.row;
                            int column = next_position.column;

                            Position position= new Position(row, column);
                            char[] checkList = cross_check(position);
                             for(char checkChar: checkList) {
                                 if (checkChar == key) {
                                     rack.deleteCharAt(i);
                                     next_position.column += 1; // what is different with .getcolumn????
                                     extend_right(partial_word += key, current_node.children.get(key), next_position);
                                 }
                             }
                            //how to go to sibling node
                        }
                    }
                } else { // if this square has existing letter, we use this letter to check if dictionary allow it
                    String letter_existONTile = square[next_position.row][next_position.column].getTile().getLetter();  // use this letter
                    char[] char_OnTile = letter_existONTile.toCharArray();
                    if (key == char_OnTile[0]) { // check if this letter in the dictionary
                        next_position.column += 1; // what is different with .getcolumn????
                        extend_right(partial_word += key, current_node.children.get(key), next_position); // use it for recursion
                    }
                }
            }
        }
    }


    public static void all_possible_word(int board_size, Square[][] squares, List<Position> anchors, String partial_word){
        List<Position> anchor_position = find_anchor(board_size, squares, anchors, partial_word);
        //loop through every anchor position and find the legal move for each spot
        for(int i=0; i< anchor_position.size(); i++) { // loop through every anchor
            int row= anchor_position.get(i).row;
            int column =anchor_position.get(i).column;
            if(square[row][column-1].is_filled(row, column-1, board_size)) {  // if anchor's left side is filled
                int j=0;
                    while(squares[row][column-1-j].is_filled(row, column-1-j, board_size)) {  // while square is filled, continue go left
                        // add this letter to beginning of partial_word
                        partial_word = squares[row][column-1-j].getTile().getLetter() + partial_word;
                        scan_pos.row=positions.row;
                        scan_pos.column = positions.column-1-i;
                        System.out.println("scan_pos.row = " + scan_pos.row+ ", column = " + scan_pos.column);
                        j++;
                    }
                //after done all scan word, use trie dictionary to look up if it is in dictionary
                TrieNode pw_node = Trie.startWith(partial_word);
                if(pw_node != null) {
                    extend_right(partial_word,  pw_node , anchor_position.get(i)); // use extend_right
                }
                }else{ //if anchor's left side is not filled
                left_part("", Trie.root, anchor_position.get(i)); // use left_part() function

            }
        }
    }
    }

}
