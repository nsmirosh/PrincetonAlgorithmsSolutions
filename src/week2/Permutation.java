
package week2;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {


    public static void main(String[] args) {

        String value = StdIn.readLine();

        String[] array =  value.split(" ");


        System.out.println(" creating a  queue ");

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        for (
                int i = 0;
                i < array.length; i++) {
            System.out.println(" enqueueing = " + array[i]);
            queue.enqueue(array[i]);
        }

        while (!queue.isEmpty()) {
            System.out.println("AAAAAAAAAA queue.dequee = " + queue.dequeue());
        }
    }
}
