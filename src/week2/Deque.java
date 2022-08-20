package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private int n;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null && last == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        n++;
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (last == null) last = first;
        else  {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        n++;
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (first == null) first = last;
        else  {
            oldLast.next = last;
            last.prev = oldLast;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        n--;
        Node oldFirst = first;
        first = first.next;
        if (first == null) last = null;
        else {
            first.prev = null;
        }
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        n--;

        Node oldLast = last;
        Node prev  = last.prev;

        if (prev == null)  {
            first = null;
            last = null; // the queue is now empty, so assign first and last to null
        }
        else  {
            last = prev;
            last.next = null;
        }

        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Item next() {
                if (isEmpty()) throw new NoSuchElementException();
                return removeFirst();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
    //[UncommentedEmptyMethodBody]
    }
}