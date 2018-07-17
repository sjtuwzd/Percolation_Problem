import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private boolean[][] grids;
    private final int length;
    private final int bottom;
    private final WeightedQuickUnionUF uf1;
    private final WeightedQuickUnionUF uf2;
    private int numOpen = 0;


    /**
     * create by n-by-n grid, with all sites blocked
     * @param n
     */
    public Percolation(int n) {

        if (n < 1) {
            throw new IllegalArgumentException(("length should be at least one"));
        }


        // Union-find structure
        this.uf1 = new WeightedQuickUnionUF(n * n + 2);
        this.uf2 = new WeightedQuickUnionUF(n * n + 1);

        // Initialize the array
        this.grids = new boolean[n][n];
        this.length = n;
        this.bottom = n*n + 1;

    }

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
    private int xyTo1D(int row, int col) {
        return (((row - 1) * length + col));
    }


    /**
     * open site(row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (!grids[(row-1)][(col-1)]) {
            grids[(row - 1)][(col - 1)] = true;
            this.numOpen++;


            int center = xyTo1D(row, col);

            if (row == this.length) {
                uf1.union(bottom, center);
//                uf2.union(bottom, center);
            }

            if (row == 1) {
                uf1.union(0, center);
                uf2.union(0, center);
            }

            if (col >= 2) {
                int left = xyTo1D(row, col - 1);
                if (isOpen(row, col - 1)) {
                    uf1.union(left, center);
                    uf2.union(left, center);
                }
            }
            if (col <= (length - 1)) {
                int right = xyTo1D(row, col + 1);
                if (isOpen(row, col + 1)) {
                    uf1.union(right, center);
                    uf2.union(right, center);
                }
            }
            if (row >= 2) {
                int up = xyTo1D(row - 1, col);
                if (isOpen(row - 1, col)) {
                    uf1.union(up, center);
                    uf2.union(up, center);
                }
            }
            if (row <= (length - 1)) {
                int down = xyTo1D(row + 1, col);
                if (isOpen(row + 1, col)) {
                    uf1.union(down, center);
                    uf2.union(down, center);
                }
            }
        }

    }

    /**
     * is site(row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
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
    public boolean isFull(int row, int col) {

        validate(row);
        validate(col);
        return ((this.uf2.connected(0, xyTo1D(row, col))) && (isOpen(row, col)));

    }


    /**
     * number of open sites
     * @return
     */
    public int numberOfOpenSites() {

        return this.numOpen;
    }


    /**
     * does the system percolate
     * @return
     */
    public boolean percolates() {
        return this.uf1.connected(0, bottom);
    }


    /**
     * test client
     * @param args
     */
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(1, 2);
        System.out.println(percolation.uf1.connected(1, 2));
        System.out.println(percolation.numberOfOpenSites());
        System.out.println(percolation.uf1.connected(8, 10));

    }
}
