package Scrable;

public class Square {
    int wordMultiplier;
    int letterMultiplier;
    boolean occupied;

    public Square(int wordMultiplier, int letterMultiplier, boolean occupied) {
        this.wordMultiplier = wordMultiplier;
        this.letterMultiplier = letterMultiplier;
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

    @Override
    public String toString() {
        return "Square{" +
                "wordMultiplier=" + wordMultiplier +
                ", letterMultiplier=" + letterMultiplier +
                ", occupied=" + occupied +
                '}';
    }
}
