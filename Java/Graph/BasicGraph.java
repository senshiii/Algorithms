import java.util.*;

public class BasicGraph {
  public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // System.out.println("Enter the number of nodes in the Graph: ");
    // int n = Integer.parseInt(sc.nextLine());
    Graph g = new Graph(6);
    g.addEdge(5, 0);
    g.addEdge(5, 2);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);
    System.out.println("Adjacency List");
    g.print();
    System.out.println("BFS: ");
    g.BFS(2);
    System.out.println("DFS: ");
    g.DFS(2);
    System.out.println("Topological Sort: ");
    g.topologicalSort();
  }
}
