package Graph.Representation;

import java.util.LinkedList;
import java.util.List;

public class GraphList {
    private List<Integer>[] adjList;
    private int numVertices;

    // Constructor to create a graph with n vertices
    public GraphList(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add an edge between two vertices
    public void addEdge(int i, int j) {
        adjList[i].add(j);
        adjList[j].add(i); // For undirected graph
    }

    // Print the adjacency list
    public void printList() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " -> ");
            for (Integer neighbor : adjList[i]) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphList g = new GraphList(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        g.printList();
    }
}

