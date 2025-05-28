package nz.ac.auckland.se281;

import java.util.*;

public class Graph<T> {

  private Map<T, List<T>> adjacencyMap;

  public Graph() {
    this.adjacencyMap = new HashMap<>();
  }

  public void addNode(T node) {
    adjacencyMap.putIfAbsent(node, new ArrayList<>());
  }

  public void removeNode(T node) {
    adjacencyMap.remove(node);
    for (T key : adjacencyMap.keySet()) {
      adjacencyMap.get(key).remove(node);
    }
  }

  public void addEdge(T node1, T node2) {
    addNode(node1);
    addNode(node2);
    adjacencyMap.get(node1).add(node2);
  }

  public void removeEdge(T node1, T node2) {
    adjacencyMap.getOrDefault(node2, new ArrayList<>()).remove(node2);
    adjacencyMap.getOrDefault(node2, new ArrayList<>()).remove(node1);
  }

  public List<T> getAdjacentNodes(T node) {
    return adjacencyMap.get(node);
  }

  public List<T> getShortestPath(T root, T target) {
    Set<T> visited = new HashSet<>();
    Queue<T> queue = new LinkedList<>();
    Map<T, T> parentMap = new HashMap<>();

    visited.add(root);
    queue.add(root);
    parentMap.put(root, null);

    while (!queue.isEmpty()) {
      T node = queue.poll();

      if (node.equals(target)) {
        List<T> path = new ArrayList<>();

        for (T current = target; current != null; current = parentMap.get(current)) {
          path.add(current);
        }

        return path.reversed();
      }

      for (T adjNode : adjacencyMap.get(node)) {

        if (visited.add(adjNode)) {
          queue.add(adjNode);
          parentMap.put(adjNode, node);
        }
      }
    }

    return null;
  }
}
