package Scrable;

import java.util.*;

public class humanPlayer {
    public static void take1(Map humanPlayerMap, Map listOfTiles){
        Random random = new Random();
        List<Integer> keys = new ArrayList<Integer>(listOfTiles.keySet());
        int k = keys.get( random.nextInt(keys.size()));
        if (listOfTiles.containsKey(k)) {
            humanPlayerMap.put(k, listOfTiles.get(k));
            listOfTiles.remove(k);
        }

    }
    public static Integer chooseKey(Map humanPlayerMap){
        Random random = new Random();
        List<Integer> keys = new ArrayList<Integer>(humanPlayerMap.keySet());
        int k = keys.get(random.nextInt(keys.size()));

        Scanner player_input = new Scanner(System.in);
        System.out.println("Which scrabble you want to put down?");
        System.out.println(humanPlayerMap.entrySet());
        System.out.println("humanPlayerMap has these key: " + humanPlayerMap.keySet());
        System.out.println("Enter your key here: ");
        int inputKey = player_input.nextInt();
        return inputKey;
    }
}
