package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import javafx.util.Pair;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Percolation {

    private final WeightedQuickUnionUF weightedQuickUnion;

    private final boolean[][] grid;

    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        grid = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = false;
            }
        }
        weightedQuickUnion = new WeightedQuickUnionUF(n * n);
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
            weightedQuickUnion.union(p, q);
        }

        if (bottomOpen) {
            int q = getUnderlyingPos(row + 1, col);
            weightedQuickUnion.union(p, q);
        }

        if (leftOpen) {
            int q = getUnderlyingPos(row, col - 1);
            weightedQuickUnion.union(p, q);
        }

        if (rightOpen) {
            int q = getUnderlyingPos(row, col + 1);
            weightedQuickUnion.union(p, q);
        }

        grid[row - 1][col - 1] = true;
    }

    private int getUnderlyingPos(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private int getUnderlyingValueInPos(int row, int col) {
        return weightedQuickUnion.find((row - 1) * n + (col - 1));
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
                if (weightedQuickUnion.find(i) == value) {
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

        if (n > 1) {
            for (int i = 0; i < n; i++) {
                int first_row_entry = weightedQuickUnion.find(i);
                for (int j = n * (n - 1); j < n * n; j++) {
                    int last_row_entry = weightedQuickUnion.find(j);
                    if (first_row_entry == last_row_entry) {
                        percolates = true;
                        break;
                    }
                }
            }
        } else {
            percolates = grid[0][0];
        }
        return percolates;
    }

    /*private void printGrid() {

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
    }*/

    // test client (optional)
    public static void main(String[] args) {


        try {
            readFile("");
        } catch (IOException e) {
            e.printStackTrace();
        }

       /*  Percolation percolationQuickFind = new Percolation(1);
        System.out.println("percolation.percolates() = " + percolationQuickFind.percolates());
       Percolation percolationQuickFind = new Percolation(3);

        percolationQuickFind.printGrid();

        percolationQuickFind.open(1, 2);
        percolationQuickFind.printGrid();

        percolationQuickFind.open(2, 2);
        percolationQuickFind.printGrid();

        System.out.println("isFull 2, 2 = " + percolationQuickFind.isFull(2, 2));

        percolationQuickFind.open(3, 2);

        System.out.println("number of open sites = " + percolationQuickFind.numberOfOpenSites());

        percolationQuickFind.printGrid();

        testIsOpen(percolationQuickFind);

        System.out.println("percolation.percolates() = " + percolationQuickFind.percolates());*/

    }

  /*  private static void testIsOpen(Percolation percolationQuickFind) {
        System.out.println("percolation.isOpen(3, 2) = " + percolationQuickFind.isOpen(1, 2));
        System.out.println("percolation.isOpen(2, 3) = " + percolationQuickFind.isOpen(2, 2));
        System.out.println("percolation.isOpen(3, 3) = " + percolationQuickFind.isOpen(3, 2));
    }*/


    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private static List<Pair<Integer, Integer>> readFile(String path) throws IOException {
//        String file ="src/test/resources/fileTest.txt";

        String first = "./src/week1/snake13.txt";
        List<String> lines = Files.readAllLines(Paths.get(first));
        lines.remove(0);

        List<Pair<Integer, Integer>> pairs = new ArrayList<>();

        for (String line: lines) {
            String newLine = line.substring(1);

            int firstSpace = newLine.indexOf(" ");
            String firstNumber = newLine.substring(0, firstSpace);
            String secondNumber = newLine.substring(firstSpace).trim();
            int i = Integer.parseInt(firstNumber);
            int i1 = Integer.parseInt(secondNumber);
            Pair<Integer, Integer> pair = new Pair<>(i, i1);
            pairs.add(pair);
        }

        return pairs;
    }
}
