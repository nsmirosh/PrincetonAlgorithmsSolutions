package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    int first, last;

    //must support constant amortized time for each queue operation
    Item[] array;

    int N;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[4];
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
        array[last++] = item;
        for (Item value : array) {
            System.out.println("array[i] = " + value);
        }
        N++;
    }

    // remove and return a random item
    public Item dequeue() {

        if (isEmpty()) throw new NoSuchElementException();

        /*
       https://www.coursera.org/learn/algorithms-part1/discussions/forums/jlI-WTmaEeaA1Q4yX3ldsQ/threads/OdA9evssEeaYlAo23CV3mg
         */
        return array[0];
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        return array[0];
    }


    int currentIndex = 0;

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {


        //TODO shuffle only non-nulls and iterate only over non-nulls
        StdRandom.shuffle(array);

        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return N > 0;
            }

            @Override
            public Item next() {
                if (isEmpty()) throw new NoSuchElementException();

                //TODO make sure I'm not trying to acess a null here
                return array[N--];
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

        queue.enqueue(new Balls("my balls"));
        queue.enqueue(new Balls("my balls2"));
        queue.enqueue(new Balls("my balls3"));
//        queue.enqueue(new Balls("my balls4"));

        for (Balls balls : queue) {
            System.out.println("item 1 = " + balls.text);
        }

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