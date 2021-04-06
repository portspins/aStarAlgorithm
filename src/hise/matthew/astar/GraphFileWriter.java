// File : GraphFileWriter.java
// Purpose : Define the GraphFileWriter class to append the node and connection data of a graph to a file
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GraphFileWriter {

    /**
     * Appends the node and connection data from a graph to a file
     * @param graph the graph to output to the file
     * @param filepath the path to the file to which to append the graph data
     * @throws IOException
     */
    public void writeGraphToFile(Graph graph, String filepath) throws IOException {
        FileWriter outFile = new FileWriter(filepath, true);
        BufferedWriter bufferedOutFile = new BufferedWriter(outFile);
        Node n = graph.getNode(1);
        ArrayList<Connection> connections = graph.getConnections(1);
        int i = 2; // Set i to 2 since the first node and connections are already initialized
        boolean isFirstNode = true;

        bufferedOutFile.write("\nNodes and connections of graph:\n\n");
        while (n != null) {
            // Check that we are printing the expected node
            if ( (i - 1) != n.getN()) {
                System.out.println("Error: Node number does not match expected value of " + Integer.toString(i - 1));
            }

            if (!isFirstNode) {
                bufferedOutFile.write("\n\n");
            } else {
                isFirstNode = false;
            }

            bufferedOutFile.write("Node " + (i - 1) + ": (" + n.getX() + ", " + n.getZ() + ")\n");
            bufferedOutFile.write("Outgoing connections:  ");
            if (connections.isEmpty()) {
                bufferedOutFile.write("No outgoing connections\n");
                continue;
            }

            boolean isFirst = true;

            for (Connection c : connections) {
                if(!isFirst) {
                    bufferedOutFile.write(",  ");
                } else {
                    isFirst = false;
                }
                bufferedOutFile.write((i - 1) + " -> " + c.getToNode());
            }
            n = graph.getNode(i);
            connections = graph.getConnections(i++);
        }
        bufferedOutFile.write("\n");
        bufferedOutFile.close();

    }

    /**
     * Appends the connection and cost data from a path to a file
     * @param connections the connections forming the path
     * @param filepath the path to the file to which to append the path data
     * @throws IOException
     */
    public void writePathToFile(ArrayList<Connection> connections, String filepath) throws IOException {
        FileWriter outFile = new FileWriter(filepath, true);
        BufferedWriter bufferedOutFile = new BufferedWriter(outFile);
        double pathCost = 0.0;

        if (connections == null) {
            bufferedOutFile.write("\nPath not found!\n");
            bufferedOutFile.close();
            return;
        }

        bufferedOutFile.write("\n\nPath from node " + connections.get(0).getFromNode() + " to node " + connections.get(connections.size() - 1).getToNode() + ":\n");

        for(Connection c : connections) {
            bufferedOutFile.write(c.getFromNode() + " -> ");
            pathCost += c.getWeight();
        }
        bufferedOutFile.write(connections.get(connections.size() - 1).getToNode() + "\ncost = " + pathCost);
        bufferedOutFile.close();
    }

}
