//Name - Yashasshree Bimsara Madurapperuma
//UoW - w1912884
//IIT - 20210695

package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph();

//      Modify the file path by adding the absolute path of the file.
        String filepath = "D:\\ALGOCW\\src\\com\\company\\assets\\input.txt";
        readFromFile(filepath, graph);

        graph.printGraph();

        if (graph.adjacentList.isEmpty()){
            System.out.println("No graph in input");
            return;
        }


        boolean statusChecker = checkAcyclic(graph);

        if (statusChecker == true){
            System.out.println("The Graph is Acyclic, it has no cycles");
        }else{
            System.out.println("The graph is Cyclic");
        }

    }
    public static boolean checkAcyclic(Graph graph){
        while(true){
            if(graph.hasSink() != 0){
                System.out.println("Removed sink "+graph.hasSink());
                graph.removeSink();
                graph.printGraph();
            }else{
                return false;
            }
            if(graph.adjacentList.isEmpty()){
                System.out.println("Graph is empty");
                return true;
            }
        }

    }




    public static void readFromFile(String path, Graph graph){

        List column1 = new ArrayList<Integer>();
        List column2 = new ArrayList<Integer>();

        try{
            List<String> lines = Files.readAllLines(Paths.get(path));
            for(String line : lines){
                String[] columns = line.split(" ");
                int value1 = Integer.parseInt(columns[0]);
                int value2 = Integer.parseInt(columns[1]);
                column1.add(value1);
                column2.add(value2);
            }
        }catch (Exception e){
            System.out.println("An error has occured when reading from the file");
            e.printStackTrace();
        }
        if (column1.size() != column2.size()){
            System.out.println("Invalid input file");
            return;
        }


//        Gets all the unique numbers from file so vertices can be found
        ArrayList<Integer> uniqueList = new ArrayList<>();
        for (Object number : column1) {
            if (!uniqueList.contains((int)number)) {
                uniqueList.add( (int)number );
            }
        }
        for (Object number : column2) {
            if (!uniqueList.contains((int)number)) {
                uniqueList.add( (int)number );
            }
        }
        Collections.sort(uniqueList);
        for(int data : uniqueList){
            graph.addNode(new Node(data));
        }

        //Adds the edges
        for(int i=0; i<column1.size(); i++){
            int src = (int) column1.get(i);
            int dst = (int) column2.get(i);

            graph.addEdge(src,dst );
        }

    }
//================================Path of cycle================================
public static boolean isCyclicUtil(Graph graph, Node node, Set<Node> visited, Set<Node> recursionStack, List<Node> cyclePath) {
    visited.add(node);
    recursionStack.add(node);
    cyclePath.add(node);

    LinkedList<Node> adjList = graph.getAdjacentNodes(node);

    for (Node adjacentNode : adjList) {
        if (recursionStack.contains(adjacentNode)) {
            cyclePath.add(adjacentNode);
            return true;
        }

        if (!visited.contains(adjacentNode) && isCyclicUtil(graph, adjacentNode, visited, recursionStack, cyclePath)) {
            return true;
        }
    }

    recursionStack.remove(node);
    cyclePath.remove(node);
    visited.remove(node); // Remove from visited nodes after removing from recursion stack

    return false;
}

    public static boolean checkCycle(Graph graph) {
        Set<Node> visited = new HashSet<>();
        Set<Node> recursionStack = new HashSet<>();
        List<Node> cyclePath = new ArrayList<>();

        for (LinkedList<Node> adjList : graph.adjacentList) {
            for (Node node : adjList) {
                if (!visited.contains(node) && isCyclicUtil(graph, node, visited, recursionStack, cyclePath)) {
                    // Cycle detected
                    printCyclePath(cyclePath);
                    return true;
                }
            }
        }

        return false;
    }

    public static void printCyclePath(List<Node> cyclePath) {
        boolean isCycleStarted = false;

        for (Node node : cyclePath) {
            if (isCycleStarted) {
                System.out.print(" -> " + node.data);
            } else if (cyclePath.lastIndexOf(node) != cyclePath.indexOf(node)) {
                // Check if the node occurs more than once in the cyclePath
                isCycleStarted = true;
                System.out.print(node.data);
            }
        }
        System.out.println();
    }

}
