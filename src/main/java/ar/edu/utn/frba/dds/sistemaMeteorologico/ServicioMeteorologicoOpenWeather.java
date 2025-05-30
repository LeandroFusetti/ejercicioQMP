package ar.edu.utn.frba.dds.sistemaMeteorologico;

import ar.edu.utn.frba.dds.apiGratuita.OpenWeatherApi;
import ar.edu.utn.frba.dds.apiGratuita.TemperatureResponse;
import ar.edu.utn.frba.dds.apiclima.AccuWeatherAPI;

import java.io.IOException;

public class ServicioMeteorologicoOpenWeather implements ServicioMeteorologico {
  public OpenWeatherApi openWeatherApi;
  public ServicioMeteorologicoOpenWeather() {
    this.openWeatherApi = OpenWeatherApi.getInstance();
  }


  @Override
  public Double getTemperaturaActual() throws IOException {
    TemperatureResponse response = this.openWeatherApi.getTemperature(40.4167047, -3.7035825, "metric");
    System.out.println("Temperatura: " + response.getTemp() + "°C");
    return response.getTemp();
  }
}
