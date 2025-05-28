package nz.ac.auckland.se281;

/** Represents a country with its name, continent, and fuel cost. */
public class Country {

  private String name;
  private Continent continent;
  private int fuelCost;

  /**
   * Constructs a new Country with the specified name, continent, and fuel cost.
   *
   * @param name the name of the country
   * @param continent the continent where the country is located
   * @param fuelCost the fuel cost as a string that will be converted to an integer
   */
  public Country(String name, String continent, String fuelCost) {
    this.name = name;
    this.continent = Continent.fromString(continent);
    this.fuelCost = Integer.valueOf(fuelCost);
  }

  /**
   * Returns the name of the country.
   *
   * @return the name of the country
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the continent where the country is located.
   *
   * @return the continent of the country
   */
  public Continent getContinent() {
    return this.continent;
  }

  /**
   * Returns the fuel cost of the country.
   *
   * @return the fuel cost as an integer
   */
  public int getFuelCost() {
    return this.fuelCost;
  }

  /**
   * Generates a hash code for the country based on its name.
   *
   * @return the hash code value for this country
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  /**
   * Compares this country with another object for equality. Two countries are considered equal if
   * they have the same name.
   *
   * @param obj the reference object with which to compare
   * @return true if this country is equal to the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    // compare Country names
    Country other = (Country) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }
}
