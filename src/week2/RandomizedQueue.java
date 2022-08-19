package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    int first, last;
    Item[] s;
    int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        System.out.println("s before enqueue");

        printArray();

        if (item == null) throw new IllegalArgumentException();
        N++;
        if (first + N == s.length) resize(s.length * 2);
        s[last++] = item;
        System.out.println("s after enqueue");
        printArray();

    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        int i = 0;
        int j = 0;
        while (i < N) {
            Item result = s[first + i];
            if (result != null) {
                copy[j] = result;
                j++;
            }
            i++;
        }

        first = 0;
        last = N - 1;
        s = copy;
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

        if (N > 0 && N == s.length / 4) resize(s.length / 2);


        System.out.println("s after dequeue");

//        System.out.println("dequeud random text = " + ((Balls) randomValue).text);

        printArray();


        return randomValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        return s[StdRandom.uniform(N) + first];
    }


    private void printArray() {
/*        for (int i = 0; i < s.length; i++) {
            if (s[i] == null) {
                System.out.println("s[" + i + "] = null");

            } else {
                System.out.println("s[" + i + "] = " + ((Balls) s[i]).text);
            }
        }

        System.out.println("===========");
        System.out.println();*/
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
    }

}