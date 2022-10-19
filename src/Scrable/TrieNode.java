package Scrable;

import com.sun.jdi.Value;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    boolean isWord;
    HashMap<Character, TrieNode> children;
    public TrieNode(){
        this.isWord = false;
        children= new HashMap<>();
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public HashMap<Character, TrieNode>  getChildren() {
        return children;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }
}
