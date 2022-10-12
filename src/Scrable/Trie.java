package Scrable;

public class Trie {
    static final int ALPHABET_SIZE = 26;
    static TrieNode root = new TrieNode() ;

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
        int wordCount;
        TrieNode(){
            isEndOfWord = false;
            for(int i=0; i < ALPHABET_SIZE; i++){
                children[i] =null;
            }
            wordCount = 0;
        }
    }

    public static void insert(String s){
        TrieNode current = root;
        for (char c: s) {
            if(current.children[c] == null){
                current.children[c] = new TrieNode();
            }
            //update currentNode
            current = current.children[c];
        }
    }
    public static boolean trieSearch(String s){
        TrieNode current = root;
        for(char c: s){
            if(current.children[c] == null){
                return false;
            }
            current = current.children[c];
        }
        return true;
    }

}
