
// package Graph;
import java.util.*;

public class Dijkstra {

  static class Edge {

    int source, destination, weight;

    @Override
    public String toString() {
      return "Source: " + this.source + " , Destination: " + this.destination + " , Weight: " + this.weight;
    }

    Edge(int source, int dest, int weight) {
      this.destination = dest;
      this.source = source;
      this.weight = weight;
    }
  }

  static class WeightedGraph {

    int numberOfVertices;
    LinkedList<Edge>[] adjList;

    WeightedGraph(int n) {
      numberOfVertices = n;
      adjList = new LinkedList[n];
      for (int i = 0; i < n; i++) {
        adjList[i] = new LinkedList<Edge>();
      }
    }

    void addEdge(int source, int dest, int weight) {
      Edge newEdge = new Edge(source, dest, weight);
      adjList[source].add(newEdge);
    }

    void print() {
      for (int i = 0; i < numberOfVertices; i++) {
        ListIterator<Edge> iterator = adjList[i].listIterator();
        System.out.println("Vertex " + i + " :");
        while (iterator.hasNext()) {
          Edge edge = (Edge) iterator.next();
          System.out.println("\t --> ( Destination Vertex: " + edge.destination + ", Weight: " + edge.weight + " )");
        }
      }
    }

  }

  static int getMinDistanceVertex(int dist[], boolean visited[]) {
    int min = Integer.MAX_VALUE, min_index = -1;
    for (int i = 0; i < dist.length; i++) {
      if (!visited[i] && dist[i] < min) {
        min = dist[i];
        min_index = i;
      }
    }
    return min_index;
  }

  static int[] findShortestPathByDijkstra(WeightedGraph g, int src) {
    int[] dist = new int[g.numberOfVertices];
    boolean[] visited = new boolean[g.numberOfVertices];

    // Initialize distance of every vertex as Infinity
    for (int i = 0; i < nV; i++) {
      dist[i] = Integer.MAX_VALUE;
      visited[i] = false;
    }
    dist[src] = 0; // Initiaizing Distance of Source Vertex as 0.

    for (int i = 0; i < g.numberOfVertices - 1; i++) {

      // Get Vertex with the minimum distance as source.
      int u = getMinDistanceVertex(dist, visited);
      System.out.println("Minimum Distance Vertex, Distance: " + u + ", " + dist[u]);

      // Set this vertex as the current vertex. Update the visited flag for this
      // vertex.
      visited[u] = true;

      // Update the distance of the neighbours
      Iterator<Edge> it = g.adjList[u].listIterator();
      while (it.hasNext()) {
        Edge edge = it.next();
        if (!visited[edge.destination] && dist[u] != Integer.MAX_VALUE
            && dist[u] + edge.weight < dist[edge.destination]) {
          System.out.println("Updating Distance of Vertex: " + edge.destination + " from: " + dist[edge.destination]
              + " to: " + (edge.weight + dist[u]));
          dist[edge.destination] = dist[u] + edge.weight;
        }
      }
      ArrayList<Integer> distList = new ArrayList<Integer>();
      Collections.addAll(distList, Arrays.stream(dist).boxed().toArray(Integer[]::new));
      System.out.println("Shortest Distances for Vertex: " + u + ", " + distList);
      System.out.println();
    }

    Integer[] distArr = Arrays.stream(dist).boxed().toArray(Integer[]::new);
    ArrayList<Integer> distList = new ArrayList<Integer>();
    Collections.addAll(distList, distArr);
    System.out.println(distList);
    return dist;
  }

  public static void main(String args[]) {
    WeightedGraph g = new WeightedGraph(6);
    g.addEdge(0, 1, 4);
    g.addEdge(0, 2, 3);

    g.addEdge(1, 2, 5);
    g.addEdge(1, 3, 2);

    g.addEdge(2, 3, 7);

    g.addEdge(3, 4, 2);

    g.addEdge(4, 0, 4);
    g.addEdge(4, 1, 4);
    g.addEdge(4, 5, 6);

    System.out.println("Weighted Graph is: ");
    g.print();

    System.out.println("Shortest Path Distances: ");
    findShortestPathByDijkstra(g, 0);

  }

}
