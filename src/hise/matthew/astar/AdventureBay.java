/*
 *   Project: CS 330-02 Programming Assignment 3
 *   Program: A* Pathfinding
 *   Purpose: Implement the A* algorithm for pathfinding
 *   Author: Matthew Hise (mrh0036@uah.edu)
 *   Date Created: 04/03/2021
 *   Date Modified: 04/03/2021
 */

package hise.matthew.astar;

import java.io.*;

public class AdventureBay {
    public static void main(String[] args) {
        Graph graph = null;
        GraphFileReader graphReader = new GraphFileReader();
        GraphFileWriter graphWriter = new GraphFileWriter();
        File newFile = new File("src/hise/matthew/astar/CS 330, Pathfinding Output.txt");
        FileWriter outputFile = null;
        try {
            outputFile = new FileWriter(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedOutputFile = new BufferedWriter(outputFile);
        String[] graphFiles = { "src/hise/matthew/astar/CS 330, Pathfinding W1, Connections v3.txt", "src/hise/matthew/astar/CS 330, Pathfinding W1, Nodes v3.txt" };
        try {
            graph = graphReader.constructGraph(graphFiles);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            bufferedOutputFile.write("\nName: Matthew Hise\nClass: CS 330-02\n");
            bufferedOutputFile.close();
            graphWriter.writeGraphToFile(graph, newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}