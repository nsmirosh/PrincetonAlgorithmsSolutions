package week1.first_task;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        int counter = 1;
        String champion = null;
        while(!StdIn.isEmpty()) {
            boolean isChampion = StdRandom.bernoulli(1.0 / counter);
            String word = StdIn.readString();

            if (isChampion) {
                champion = word;
            }
            counter++;
        }
        System.out.println(champion);
    }
}
