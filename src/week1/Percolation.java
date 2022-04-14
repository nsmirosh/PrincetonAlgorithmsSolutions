package week1;

import edu.princeton.cs.algs4.QuickFindUF;

import java.util.Arrays;

public class Percolation {

    QuickFindUF quickFind = null;

    private int[][] grid;

    int n = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        grid = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = -1;
            }
        }
        System.out.println("grid = " + Arrays.deepToString(grid));
        quickFind = new QuickFindUF(n * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
/*
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }

        boolean upOpen = isOpen(row - 1, col);
        boolean bottomOpen = isOpen(row + 1, col);



        boolean rightOpen = false;
        if (col + 1 <= n) {
            rightOpen = isOpen(row, col + 1);
        }
        if (col )
        boolean leftOpen = isOpen(row, col - 1);*/




        // TODO if the site is being opened between already open sites - make sure to connect them altogether


        quickFind.union(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }



        return/* id[row + col] != -1;*/ false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {


        for (int i = 0; i < n; i++) {

        }



        /*

        check if id[row + col] ==
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


        Percolation percolation = new Percolation(3);

    }
}