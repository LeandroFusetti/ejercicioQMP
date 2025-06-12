package ar.edu.utn.frba.dds.prenda;

public class SolicitudQuitarDeGuardarropa extends SolicitudModificacionGuardarropa{
  public SolicitudQuitarDeGuardarropa(Prenda prenda) {
    super(prenda);
  }
  public void realizarAccionEn(Guardarropa guardarropa) {
    guardarropa.quitarPrenda(this.prenda);
  }
  public void deshacerAccionEn(Guardarropa guardarropa) {
    guardarropa.agregarPrenda(this.prenda);
  }
}
