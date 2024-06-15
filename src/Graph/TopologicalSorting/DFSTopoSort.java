package Graph.TopologicalSorting;

import java.util.*;

public class DFSTopoSort {
    private int numVertices;
    private List<List<Integer>> adjList;
    private boolean[] visited;
    private Stack<Integer> stack;

    // Constructor to initialize the graph
    public DFSTopoSort(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[numVertices];
        stack = new Stack<>();
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    // Utility method for DFS-based topological sort
    private void topologicalSortUtil(int v) {
        visited[v] = true;
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor);
            }
        }
        stack.push(v);
    }

    // Method to perform topological sort using DFS
    public List<Integer> topologicalSort() {
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topoOrder.add(stack.pop());
        }
        return topoOrder;
    }

    public static void main(String[] args) {
        DFSTopoSort g = new DFSTopoSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        List<Integer> topoOrder = g.topologicalSort();
        System.out.println("Topological Sort: " + topoOrder);
    }
}

