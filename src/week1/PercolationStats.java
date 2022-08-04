package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

//https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php

public class PercolationStats {

    private final int n;

//    private final int trials;

    private final double mean;

    private final double stddev;

//    private final Percolation[] percolated;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
//        this.trials = trials;
//        percolated = new Percolation[trials];

        /*for (int i = 0; i < trials; i++) {
            Percolation percolationWeighted = new Percolation(n);
            while (!percolationWeighted.percolates()) {
                int openRow = StdRandom.uniform(n) + 1;
                int openCol = StdRandom.uniform(n) + 1;
                percolationWeighted.open(openRow, openCol);
            }
            percolated[i] = percolationWeighted;
        }*/
        Percolation[] percolated = runTrials(trials);

        double[] arrayOfPercolations = new double[percolated.length];

        for (int i = 0; i < percolated.length; i++) {
            arrayOfPercolations[i] = ((percolated[i].numberOfOpenSites() * 1.0) / (n * n));
        }

        mean = StdStats.mean(arrayOfPercolations);
        stddev = StdStats.stddev(arrayOfPercolations);
    }

    public double mean() {
      /*  Percolation[] percolated = runTrials(trials);
        double[] arrayOfPercolations = new double[percolated.length];

        for (int i = 0; i < percolated.length; i++) {
            arrayOfPercolations[i] = ((percolated[i].numberOfOpenSites() * 1.0) / (n * n));
        }*/
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
       /* Percolation[] percolated = runTrials(trials);
        double[] arrayOfPercolations = new double[percolated.length];
        for (int i = 0; i < percolated.length; i++) {
            arrayOfPercolations[i] = ((percolated[i].numberOfOpenSites() * 1.0) / (n * n));
        }*/
        return /*StdStats.stddev(arrayOfPercolations);*/ stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - 1.96 * stddev() / Math.sqrt(n));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + 1.96 * stddev() / Math.sqrt(n));
    }

    // test client (see below)
    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]); // n by n grid
//        int T = Integer.parseInt(args[1]); // amount of times to run the experiment

        int n = 20;
        int T = 10;
        if (n <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        PercolationStats stats = new PercolationStats(n, T);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }


    private Percolation[] runTrials(int trials) {
        Percolation[] percolated = new Percolation[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolationWeighted = new Percolation(n);
            while (!percolationWeighted.percolates()) {
                int openRow = StdRandom.uniform(n) + 1;
                int openCol = StdRandom.uniform(n) + 1;
                percolationWeighted.open(openRow, openCol);
            }
            percolated[i] = percolationWeighted;
        }

        return percolated;
    }
}