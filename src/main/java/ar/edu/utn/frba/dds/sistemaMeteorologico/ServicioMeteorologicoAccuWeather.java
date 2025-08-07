package ar.edu.utn.frba.dds.sistemaMeteorologico;

import ar.edu.utn.frba.dds.apiclima.AccuWeatherAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//esto es el adapter
public class ServicioMeteorologicoAccuWeather implements ServicioMeteorologico {
  public AccuWeatherAPI accuWeatherAPI;

  public ServicioMeteorologicoAccuWeather(AccuWeatherAPI accuWeatherAPI) {
    this.accuWeatherAPI = accuWeatherAPI;

  }

public List<AlertaMeteorologica> getAlertasMeteorologicas() {
  Map<String, Object> alertas= this.accuWeatherAPI.getAlerts("Buenos Aires");
  List<String> alertasString = (List<String>) alertas.get("CurrentAlerts");
  return alertasString.stream().map(s -> AlertaMeteorologica.valueOf(s.toUpperCase())).toList();
}



  public Double getTemperaturaActual() {
    List<Map<String, Object>> condicionesClimaticas = this.accuWeatherAPI.getWeather("Buenos Aires, Argentina");
    Map<String, Object> temperatureMap = (Map<String, Object>) condicionesClimaticas.get(0).get("Temperature");
    Number tempValue = (Number) temperatureMap.get("Value");
    //System.out.println(tempValue.doubleValue());
    return tempValue.doubleValue();
  }

}
