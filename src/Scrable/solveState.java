package Scrable;

import java.util.ArrayList;
import java.util.List;

public class solveState {
    Trie dictionary;

    public static void legal_move(String word){
        System.out.println("Found a word: "+ word);
    }

    public static List<String> all_possible_word(String partial_word, TrieNode currentNode, List<Tile> computerPlayerTray){
        List<String> all_possible_list =new ArrayList<>();
        if(currentNode.isWord){
            legal_move(partial_word);
            all_possible_list.add(partial_word);
        }
        for(Character nextLetter: currentNode.children.keySet()){
            if(computerPlayerTray.contains(nextLetter)){
                computerPlayerTray.remove(nextLetter);
                all_possible_word(partial_word+nextLetter, currentNode.children[nextLetter], computerPlayerTray);
            }
            computerPlayerTray.add(nextLetter);
        }

        return all_possible_list;
    }

}
