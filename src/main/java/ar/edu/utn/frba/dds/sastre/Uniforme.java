package ar.edu.utn.frba.dds.sastre;

import ar.edu.utn.frba.dds.prenda.Prenda;

public class Uniforme {
  private Prenda prendaSuperior;
  private Prenda prendaInferior;
  private Prenda prendaCalzado;


  public Uniforme(Prenda prendaSuperior,Prenda prendaInferior, Prenda prendaCalzado) {
    if (prendaSuperior == null || prendaCalzado == null || prendaInferior == null) {
      throw new UniformeException("No puede haber campos en nulos");
    }
    this.prendaSuperior = prendaSuperior;
    this.prendaCalzado = prendaCalzado;
    this.prendaInferior = prendaInferior;
  }

  public Prenda getPrendaSuperior() {
    return prendaSuperior;
  }

  public Prenda getPrendaCalzado() {
    return prendaCalzado;
  }

  public Prenda getPrendaInferior() {
    return prendaInferior;
  }

  public class UniformeException extends RuntimeException {
    public UniformeException(String mensaje) {
      super(mensaje);
    }
  }


}
