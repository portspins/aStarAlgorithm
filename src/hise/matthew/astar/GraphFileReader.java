// File : GraphFileReader.java
// Purpose : Define the GraphFileReader class
// Author : Matthew Hise (mrh0036@uah.edu)

package hise.matthew.astar;

import java.io.*;
import java.util.Scanner;

public class GraphFileReader {
    public Graph constructGraph(String[] filepaths) throws FileNotFoundException {
        Graph g = new Graph();
        File file;
        Scanner graphScanner;

        for (String f : filepaths) {
            file = new File(f);
            graphScanner = new Scanner(file);

            while(graphScanner.hasNextLine()) {
                String line = graphScanner.nextLine();
                Character firstChar = line.charAt(0);
                if(firstChar.equals('#')) {
                    continue;
                }
                String lineType = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                if (lineType.equals("\"C\"") || lineType.equals("\"c\"")) {
                    Connection c = new Connection();
                    String[] fields = line.split(",");
                    c.setN(Integer.parseInt(fields[0].strip()));
                    c.setFromNode(Integer.parseInt(fields[1].strip()));
                    c.setToNode(Integer.parseInt(fields[2].strip()));
                    c.setWeight(Double.parseDouble(fields[3].strip()));
                    g.addConnection(c);
                } else if (lineType.equals("\"N\"") || lineType.equals("\"n\"")) {
                    Node n = new Node();
                    String[] fields = line.split(",");
                    n.setN(Integer.parseInt(fields[0].strip()));
                    n.setX(Double.parseDouble(fields[6].strip()));
                    n.setZ(Double.parseDouble(fields[7].strip()));
                    g.addNode(n);
                } else continue;
            }
            graphScanner.close();
        }

        return g;
    }
}
