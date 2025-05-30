package ar.edu.utn.frba.dds.motorBusqueda;

import ar.edu.utn.frba.dds.prenda.Formalidad;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.sistemaMeteorologico.ServicioMeteorologico;
import ar.edu.utn.frba.dds.usuario.Usuario;

import java.io.IOException;
import java.util.List;

public class MotorAtuendoATemperaturaActual extends MotorSugerencia {
  public ServicioMeteorologico servicioMeteorologico;

  public MotorAtuendoATemperaturaActual(ServicioMeteorologico servicioMeteorologico) {
    super();
    this.servicioMeteorologico = servicioMeteorologico;

  }

  public Double getTemperaturaActual() throws IOException {
    return this.servicioMeteorologico.getTemperaturaActual();
  }

  private boolean TempDentroDeIntervalo(Double tempMin, Double tempMax, Double tempActual) {
    System.out.println("tempMin: " + tempMin + ", tempMax: " + tempMax);
    return tempActual >= tempMin&& tempActual <= tempMax;

  }

  @Override
  public List<Prenda> filtrarSegunCriterio(Usuario usuario) throws IOException {
  Double temperaturaActual= getTemperaturaActual();
    return usuario.getPrendas().stream()
        .filter(p -> {
          return this.TempDentroDeIntervalo(
              p.getTipo().getEsAptaDesdeLaTempDe(),
              p.getTipo().getEsAptaHastaLaTempDe(),temperaturaActual);
        })
        .toList();
  }
}
