package ar.edu.utn.frba.dds.sistemaMeteorologico;

import java.io.IOException;

public interface ServicioMeteorologico {
  public Double getTemperaturaActual() throws IOException;
}
