package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

    // start from the root node
    visited.add(root);
    queue.add(root);
    parentMap.put(root, null);

    while (!queue.isEmpty()) {
      T node = queue.poll();

      // if target is found, compute the path by backtracking
      if (node.equals(target)) {
        List<T> path = new ArrayList<>();
        for (T current = target; current != null; current = parentMap.get(current)) {
          path.add(current);
        }

        // make the path start from the root
        return path.reversed();
      }

      for (T adjNode : adjacencyMap.get(node)) {
        // if we haven't visited a child node, add to queue
        if (visited.add(adjNode)) {
          queue.add(adjNode);
          parentMap.put(adjNode, node);
        }
      }
    }

    return null;
  }
}
