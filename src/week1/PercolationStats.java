package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

//https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php

public class PercolationStats {

    int n;

    Percolation[] percolated;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;

        percolated = new Percolation[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolationWeighted = new Percolation(n);
            while (!percolationWeighted.percolates()) {
                int openRow = StdRandom.uniform(n) + 1;
                int openCol = StdRandom.uniform(n) + 1;
                percolationWeighted.open(openRow, openCol);
            }
            percolated[i] = percolationWeighted;
        }
    }

    public double mean() {
        double[] arrayOfPercolations = new double[percolated.length];

        for (int i = 0; i < percolated.length; i++) {
            arrayOfPercolations[i] = ((percolated[i].numberOfOpenSites() * 1.0) / (n * n));
        }
        return StdStats.mean(arrayOfPercolations);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double[] arrayOfPercolations = new double[percolated.length];
        for (int i = 0; i < percolated.length; i++) {
            arrayOfPercolations[i] = ((percolated[i].numberOfOpenSites() * 1.0) / (n * n));
        }
        return StdStats.stddev(arrayOfPercolations);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - 1.96 * Math.sqrt(stddev() / n));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + 1.96 * Math.sqrt(stddev() / n));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // n by n grid
        int T = Integer.parseInt(args[1]); // amount of times to run the experiment

        if (n <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        PercolationStats stats = new PercolationStats(n, T);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}