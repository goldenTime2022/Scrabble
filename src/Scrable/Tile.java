package Scrable;

public class Tile {
    char letter;
    int value;


    public Tile(char letter, int value) {
        this.letter = letter;
        this.value = value;

    }

    public char getLetter(){
        return this.letter;
    }
    public int getValue(){
        return this.value;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public String toString(){
        return ""+ letter +"{" + value +"}";
    }
    public boolean equals(Tile tile){
        if(this.letter == tile.letter){
            return true;
        }else{
            return false;
        }
    }

}
