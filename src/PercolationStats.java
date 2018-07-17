import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private int numberOfTrials;
    private int length;
    private double[] results;

    /**
     * Perform trials independent experiments on an n-by-n grid
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials){
        this.numberOfTrials = trials;
        this.length = n;
        this.results = new double[this.numberOfTrials];
        int gridsTotal = this.length * this.length;
        int Max = this.length;
        int Min = 1;

        for(int i = 0; i<this.numberOfTrials;i++){
            Percolation percolation = new Percolation(this.length);

            //loop until percolates
            while(!percolation.percolates()){

                int row = (int) (Math.round(Math.random() * (Max - Min) + Min));
                int col = (int) (Math.round(Math.random() * (Max - Min) + Min));
                percolation.open(row, col);
            }
            results[i] = ((double)percolation.numberOfOpenSites()/(double)(gridsTotal));
        }
    }


    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean(){

        return StdStats.mean(results);
    }


    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(results);
    }


    /**
     * low end point of 95% confidence interval
     * @return
     */
    public double confidenceLo(){
        return (this.mean()-(1.96*this.stddev())/(Math.sqrt((double)this.numberOfTrials)));
    }


    /**
     * high end point of 95% confidence interval
     * @return
     */
    public double confidenceHi(){
        return (this.mean()+(1.96*this.stddev())/(Math.sqrt((double)this.numberOfTrials)));
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
