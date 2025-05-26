package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

  private Map<Country, List<Country>> adjacencyMap;

  public Graph() {
    this.adjacencyMap = new HashMap<>();
  }

  public void addNode(Country node) {
    adjacencyMap.putIfAbsent(node, new ArrayList<>());
  }

  public void removeNode(Country node) {
    adjacencyMap.remove(node);
    for (Country key : adjacencyMap.keySet()) {
      adjacencyMap.get(key).remove(node);
    }
  }

  public void addEdge(Country node1, Country node2) {
    addNode(node1);
    addNode(node2);
    adjacencyMap.get(node1).add(node2);
  }

  public void removeEdge(Country node1, Country node2) {
    adjacencyMap.getOrDefault(node2, new ArrayList<>()).remove(node2);
    adjacencyMap.getOrDefault(node2, new ArrayList<>()).remove(node1);
  }

  public List<String> getAdjacentNodes(Country node) {
    List<String> adjacentNodes = new ArrayList<>();

    for (Country adjacentNode : adjacencyMap.get(node)) {
      adjacentNodes.add(node.getName());
    }

    return adjacentNodes;
  }
}
