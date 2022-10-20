package Scrable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Board<Positions> {
    int size;
    List<Position> all_position;
    //Tile[] all_tiles;
    Square[][] square;

    public Board(int size, Square[][] square) {
        this.size = size;

        for(int row=0; row<size; row++){
            for(int column=0; column<size; column++){
                this.square[row][column]=null;    ////build up the empty tiles at every spots
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Position> all_position(){
        for(int i=0; i< size; i++){
            for(int j=0; j<size; j++){
                all_position.add(new Position(i,j));
            }
        }
        return all_position;
    }

    ////////////////////////////////////////////////////////////////////////
    //make copy of new board
    public Board copy(Board board){
        Board newBoard = new Board(size, square);
        for(Position position: all_position()){
            this.square[position.row][position.column] = newBoard.square[position.row][position.column];
        }
        return newBoard;
    }


}
