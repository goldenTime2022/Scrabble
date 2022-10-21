package Scrable;

import java.util.HashMap;

public class TrieNode {
    private static final int ALPHABET_SIZE = 26;
        HashMap<Character, TrieNode> children;
        int index;
        boolean isWord;
        TrieNode(){
            isWord = false;
            children = new HashMap<>();
            /*for(int i=0; i< ALPHABET_SIZE; i++){
                children.put((char)(i+97), null);
            }*/
        }
    }

