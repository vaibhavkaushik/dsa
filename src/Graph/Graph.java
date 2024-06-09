package Graph;
import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList;

    // Constructor to initialize the graph
    public Graph() {
        adjList = new HashMap<>();
    }

    // Vertex add karne ka method
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Edge add karne ka method
    public void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // Yeh undirected graph hai
    }

    // DFS Traversal
    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    // BFS Traversal
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // DFS Cycle Detection
    public boolean hasCycleDFS() {
        Set<Integer> visited = new HashSet<>();
        for (int vertex : adjList.keySet()) {
            if (!visited.contains(vertex)) {
                if (dfsCycleHelper(vertex, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsCycleHelper(int vertex, int parent, Set<Integer> visited) {
        visited.add(vertex);

        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (dfsCycleHelper(neighbor, vertex, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    // BFS Cycle Detection
    public boolean hasCycleBFS() {
        Set<Integer> visited = new HashSet<>();
        for (int start : adjList.keySet()) {
            if (!visited.contains(start)) {
                if (bfsCycleHelper(start, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfsCycleHelper(int start, Set<Integer> visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, -1});
        visited.add(start);

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int vertex = node[0];
            int parent = node[1];

            for (int neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new int[]{neighbor, vertex});
                } else if (neighbor != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0); // Adding this edge creates a cycle

        System.out.println("DFS Traversal:");
        graph.dfs(0); // Output: 0 1 2 3

        System.out.println("BFS Traversal:");
        graph.bfs(0); // Output: 0 1 2 3

        if (graph.hasCycleDFS()) {
            System.out.println("Cycle detected using DFS.");
        } else {
            System.out.println("No cycle detected using DFS.");
        }

        if (graph.hasCycleBFS()) {
            System.out.println("Cycle detected using BFS.");
        } else {
            System.out.println("No cycle detected using BFS.");
        }
    }
}

