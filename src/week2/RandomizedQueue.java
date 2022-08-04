package week2;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    Node first, last;

    //must support constant amortized time for each queue operation
    Item [] array = new Item[1];

    int N;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        N++;
        Node oldLast = last;
        last = new Node();
        last.next = null;
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    // remove and return a random item
    public Item dequeue() {

        int random = StdRandom.uniform(N);

        return Item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Item next() {
                return null;
            }
        };

    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}