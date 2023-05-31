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