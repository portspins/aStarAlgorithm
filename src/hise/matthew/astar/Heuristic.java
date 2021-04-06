// File : Heuristic.java
// Purpose : Define the Heuristic class
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

public class Heuristic {
    private Node goalNode; // The goal node for the A* algorithm

    /**
     * Estimates the remaining path from a node to the goal node based off Euclidean distance
     * @param fromNode the node for which the heuristic is to be calculated
     * @return the heuristic value
     */
    public double estimate(Node fromNode) {
        return estimate(fromNode, goalNode);
    }

    /**
     * Estimates the remaining path from one node to another node based off Euclidean distance
     * @param fromNode the from node for the heuristic calculation
     * @param toNode the to node for the heuristic calculation
     * @return the heuristic value
     */
    public double estimate(Node fromNode, Node toNode) {
        return Math.sqrt(Math.pow(toNode.getX() - fromNode.getX(), 2) + Math.pow(toNode.getZ() - fromNode.getZ(), 2));
    }

    public void setGoalNode(Node goalNode) {
        this.goalNode = goalNode;
    }

    public Node getGoalNode() {
        return goalNode;
    }
}
