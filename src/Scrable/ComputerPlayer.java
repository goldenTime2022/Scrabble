package Scrable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ComputerPlayer {
    public static void take1(List<Tile> computerPlayerTray, List<Tile> listOfTiles){
        Random random = new Random();
        int index = random.nextInt(listOfTiles.size());
        //System.out.println("Random Element is :" + listOfTiles.get(index));
        computerPlayerTray.add(listOfTiles.get(index));
        listOfTiles.remove(listOfTiles.get(index));
    }
}
