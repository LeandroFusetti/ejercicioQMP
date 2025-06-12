package ar.edu.utn.frba.dds.prenda;

public class SolicitudAgregarAGuardarropa extends SolicitudModificacionGuardarropa{
  public SolicitudAgregarAGuardarropa(Prenda prenda) {
    super(prenda);
  }
  public void realizarAccionEn(Guardarropa guardarropa) {
    guardarropa.agregarPrenda(this.prenda);
  }
  public void deshacerAccionEn(Guardarropa guardarropa) {
    guardarropa.quitarPrenda(this.prenda);
  }
}
