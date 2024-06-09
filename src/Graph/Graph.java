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

    // Edge add karne ka method for undirected graph
    public void addEdgeUndirected(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // Yeh undirected graph hai
    }

    // Edge add karne ka method for directed graph
    public void addEdgeDirected(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
    }

    // DFS Traversal for undirected graph
    public void dfsUndirected(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited);
        System.out.println();
    }

    // DFS Traversal for directed graph
    public void dfsDirected(int start) {
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

    // BFS Traversal for undirected graph
    public void bfsUndirected(int start) {
        bfsHelper(start);
    }

    // BFS Traversal for directed graph
    public void bfsDirected(int start) {
        bfsHelper(start);
    }

    private void bfsHelper(int start) {
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

    // DFS Cycle Detection for undirected graph
    public boolean hasCycleDFSUndirected() {
        Set<Integer> visited = new HashSet<>();
        for (int vertex : adjList.keySet()) {
            if (!visited.contains(vertex)) {
                if (dfsCycleHelperUndirected(vertex, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // DFS Cycle Detection for directed graph
    public boolean hasCycleDFSDirected() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();
        for (int vertex : adjList.keySet()) {
            if (!visited.contains(vertex)) {
                if (dfsCycleHelperDirected(vertex, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsCycleHelperUndirected(int vertex, int parent, Set<Integer> visited) {
        visited.add(vertex);

        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (dfsCycleHelperUndirected(neighbor, vertex, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsCycleHelperDirected(int vertex, Set<Integer> visited, Set<Integer> recStack) {
        visited.add(vertex);
        recStack.add(vertex);

        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (dfsCycleHelperDirected(neighbor, visited, recStack)) {
                    return true;
                }
            } else if (recStack.contains(neighbor)) {
                return true;
            }
        }

        recStack.remove(vertex);
        return false;
    }

    // BFS Cycle Detection for undirected graph (using parent tracking)
    public boolean hasCycleBFSUndirected() {
        Set<Integer> visited = new HashSet<>();
        for (int start : adjList.keySet()) {
            if (!visited.contains(start)) {
                if (bfsCycleHelperUndirected(start, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // BFS Cycle Detection for directed graph (without Kahn's Algorithm)
    public boolean hasCycleBFSDirected() {
        Set<Integer> visited = new HashSet<>();
        for (int start : adjList.keySet()) {
            if (!visited.contains(start)) {
                if (bfsCycleHelperDirected(start, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfsCycleHelperUndirected(int start, Set<Integer> visited) {
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        parent.put(start, -1);
        visited.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parent.put(neighbor, vertex);
                } else if (parent.get(vertex) != neighbor) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfsCycleHelperDirected(int start, Set<Integer> visited) {
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        parent.put(start, -1);
        visited.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parent.put(neighbor, vertex);
                } else if (parent.get(vertex) != neighbor) {
                    return true;
                }
            }
        }
        return false;
    }

    //Leetcode 547
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;  // Cities ki sankhya
        Map<Integer, List<Integer>> adj = new HashMap<>();  // Graph ka adjacency list representation

        // isConnected matrix se adjacency list banate hain
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (isConnected[i - 1][j - 1] == 1) {  // Agar city i aur city j connected hain
                    adj.putIfAbsent(i, new ArrayList<>());  // Agar city i list mein nahi hai to add karo
                    if (i != j) {  // Self-loop add mat karo
                        adj.get(i).add(j);  // City j ko city i ke adjacency list mein add karo
                    }
                }
            }
        }

        boolean[] visited = new boolean[n + 1];  // Visited cities ko track karne ke liye
        int count = 0;  // Provinces ki ginti

        // Har city ke liye BFS karo taaki connected components (provinces) mil sakein
        for (int node : adj.keySet()) {
            if (!visited[node]) {  // Agar city visit nahi hui hai
                BFS(adj, visited, node);  // Is province ke sabhi cities ko explore karo
                count += 1;  // Province count increment karo
            }
        }

        return count;  // Provinces ki sankhya return karo
    }

    void BFS(Map<Integer, List<Integer>> adj, boolean[] visited, int u) {
        Queue<Integer> q = new LinkedList<>();  // BFS ke liye queue
        q.offer(u);  // Shuru karo given city se
        visited[u] = true;  // Mark as visited

        while (!q.isEmpty()) {
            int current_node = q.poll();  // Current node ko queue se nikalo
            for (int neighbour : adj.get(current_node)) {  // Sab neighbours check karo
                if (!visited[neighbour]) {  // Agar neighbour visit nahi hua hai
                    q.offer(neighbour);  // Queue mein add karo
                    visited[neighbour] = true;  // Mark as visited
                }
            }
        }
    }

    public static void main(String[] args) {
        // Undirected Graph Example
        Graph undirectedGraph = new Graph();
        undirectedGraph.addVertex(0);
        undirectedGraph.addVertex(1);
        undirectedGraph.addVertex(2);
        undirectedGraph.addVertex(3);

        undirectedGraph.addEdgeUndirected(0, 1);
        undirectedGraph.addEdgeUndirected(1, 2);
        undirectedGraph.addEdgeUndirected(2, 3);
        undirectedGraph.addEdgeUndirected(3, 0); // Adding this edge creates a cycle

        System.out.println("DFS Traversal for Undirected Graph:");
        undirectedGraph.dfsUndirected(0); // Output: 0 1 2 3

        System.out.println("BFS Traversal for Undirected Graph:");
        undirectedGraph.bfsUndirected(0); // Output: 0 1 2 3

        if (undirectedGraph.hasCycleDFSUndirected()) {
            System.out.println("Cycle detected in Undirected Graph using DFS.");
        } else {
            System.out.println("No cycle detected in Undirected Graph using DFS.");
        }

        if (undirectedGraph.hasCycleBFSUndirected()) {
            System.out.println("Cycle detected in Undirected Graph using BFS.");
        } else {
            System.out.println("No cycle detected in Undirected Graph using BFS.");
        }

        // Directed Graph Example
        Graph directedGraph = new Graph();
        directedGraph.addVertex(0);
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);

        directedGraph.addEdgeDirected(0, 1);
        directedGraph.addEdgeDirected(1, 2);
        directedGraph.addEdgeDirected(2, 3);
        directedGraph.addEdgeDirected(3, 1); // Adding this edge creates a cycle

        System.out.println("DFS Traversal for Directed Graph:");
        directedGraph.dfsDirected(0); // Output: 0 1 2 3

        System.out.println("BFS Traversal for Directed Graph:");
        directedGraph.bfsDirected(0); // Output: 0 1 2 3

        if (directedGraph.hasCycleDFSDirected()) {
            System.out.println("Cycle detected in Directed Graph using DFS.");
        } else {
            System.out.println("No cycle detected in Directed Graph using DFS.");
        }

        if (directedGraph.hasCycleBFSDirected()) {
            System.out.println("Cycle detected in Directed Graph using BFS.");
        } else {
            System.out.println("No cycle detected in Directed Graph using BFS.");
        }
    }
}
