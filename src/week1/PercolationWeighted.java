package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationWeighted {

    WeightedQuickUnionUF weightedQuickFind;

    private final boolean[][] grid;

    int n;

    // creates n-by-n grid, with all sites initially blocked
    public PercolationWeighted(int n) {
        this.n = n;
        grid = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = false;
            }
        }
        printGrid();
        weightedQuickFind = new WeightedQuickUnionUF(n * n);
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
            System.out.println("open() upOpen");
            int q = getUnderlyingPos(row - 1, col);
            System.out.println("q = " + q);
            System.out.println("p = " + p);


            weightedQuickFind.union(p, q);
        }

        if (bottomOpen) {
            System.out.println("open() bottomOpen");

            int q = getUnderlyingPos(row + 1, col);
            weightedQuickFind.union(p, q);
        }

        if (leftOpen) {
            System.out.println("open() leftOpen");

            int q = getUnderlyingPos(row, col - 1);
            weightedQuickFind.union(p, q);
        }

        if (rightOpen) {
            System.out.println("open() rightOpen");

            int q = getUnderlyingPos(row, col + 1);
            weightedQuickFind.union(p, q);

        }

        grid[row - 1][col - 1] = true;
    }

    private int getUnderlyingPos(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private int getUnderlyingValueInPos(int row, int col) {
        return weightedQuickFind.find((row - 1) * n + (col - 1));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }

        return grid[--row][--col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        if (row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }

        boolean isFull = false;
        if (isOpen(row, col)) {
            int value = getUnderlyingValueInPos(row, col);
            for (int i = 0; i < n; i++) {
                if (weightedQuickFind.find(i) == value) {
                    isFull = true;
                    break;
                }
            }
        }
        /*
        is it open AND
        is the value of the site equal to any of the first N elements
         */

        return isFull;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int openSites = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]) {
                    openSites++;
                }
            }
        }
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {

        /*

        a system percolates if any of the last N entries values are
        equal to any of the first N entries
         */

        boolean percolates = false;

        for (int i = 0; i < n; i++) {
            int first_row_entry = weightedQuickFind.find(i);
            for (int j = n * (n - 1); j < n * n; j++) {
                int last_row_entry = weightedQuickFind.find(j);
                if (first_row_entry == last_row_entry) {
                    percolates = true;
                    break;
                }
            }
        }
        return percolates;
    }

    public void printGrid() {

        System.out.println("Current grid status : ");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    System.out.print(" ");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }

        System.out.println();
    }

    public int count() {

        return weightedQuickFind.count();
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);

        percolation.printGrid();

        percolation.open(1, 2);
        percolation.printGrid();

        percolation.open(2, 2);
        percolation.printGrid();

        System.out.println("isFull 2, 2 = " + percolation.isFull(2, 2));

        percolation.open(3, 2);

        System.out.println("number of open sites = " + percolation.numberOfOpenSites());

        percolation.printGrid();

        testIsOpen(percolation);

        System.out.println("percolation.percolates() = " + percolation.percolates());

    }

    private static void testIsOpen(Percolation percolation) {
        System.out.println("percolation.isOpen(3, 2) = " + percolation.isOpen(1, 2));
        System.out.println("percolation.isOpen(2, 3) = " + percolation.isOpen(2, 2));
        System.out.println("percolation.isOpen(3, 3) = " + percolation.isOpen(3, 2));
    }
}
