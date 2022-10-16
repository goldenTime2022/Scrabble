package Scrable;

public class Square {
    int wordMultiplier;
    int letterMultiplier;
    Tile tile;
    boolean occupied;

    public Square(int wordMultiplier, int letterMultiplier, boolean occupied, Tile tile) {
        this.wordMultiplier = wordMultiplier;
        this.letterMultiplier = letterMultiplier;
        this.tile = tile;
        this.occupied = occupied;
    }

    public int getWordMultiplier() {
        return wordMultiplier;
    }

    public void setWordMultiplier(int wordMultiplier) {
        this.wordMultiplier = wordMultiplier;
    }

    public int getLetterMultiplier() {
        return letterMultiplier;
    }

    public void setLetterMultiplier(int letterMultiplier) {
        this.letterMultiplier = letterMultiplier;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public String toString() {
        String firstSpace = null;
        String secondSpace =null;
        String returnStatement=null;
        if(occupied == false) {
            if(wordMultiplier == 0 && letterMultiplier == 0){
                firstSpace = ".";
                secondSpace = ".";
            }else if (wordMultiplier == 0) {
                firstSpace = ".";
                secondSpace = Integer.toString(letterMultiplier);
            } else if (letterMultiplier == 0) {
                firstSpace = Integer.toString(wordMultiplier);
                secondSpace = ".";
            }
            returnStatement = firstSpace + secondSpace;
        }else{
            returnStatement = " "+tile.getLetter();
        }
        return returnStatement;
    }
}
