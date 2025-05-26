package nz.ac.auckland.se281;

public class InvalidCountryNameException extends Exception {
  public InvalidCountryNameException(String countryName) {
    super("Invalid country name: " + countryName);
  }
}
