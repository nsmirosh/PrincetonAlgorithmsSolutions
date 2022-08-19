
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int noOfItems = 0;
    private int first, last;
    private Item[] s;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        n++;
        if (first + n == s.length) resize(s.length * 2);
        s[last++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        int i = 0;
        int j = 0;
        while (i < n) {
            Item result = s[first + i];
            if (result != null) {
                copy[j] = result;
                j++;
            }
            i++;
        }

        first = 0;
        last = n - 1;
        s = copy;
    }

    // remove and return a random item
    public Item dequeue() {

        if (isEmpty()) throw new NoSuchElementException();

        int randomPosInActiveRange = StdRandom.uniform(n) + first;

        Item randomValue = s[randomPosInActiveRange];
        Item valueInFirst = s[first];

        s[randomPosInActiveRange] = valueInFirst;
        s[first++] = null;
        n--;

        if (n > 0 && n == s.length / 4) resize(s.length / 2);

        return randomValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        return s[StdRandom.uniform(n) + first];
    }

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