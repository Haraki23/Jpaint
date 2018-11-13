/* Collision logic obtained from
https://algs4.cs.princeton.edu/91primitives/BoundingBox.java.html*/
package controller;

public class BoundingBox {
    private double x1, y1;   // Original lower left
    private double x2, y2;   // Original upper right
    public BoundingBox(int sx, int sy, int ex, int ey) {
        this.x1 = Math.min(sx, ex);
        this.x2 = Math.max(sx, ex);
        this.y1 = Math.min(sy, ey);
        this.y2 = Math.max(sy,ey);
    }

    public boolean point_intersect(BoundingBox compare_to) {
        BoundingBox original = this;
        //Check collisions printout
        //System.out.println(original.x2 >= compare_to.x1 && original.y2 >= compare_to.y1 && compare_to.x2 >= original.x1 && compare_to.y2 >= original.y1);
        return (original.x2 >= compare_to.x1 && original.y2 >= compare_to.y1 && compare_to.x2 >= original.x1 && compare_to.y2 >= original.y1);
    }

}