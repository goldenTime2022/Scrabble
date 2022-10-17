package Scrable;

public class Tile {
    String letter;
    int value;

    public Tile(String letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    public String getLetter(){
        return this.letter;
    }
    public int getValue(){
        return this.value;
    }
    public void setLetter(String letter) {
        this.letter = letter;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String toString(){
        return letter+" "+ value;
        //return ""+ letter +"{" + value +"}";
    }

    public boolean equalsTile(String letter){
        if(this.letter.equals(letter)){
            return true;
        }else{
            return false;
        }
    }
}
