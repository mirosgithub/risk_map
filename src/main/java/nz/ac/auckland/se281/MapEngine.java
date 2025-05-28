package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
      // parse line into list
      String[] parts = countryData.split(",");
      String name = parts[0];
      String continent = parts[1];
      String fuelCost = parts[2];

      // create new country and add to country list
      Country country = new Country(name, continent, fuelCost);
      this.countries.add(country);
      this.graph.addNode(country);
    }

    // e.g. Country,Neighbour1,Neighbour2,...
    List<String> adjacencies = Utils.readAdjacencies();

    for (String adjacencyData : adjacencies) {
      // parse line into list
      String[] parts = adjacencyData.split(",");
      Country country;

      // get the corresponding country from the list
      try {
        country = getCountry(parts[0]);
      } catch (InvalidCountryNameException e) {
        // print error message if the csv file contains invalid country name
        System.out.println("Invalid country name in the csv file: " + parts[0]);
        continue;
      }

      for (int i = 1; i < parts.length; i++) {
        Country neighbour;
        
        // get the adjacent country from the list
        try {
          neighbour = getCountry(parts[i]);
        } catch (InvalidCountryNameException e) {
          // print error message if the csv file contains invalid country name
          System.out.println("Invalid country name in the csv file: " + parts[i]);
          continue;
        }
        
        this.graph.addEdge(country, neighbour);
      }
    }
  }

  private Country getCountry(String string) throws InvalidCountryNameException {
    // check each country in the list to see if it matches the country name
    for (Country country : this.countries) {
      if (country.getName().equals(string)) {
        return country;
      }
    }

    // throw exception if no country is found
    throw new InvalidCountryNameException(string);
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // get country name from the user
    MessageCli.INSERT_COUNTRY.printMessage();
    Country country = readCountryInput();

    // gather country information
    String countryName = country.getName();
    String continent = country.getContinent().getName();
    String fuelCost = Integer.toString(country.getFuelCost());
    String neighbours = convertListToString(graph.getAdjacentNodes(country));

    // display information
    MessageCli.COUNTRY_INFO.printMessage(countryName, continent, fuelCost, neighbours);
  }

  private Country readCountryInput() {
    Country country = null;

    while (country == null) {
      // read input
      String input = Utils.scanner.nextLine();
      input = Utils.capitalizeFirstLetterOfEachWord(input);

      // check input for validity
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

    // append each country's name from the list
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
    // get source and destination from the user
    MessageCli.INSERT_SOURCE.printMessage();
    Country source = readCountryInput();
    MessageCli.INSERT_DESTINATION.printMessage();
    Country destination = readCountryInput();

    // handle same country case
    if (source.equals(destination)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }

    List<Country> fastestRoute = graph.getShortestPath(source, destination);

    // display fastest route
    String fastestRouteString = convertListToString(fastestRoute);
    MessageCli.ROUTE_INFO.printMessage(fastestRouteString);

    // display total fuel
    int totalFuel = calculateTotalFuel(fastestRoute);
    MessageCli.FUEL_INFO.printMessage(Integer.toString(totalFuel));

    // display continents visited
    String continentsVisited = getContinentsVisited(fastestRoute).get(0);
    MessageCli.CONTINENT_INFO.printMessage(continentsVisited);

    // display continent with highest fuel
    String highestFuelContinent = getContinentsVisited(fastestRoute).get(1);
    MessageCli.FUEL_CONTINENT_INFO.printMessage(highestFuelContinent); 

  }

  private int calculateTotalFuel(List<Country> route) {
    int totalFuel = 0;

    // sum up fuel cost from intermediate countries
    for (int i = 1; i < route.size() - 1; i++) {
      totalFuel += route.get(i).getFuelCost();
    }

    return totalFuel;
  }

  private List<String> getContinentsVisited(List<Country> route) {
    Map<Continent, Integer> visited = new LinkedHashMap<>();
    List<String> result = new ArrayList<>();
    int highestFuel = -1;
    String highestFuelContinent = null;

    // calculate and store fuel consumption in each continent
    for (int i = 0; i < route.size(); i++) {
      Country country = route.get(i);
      Continent continent = country.getContinent();
      int fuelCost;

      // exclude source and destination for fuel calculation
      if (i == 0 || i == route.size() - 1) {
        fuelCost = 0;
      } else {
        fuelCost = country.getFuelCost();
      }

      // add current country's fuel cost to the corresponding continent
      visited.put(continent, visited.getOrDefault(continent, 0) + fuelCost);

      // update highest fuel across continents
      if (visited.get(continent) > highestFuel) {
        highestFuel = visited.get(continent);
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append("[");

    Set<Continent> visitedContinents = visited.keySet();
    int i = 0;

    // form string representation of visited continents and fuel
    for (Continent c : visitedContinents) {
      String continentFuel = c.getName() + " (" + visited.get(c) + ")";

      if (i != 0) {
        sb.append(", ");
      }
      sb.append(continentFuel);
      i++;

      // store the first continent with highest fuel consumption
      if (visited.get(c) == highestFuel && highestFuelContinent == null) {
        highestFuelContinent = continentFuel;
      }
    }

    sb.append("]");
    result.add(sb.toString());
    result.add(highestFuelContinent);

    return result;
  }
}
