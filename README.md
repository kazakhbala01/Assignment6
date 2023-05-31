# Assignment6

# Main
```java
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
```
# Vertex class
```java
import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public V getData() {
        return data;
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

class WeightedGraph<V> {
    private Map<Vertex<V>, Map<Vertex<V>, Double>> adjacencyList;

    public WeightedGraph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex<V> vertex) {
        adjacencyList.put(vertex, new HashMap<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        validateVertex(source);
        validateVertex(destination);
        adjacencyList.get(source).put(destination, weight);
    }

    private void validateVertex(Vertex<V> vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + vertex + " is out of the range");
        }
    }

    public Map<Vertex<V>, Double> getAdjacentVertices(Vertex<V> vertex) {
        validateVertex(vertex);
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }

    public void printGraph() {
        for (Map.Entry<Vertex<V>, Map<Vertex<V>, Double>> entry : adjacencyList.entrySet()) {
            Vertex<V> vertex = entry.getKey();
            Map<Vertex<V>, Double> neighbors = entry.getValue();
            System.out.print("Vertex " + vertex + " is connected to: ");
            for (Map.Entry<Vertex<V>, Double> neighbor : neighbors.entrySet()) {
                System.out.print(neighbor.getKey() + " (Weight: " + neighbor.getValue() + ") ");
            }
            System.out.println();
        }
    }
}
```

# Search class
```java
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Search {
    public static void BFS(Vertex<Integer> start) {
        WeightedGraph<Integer> graph = new WeightedGraph<>();
        for (int i = 1; i <= 5; i++) {
            graph.addVertex(new Vertex<>(i));
        }

        graph.addEdge(new Vertex<>(1), new Vertex<>(2), 0);
        graph.addEdge(new Vertex<>(1), new Vertex<>(3), 0);
        graph.addEdge(new Vertex<>(2), new Vertex<>(4), 0);
        graph.addEdge(new Vertex<>(3), new Vertex<>(4), 0);
        graph.addEdge(new Vertex<>(4), new Vertex<>(5), 0);

        Map<Vertex<Integer>, Boolean> visited = new HashMap<>();
        for (Vertex<Integer> vertex : graph.getAdjacentVertices(start).keySet()) {
            visited.put(vertex, false);
        }

        Queue<Vertex<Integer>> queue = new LinkedList<>();
        visited.put(start, true);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Vertex<Integer> vertex = queue.poll();
            System.out.print(vertex + " ");

            for (Vertex<Integer> neighbor : graph.getAdjacentVertices(vertex).keySet()) {
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    queue.offer(neighbor);
                }
            }
        }
    }
    }
```
#Dijkstra search
```java
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch {
    public static Map<Vertex<Integer>, Double> dijkstraSearch(Vertex<Integer> start) {
        WeightedGraph<Integer> graph = new WeightedGraph<>();

        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);
        Vertex<Integer> vertex5 = new Vertex<>(5);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);

        graph.addEdge(vertex1, vertex2, 4);
        graph.addEdge(vertex1, vertex3, 2);
        graph.addEdge(vertex2, vertex4, 3);
        graph.addEdge(vertex3, vertex4, 1);
        graph.addEdge(vertex4, vertex5, 2);

        Map<Vertex<Integer>, Double> distances = new HashMap<>();
        for (Vertex<Integer> vertex : graph.getAdjacentVertices(start).keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);

        PriorityQueue<Vertex<Integer>> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        pq.offer(start);

        while (!pq.isEmpty()) {
            Vertex<Integer> vertex = pq.poll();
            double currentDistance = distances.get(vertex);

            for (Map.Entry<Vertex<Integer>, Double> entry : graph.getAdjacentVertices(vertex).entrySet()) {
                Vertex<Integer> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDistance = currentDistance + weight;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    pq.offer(neighbor);
                }
            }
        }

        return distances;
    }
}
```
