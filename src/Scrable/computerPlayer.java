package Scrable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class computerPlayer {
    public static void take1(Map computerPlayerMap, Map listOfTiles){
        Random random = new Random();
        List<Integer> keys = new ArrayList<Integer>(listOfTiles.keySet());
        int k = keys.get( random.nextInt(keys.size()));
        if (listOfTiles.containsKey(k)) {
            computerPlayerMap.put(k, listOfTiles.get(k));
            listOfTiles.remove(k);
        }

    }
}