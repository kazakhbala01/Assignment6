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