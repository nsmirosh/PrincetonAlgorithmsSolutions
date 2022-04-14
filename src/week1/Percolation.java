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
        printGrid();
        quickFind = new QuickFindUF(n * n);
    }

    public void open(int row, int col) {

        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }
        boolean upOpen = false;
        if (row > 1) {
            upOpen = isOpen(row - 1, col);
        }

        boolean bottomOpen = false;
        if (row < n) {
            bottomOpen = isOpen(row + 1, col);
        }

        boolean leftOpen = false;
        if (col > 1) {
            leftOpen = isOpen(row, col - 1);
        }

        boolean rightOpen = false;
        if (col < n) {
            rightOpen = isOpen(row, col + 1);
        }


        int p = getUnderlyingPos(row, col);

        if (upOpen) {
            int q = getUnderlyingPos(row - 1, col);
            quickFind.union(p, q);
        }

        if (bottomOpen) {
            int q = getUnderlyingPos(row + 1, col);
            quickFind.union(p, q);
        }

        if (leftOpen) {
            int q = getUnderlyingPos(row , col - 1);
            quickFind.union(p, q);
        }

        if (rightOpen) {
            int q = getUnderlyingPos(row , col + 1);
            quickFind.union(p, q);
        }


        //TODO if multiple adjacent site are open - check if they're in the same component first
        //TODO if not - unite them altogether



        grid[row - 1][col - 1] = --row * n + col;


        // TODO if the site is being opened between already open sites - make sure to connect them altogether


        quickFind.union(row, col);
    }


    private int getUnderlyingPos(int row, int col) {
        return (row - 1) * n + (col -1);
    }


    private int getUnderlyingValueInPos(int row, int col) {
        return quickFind.find((row - 1) * n + (col -1));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }

        return grid[--row][--col] != -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }

        int item = grid[--row][--col];

        for (int i = 0; i < n; i++) {
            grid[row]
        }



        /*

        check if id[row + col] ==
         */

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

    public void printGrid() {
        System.out.println("grid = " + Arrays.deepToString(grid));
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);

        percolation.open(2, 3);
        percolation.open(3, 2);
        percolation.printGrid();

        testIsOpen(percolation);
    }

    private static void testIsOpen(Percolation percolation) {
        System.out.println("percolation.isOpen(3, 2) = " + percolation.isOpen(3, 2));
        System.out.println("percolation.isOpen(2, 3) = " + percolation.isOpen(2, 3));
        System.out.println("percolation.isOpen(3, 3) = " + percolation.isOpen(3, 3));
    }
}