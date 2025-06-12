package ar.edu.utn.frba.dds.prenda;

import java.util.ArrayList;
import java.util.List;

public class Guardarropa {
  private List<Prenda> prendas;
  private List<SolicitudModificacionGuardarropa> solicitudesModificacionGuardarropa;

  public Guardarropa(List<Prenda> prendas) {
    this.prendas = prendas;
    this.solicitudesModificacionGuardarropa = new ArrayList<>();
  }

  public List<Prenda> getPrendas() {
    return prendas;
  }

  public SolicitudAgregarAGuardarropa crearSolicitudAgregarAGuardarropa(Prenda prenda) {
    SolicitudAgregarAGuardarropa solicitud = new SolicitudAgregarAGuardarropa(prenda);
    this.agregarASolicitudes(solicitud);
    return solicitud;
  }

  public SolicitudQuitarDeGuardarropa crearSolicitudQuitarDeGuardarropa(Prenda prenda) {
    SolicitudQuitarDeGuardarropa solicitud = new SolicitudQuitarDeGuardarropa(prenda);
    this.agregarASolicitudes(solicitud);
    return solicitud;
  }

  public void agregarASolicitudes(SolicitudModificacionGuardarropa solicitudModificacionGuardarropa) {
    this.solicitudesModificacionGuardarropa.add(solicitudModificacionGuardarropa);
  }

  public void agregarPrenda(Prenda prenda) {
    this.prendas.add(prenda);
  }

  public void quitarPrenda(Prenda prenda) {
    this.prendas.remove(prenda);
  }

  public List<SolicitudModificacionGuardarropa> getSolicitudModificacionGuardarropa() {
    return solicitudesModificacionGuardarropa;
  }

  public List<SolicitudModificacionGuardarropa> filtrarSolicitudesPorEstado(Estado estado) {
    return this.solicitudesModificacionGuardarropa.stream().filter(
        s -> s.getEstado() == estado
    ).toList();
  }

}
