package Scrable;

import java.util.Locale;

public class Trie {
    //static final int ALPHABET_SIZE =26;
    static final TrieNode root= new TrieNode();

    public static void insert(String word) {
        word = word.toLowerCase(); // change all word to lower case
        TrieNode current = root;  // every time insert start from root
        char[] charArr = word.toCharArray(); // separate string to char
        int index;
        for (char curChar : charArr) { // loop through each individual char
            //index = curChar - 'a';
            if (current.children.get(curChar)==null) { // if current's child do not have this char
               current.children.put(curChar, new TrieNode());
            }
            current= current.children.get(curChar);
        }
            current.isWord = true;
        }
        public static boolean trieSearch (String word){
            word = word.toLowerCase(); // change all word to lower case
            TrieNode current = root;  // every time insert start from root
            char[] charArr = word.toCharArray(); // separate string to char
            int level;
            int index;
            int length= word.length();
            for (char curChar : charArr) { // loop through each individual char
                index = curChar - 'a';
                if (current.children.get(curChar)==null) { // if current's child do not have this char
                    return false;
                }
                current= current.children.get(curChar);
            }
            return current.isWord;
        }


}
