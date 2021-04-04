// File : NodeRecord.java
// Purpose : Define the NodeRecord class
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

// A class to store the data for each node
public class NodeRecord implements Comparable<NodeRecord> {
    private Node node; // The node for this record
    private int status; // Denotes whether the node is unvisited (0), open (1), or closed (2)
    private double costSoFar; // The lowest computed cost so far from the start node
    private double estimatedTotalCost; // The sum of the estimated cost to goal and the cost-so-far
    private Connection pathConnect; // The connection from previous node in the path

    public NodeRecord() {
        node = null;
        status = 0;
        costSoFar = 0;
        estimatedTotalCost = 0;
        pathConnect = null;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCostSoFar(double costSoFar) {
        this.costSoFar = costSoFar;
    }

    public void setEstimatedTotalCost(double estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    public void setPathConnect(Connection pathConnect) {
        this.pathConnect = pathConnect;
    }

    public Node getNode() {
        return node;
    }

    public int getStatus() {
        return status;
    }

    public double getCostSoFar() {
        return costSoFar;
    }

    public double getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public Connection getPathConnect() {
        return pathConnect;
    }

    @Override
    public int compareTo(NodeRecord record) {
        return Double.compare(this.estimatedTotalCost, record.estimatedTotalCost);
    }
}