package Scrable;

public class Position {
    int row;
    int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean comparePos(Position position){
        if(this.getRow()==position.getRow() && this.getColumn()== position.getColumn()){
            return true;
        }
        return false;
    }



}
