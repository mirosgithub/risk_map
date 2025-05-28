package nz.ac.auckland.se281;

import java.util.*;

/** This class is the main entry point. */
public class MapEngine {

  private Graph<Country> graph = new Graph<Country>();
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

      Country country;

      try {
        country = getCountry(parts[0]);
      } catch (InvalidCountryNameException e) {
        System.out.println("Invalid country name in the csv file: " + parts[0]);
        continue;
      }

      for (int i = 1; i < parts.length; i++) {
        Country neighbour;
        try {
          neighbour = getCountry(parts[i]);
        } catch (InvalidCountryNameException e) {
          System.out.println("Invalid country name in the csv file: " + parts[i]);
          continue;
        }
        this.graph.addEdge(country, neighbour);
      }
    }
  }

  private Country getCountry(String string) throws InvalidCountryNameException {
    for (Country country : this.countries) {
      if (country.getName().equals(string)) {
        return country;
      }
    }
    throw new InvalidCountryNameException(string);
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    MessageCli.INSERT_COUNTRY.printMessage();
    Country country = readCountryInput();

    String countryName = country.getName();
    String continent = country.getContinent().getName();
    String fuelCost = Integer.toString(country.getFuelCost());
    String neighbours = convertListToString(graph.getAdjacentNodes(country));

    MessageCli.COUNTRY_INFO.printMessage(countryName, continent, fuelCost, neighbours);
  }

  private Country readCountryInput() {
    Country country = null;

    while (country == null) {
      String input = Utils.scanner.nextLine();
      input = Utils.capitalizeFirstLetterOfEachWord(input);
      try {
        country = getCountry(input);
      } catch (InvalidCountryNameException e) {
        MessageCli.INVALID_COUNTRY.printMessage(input);
      }
    }

    return country;
  }

  private String convertListToString(List<Country> countries) {
    StringBuilder sb = new StringBuilder();

    sb.append("[");

    for (int i = 0; i < countries.size(); i++) {
      if (i != 0) {
        sb.append(", ");
      }
      sb.append(countries.get(i).getName());
    }

    sb.append("]");

    return sb.toString();
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    MessageCli.INSERT_SOURCE.printMessage();
    Country source = readCountryInput();

    MessageCli.INSERT_DESTINATION.printMessage();
    Country destination = readCountryInput();

    if (source.equals(destination)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }

    List<Country> fastestRoute = graph.getShortestPath(source, destination);

    // fastest route
    String fastestRouteString = convertListToString(fastestRoute);
    MessageCli.ROUTE_INFO.printMessage(fastestRouteString);

    // total fuel
    int totalFuel = calculateTotalFuel(fastestRoute);
    MessageCli.FUEL_INFO.printMessage(Integer.toString(totalFuel));

    // continents visited
    String continentsVisited = getContinentsVisited(fastestRoute);
    MessageCli.CONTINENT_INFO.printMessage(continentsVisited);

    // continent w/ highest fuel
  }

  private int calculateTotalFuel(List<Country> route) {
    int totalFuel = 0;

    for (int i = 1; i < route.size() - 1; i++) {
      totalFuel += route.get(i).getFuelCost();
    }

    return totalFuel;
  }

  private String getContinentsVisited(List<Country> route) {
    Map<Continent, Integer> visited = new LinkedHashMap<>();

    for (int i = 0; i < route.size(); i++) {
      Country country = route.get(i);
      Continent continent = country.getContinent();
      int fuelCost;

      if (i == 0 || i == route.size() - 1) {
        fuelCost = 0;
      } else {
        fuelCost = country.getFuelCost();
      }

      visited.put(continent, visited.getOrDefault(continent, 0) + fuelCost);
    }

    StringBuilder sb = new StringBuilder();
    sb.append("[");

    Set<Continent> visitedContinents = visited.keySet();
    int i = 0;

    for (Continent c : visitedContinents) {
      if (i != 0) {
        sb.append(", ");
      }
      sb.append(c.getName() + " (" + visited.get(c) + ")");
      i++;
    }

    sb.append("]");

    return sb.toString();
  }
}
