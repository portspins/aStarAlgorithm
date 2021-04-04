// File : Node.java
// Purpose : Define the Node class
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

public class Node {
    private int n; // The number corresponding to the node
    private double x; // The x coordinate for the node
    private double z; // The z coordinate for the node

    public void setN(int n) {
        this.n = n;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getN() {
        return n;
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }
}
