package Scrable;

import java.io.*;
import java.util.ArrayList;
public class testing {
    public static void main(String[] args)
    {
        // creating ArrayList
        ArrayList<Character> my_list = new ArrayList<>();

        // adding elements
        my_list.add('a');
        my_list.add('b');
        my_list.add('c');
        my_list.add('t');
        my_list.add('k');
        my_list.add('s');
        my_list.add('p');
        my_list.add('v');

    // loop to print elements at randonm
        for (int i = 0; i < my_list.size(); i++)
    {
        // generating the index using Math.random()
        int index = (int)(Math.random() * my_list.size());

        System.out.println("Random Element is :"
                + my_list.get(index));
    }
}

}
