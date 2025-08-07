package ar.edu.utn.frba.dds.motorBusqueda;

import ar.edu.utn.frba.dds.prenda.Material;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.sistemaMeteorologico.AlertaMeteorologica;
import ar.edu.utn.frba.dds.sistemaMeteorologico.ServicioMeteorologico;
import ar.edu.utn.frba.dds.usuario.Usuario;

import java.util.List;

public class MotorAlertaMeteorologica extends MotorSugerencia {
  public ServicioMeteorologico servicioMeteorologico;

  public MotorAlertaMeteorologica(ServicioMeteorologico servicioMeteorologico) {
    this.servicioMeteorologico = servicioMeteorologico;
  }

  public List<AlertaMeteorologica> getAlertasMeteorologicas() {
    return this.servicioMeteorologico.getAlertasMeteorologicas();
  }

  public boolean hayAlertaMeteorologica() {
    List<AlertaMeteorologica> alertas = this.getAlertasMeteorologicas();
    return alertas.contains(AlertaMeteorologica.GRANIZO)
        || alertas.contains(AlertaMeteorologica.TORMENTA);
  }

  @Override
  List<Prenda> filtrarSegunCriterio(Usuario usuario) {
    return this.hayAlertaMeteorologica() ? usuario.getPrendas().stream()
        .filter(p -> p.getMaterial() == Material.IMPERMEABLE).toList()
        : usuario.getPrendas().stream().filter(p -> p.getMaterial() != Material.IMPERMEABLE).toList();
  }
}
