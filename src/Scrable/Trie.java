package Scrable;

import java.util.Locale;

public class Trie {
    static TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    public static void insert(String word){
        if(word == null || word.isEmpty()){
            throw new IllegalArgumentException("Invalid Input");
        }
        word= word.toLowerCase(); // change all word to lower case
        TrieNode current = root;  // every time insert start from root
        char[] charArr = word.toCharArray(); // separate string to char
        for (char curChar: charArr) { // loop through each individual char
           // int index = curChar - 'a';
            if(current.children.containsKey(curChar)){ // if current's child do not have this char
                TrieNode newTrieNode = new TrieNode();
                current.children.put( curChar,newTrieNode); // create new an empty Tried node
                current = newTrieNode; // move to next node
            }else{
                current = current.children.get(curChar);
            }

        }
        current.isWord=true;
    }
    public static boolean trieSearch(String word){
        word= word.toLowerCase(); // change all word to lower case
        TrieNode current = root;  // every time insert start from root
        char[] charArr = word.toCharArray(); // separate string to char
        for (char curChar: charArr) { // loop through each individual char
            int index = curChar - 'a';
            if(current.children.containsKey(curChar)){ // if current's child do not have this char
                return false; // give up
            }else{
                current = current.children.get(curChar);
            }

        }
        return current.isWord;
    }

}
