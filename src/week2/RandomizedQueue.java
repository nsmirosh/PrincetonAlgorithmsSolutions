package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    int first, last;

    //must support constant amortized time for each queue operation
    Item[] s;

    int N;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[4];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return last - first;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        //grow the array when needed
        s[last++] = item;
        N++;

        System.out.println("s after enqueue");
        printArray();

    }

    // remove and return a random item
    public Item dequeue() {

        if (isEmpty()) throw new NoSuchElementException();

        int randomPosInActiveRange = StdRandom.uniform(N) + first;
        System.out.println("randomPosInActiveRange = " + randomPosInActiveRange);

        Item randomValue = s[randomPosInActiveRange];
        Item valueInFirst = s[first];

        s[randomPosInActiveRange] = valueInFirst;
        s[first++] = null;
        N--;

        System.out.println("s after dequeue");


        /*
       https://www.coursera.org/learn/algorithms-part1/discussions/forums/jlI-WTmaEeaA1Q4yX3ldsQ/threads/OdA9evssEeaYlAo23CV3mg
         */

        System.out.println("dequeud random text = " +((Balls) randomValue).text);

        printArray();


        return randomValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        return s[0];
    }


    private void printArray() {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == null) {
                System.out.println("s[" + i + "] = null");

            } else {
                System.out.println("s[" + i + "] = " + ((Balls) s[i]).text);
            }
        }

        System.out.println("===========");
        System.out.println();
    }


    int noOfItems = 0;

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        StdRandom.shuffle(s);

        noOfItems = s.length;

        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return noOfItems > 0;
            }

            @Override
            public Item next() {
                if (isEmpty()) throw new NoSuchElementException();
                return s[--noOfItems];
            }

            @Override
            public void remove() {
                Iterator.super.remove();
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Balls> queue = new RandomizedQueue<>();

        queue.enqueue(new Balls("A"));
        queue.enqueue(new Balls("B"));
        queue.enqueue(new Balls("C"));


        queue.dequeue();
        queue.dequeue();
        queue.dequeue();


//        queue.enqueue(new Balls("4"));

       /* for (Balls balls : queue) {
            if (balls == null) {
                System.out.println("item == null");
            } else {
                System.out.println("item  = " + balls.text);
            }
        }*/
    }



    /*

    1. Either enqueue or dequeue has to shuffle the items
        1a. in order to get constant amortized time we could keep track of the number of randomized items, dequeue them
        and when they end we can shuffle the rest of the items again.


                        	Randomized Queue
Non-iterator operations		Constant amortized time
Iterator constructor		linear in current # of items
Other iterator operations	Constant worst-case time
Non-iterator memory use 	Linear in current # of items
Memory per iterator	Constant	Linear in current # of items
     */



    /*

    TODO ADD the array based queue implemenation to Anki
     */

}