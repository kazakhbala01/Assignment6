import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create vertices
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);
        Vertex<Integer> vertex5 = new Vertex<>(5);

        // Create a weighted graph
        WeightedGraph<Integer> weightedGraph = new WeightedGraph<>();
        weightedGraph.addVertex(vertex1);
        weightedGraph.addVertex(vertex2);
        weightedGraph.addVertex(vertex3);
        weightedGraph.addVertex(vertex4);
        weightedGraph.addVertex(vertex5);

        // Add edges with weights
        weightedGraph.addEdge(vertex1, vertex2, 4);
        weightedGraph.addEdge(vertex1, vertex3, 2);
        weightedGraph.addEdge(vertex2, vertex4, 3);
        weightedGraph.addEdge(vertex3, vertex4, 1);
        weightedGraph.addEdge(vertex4, vertex5, 2);

        // Print the graph
        weightedGraph.printGraph();

        // Perform Dijkstra's search
        Map<Vertex<Integer>, Double> dijkstraDistances = DijkstraSearch.dijkstraSearch(vertex1);
        System.out.println("Dijkstra's distances:");
        for (Map.Entry<Vertex<Integer>, Double> entry : dijkstraDistances.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Perform BFS search
        System.out.print("BFS traversal: ");
        Search.BFS(vertex1);
    }
}