package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
