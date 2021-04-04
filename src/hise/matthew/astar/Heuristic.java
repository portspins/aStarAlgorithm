// File : Heuristic.java
// Purpose : Define the Heuristic class
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

public class Heuristic {
    private Node goalNode;

    public double estimate(Node fromNode) {
        return estimate(fromNode, goalNode);
    }

    public double estimate(Node fromNode, Node toNode) {
        return Math.sqrt(Math.pow(toNode.getX() - fromNode.getX(), 2) + Math.pow(toNode.getZ() - fromNode.getZ(), 2));
    }

    public void setGoalNode(Node goalNode) {
        this.goalNode = goalNode;
    }
}
