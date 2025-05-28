package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * A generic graph implementation that supports adding/removing nodes and edges, and finding the
 * shortest path between nodes.
 *
 * @param <T> the type of elements stored in the graph nodes
 */
public class Graph<T> {

  private Map<T, List<T>> adjacencyMap;

  /** Constructs an empty graph. */
  public Graph() {
    this.adjacencyMap = new HashMap<>();
  }

  /**
   * Adds a node to the graph if it doesn't already exist.
   *
   * @param node the node to be added to the graph
   */
  public void addNode(T node) {
    adjacencyMap.putIfAbsent(node, new ArrayList<>());
  }

  /**
   * Removes a node and all its associated edges from the graph.
   *
   * @param node the node to be removed from the graph
   */
  public void removeNode(T node) {
    adjacencyMap.remove(node);
    for (T key : adjacencyMap.keySet()) {
      adjacencyMap.get(key).remove(node);
    }
  }

  /**
   * Adds an edge between two nodes in the graph. If either node doesn't exist, it will be created.
   *
   * @param node1 the first node of the edge
   * @param node2 the second node of the edge
   */
  public void addEdge(T node1, T node2) {
    addNode(node1);
    addNode(node2);
    adjacencyMap.get(node1).add(node2);
  }

  /**
   * Removes an edge between two nodes in the graph.
   *
   * @param node1 the first node of the edge
   * @param node2 the second node of the edge
   */
  public void removeEdge(T node1, T node2) {
    adjacencyMap.getOrDefault(node2, new ArrayList<>()).remove(node2);
    adjacencyMap.getOrDefault(node2, new ArrayList<>()).remove(node1);
  }

  /**
   * Returns a list of nodes adjacent to the specified node.
   *
   * @param node the node whose adjacent nodes are to be returned
   * @return a list of adjacent nodes, or null if the node doesn't exist
   */
  public List<T> getAdjacentNodes(T node) {
    return adjacencyMap.get(node);
  }

  /**
   * Finds the shortest path between two nodes using breadth-first search.
   *
   * @param root the starting node
   * @param target the target node
   * @return a list of nodes representing the shortest path from root to target, or null if no path
   *     exists
   */
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
