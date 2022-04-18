package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    int n;
    int trials;

    PercolationWeighted[] percolated = new PercolationWeighted[trials];

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            PercolationWeighted percolationWeighted = new PercolationWeighted(n);
            while (!percolationWeighted.percolates()) {
                int openRow = StdRandom.uniform(n);
                int openCol = StdRandom.uniform(n);
                percolationWeighted.open(openRow, openCol);
            }
            percolated[i] = percolationWeighted;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double[] arrayOfPercolations = new double[percolated.length];
        for (int i = 0; i < percolated.length; i++) {
            arrayOfPercolations[i] = ((percolated[i].numberOfOpenSites() * 1.0) / n * n);
        }
        return StdStats.mean(arrayOfPercolations);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0f;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0f;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0f;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // n by n grid
        int T = Integer.parseInt(args[1]); // amount of times to run the experiment

        if (n <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
    }

}