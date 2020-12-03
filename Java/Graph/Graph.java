import java.util.*;

/**
 * Graph
 */
public class Graph {

  LinkedList<Integer> graph[];
  int numberOfNodes;

  Graph(int n) {
    numberOfNodes = n;
    graph = new LinkedList[n];
    for (int i = 0; i < numberOfNodes; i++) {
      graph[i] = new LinkedList<Integer>();
    }
  }

  void addEdge(int u, int v) {
    graph[u].add(v);
  }

  void print() {
    for (LinkedList<Integer> list : graph) {
      System.out.println(list);
    }
  }

  void BFS(int s) {
    boolean visited[] = new boolean[numberOfNodes];
    int curNode;
    LinkedList<Integer> q = new LinkedList<Integer>();
    q.add(s);
    visited[s] = true;
    while (q.size() != 0) {
      curNode = q.remove();
      System.out.print(curNode + " ");
      Iterator<Integer> i = graph[curNode].listIterator();
      while (i.hasNext()) {
        int node = i.next();
        if (!visited[node]) {
          visited[node] = true;
          q.add(node);
        }
      }
    }
    System.out.println();
  }

  void DFS(int s) {
    boolean visited[] = new boolean[numberOfNodes];
    LinkedList<Integer> stack = new LinkedList<Integer>();
    stack.add(s);
    visited[s] = true;
    int top;
    while (stack.size() > 0) {
      top = stack.removeLast();
      System.out.print(top + " ");
      Iterator<Integer> it = graph[top].listIterator();
      while (it.hasNext()) {
        int node = it.next();
        if (!visited[node]) {
          visited[node] = true;
          stack.add(node);
        }
      }
    }
    System.out.println();
  }

  void topologicalSort() {
    int inDeg[] = new int[numberOfNodes], visitedNodes = 0;
    for (int i = 0; i < numberOfNodes; i++) {
      LinkedList<Integer> list = (LinkedList<Integer>) graph[i];
      for (int node : list) {
        inDeg[node]++;
      }
    }
    LinkedList<Integer> q = new LinkedList<Integer>();
    ArrayList<Integer> topOrder = new ArrayList<Integer>();
    for (int i = 0; i < inDeg.length;i++) {
      if (inDeg[i] == 0) {
        q.add(i);
      }
    }
    int curNode, neighbourNode;
    while (q.size() != 0) {
      curNode = q.removeFirst();
      // System.out.println("Current Node: " + curNode);
      visitedNodes++;
      topOrder.add(curNode);
      ListIterator<Integer> it = graph[curNode].listIterator();
      while (it.hasNext()) {
        neighbourNode = it.next();
        if (--inDeg[neighbourNode] == 0) {
          q.add(neighbourNode);
        }
      }
    }

    if (visitedNodes != numberOfNodes) {
      System.out
          .println("Topological Order is not possible for given Graph. Graph contains a cycle and hence is not a DAG");
    } else {
      System.out.println(topOrder);
    }

  }

}
