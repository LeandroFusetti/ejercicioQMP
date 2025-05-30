package ar.edu.utn.frba.dds.sistemaMeteorologico;

import ar.edu.utn.frba.dds.apiclima.AccuWeatherAPI;

import java.util.List;
import java.util.Map;

public class ServicioMeteorologicoAccuWeather implements ServicioMeteorologico {
  public AccuWeatherAPI accuWeatherAPI;
  public ServicioMeteorologicoAccuWeather(AccuWeatherAPI accuWeatherAPI) {
    this.accuWeatherAPI = accuWeatherAPI;
  }


  public Double getTemperaturaActual() {
    List<Map<String, Object>> condicionesClimaticas = this.accuWeatherAPI.getWeather("Buenos Aires, Argentina");
    Map<String, Object> temperatureMap = (Map<String, Object>) condicionesClimaticas.get(0).get("Temperature");
    Number tempValue = (Number) temperatureMap.get("Value");
    //System.out.println(tempValue.doubleValue());
    return tempValue.doubleValue();
  }

}
