package Scrable;

import java.util.HashMap;

public class TrieNode {

    HashMap<Character, TrieNode> children;
    int index;
    boolean isWord;
    TrieNode(){
        isWord = false;
        children = new HashMap<>();

    }
}

