package nz.ac.auckland.se281;

public class Country {

  private String name;
  private Continent continent;
  private int fuelCost;

  public Country(String name, String continent, String fuelCost) {
    this.name = name;
    this.continent = Continent.fromString(continent);
    this.fuelCost = Integer.valueOf(fuelCost);
  }

  public String getName() {
    return this.name;
  }

  public Continent getContinent() {
    return this.continent;
  }

  public int getFuelCost() {
    return this.fuelCost;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

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
