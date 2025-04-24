package ar.edu.utn.frba.dds;

public class PrendaInvalidaException extends RuntimeException {

  public PrendaInvalidaException(String mensaje) {
    super("La prenda es invalida: " + mensaje);
  }



}
