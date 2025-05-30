package ar.edu.utn.frba.dds.apiGratuita;

import java.io.IOException;

public class EjemploDeUso {
  public static void main(String[] args) throws IOException {
    OpenWeatherApi api = OpenWeatherApi.getInstance();
    TemperatureResponse response = api.getTemperature(40.4167047, -3.7035825, "metric");
    System.out.println("Temperatura: " + response.getTemp() + "°C");
  }
}
