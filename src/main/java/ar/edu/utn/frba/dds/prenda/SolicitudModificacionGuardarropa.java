package ar.edu.utn.frba.dds.prenda;

public abstract class SolicitudModificacionGuardarropa {


  protected Prenda prenda;//permite el acceso a las sub clases
  private Estado estado;
  public SolicitudModificacionGuardarropa(Prenda prenda) {
    this.estado = Estado.PENDIENTE;
    this.prenda = prenda;
  }

  public Prenda getPrenda() {
    return prenda;
  }

  public Estado getEstado() {
    return estado;
  }
  //template method
  public void aceptarSolicitudEn(Guardarropa guardarropa) {
    this.estado = Estado.ACEPTADO;
    this.realizarAccionEn(guardarropa);
  }
  public void rechazarSolicitud() {
    this.estado = Estado.RECHAZADO;
  }
  public void deshacerModificacion(Guardarropa guardarropa) {
    this.estado = Estado.PENDIENTE;
    this.deshacerAccionEn(guardarropa);
  }
  public abstract void deshacerAccionEn(Guardarropa guardarropa);

  public abstract void realizarAccionEn(Guardarropa guardarropa);
}
