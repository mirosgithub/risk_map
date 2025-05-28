package nz.ac.auckland.se281;

/** Represents a continent in the world with its name. */
public enum Continent {
  ASIA("Asia"),
  NORTH_AMERICA("North America"),
  SOUTH_AMERICA("South America"),
  AFRICA("Africa"),
  AUSTRALIA("Australia"),
  EUROPE("Europe");

  /**
   * Converts a string representation of a continent to its corresponding Continent enum value. The
   * comparison is case-insensitive.
   *
   * @param text the string representation of the continent
   * @return the corresponding Continent enum value, or null if no match is found
   */
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

  /**
   * Returns the name of the continent.
   *
   * @return the name of the continent as a String
   */
  public String getName() {
    return this.name;
  }
}
