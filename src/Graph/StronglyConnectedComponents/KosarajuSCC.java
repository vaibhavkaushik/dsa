package Graph.StronglyConnectedComponents;

import java.util.*;

public class KosarajuSCC {
    private int numVertices;
    private List<List<Integer>> adjList;
    private List<List<Integer>> transposedList;
    private boolean[] visited;
    private Stack<Integer> stack;

    // Constructor to initialize the graph
    public KosarajuSCC(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        transposedList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
            transposedList.add(new ArrayList<>());
        }
        visited = new boolean[numVertices];
        stack = new Stack<>();
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    // DFS utility to fill stack
    private void fillOrder(int v) {
        visited[v] = true;
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                fillOrder(neighbor);
            }
        }
        stack.push(v);
    }

    // Transpose the graph
    private void transposeGraph() {
        for (int v = 0; v < numVertices; v++) {
            for (int neighbor : adjList.get(v)) {
                transposedList.get(neighbor).add(v);
            }
        }
    }

    // DFS utility for transposed graph
    private void dfsTransposed(int v, List<Integer> scc) {
        visited[v] = true;
        scc.add(v);
        for (int neighbor : transposedList.get(v)) {
            if (!visited[neighbor]) {
                dfsTransposed(neighbor, scc);
            }
        }
    }

    // Method to find and print SCCs
    public void findSCCs() {
        // Step 1: Fill vertices in stack according to their finishing times
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                fillOrder(i);
            }
        }

        // Step 2: Transpose the graph
        transposeGraph();

        // Step 3: Process all vertices in order defined by the stack
        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> scc = new ArrayList<>();
                dfsTransposed(v, scc);
                System.out.println("SCC: " + scc);
            }
        }
    }

    public static void main(String[] args) {
        KosarajuSCC g = new KosarajuSCC(5);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        g.findSCCs();
    }
}

