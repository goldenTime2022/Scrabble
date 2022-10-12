package Scrable;

public class Trie {
    static TrieNode root = new TrieNode() ;
    TrieNode[] children = new TrieNode[26];
    public Trie(){
        root = new TrieNode();
    }
    public static void insert(String word){
        TrieNode current = root;
        char[] charArr = word.toCharArray();
        for (char curChar: charArr) {
            if(current.children.containsKey(curChar) == false){
                current.children.put(curChar, new TrieNode());
            }
            //update currentNode
            current = current.children.get(curChar);
        }
        current.isWord=true;
    }
    public static boolean trieSearch(String word){
        TrieNode current = root;
        char[] charArr = word.toCharArray();
        for (char curChar: charArr) {
            if(current.children.containsKey(curChar) == false){
                return false;
            }
            //update currentNode
            current = current.children.get(curChar);
        }
        return current.isWord;
    }

}
