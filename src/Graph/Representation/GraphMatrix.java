package Graph.Representation;

public class GraphMatrix {
    private int[][] adjMatrix;
    private int numVertices;

    // Constructor to create a graph with n vertices
    public GraphMatrix(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];
    }

    // Add an edge between two vertices
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = 1;
        adjMatrix[j][i] = 1; // For undirected graph
    }

    // Remove an edge between two vertices
    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = 0;
        adjMatrix[j][i] = 0; // For undirected graph
    }

    // Print the adjacency matrix
    public void printMatrix() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphMatrix g = new GraphMatrix(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        g.printMatrix();
    }
}

