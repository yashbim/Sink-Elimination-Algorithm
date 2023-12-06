package com.company;

import java.util.*;

public class Graph {
    ArrayList<LinkedList<Node>> adjacentList;

    Graph() {
        adjacentList = new ArrayList<>();
    }

    public void addNode(Node node) {
        LinkedList<Node> currentList = new LinkedList<>();
        currentList.add(node);
        adjacentList.add(currentList);

    }

    public void removeNode(Node node) {
        //If for loops used, leads to cuncurrentModificatin exception
        Iterator<LinkedList<Node>> iterator = adjacentList.iterator();
        while (iterator.hasNext()) {
            LinkedList<Node> currentList = iterator.next();
            if (currentList.getFirst().data == node.data) {
                iterator.remove();
            } else {   //checks to see if the node to be removed is present in each linkedlist in the Arraylist, and if it is, removes it from the linkedlist.
                Iterator<Node> nodeIterator = currentList.iterator();
                while (nodeIterator.hasNext()) {
                    Node currentNode = nodeIterator.next();
                    if (currentNode.data == node.data) {
                        nodeIterator.remove();
                    }
                }
            }
        }

    }

    public void addEdge(int src, int dst) {
        for (LinkedList<Node> currentList : adjacentList) {
            if (currentList.getFirst().data == src) {
                currentList.add(new Node(dst));
                break;
            }

        }
    }


    public void removeSink() {
        Iterator<LinkedList<Node>> iterator = adjacentList.iterator();
        Node nodeRemove = null;
        while (iterator.hasNext()) {
            LinkedList<Node> currentList = iterator.next();


            if (currentList.size() == 1) {   //If only one node, it means it has no outgoing edges
                nodeRemove = currentList.getFirst();
                iterator.remove();
            }
            if (nodeRemove != null) {
                removeAllOccurances(nodeRemove);
                break;
            }
        }
    }

    public void removeAllOccurances(Node nodeToRemove) {
        Iterator<LinkedList<Node>> iterator = adjacentList.iterator();
        while (iterator.hasNext()) {
            LinkedList<Node> currentList = iterator.next();

            Iterator<Node> nodeIterator = currentList.iterator();
            while (nodeIterator.hasNext()) {
                Node currentNode = nodeIterator.next();
                if (currentNode.data == nodeToRemove.data) {
                    nodeIterator.remove();
                }
            }

        }
    }

    public int hasSink() {
        Iterator<LinkedList<Node>> iterator = adjacentList.iterator();
        while (iterator.hasNext()) {
            LinkedList<Node> currentList = iterator.next();

            if (currentList.size() == 1) {
                return currentList.getFirst().data;
            }
        }
        return 0;

    }


    public void printGraph() {

        for (LinkedList<Node> currentList : adjacentList) {
            for (Node node : currentList) {
                System.out.print(node.data + " -> ");
            }
            System.out.println();
        }

    }

    //--------------------------------------------------------------------------------------------
    public LinkedList<Node> getAdjacentNodes(Node node) {
        LinkedList<Node> adjNodes = new LinkedList<>();

        for (LinkedList<Node> currentList : adjacentList) {
            if (currentList.getFirst().data == node.data) {
                adjNodes.addAll(currentList.subList(1, currentList.size()));
                break;
            }
        }

        return adjNodes;
    }

}