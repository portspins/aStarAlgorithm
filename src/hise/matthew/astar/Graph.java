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
        nodes = new ArrayList<Node>();
        connections = new ArrayList<Connection>();
    }

    public void addNode(Node n) {
        if(n != null) {
            nodes.add(n);
        }
    }

    public void addConnection(Connection c) {
        if(c != null) {
            connections.add(c);
        }
    }

    public ArrayList<Connection> getConnections(int n) {
        ArrayList<Connection> outgoing = new ArrayList<Connection>();
        for (Connection c : connections) {
            if (c.getFromNode() == n) {
                outgoing.add(c);
            }
        }
        return outgoing;
    }

    public Node getNode(int n) {
        for (Node node : nodes) {
            if (node.getN() == n) {
                return node;
            }
        }
        return null;
    }

    public ArrayList<Connection> findPathAStar(int start, int end) {
        Heuristic heuristic = new Heuristic();
        heuristic.setGoalNode(getNode(end));
        ArrayList<NodeRecord> nodeArray = new ArrayList<>();
        NodeRecord currentRecord = null;
        Node currentNode = null;
        int n = 0;

        for (Node node : nodes) {
            n = node.getN();
            NodeRecord newRecord = new NodeRecord();
            newRecord.setNode(node);
            nodeArray.add(n, newRecord);
        }

        NodeRecord startRecord = nodeArray.get(start);

        Node startNode = getNode(start);
        startRecord.setNode(startNode);
        startRecord.setEstimatedTotalCost(heuristic.estimate(startNode));

        PriorityQueue<NodeRecord> open = new PriorityQueue<>();

        open.add(startRecord);

        while (!open.isEmpty()) {
            currentRecord = open.remove();
            currentNode = currentRecord.getNode();
            n = currentNode.getN();

            if (n == end) {
                break;
            }

            ArrayList<Connection> connections = getConnections(n);

            for (Connection c : connections) {
                int toNode = c.getToNode();
                double costSoFar = currentRecord.getCostSoFar() + c.getWeight();
                double toHeuristic = 0.0;

                NodeRecord toRecord = nodeArray.get(toNode);
                int toNodeStatus = toRecord.getStatus();

                if (toNodeStatus == 2 || toNodeStatus == 1) {
                    if (toRecord.getCostSoFar() <= costSoFar) {
                        continue;
                    }
                    toHeuristic = toRecord.getEstimatedTotalCost() - toRecord.getCostSoFar();
                } else {
                    toHeuristic = heuristic.estimate(getNode(toNode));
                }

                toRecord.setPathConnect(c);
                toRecord.setCostSoFar(costSoFar);
                toRecord.setEstimatedTotalCost(costSoFar + toHeuristic);

                if (toNodeStatus != 1) {
                    toRecord.setStatus(1);
                }
            }
        }

        if (n != end) {
            return null;
        }

        ArrayList<Connection> path = new ArrayList<>();
        Connection currentConnect = currentRecord.getPathConnect();

        while (currentConnect != null) {
            path.add(0, currentConnect);
            currentConnect = nodeArray.get(currentConnect.getFromNode()).getPathConnect();
        }

        return path;

    }

}