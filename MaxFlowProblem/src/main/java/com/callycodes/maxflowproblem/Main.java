/*
 * Name: Callum Bass
 * Student Number: w1682693
 * Group: 5SENG001W/2
 * University of Westminster - Coursework
 */
package com.callycodes.maxflowproblem;

import java.util.Scanner;

/**
 *
 * @author Callum Bass <w1682693>
 */
public class Main {
    
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        int[][] graph = new int[0][0];

        System.out.println("Max Flow Problem (Ford Fulkerson Algorithm)");
        System.out.println("    Main Menu:");
        System.out.println("        1. Generate a network of nodes");
        System.out.println("        2. Print the network");
        System.out.println("        3. Modify edge");
        System.out.println("        4. Find max flow");
        System.out.println("        5. Exit");

        while (true) {
            
            System.out.println();
            System.out.print("Option: ");
            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    System.out.print("Enter node count: ");
                    int nodes = scanner.nextInt();
                    graph = Graph.generateGraph(nodes);
                    System.out.println("Network generated - Nodes: " + Graph.getNodeCount(graph));
                break;

                case 2:
                System.out.println("Network:");
                    Graph.printGraph(graph);
                break;

                case 3:
                    System.out.print("Enter start node: ");
                    int startNode, endNode = 0;
                    startNode = scanner.nextInt();
                    System.out.print("Enter end node: ");
                    endNode = scanner.nextInt();

                    System.out.print("Current weight is (" + graph[startNode][endNode] + "), enter new weight: ");
                    int weight = scanner.nextInt();
                    graph[startNode][endNode] = weight;
                    System.out.println("Node " + startNode + " to " + endNode + " weight has been set to: " + weight);
                break;

                case 4:
                    int nodeCount = Graph.getNodeCount(graph);
                    int source = 0;
                    int sink = nodeCount-1;
            
                    System.out.println("Nodes: " + Graph.getNodeCount(graph) + " - Source/Sink: " + source + "/" + sink);
                    GraphResult result = Graph.fulkersonAlgorithm(graph);
                    result.printResult();
                break;

                case 5:
                    System.exit(0);
                break;
            }
        }

        
    }
    
}
