package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF weightedQuickUnion;

    private final boolean[][] grid;

    private final int n;

/*    private int openCalls = 0;
    private int percolatedCalls = 0;
    private int getRootOfCalls = 0;
    private int isFullCalls = 0;*/

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

        if (isOpen(row, col)) {
            return;
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
//            openCalls++;
            weightedQuickUnion.union(p, q);
        }

        if (bottomOpen) {
            int q = getUnderlyingPos(row + 1, col);
//            openCalls++;

            weightedQuickUnion.union(p, q);
        }

        if (leftOpen) {
            int q = getUnderlyingPos(row, col - 1);
//            openCalls++;

            weightedQuickUnion.union(p, q);
        }

        if (rightOpen) {
            int q = getUnderlyingPos(row, col + 1);
//            openCalls++;

            weightedQuickUnion.union(p, q);
        }

        grid[row - 1][col - 1] = true;
    }

    private int getUnderlyingPos(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private int getRootOf(int row, int col) {
//        getRootOfCalls++;
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
            int value = getRootOf(row, col);
            for (int i = 0; i < n; i++) {
//                isFullCalls++;
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
/*            for (int i = 0; i < n; i++) {
                percolatedCalls++;
                int first_row_entry = weightedQuickUnion.find(i);
                for (int j = n * (n - 1); j < n * n; j++) {
                    percolatedCalls++;
                    int last_row_entry = weightedQuickUnion.find(j);
                    if (first_row_entry == last_row_entry) {
                        percolates = true;
                        break;
                    }
                }
            }*/

            for (int i = 1; i < n + 1; i++) {
                if (isFull(n, i)) {
                    percolates = true;
                    break;
                }
            }
        } else {
            percolates = grid[0][0];
        }
        return percolates;
    }
/*
    private void printGrid() {

        System.out.println("Current grid status : ");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    System.out.print("o");
                } else {
                    System.out.print("x");
                }
            }
            System.out.println();
        }

        System.out.println();
    }*/

    // test client (optional)
    public static void main(String[] args) {


//        tests1through7();

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
    }


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

    private static List<Pair<Integer, Integer>> readFile() throws IOException {

        String first = "./src/week1/snake101.txt";
        List<String> lines = Files.readAllLines(Paths.get(first));
        lines.remove(0);

        List<Pair<Integer, Integer>> pairs = new ArrayList<>();

        for (String line : lines) {
            String newLine = line.trim();
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


    private static void tests1through7() {


        Tests 1 through 7 create a Percolation object using your code, then repeatedly
open sites by calling open(). After each call to open(), it checks the return
values of isOpen(), percolates(), numberOfOpenSites(), and isFull() in that order.
Tests 12 through 15 create a Percolation object using your code, then repeatedly
call the methods open(), isOpen(), isFull(), percolates(), and, numberOfOpenSites()
in random order with probabilities p = (p1, p2, p3, p4, p5). The tests stop
immediately after the system percolates.


        Percolation percolation = new Percolation(101);

        List<Pair<Integer, Integer>> pairs = null;
        try {
            pairs = readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (pairs != null) {
            for (Pair<Integer, Integer> pair : pairs) {
                int row = pair.getKey();
                int col = pair.getValue();
                percolation.open(row, col);
                percolation.isOpen(row, col);
                percolation.percolates();
                percolation.numberOfOpenSites();
                percolation.isFull(row, col);
            }

            percolation.printGrid();
            System.out.println("pairs.size " + pairs.size());
        }

        System.out.println("open calls = " + percolation.openCalls);
        System.out.println("percolates calls = " + percolation.percolatedCalls);
        System.out.println("open getRootOf calls = " + percolation.getRootOfCalls);
        System.out.println("open isFullCalls = " + percolation.isFullCalls);
        System.out.println("total calls = " + (percolation.openCalls + percolation.percolatedCalls + percolation.getRootOfCalls + percolation.isFullCalls));

    }*/
}
