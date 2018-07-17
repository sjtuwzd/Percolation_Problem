import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private int numberOfTrials;
    private int length;

    /**
     * Perform trials independent experiments on an n-by-n grid
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials){
        this.numberOfTrials = trials;
        this.length = n;

        for(int i = 0; i<this.numberOfTrials;i++){

        }
    }


    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean(){
        return 0;

    }


    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev(){
        return 0;
    }


    /**
     * low end point of 95% confidence interval
     * @return
     */
    public double confidenceLo(){
        return 0;
    }


    /**
     * high end point of 95% confidence interval
     * @return
     */
    public double confidenceHi(){
        return 0;
    }


    /**
     * test client
     * @param args
     */
    public static void main(String[] args){

    }
}
