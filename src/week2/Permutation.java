
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        String stringK = args[0];
        int k = Integer.parseInt(stringK);

        String value = StdIn.readLine();

        String[] array = value.split(" ");

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        for (int i = 0;
             i < array.length; i++) {
            queue.enqueue(array[i]);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
