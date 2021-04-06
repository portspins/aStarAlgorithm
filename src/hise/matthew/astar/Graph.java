// File : Graph.java
// Purpose : Define the Graph class
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
    private ArrayList<Node> nodes; // A list of the nodes in the graph
    private ArrayList<Connection> connections; // A list of the connections in the graph

    public Graph() {
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
    }

    /**
     * Adds a node to the graph
     * @param n the node to be added
     */
    public void addNode(Node n) {
        if(n != null) {
            nodes.add(n);
        }
    }

    /**
     * Adds a connection to the graph
     * @param c the connection to be added
     */
    public void addConnection(Connection c) {
        if(c != null) {
            connections.add(c);
        }
    }

    /**
     * Get the connections for a given node
     * @param n the integer of the node
     * @return the list of outgoing connections for the node
     */
    public ArrayList<Connection> getConnections(int n) {
        ArrayList<Connection> outgoing = new ArrayList<>();
        for (Connection c : connections) {
            if (c.getFromNode() == n) {
                outgoing.add(c);
            }
        }
        return outgoing;
    }

    /**
     * Gets a node based on its integer value
     * @param n the integer value assigned to the node
     * @return the node or null if the node was not found
     */
    public Node getNode(int n) {
        for (Node node : nodes) {
            if (node.getN() == n) {
                return node;
            }
        }
        return null;
    }

    /**
     * Implements the A* pathfinding algorithm
     * @param start the integer of the start node
     * @param end the integer of the goal node
     * @return a list of connections from the start node to the goal node
     */
    public ArrayList<Connection> findPathAStar(int start, int end) {
        Heuristic heuristic = new Heuristic();
        heuristic.setGoalNode(getNode(end));
        ArrayList<NodeRecord> nodeArray = new ArrayList<>();
        NodeRecord currentRecord = null;
        Node currentNode;
        int n = 0;


        for (Node node : nodes) {
            n = node.getN();
            NodeRecord newRecord = new NodeRecord();
            newRecord.setNode(node);
            if (nodeArray.size() <= n) {
                while(nodeArray.size() <= n) nodeArray.add(null);  // May need to expand the array to accommodate the index
            }
            nodeArray.add(n, newRecord);
        }

        NodeRecord startRecord = nodeArray.get(start);
        Node startNode = startRecord.getNode();

        if (heuristic.getGoalNode() == null || startNode == null) {
            return null;
        }

        startRecord.setEstimatedTotalCost(heuristic.estimate(startNode));
        PriorityQueue<NodeRecord> open = new PriorityQueue<>();
        open.add(startRecord);

        while (!open.isEmpty()) {
            currentRecord = open.remove();
            currentNode = currentRecord.getNode();
            n = currentNode.getN();

            // If the goal node is the current node, we have found the path
            if (n == end) {
                break;
            }

            ArrayList<Connection> connections = getConnections(n);

            for (Connection c : connections) {
                int toNode = c.getToNode();
                double costSoFar = currentRecord.getCostSoFar() + c.getWeight();
                double toHeuristic;

                NodeRecord toRecord = nodeArray.get(toNode);
                int toNodeStatus = toRecord.getStatus();

                // If the node is closed or open
                if (toNodeStatus == 2 || toNodeStatus == 1) {
                    if (toRecord.getCostSoFar() <= costSoFar) {
                        continue;  // The new route to the node was no better, so no need to update it
                    }
                    toHeuristic = toRecord.getEstimatedTotalCost() - toRecord.getCostSoFar();
                } else {  // The node is unvisited
                    toHeuristic = heuristic.estimate(getNode(toNode));
                }

                toRecord.setPathConnect(c);
                toRecord.setCostSoFar(costSoFar);
                toRecord.setEstimatedTotalCost(costSoFar + toHeuristic);

                // If the node was not open, set it to open and add it to the open list
                if (toNodeStatus != 1) {
                    toRecord.setStatus(1);
                    open.add(toRecord);
                }
            }
        }

        // Check if the goal node was not reached
        if (n != end) {
            return null;
        }

        ArrayList<Connection> path = new ArrayList<>();
        Connection currentConnect = currentRecord.getPathConnect();

        while (currentConnect != null && currentConnect.getToNode() != start) {
            path.add(0, currentConnect);
            currentConnect = nodeArray.get(currentConnect.getFromNode()).getPathConnect();
        }

        return path;

    }

}