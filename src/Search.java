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