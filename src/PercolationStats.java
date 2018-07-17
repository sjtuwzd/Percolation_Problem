import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {


    private static final double CONFIDENCE_95 = 1.96;
    private final int numberOfTrials;
    private final double[] results;
    private double tempMean;
    private double tempDev;


    /**
     * Perform trials independent experiments on an n-by-n grid
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials <= 0) {
            throw new IllegalArgumentException(("length should be at least one"));
        }
        this.numberOfTrials = trials;
        this.results = new double[this.numberOfTrials];
        int gridsTotal = n * n;

        for (int i = 0; i < this.numberOfTrials;i++) {
            Percolation percolation = new Percolation(n);

            // loop until percolates
            while (!percolation.percolates()) {

                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            results[i] = ((double) percolation.numberOfOpenSites()/(double) (gridsTotal));
        }
        this.tempMean = StdStats.mean(results);
        this.tempDev = StdStats.stddev(results);
    }


    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return this.tempMean;
    }


    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return this.tempDev;
    }


    /**
     * low end point of 95% confidence interval
     * @return
     */
    public double confidenceLo() {
        return (this.tempMean-(CONFIDENCE_95*this.tempDev)/(Math.sqrt((double) this.numberOfTrials)));
    }


    /**
     * high end point of 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return (this.tempMean+(CONFIDENCE_95*this.tempDev)/(Math.sqrt((double) this.numberOfTrials)));
    }


    /**
     * test client
     * @param args
     */
    public static void main(String[] args){
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.println(percolationStats.results[0]);
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println(percolationStats.confidenceLo());
        System.out.println(percolationStats.confidenceHi());

    }
}
