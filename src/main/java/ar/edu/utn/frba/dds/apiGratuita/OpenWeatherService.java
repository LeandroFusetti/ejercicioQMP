package ar.edu.utn.frba.dds.apiGratuita;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {
  @GET("data/2.5/weather")
  Call<TemperatureResponse> getTemperature(
      @Query("lat") double lat,
      @Query("lon") double lon,
      @Query("appid") String apiKey,
      @Query("units") String units
  );


}
