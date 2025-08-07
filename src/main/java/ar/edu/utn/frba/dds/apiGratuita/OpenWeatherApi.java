package ar.edu.utn.frba.dds.apiGratuita;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;

import java.io.IOException;

public class OpenWeatherApi {
  private static final String BASE_URL = "https://api.openweathermap.org/";
  private static final String API_KEY = "ACA VA LA API_KEY";
  private static OpenWeatherApi INSTANCE = new OpenWeatherApi();
  private Retrofit retrofit;
  private OpenWeatherService service;

  private OpenWeatherApi() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    this.service = retrofit.create(OpenWeatherService.class);
  }

  public static OpenWeatherApi getInstance() {
    return INSTANCE;
  }

  public TemperatureResponse getTemperature(double lat, double lon, String units) {
    try {
      Call<TemperatureResponse> call = service.getTemperature(lat, lon, API_KEY, units);
      Response<TemperatureResponse> response = call.execute();
      if (!response.isSuccessful()) {
        String errorBody = response.errorBody() != null ?
            response.errorBody().string() : "Sin detalles";
        throw new RuntimeException("Error en API: " + response.code() + " - " + errorBody);
      }
      return response.body();
    } catch (IOException e) {
      throw new RuntimeException("Fallo al llamar a la API del clima: " + e.getMessage(), e);
    }

  }
}