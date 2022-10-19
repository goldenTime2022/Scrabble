package Scrable;

import java.util.ArrayList;
import java.util.List;

public class solveState {
    Trie dictionary;

    public static void legal_move(StringBuffer word){
        System.out.println("Found a letter combination: "+ word);
    }

    ///////////////////////////////////////////////////////////////
    // find all possible letters combination --- back tracking
    public static void possible_combination(StringBuffer partial_word, TrieNode current_node, StringBuffer rack){
       if(current_node.isWord){
           legal_move(partial_word);
       }
       for(Character nextLetter: current_node.children.keySet()){
           if(rack.indexOf(nextLetter.toString()) != -1){
               rack.deleteCharAt(rack.indexOf(Character.toString(nextLetter)));
               possible_combination(partial_word, current_node, rack);
           }
           rack.append(nextLetter);
       }

    }


    public static void all_possible_word(StringBuffer rack){
        possible_combination( null, Trie.root, rack);
    }

}
