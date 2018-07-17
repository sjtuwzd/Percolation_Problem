import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private boolean[][] grids;
    private int length;
    private  WeightedQuickUnionUF UF;


    /**
     * To check if the given coordinate meets the requirement
     * @param p
     */
    private void validate(int p) {
        if (p <= 0 || p > this.length) {
            throw new IllegalArgumentException("index " + p + " is not between 1 and " + (this.length));
        }
    }

    /**
     * mapping from 2D to 1D
     * index : 0,1......n*n+1
     * 0->the top virtual site,1->the bottom virtual site
     * @param row
     * @param col
     * @return
     */
    private int xyTo1D(int row, int col){


        return (((row-1)*length + col));

    }

    /**
     * create by n-by-n grid, with all sites blocked
     * @param n
     */
    public Percolation(int n){


        //Union-find structure
        this.UF = new WeightedQuickUnionUF(n*n +2);

        //Initialize the array
        this.grids = new boolean[n][n];
        this.length = n;

        //Initially all grids blocked
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                grids[i][j] =false;
            }
        }

        //connect the virtual top site with all the first row
        for(int i=1;i<=n;i++){
            this.UF.union(i, 0);
        }

        //connect the virtual bottom site with all the last row
        for(int i=(n*n-n+1);i<=n*n;i++){
            this.UF.union(i,n*n+1);
        }
    }


    /**
     * open site(row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col){
        validate(row);
        validate(col);
        if(grids[(row-1)][(col-1)] == false) {
            grids[(row - 1)][(col - 1)] = true;
        }
        int center = xyTo1D(row, col);

        if(col>=2){
            int left = xyTo1D(row, col-1);
            if(isOpen(row, col-1)){
                UF.union(left, center);
            }
        }
        if(col<=(length-1)){
            int right = xyTo1D(row, col+1);
            if(isOpen(row, col+1)){
                UF.union(right, center);
            }
        }
        if(row>=2){
            int up = xyTo1D(row-1, col);
            if(isOpen(row-1, col)){
                UF.union(up, center);
            }
        }
        if(row<=(length-1)){
            int bottom = xyTo1D(row+1, col);
            if(isOpen(row+1, col)){
                UF.union(bottom, center);
            }
        }

    }

    /**
     * is site(row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col){
        validate(row);
        validate(col);

        return (grids[(row-1)][(col-1)]);

    }

    /**
     * is site(row, col) full?
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col){

//        int center = xyTo1D(row, col);
        return ((this.UF.connected(0,xyTo1D(row, col)))&&(isOpen(row, col)));

    }


    /**
     * number of open sites
     * @return
     */
    public int numberOfOpenSites(){

        int count = 0;
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                if(grids[i][j]){
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * does the system percolate
     * @return
     */
    public boolean percolates(){
        return this.UF.connected(0, length*length+1);
    }


    /**
     * test client
     * @param args
     */
    public static void main(String[] args){
        Percolation percolation = new Percolation(3);
        percolation.open(1,1);
        percolation.open(1,2);
        System.out.println(percolation.UF.connected(1,2));
        System.out.println(percolation.numberOfOpenSites());
        System.out.println(percolation.UF.connected(8,10));

    }
}
