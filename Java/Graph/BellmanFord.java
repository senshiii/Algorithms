
// package Graph;
import java.util.*;

public class BellmanFord {

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

  static void printIntArray(int[] arr) {
    ArrayList<Integer> al = new ArrayList<Integer>();
    Collections.addAll(al, Arrays.stream(arr).boxed().toArray(Integer[]::new));
    System.out.println(al);
  }

  static int[] getShortestPathByBellmanFord(WeightedGraph g, int source) {
    int dist[] = new int[g.numberOfVertices];
    for (int i = 0; i < dist.length; i++) {
      dist[i] = Integer.MAX_VALUE;
    }
    dist[source] = 0;
    Edge edge;

    // Relax all the edges for N-1 times where N = Number of vertices in the graph
    for (int count = 0; count < g.numberOfVertices - 1; count++) {
      for (int i = 0; i < g.numberOfVertices; i++) {
        Iterator<Edge> it = g.adjList[i].listIterator();
        while (it.hasNext()) {
          edge = (Edge) it.next();
          System.out.println(edge);
          System.out.println("Current Distance of Source, Destination: " + dist[i] + " , " + dist[edge.destination]);
          if (dist[i] != Integer.MAX_VALUE && dist[i] + edge.weight < dist[edge.destination]) {
            System.out.println("Relaxed weight of Edge from " + i + " to " + edge.destination + " from :"
                + dist[edge.destination] + " to " + (dist[i] + edge.weight));
            dist[edge.destination] = dist[i] + edge.weight;
          }
        }
      }
      System.out.print("Distances after Relaxation-" + count + ": ");
      printIntArray(dist);
    }

    // Relaxing the edges one more time to check for negative weigh cycles
    for (int i = 0; i < g.numberOfVertices; i++) {
      Iterator<Edge> it = g.adjList[i].listIterator();
      while (it.hasNext()) {
        edge = (Edge) it.next();
        if (dist[i] != Integer.MAX_VALUE && dist[i] + edge.weight < dist[edge.destination]) {
          System.out.println(
              "\n GRAPH CONTAINS NEGATIVE WEIGHT CYCLE. BELLMAN FORD ALGORITHM WILL NOT WORK. SHORTEST PATH COULD NOT BE FOUND ");
          // Returning Empty Array
          return new int[0];
        }
      }
    }

    return dist;
  }

  public static void main(String[] args) {
    WeightedGraph g = new WeightedGraph(5);

    g.addEdge(0, 2, 4);
    g.addEdge(0, 1, -1);

    g.addEdge(1, 2, 3);
    g.addEdge(1, 3, 2);
    g.addEdge(1, 4, 2);

    g.addEdge(3, 1, 1);
    g.addEdge(3, 2, 5);

    g.addEdge(4, 3, -3);

    int[] ans = getShortestPathByBellmanFord(g, 0);

    System.out.println("\n Single Source Shortest Path by Bellman Ford Algorithm: ");
    printIntArray(ans);

  }

}
