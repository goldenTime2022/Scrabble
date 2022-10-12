package Scrable;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    static final int ALPHABET_SIZE = 26;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;

    //int wordCount;
   // TrieNode(){
        //isWord = false;
        //for(int i=0; i < ALPHABET_SIZE; i++){
            //children[i] =null;
       // }
        //wordCount = 0;
}
