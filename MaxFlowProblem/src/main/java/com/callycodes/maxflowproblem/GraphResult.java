/*
 * Name: Callum Bass
 * Student Number: w1682693
 * Group: 5SENG001W/2
 * University of Westminster - Coursework
 */
package com.callycodes.maxflowproblem;

/**
 *
 * @author Callum Bass <w1682693>
 */
public class GraphResult {

    int maxFlow;
    String pathTaken;
    long timeTaken;


    public int getMaxFlow() {
        return this.maxFlow;
    }

    public void setMaxFlow(int maxFlow) {
        this.maxFlow = maxFlow;
    }

    public String getPathTaken() {
        return this.pathTaken;
    }

    public void setPathTaken(String pathTaken) {
        this.pathTaken = pathTaken;
    }

    public long getTimeTaken() {
        return this.timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }




    public GraphResult(int maxFlow, String pathTaken, long timeTaken) {
        this.maxFlow = maxFlow;
        this.pathTaken = pathTaken;
        this.timeTaken = timeTaken;
    }

    public void printResult() {
        System.out.println("Max Flow: " + this.maxFlow + " - Time Taken: " + this.timeTaken + "ms");
        System.out.println("Path Taken: " + this.pathTaken);
    }


}
