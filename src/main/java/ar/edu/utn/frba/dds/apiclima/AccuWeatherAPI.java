package ar.edu.utn.frba.dds.apiclima;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccuWeatherAPI {

  public List<Map<String, Object>> getWeather(String ciudad) {
    return Arrays.asList(new HashMap<String, Object>() {{
      put("DateTime", "2019-05-03T01:00:00-03:00");
      put("EpochDateTime", 1556856000);
      put("WeatherIcon", 33);
      put("IconPhrase", "Clear");
      put("IsDaylight", false);
      put("PrecipitationProbability", 0);
      put("MobileLink", "http://m.accuweather.com/en/ar/villa-vil/7984/");
      put("Link", "http://www.accuweather.com/en/ar/villa-vil/7984");
      put("Temperature", new HashMap<String, Object>() {{
        put("Value", 27);
        put("Unit", "C");
        put("UnitType", 18);
      }});
    }});
  }

  public Map<String, Object> getAlerts(String ciudad) {
    return new HashMap<String, Object>() {{
      put("CurrentAlerts", Arrays.asList("Tormenta", "Granizo"));
    }};
  }


}
