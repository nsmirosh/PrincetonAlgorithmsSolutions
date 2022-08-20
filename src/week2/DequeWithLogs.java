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
        return first == null && last == null;
    }


    private void printFirstLast() {
        if (first != null) {
            System.out.print("first == " + first.item + ", ");
        } else {
            System.out.print("first == null, ");
        }
        if (last != null) {
            System.out.println("last == " + last.item);
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
        first = first.next; //first.next can be null
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

        //if the queue is empty we throw an exception, so first and last are NOT null
        //check if oldLast has something in their prev
        //if it does = assign it to last
        //if it doesn't - that means that the queue is now empty so first and last == nullt;
        Node oldLast = last;
        Node prev  = last.prev;
        System.out.println("last.prev == " + last.prev);

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
               /* if (isEmpty()) throw new NoSuchElementException();
                n--;
                Node oldFirst = first;
                first = first.next;
                if (first == null) last = null;
                return oldFirst.item;*/

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


    /*    test1();
        test2();

        test3();

        test4();

        test7();*/

        test8();

    }



    public static void test1() {
        DequeWithLogs<String> myDeque = new DequeWithLogs<>();
        System.out.println("myDeque.isEmpty() = " + myDeque.isEmpty());
        myDeque.addFirst("AF");
        myDeque.addLast("BL");
        myDeque.addFirst("CF");
        myDeque.removeFirst();
        myDeque.removeFirst();
        System.out.println(" test 1 =================");

        for (String string : myDeque) {
            System.out.println("should print BL = " + string);
        }
    }

    public static void test3() {
        DequeWithLogs<String> myDeque = new DequeWithLogs<>();
        System.out.println("myDeque.isEmpty() = " + myDeque.isEmpty());
        myDeque.addFirst("AF");

        System.out.println("should print AF = " + myDeque.removeLast());

        myDeque.addFirst("AF");
        myDeque.addLast("BL"); //AF , BL
        myDeque.addFirst("CF"); //CF, AF , BL
        myDeque.addFirst("DF"); //DF, CF, AF , BL
        myDeque.addLast("EL"); //DF, CF, AF , BL, EL
        System.out.println("should print EL = " + myDeque.removeLast());

        System.out.println("should print DF = " + myDeque.removeFirst());

        System.out.println("should print BL = " + myDeque.removeLast());

      /*  System.out.println(" test 1 =================");

        for (String string : myDeque) {
            System.out.println("should print BL = " + string);
        }*/
    }


    public static void test2() {
        System.out.println("\n test 2  ================= \n ");
        DequeWithLogs<String> myDeque2 = new DequeWithLogs<>();

        System.out.println("myDeque.isEmpty() = " + myDeque2.isEmpty());

        myDeque2.addFirst("A"); //A
        myDeque2.removeLast(); //
        myDeque2.printFirstLast();
        myDeque2.addLast("B"); //B
        myDeque2.removeFirst(); //
        myDeque2.addFirst("C"); //C
    }

    public static void test4() {
        DequeWithLogs<Integer> deque = new DequeWithLogs<>();
        deque.addFirst(1);
        deque.printFirstLast();
        deque.addFirst(2);
        deque.printFirstLast();
        deque.addFirst(3);
        deque.printFirstLast();

        deque.addFirst(4);
        deque.printFirstLast();

        System.out.println("removing last, should be 1 = " + deque.removeLast());
        deque.printFirstLast();

        deque.printFirstLast();
        deque.addFirst(6);
        deque.addFirst(7);
        System.out.println("removing last, should be 2 = " + deque.removeLast());
    }



    public static void test6FromCoursera() {
        DequeWithLogs<Integer> deque = new DequeWithLogs<>();
        deque.addFirst(1);
        deque.addFirst(2);
        System.out.println("removing first, should be 2 == " + deque.removeFirst());
       deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        Iterator<Integer> iterator = deque.iterator();


        //[6, 5, 4, 1]
        while(iterator.hasNext()) {
            System.out.println(" item in iterator = " + iterator.next());
        }


        /* ==> [6, 5, 4, 1]*/
        deque.removeFirst();
    }


    public static void test7() {
        DequeWithLogs<Integer> deque = new DequeWithLogs<>();
        deque.addFirst(1);
        deque.addLast(2); // 1 , 2

        System.out.println("remove last should be 2 = " + deque.removeLast());
        System.out.println("remove last should be 1 = " + deque.removeLast());
        System.out.println("isEmpty should be true = " + deque.isEmpty());

        deque.addLast(4);
        deque.addFirst(5); //5, 4
        deque.addLast(6); //5 , 4, 6
        deque.addLast(7); // 5, 4, 6, 7

        System.out.println("isEmpty should be false ==" + deque.isEmpty());

        System.out.println("remove first should be 5 = " + deque.removeFirst());
        System.out.println("remove first should be 4 = " + deque.removeFirst());
        System.out.println("remove first should be 6 = " + deque.removeFirst());
        System.out.println("remove last should be 7 = " + deque.removeLast());
        System.out.println("isEmpty should be true ==" + deque.isEmpty());

        Iterator<Integer> iterator = deque.iterator();


        //[6, 5, 4, 1]
        while(iterator.hasNext()) {
            System.out.println(" item in iterator = " + iterator.next());
        }


        /* ==> [6, 5, 4, 1]*/
        deque.removeFirst();
    }

    private static void test8() {
        DequeWithLogs<Integer> deque = new DequeWithLogs<>();
        deque.addFirst(1);
        System.out.println("should be 1 ==" + deque.removeFirst());//     ==> 1
        deque.addFirst(3);
//        deque.iterator();   //==> [3]

        Iterator<Integer> iterator = deque.iterator();

        //[6, 5, 4, 1]
        while(iterator.hasNext()) {
            System.out.println(" item in iterator = " + iterator.next());
        }
        deque.removeFirst();
    }

}