package Scrable;

import java.util.*;

public class HumanPlayer {
    public static void take1(List<Tile> humanPlayerTray, List<Tile> listOfTiles){
        Random random = new Random();
        int index = random.nextInt(listOfTiles.size());
        //System.out.println("Random Element is :" + listOfTiles.get(index));
        humanPlayerTray.add(listOfTiles.get(index));
        listOfTiles.remove(listOfTiles.get(index));

    }
}
