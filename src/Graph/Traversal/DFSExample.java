package Graph.Traversal;

import java.util.*;

public class DFSExample {
    private List<Integer>[] adjList;
    private boolean[] visited;

    // Constructor to create a graph with n vertices
    public DFSExample(int numVertices) {
        adjList = new LinkedList[numVertices];
        visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add an edge between two vertices
    public void addEdge(int i, int j) {
        adjList[i].add(j);
        adjList[j].add(i); // For undirected graph
    }

    // Depth-First Search algorithm
    public void DFS(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (Integer neighbor : adjList[vertex]) {
            if (!visited[neighbor]) {
                DFS(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        DFSExample g = new DFSExample(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        System.out.println("DFS starting from vertex 0:");
        g.DFS(0);
    }
}

