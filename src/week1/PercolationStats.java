package week1;

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0f;
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