import java.util.*;

public class Driver {
  public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // System.out.println("Enter the number of nodes in the Graph: ");
    // int n = Integer.parseInt(sc.nextLine());
    Graph g = new Graph(4);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);
    System.out.println("BFS: ");
    g.BFS(2);
    System.out.println("DFS: ");
    g.DFS(2);
    // g.print();
  }
}
