package week2;

public class RandomizedQueueTests {

    // unit testing (required)
    public static void main(String[] args) {
        test1();
        increasing_the_array_works_ok();
        decreasing_and_increasing_the_array_works_ok();
        testSample();
        testEmptyQueue();
        testIterator();

    }


    public static void test1() {
        RandomizedQueue<Balls> queue = new RandomizedQueue<>();

        queue.enqueue(new Balls("A"));
        queue.enqueue(new Balls("B"));
        queue.enqueue(new Balls("C"));


        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }


    public static void increasing_the_array_works_ok() {
        RandomizedQueue<Balls> queue = new RandomizedQueue<>();

        queue.enqueue(new Balls("A"));
        queue.enqueue(new Balls("B"));
        queue.enqueue(new Balls("C"));
        queue.enqueue(new Balls("D"));
        queue.enqueue(new Balls("E"));
    }


    public static void decreasing_and_increasing_the_array_works_ok() {
        RandomizedQueue<Balls> queue = new RandomizedQueue<>();

        queue.enqueue(new Balls("A"));
        queue.enqueue(new Balls("B"));
        queue.enqueue(new Balls("C"));
        queue.enqueue(new Balls("D"));

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        queue.enqueue(new Balls("A"));
        queue.enqueue(new Balls("B"));
    }

    public static void testSample() {
        RandomizedQueue<Balls> queue = new RandomizedQueue<>();

        queue.enqueue(new Balls("A"));
        queue.enqueue(new Balls("B"));
        System.out.println(" queue sample = " + queue.sample().text);

    }


    public static void testEmptyQueue() {
        RandomizedQueue<Balls> queue = new RandomizedQueue<>();
        queue.dequeue();
    }


    public static void testIterator() {
        RandomizedQueue<Balls> queue = new RandomizedQueue<>();

        queue.enqueue(new Balls("A"));
        queue.enqueue(new Balls("B"));
        queue.enqueue(new Balls("C"));
        queue.enqueue(new Balls("D"));


        for (Balls balls: queue) {
            System.out.println("balls = " +  balls);
        }
    }


        /*




       https://www.coursera.org/learn/algorithms-part1/discussions/forums/jlI-WTmaEeaA1Q4yX3ldsQ/threads/OdA9evssEeaYlAo23CV3mg

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
