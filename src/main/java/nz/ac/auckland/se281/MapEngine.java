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

    List<String> adjacencies = Utils.readAdjacencies();
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {}

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
