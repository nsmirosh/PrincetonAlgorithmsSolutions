package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeWithLogs<Item> implements Iterable<Item> {


    private Node first, last;

    private int n;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public DequeWithLogs() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }


    private void printFirstLast() {
        if (first != null) {
            System.out.println("first value is " + first.item);
        } else {
            System.out.println("first == null");

        }
        if (last != null) {
            System.out.println("last value is " + last.item);
        } else {
            System.out.println("last == null");
        }

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
        if (isEmpty()) last = first;
        else first.next = oldFirst;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        n++;
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        n--;
        Node oldFirst = first;
        first = first.next;
        if (isEmpty()) last = null;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        n--;
        Node oldLast = last;
        last = last.prev;
        if (isEmpty()) first = last;
        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return last != null;
            }

            @Override
            public Item next() {
                Node oldFirst = first;
                if (oldFirst == null) throw new NoSuchElementException();
                first = first.next;
                if (isEmpty()) last = null;
                return oldFirst.item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {


       /* Deque<String> myDeque = new Deque<>();
        System.out.println("myDeque.isEmpty() = " + myDeque.isEmpty());
        myDeque.addFirst("balls");
        myDeque.addLast("last");
        myDeque.addFirst("first");
        myDeque.removeFirst();
        myDeque.removeFirst();
        //should print "last" after that
        System.out.println(" test 1 =================");


        for (String string : myDeque) {
            System.out.println("item = " + string);
        }



         Deque<String> myDeque2 = new Deque<>();

        System.out.println("\n test 2  ================= \n ");

        System.out.println("myDeque.isEmpty() = " + myDeque2.isEmpty());

        myDeque2.addFirst("balls");
        myDeque2.removeLast();
        myDeque2.printFirstLast();
        myDeque2.addLast("last");
        myDeque2.removeFirst();
        myDeque2.addFirst("first");
        //should print "first" after that

       for (String string : myDeque2) {
            System.out.println("item = " + string);
        }*/

    }
}