package week1;

import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {


//    private int[] id;

    QuickFindUF quickFind = null;

    int n = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
//        id = new int[n];
        this.n = n;
        quickFind = new QuickFindUF(n * n);

       /* for (int i = 0; i < n; i++) {
            id[i] = -1;
        }*/
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }

        boolean upOpen = isOpen(row - 1, col);
        boolean bottomOpen = isOpen(row + 1, col);

        boolean leftOpen = isOpen(row, col - 1);


        boolean rightOpen = false;
        if (col + 1 <= n) {
            rightOpen = isOpen(row, col + 1);
        }

        quickFind.union(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }


        return id[row + col] != -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {



        /*

        check if either top, left, right or bottom are not -1
         */
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {


    }
}