// File : GraphFileWriter.java
// Purpose : Define the GraphFileWriter class
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GraphFileWriter {
    public void writeGraphToFile(Graph graph, String filename) throws IOException {
        FileWriter outFile = new FileWriter(filename, true);
        BufferedWriter bufferedOutFile = new BufferedWriter(outFile);
        Node n = graph.getNode(1);
        ArrayList<Connection> connections = graph.getConnections(1);
        int i = 2;

        bufferedOutFile.write("\nNodes and connections:\n\n");
        while (n != null) {
            if ( (i - 1) != n.getN()) {
                System.out.println("Error: Node number does not match expected value of " + Integer.toString(i - 1));
            }
            bufferedOutFile.write("Node " + Integer.toString(i - 1) + ": (" + Double.toString(n.getX()) + ", " + Double.toString(n.getZ()) + ")\n");
            bufferedOutFile.write("Outgoing connections from node " + Integer.toString(i - 1) + ":\t");
            if (connections.isEmpty()) {
                bufferedOutFile.write("No outgoing connections\n");
                continue;
            }
            for (Connection c : connections) {
                bufferedOutFile.write(Integer.toString(i - 1) + " -> " + Integer.toString(c.getToNode()) + "\t\t");
            }
            bufferedOutFile.write("\n");
            n = graph.getNode(i);
            connections = graph.getConnections(i++);
        }

        bufferedOutFile.close();

    }
}
