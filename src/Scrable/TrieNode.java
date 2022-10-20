package Scrable;

public class TrieNode {
    private static final int ALPHABET_SIZE = 26;
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        int index;
        boolean isWord;
        TrieNode(){
            isWord = false;
            for(int i=0; i< ALPHABET_SIZE; i++){
                children[i]=null;
                index=ALPHABET_SIZE-97;
            }
        }
        /*public static int find_index(){
            int i=0;
            while(i< 26){
                if(children[i].)
            }
            return index;
        }*/
    }

