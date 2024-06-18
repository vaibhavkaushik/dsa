package Graph;

import java.util.*;

public class GraphQuestions {

    //Leetcode 1791
    public int findCenter(int[][] edges) {
        // vertices calculate karenge
        int vertices = edges.length + 1;

        // adjacency list banate hain
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // edges ko adjacency list mein add karenge
        for (int[] edge : edges) {
            adj.get(edge[0] - 1).add(edge[1] - 1);
            adj.get(edge[1] - 1).add(edge[0] - 1);
        }

        // node ko 1 se start karenge
        int node = 1;
        for (List<Integer> neighbours : adj) {
            // agar neighbours ki size edges ke barabar hai, to ye center node hai
            if (neighbours.size() == edges.length) {
                return node;
            }
            node++;
        }

        // agar center node nahi milta, to -1 return karenge
        return -1;
    }


    //Leetcode 1971
    // Graph path ko valid check karne ka method
    public boolean validPath(int n, int[][] edges, int source, int destination) {

        // Total vertices ki sankhya
        int vertices = n;

        // Adjacency list banate hain
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // Graph mein edges ko adjacency list mein add karte hain
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Visited array initialize karte hain
        boolean[] visited = new boolean[n];

        // BFS call karke path check karte hain
        return BFS(source, destination, visited, adj);
    }

    // BFS method jo source se destination tak path check karta hai
    public boolean BFS(int source, int destination, boolean[] visited, List<List<Integer>> adj) {

        // Agar source hi destination hai to return true
        if(source == destination) {
            return true;
        }

        // BFS ke liye queue banate hain
        Queue<Integer> q = new LinkedList<>();
        q.offer(source);
        visited[source] = true;

        // Jab tak queue empty na ho, BFS perform karte hain
        while(!q.isEmpty()) {

            // Queue se node nikalte hain
            int node = q.poll();

            // Har neighbor ko check karte hain
            for(int neighbour : adj.get(node)) {

                // Agar neighbor visit nahi hua to process karte hain
                if(!visited[neighbour]) {

                    visited[neighbour] = true;

                    // Agar neighbor destination hai to return true
                    if(neighbour == destination) {
                        return true;
                    }

                    // Neighbor ko queue mein add karte hain
                    q.offer(neighbour);
                }
            }
        }

        // Agar path nahi milta to return false
        return false;
    }

    // Method to find the minimum cost to connect all points
    //Leetcode 1584
    public int minCostConnectPoints(int[][] points) {

        // List to store all edges {u, v, weight}
        List<int[]> edges = new ArrayList<>();

        // Calculate the distance between all pairs of points and store as edges
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{i, j, dist});
            }
        }

        // Sort the edges based on their weight (distance)
        Collections.sort(edges, Comparator.comparingInt(val -> val[2]));

        // Initialize Union-Find structure
        int[] parent = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            parent[i] = i; // Initially, every node is its own parent
        }

        int totalCost = 0; // Variable to store the total cost of connecting all points

        // Iterate through the sorted edges
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            int xParent = findParent(u, parent);
            int yParent = findParent(v, parent);

            // If u and v are in different components, connect them
            if (xParent != yParent) {
                parent[xParent] = yParent; // Union operation
                totalCost += weight; // Add the weight to the total cost
            }
        }

        return totalCost; // Return the total cost
    }

    // Find the root parent of x with path compression
    int findParent(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x], parent);
        }
        return parent[x];
    }

    // Method to find the minimum cost to connect all points using Prim's Algorithm
    //Leetcode 1584
    public int minCostConnectPointsPrims(int[][] points) {

        // Priority Queue for selecting the minimum edge
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[points.length];
        int totalCost = 0;
        int edgesUsed = 0;

        // Start with the first point
        pq.offer(new int[]{0, 0}); // {node, cost}

        while (edgesUsed < points.length) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentCost = current[1];

            // If the node is already visited, skip it
            if (visited[currentNode]) {
                continue;
            }

            // Mark the node as visited
            visited[currentNode] = true;
            totalCost += currentCost;
            edgesUsed++;

            // Add all adjacent nodes to the priority queue
            for (int i = 0; i < points.length; i++) {
                if (!visited[i]) {
                    int dist = Math.abs(points[currentNode][0] - points[i][0]) + Math.abs(points[currentNode][1] - points[i][1]);
                    pq.offer(new int[]{i, dist});
                }
            }
        }

        return totalCost;
    }

    //Leetcode 2685
    // Method to count complete components in the graph
    public int countCompleteComponents(int n, int[][] edges) {

        // Adjacency list banate hain
        Map<Integer, List<Integer>> adj = new HashMap<>();

        // Edges ko adjacency list mein add karte hain
        for (int[] edge : edges) {
            adj.putIfAbsent(edge[0], new ArrayList<>());
            adj.putIfAbsent(edge[1], new ArrayList<>());

            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Visited array aur accounted array initialize karte hain
        boolean[] visited = new boolean[n];
        boolean[] accounted = new boolean[n];

        // Initial count mein un nodes ko count karte hain jo kisi bhi edge ka part nahi hain
        int count = n - adj.size();

        // Har node ko check karte hain agar wo visited nahi hai
        for (int node : adj.keySet()) {
            List<Integer> component = new ArrayList<>();
            if (!visited[node]) {
                // DFS call karte hain component ko find karne ke liye
                DFS(node, adj, visited, component);

                int expected_degree = component.size() - 1;
                int found = 0;

                // Component ke har node ka degree check karte hain
                for (int component_node : component) {
                    if (!accounted[component_node] && adj.get(component_node).size() == expected_degree) {
                        found++;
                    }
                    accounted[component_node] = true;
                }

                // Agar sab nodes ka degree expected degree ke barabar hai to count increment karte hain
                if (found == component.size()) {
                    count++;
                }
            }
        }

        return count; // Final count return karte hain
    }

    // DFS method jo component ko visit karta hai
    void DFS(int source, Map<Integer, List<Integer>> adj, boolean[] visited, List<Integer> component) {
        visited[source] = true;
        component.add(source);

        for (int neighbour : adj.get(source)) {
            if (!visited[neighbour]) {
                DFS(neighbour, adj, visited, component);
            }
        }
    }

    //Leetcode 1615
    public int maximalNetworkRank(int n, int[][] roads) {
        // Adjacency list banate hain
        Map<Integer, List<Integer>> adj = new HashMap<>();

        // Roads ko adjacency list mein add karte hain
        for (int[] road : roads) {
            adj.putIfAbsent(road[0], new ArrayList<>());
            adj.putIfAbsent(road[1], new ArrayList<>());

            adj.get(road[0]).add(road[1]);
            adj.get(road[1]).add(road[0]);
        }

        // List to store vertices with their corresponding list sizes (degrees)
        List<int[]> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(new int[]{i, adj.getOrDefault(i, new ArrayList<>()).size()});
        }

        // Sort the vertices by list size (degree) in reverse order
        Collections.sort(vertices, Comparator.comparingInt((int[] val) -> val[1]).reversed());

        // Maximal network rank ko calculate karte hain
        int maxRank = 0;
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                int[] vertexA = vertices.get(i);
                int[] vertexB = vertices.get(j);
                int rank = vertexA[1] + vertexB[1];

                // Agar vertexA aur vertexB direct connection share karte hain to rank se 1 subtract karte hain
                if (adj.get(vertexA[0])!=null && adj.get(vertexA[0]).contains(vertexB[0])) {
                    rank--;
                }

                // Update maxRank if the current rank is greater
                maxRank = Math.max(maxRank, rank);
            }
        }

        return maxRank;
    }

    //Leetcode 1615
    public int maximalNetworkRankOptimized(int n, int[][] roads) {
        // Degree array initialize karte hain
        int[] degree = new int[n];

        // Adjacency matrix initialize karte hain to keep track of direct connections
        boolean[][] directConnection = new boolean[n][n];

        // Degree array aur adjacency matrix fill karte hain
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            degree[a]++;
            degree[b]++;
            directConnection[a][b] = true;
            directConnection[b][a] = true;
        }

        // Maximal network rank ko calculate karte hain
        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentRank = degree[i] + degree[j];
                if (directConnection[i][j]) {
                    currentRank--; // Agar direct connection hai to rank se 1 subtract karte hain
                }
                maxRank = Math.max(maxRank, currentRank); // Max rank ko update karte hain
            }
        }

        return maxRank; // Maximal network rank return karte hain
    }

    //Leetcode 1466
    public int minReorder(int n, int[][] connections) {

        Map<Integer,List<Integer>> adj = new HashMap<>();
        Map<Integer,List<Integer>> directed = new HashMap<>();

        for(int[] edge : connections){
            adj.putIfAbsent(edge[0], new ArrayList<>());
            adj.putIfAbsent(edge[1], new ArrayList<>());
            directed.putIfAbsent(edge[0], new ArrayList<>());
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
            directed.get(edge[0]).add(edge[1]);
        }

        boolean[] visited = new boolean[n];
        int[] count = new int[]{0};
        DFS(0,visited,adj,directed,count);

        return count[0];
    }

    void DFS(int src, boolean[] visited, Map<Integer,List<Integer>> adj, Map<Integer,List<Integer>> directed, int[] count){

        visited[src] = true;

        for(int neighbour :  adj.get(src)){
            if(!visited[neighbour]){
                visited[neighbour] = true;
                if(directed.getOrDefault(src, new ArrayList<>()).contains(neighbour)){
                    count[0]++;
                }
                DFS(neighbour,visited,adj,directed,count);
            }
        }
    }

    //Leetcode 684
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length+1];

        for(int i=0; i<parent.length; i++) {
            parent[i] = i;
        }

        for(int[] edge : edges) {
            if(find(edge[0],parent) == find(edge[1],parent)) {
                return edge;
            }

            union(edge[0], edge[1],parent);
        }

        return new int[]{-1,-1};
    }


    // Find Operation
    private int find(int node, int[] parent) {
        while(parent[node] != node) {
            node = parent[node];
        }

        return node;
    }

    //Find Union Operation
    private void union(int x, int y, int[] parent) {
        int xRoot = find(x,parent);
        int yRoot = find(y,parent);

        if(xRoot != yRoot) {
            parent[yRoot] = xRoot;
        }
    }

    //Leetcode 802
    public List<Integer> eventualSafeNodes(int[][] graph) {


        boolean[] visited = new boolean[graph.length];
        boolean[] isInRecursion = new boolean[graph.length];

        for(int i=0; i<graph.length; i++){
            if(!visited[i]){
                DFSCycle(visited, isInRecursion, graph, i);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (!isInRecursion[i]) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }

    boolean DFSCycle(boolean[] visited, boolean[] isInRecursion, int[][] graph, int src){

        visited[src]=true;
        isInRecursion[src]=true;

        for(int neighbour : graph[src]){
            if(!visited[neighbour] && DFSCycle(visited,isInRecursion,graph,neighbour)){
                return true;
            }else if(isInRecursion[neighbour]){
                return true;
            }
        }

        isInRecursion[src]=false;
        return false;
    }

    //Leetcode 2285
    public long maximumImportance(int n, int[][] roads) {
        List<List<Integer>> adjList = new ArrayList<>(n);

        // Initialize adjacency list
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Fill the adjacency list with the roads information
        for (int[] road : roads) {
            adjList.get(road[0]).add(road[1]);
            adjList.get(road[1]).add(road[0]);
        }

        // Create a list of nodes with their degrees
        List<int[]> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new int[]{i, adjList.get(i).size()});
        }

        // Sort nodes by their degrees in descending order
        nodes.sort(Comparator.comparingInt((int[] a) -> a[1]).reversed());
        //nodes.sort((a, b) -> Integer.compare(b[1], a[1]));
        //nodes.sort(Comparator.comparingInt(a -> a[1]).reversed());
        //nodes.sort((a, b) -> b[1] - a[1]); //Little bit problematic in case of integer overflow

        int[] values = new int[n]; // Har node ko assigned value ko store karne ke liye array
        int value = n; // Initial value sabse badi value se start karte hain

        // Highest degree wale nodes ko highest values assign karte hain
        for (int[] node : nodes) {
            values[node[0]] = value--;
        }

        long totalImportance = 0; // Total importance calculate karne ke liye variable

        // Sab roads ka total importance calculate karte hain
        for (int[] road : roads) {
            totalImportance += values[road[0]] + values[road[1]];
        }

        return totalImportance; // Total importance ko return karte hain
    }

    //Leetcode 399
    // Main method to calculate the results for queries
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Graph ko build karte hain equations aur values se
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // Har equation ko graph mein add karte hain
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0); // Equation ka numerator
            String b = equations.get(i).get(1); // Equation ka denominator
            graph.putIfAbsent(a, new HashMap<>()); // Agar node 'a' graph mein nahi hai, to usko add karte hain
            graph.putIfAbsent(b, new HashMap<>()); // Agar node 'b' graph mein nahi hai, to usko add karte hain
            graph.get(a).put(b, values[i]); // 'a' se 'b' tak ka edge with ratio 'values[i]'
            graph.get(b).put(a, 1.0 / values[i]); // 'b' se 'a' tak ka edge with reciprocal ratio
        }

        double[] results = new double[queries.size()]; // Results array initialize karte hain

        // Har query ko process karte hain
        for (int i = 0; i < queries.size(); i++) {
            String numerator = queries.get(i).get(0); // Query ka numerator
            String denominator = queries.get(i).get(1); // Query ka denominator
            if (!graph.containsKey(numerator) || !graph.containsKey(denominator)) { // Agar graph mein nodes nahi hain
                results[i] = -1.0; // Result -1.0 set karte hain
            } else if (numerator.equals(denominator)) { // Agar numerator aur denominator same hain
                results[i] = 1.0; // Result 1.0 set karte hain
            } else {
                Set<String> visited = new HashSet<>(); // Visited set initialize karte hain taaki cycle na bane
                results[i] = dfs(graph, numerator, denominator, 1.0, visited); // DFS call karte hain result calculate karne ke liye
            }
        }

        return results; // Sab results return karte hain
    }

    // DFS traversal to find the ratio between two nodes
    private double dfs(Map<String, Map<String, Double>> graph, String current, String target, double value, Set<String> visited) {
        if (current.equals(target)) { // Agar current node target ke barabar hai
            return value; // Current value return karte hain
        }

        visited.add(current); // Current node ko visited set mein add karte hain

        Map<String, Double> neighbors = graph.get(current); // Current node ke neighbors ko get karte hain
        for (String neighbor : neighbors.keySet()) { // Har neighbor ko visit karte hain
            if (!visited.contains(neighbor)) { // Agar neighbor visited nahi hai
                double result = dfs(graph, neighbor, target, value * neighbors.get(neighbor), visited); // DFS call karte hain
                if (result != -1.0) { // Agar result valid hai
                    return result; // Valid result return karte hain
                }
            }
        }

        return -1.0; // Agar target node nahi mili, to -1.0 return karte hain
    }

    //Leetcode 1334
    public int findTheCity(int n, int[][] edges, int distanceThreshold)
    {//T->  O( V^3 ),  S-> O( V^2 )
        int[][] D= new int[n][n];//our matrix, containing all vertex shortest path cost

        for ( int[] row: D ){
            //filling each row of the matrix with +infinity as the distance is unreachable till now, since we have not accesed the edge list
            //when we access the edge list the actual cost will be added to the matrix cell
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for ( int[] edge: edges )
        {//adding the cost to the matrix cell

            int from= edge[0];
            int to= edge[1];
            int wt= edge[2];

            //undirected edge
            D[from][to]= wt;
            D[to][from]= wt;
        }

        //D -> D0 -> D1 -> D2 -> D3 -> D4 -> ......Dk//At every stage minimizing the cost
        for ( int k= 0; k< n; k++ )//evaluating each vertex shortest path //intermediate vertex
        {
            for ( int i= 0; i< n; i++ )//Exploring all the starting vertex
            {
                for ( int j= 0; j< n; j++ )//Exploring all the ending vertex
                {
                    if ( D[i][k] == Integer.MAX_VALUE || D[k][j] == Integer.MAX_VALUE)//if we cannot reach the k vertex from ith vertex(source) or if we cannot reach j(dest) from k, we just move on, means thats its ureachable
                        continue;
                    else if ( ( D[i][k]+ D[k][j] ) < D[i][j] )//if including the vertex k reduce the current cost we replace it with the current cost( arr[i][j] ) //Greedy Approach
                        D[i][j]= D[i][k] + D[k][j];
                }
            }
        }

        int minNoOfCity= Integer.MAX_VALUE;//Identity of minimum
        int res= -1;//worst case, if we cannot find any city or vertex in the vicinity of our current vertex

        //every row in our matrix consist of 1 vertex(row number) and it includes min cost to all other vertex as it is a n*n matrix

        for ( int i= 0; i< n; i++ )//Starting vertex
        {
            int noCityCount= 0;
            for ( int j= 0; j< n; j++ )//Ending vertex
            {
                if ( i == j ) //not consider the city inside city case
                    continue;
                if ( D[i][j] <= distanceThreshold)//city within the our threshold value
                    noCityCount+= 1;//increasing the city count for the particular vertex
            }
            if ( noCityCount <= minNoOfCity ){//updating the minimum city count vertex every time
                minNoOfCity= noCityCount;
                res= i;//current vertex with min no city
            }
        }
        return res;//returning the vertex with min no of city
    }

    //Leetcode 947
    // DFS function to visit all connected stones
    private void dfs(int[][] stones, int index, boolean[] visited, int n) {
        visited[index] = true;

        for (int i = 0; i < n; i++) {
            if (!visited[i] &&
                    (stones[i][0] == stones[index][0] || stones[i][1] == stones[index][1])) {
                dfs(stones, i, visited, n);
            }
        }
    }

    // Function to remove stones
    public int removeStones(int[][] stones) {
        int n = stones.length;
        boolean[] visited = new boolean[n];

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(stones, i, visited, n);
                count++;
            }
        }

        return n - count;
    }

    //Leetcode 2368
    // DFS function to visit all connected nodes except restricted ones
    private void dfs(List<List<Integer>> graph, boolean[] visited, boolean[] restricted, int node) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor] && !restricted[neighbor]) {
                dfs(graph, visited, restricted, neighbor);
            }
        }
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        boolean[] restrictedNodes = new boolean[n];

        for (int node : restricted) {
            restrictedNodes[node] = true;
        }

        // Start DFS from node 0
        dfs(graph, visited, restrictedNodes, 0);

        // Count visited nodes
        int count = 0;
        for (boolean visit : visited) {
            if (visit) {
                count++;
            }
        }

        return count;
    }

    //Leetcode 2924
    public int findChampion(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        // Initialize adjacency list
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Fill adjacency list and calculate indegree of each node
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            indegree[v]++;
        }

        // Find nodes with indegree 0
        int champion = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                if (champion == -1) {
                    champion = i;
                } else {
                    // More than one node with indegree 0 means no unique champion
                    return -1;
                }
            }
        }

        // Return the unique champion if there is one, otherwise return -1
        return champion;
    }

    //Leetcode 133

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) return null;

        // If the node is already cloned, return the cloned node
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Clone the node

        Node cloneNode = new Node(node.val);
        map.put(node, cloneNode);

        // Clone all the neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(dfs(neighbor, map));
        }

        return cloneNode;
    }

    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    //Leetcode 2492
    // Helper function to perform DFS
    private void dfs(Map<Integer, List<int[]>> adj, int u, boolean[] visited, int[] result) {
        visited[u] = true;

        // Traverse all neighbors
        for (int[] neighbor : adj.get(u)) {
            int v = neighbor[0];
            int c = neighbor[1];

            // Update the minimum score
            result[0] = Math.min(result[0], c);

            // If the neighbor is not visited, continue DFS
            if (!visited[v]) {
                dfs(adj, v, visited, result);
            }
        }
    }

    public int minScore(int n, int[][] roads) {
        // Create adjacency list
        Map<Integer, List<int[]>> adj = new HashMap<>();

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int c = road[2];

            // Add the road to the adjacency list using putIfAbsent
            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(u).add(new int[]{v, c});
            adj.get(v).add(new int[]{u, c});
        }

        // Initialize visited array
        boolean[] visited = new boolean[n + 1];
        int[] result = new int[]{Integer.MAX_VALUE};

        // Start DFS from node 1
        dfs(adj, 1, visited, result);

        return result[0];
    }

}
