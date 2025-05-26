package nz.ac.auckland.se281;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** This class is the main entry point. */
public class MapEngine {

  private Graph graph = new Graph();
  private Set<Country> countries = new HashSet<>();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this method invocation
  }

  /** invoked one time only when constructing the MapEngine class. */
  private void loadMap() {

    // e.g. Country,Continent,n
    List<String> countries = Utils.readCountries();

    for (String countryData : countries) {
      String[] parts = countryData.split(",");

      String name = parts[0];
      String continent = parts[1];
      String fuelCost = parts[2];

      Country country = new Country(name, continent, fuelCost);

      this.countries.add(country);
      this.graph.addNode(country);
    }

    // e.g. Country,Neighbour1,Neighbour2,...
    List<String> adjacencies = Utils.readAdjacencies();

    for (String adjacencyData : adjacencies) {
      String[] parts = adjacencyData.split(",");

      Country country = getCountry(parts[0]);
      
      for (int i = 1; i < parts.length; i++) {
        Country neighbour = getCountry(parts[i]);
        this.graph.addEdge(country, neighbour);
      }
    }
  }

  private Country getCountry(String string) {
  for (Country country : this.countries) {
      if (country.getName().equalsIgnoreCase(string)) {
        return country;
      }
    }
    return null; // or throw an exception?
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {}

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
