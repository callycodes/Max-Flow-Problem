/*
 * Name: Callum Bass
 * Student Number: w1682693
 * Group: 5SENG001W/2
 * University of Westminster - Coursework
 */
package com.callycodes.maxflowproblem;

import java.util.LinkedList;
import java.util.Random;
  
/**
 *
 * @author Callum Bass <w1682693>
 */
public class Graph {
    
    //Generate a simple 6 node network with one way paths and preset values
    public static int[][] createGraph() {
        return new int[][] 
        {
            {0, 3, 7, 0, 0, 0},
            {0, 0, 2, 9, 0, 0},
            {0, 0, 0, 6, 8, 0},
            {0, 0, 0, 0, 0, 3},
            {0, 0, 0, 0, 0, 12},
            {0, 0, 0, 0, 0, 0}
        };
    }
     
    //Generate a network of user defined node count, multi-edged paths disabled
    public static int[][] generateGraph(int nodes) {
        
        //Initialize a 2D int array of node count length and width
        int[][] graph = new int[nodes][nodes];

        //int[] pathExists = new int[nodes];

        //Loop through node count length ways
        for (var x = 0; x < nodes; x++) {
            //Loop through node count width ways
            for (var y = 0; y < nodes; y++) {

                //Random integer generate between values 0-10
                Random rand = new Random();
                int num = rand.nextInt(30) + 1;

                //If x and y are equal, node is referencing itself, distance from itself to itself is always 0
                if (x==y) {
                    num = 0;
                }

                //Checks if a node is in range, otherwise no edge is created
                if (y > (x+2) || y < (x-2)) {
                    num = 0;
                }

                //Has a path already been created to this node from a different node, if so, don't duplicate an edge.
                //This also avoids creating a multi-directional edge with two different weights.
                if (graph[y][x] > 0) {
                    num = 0;
                }

                //Store edge weight into network at specified axis
                graph[x][y] = num;
            }
        }
        return graph;
    }

    //Function to print complete network of nodes formatted
    public static void printGraph(int[][] graph) {
        int nodes = graph.length;
        for (var x = 0; x < nodes; x++) {
            String line = "";
            for (var y = 0; y < nodes; y++) {
                line += graph[x][y] + " ";
            }
            System.out.println(line);
        }
    }

    //Returns the node count using length property of an array
    public static int getNodeCount(int[][] graph) {
        return graph.length;
    }


    public static boolean isPath(int[][] graph, int source, int sink, int parent[]) 
    { 
        //Number of nodes is stored in variable
        int nodeCount = getNodeCount(graph);
        
        //Array is created to store whether each node has been visited
        boolean nodesVisisted[] = new boolean[nodeCount]; 

        //Loop is used to initialise the array and set all nodes to false
        for(int i=0; i<nodeCount; ++i) 
        nodesVisisted[i]=false; 
  
        // Create a node queue to store visited nodes in queue type
        LinkedList<Integer> visitedQueue = new LinkedList<Integer>(); 

        //Add source node to the queue
        visitedQueue.add(source); 

        //Source node is marked as visited in boolean array
        nodesVisisted[source] = true; 

        //Parent node of the source is set to -1 as source node has no parent.
        parent[source]=-1; 
  
        // Breadth-First Search loop
        while (visitedQueue.size() != 0) 
        { 
            //Pull first element from the queue and remove it from queue
            int u = visitedQueue.poll(); 
  
            for (int v=0; v<nodeCount; v++) 
            { 
                if (nodesVisisted[v]==false && graph[u][v] > 0) 
                { 
                    visitedQueue.add(v); 
                    parent[v] = u; 
                    nodesVisisted[v] = true; 
                } 
            } 
        } 
  
        // If the sink node has been reached by the end of the loop, return true.
        return (nodesVisisted[sink] == true); 
    } 

    public static GraphResult fulkersonAlgorithm(int graph[][]) 
    { 
        long startTime = System.currentTimeMillis();

        int nodeCount = getNodeCount(graph);
        int source = 0;
        int sink = nodeCount - 1;
        int u, v; 
        String pathTaken = "";
    
        //Network of nodes is created using nodeCount size
        int network[][] = new int[nodeCount][nodeCount]; 
  
        //Network of nodes is then filled according to array that was passed via parameters
        for (u = 0; u < nodeCount; u++) 
            for (v = 0; v < nodeCount; v++) 
                network[u][v] = graph[u][v]; 
  
        //Arrayu will be used to store path 
        int parent[] = new int[nodeCount]; 
  
        int maxFlow = 0;  // There is no flow initially 
  
        //This checks if there is an augmented flow within the graph from source to sink nodes.
        while (isPath(network, source, sink, parent)) 
        { 
            
            //Original path flow has been set to high value so Math.min can be used
            int pathFlow = 9999; 

            for (v=sink; v!=source; v=parent[v]) 
            { 
                u = parent[v]; 
                pathFlow = Math.min(pathFlow, network[u][v]); 
                pathTaken += network[u][v] + " ";
            } 

            //Update weight of the edges and reverse edges along the path
            for (v=sink; v != source; v=parent[v]) 
            { 
                u = parent[v]; 
                network[u][v] -= pathFlow; 
                network[v][u] += pathFlow; 
            } 
  
            //Add current path flow to overall flow
            maxFlow += pathFlow; 
        } 

        //End timer
        long endTime = System.currentTimeMillis();

        //Calculate difference between times
        long duration = (endTime - startTime);  

        //Create result object and store values
        GraphResult result = new GraphResult(maxFlow, pathTaken, duration);

        return result; 
    } 
}
