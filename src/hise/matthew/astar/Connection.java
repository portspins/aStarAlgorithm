// File : Connection.java
// Purpose : Define the Connection class
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

public class Connection {
    private int n; // The number of the connection
    private double weight; // The weight of the connection
    private int fromNode; // The number of the start node of the connection
    private int toNode; // The number of the end node of the connection

    public void setN(int n) {
        this.n = n;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setFromNode(int fromNode) {
        this.fromNode = fromNode;
    }

    public void setToNode(int toNode) {
        this.toNode = toNode;
    }

    public int getN() {
        return n;
    }

    public double getWeight() {
        return weight;
    }

    public int getFromNode() {
        return fromNode;
    }

    public int getToNode() {
        return toNode;
    }
}
