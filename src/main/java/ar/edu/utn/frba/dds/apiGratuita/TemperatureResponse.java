package ar.edu.utn.frba.dds.apiGratuita;

public class TemperatureResponse {
  private Main main;

  public static class Main {
    private double temp;

    // Getter
    public double getTemp() {
      return temp;
    }
  }

  // Getter
  public double getTemp() {
    return main.getTemp();
  }
}
