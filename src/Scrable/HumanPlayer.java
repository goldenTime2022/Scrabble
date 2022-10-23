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
   /* public static Integer chooseKey(Map humanPlayerMap){
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
    }*/
}
