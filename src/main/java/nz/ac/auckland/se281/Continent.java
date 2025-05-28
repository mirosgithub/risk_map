package nz.ac.auckland.se281;

public enum Continent {
  ASIA("Asia"),
  NORTH_AMERICA("North America"),
  SOUTH_AMERICA("South America"),
  AFRICA("Africa"),
  AUSTRALIA("Australia"),
  EUROPE("Europe");

  public static Continent fromString(String text) {
    for (Continent continent : values()) {
      if (continent.name.equalsIgnoreCase(text)) {
        return continent;
      }
    }
    return null;
  }

  private final String name;

  Continent(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
