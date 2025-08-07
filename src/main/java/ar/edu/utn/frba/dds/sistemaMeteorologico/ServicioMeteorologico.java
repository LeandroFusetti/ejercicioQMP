package ar.edu.utn.frba.dds.sistemaMeteorologico;

import java.io.IOException;
import java.util.List;

//interface de como lo quiero usar
public interface ServicioMeteorologico {
  public Double getTemperaturaActual() ;
  public List<AlertaMeteorologica> getAlertasMeteorologicas();
}
