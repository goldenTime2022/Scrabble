package Scrable;

public class Square {
    public static Position position;
    int wordMultiplier;
    int letterMultiplier;
    boolean occupied;
    Tile tile;

    public Square(Position position, int wordMultiplier, int letterMultiplier, boolean occupied, Tile tile) {
        this.position = position;
        this.wordMultiplier = wordMultiplier;
        this.letterMultiplier = letterMultiplier;
        this.occupied = occupied;
        this.tile = tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
        if(this.getTile()!=null){
            occupied=true;
        }else{
            occupied=false;
        }
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
    public boolean in_bounds(int row, int column ){
        if(row>=0 && row< MainView.squares.length && column>=0 && column < MainView.squares[0].length){
            return true;
        }
        return false;
    }
    public boolean is_empty(int row, int column){
        if(in_bounds(row, column)){
            if(this.getTile()==null){
                return true;
            }
        }
        return false;
    }
    public boolean is_filled(int row, int column){
        if(in_bounds( row, column)){
            if(this.getTile()!=null) {
                return true;
            }
        }
        return false;
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
