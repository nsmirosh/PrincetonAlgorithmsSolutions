package week3;

public class MergeSort {

    private static<T> void sort(Comparable<T>[] a, Comparable<T>[] aux, int lo, int hi) {

        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static<T> void sort(Comparable<T>[] a) {
        Comparable<T>[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static<T> void merge(Comparable<T>[] a, Comparable<T>[] aux, int lo, int mid, int hi) {

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)  //merging
        {
            if (i > mid) a[k] = aux[j++]; //first sub array is exhausted, take only from the 2nd one now
            else if (j > hi) a[k] = aux[i++]; //second sub array is exhausted, take only from the 1st one now
            else if (less(aux[j], aux[i])) {

                System.out.println(aux[j] + " is less than " + aux[i]);
                a[k] = aux[j++]; //if the current element in 2nd array is smaller than the 1st move it to a[k]
            }

            else a[k] = aux[i++]; //else aux[i] (first half) is smaller than 2nd half, so move it a[k]
            //OR they're equal and we always take the element in the first subarray as well in such case

        }
    }

    private static boolean less(Comparable a, Comparable b) {
       return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Integer[] ballsList  = new Integer[6];
        ballsList[0] = 5;
        ballsList[1] = 7;
        ballsList[2] = 2;
        ballsList[3] = 0;
        ballsList[4] = 7;
        ballsList[5] = -10;



        System.out.println(less(ballsList[0], ballsList[1]));

        sort(ballsList);

        for(int i = 0; i < ballsList.length; i++) {
            System.out.println(ballsList[i]);
        }
    }
}

