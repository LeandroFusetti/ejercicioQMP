package ar.edu.utn.frba.dds.sistemaMeteorologico;

public enum AlertaMeteorologica {
  TORMENTA("¡Salí con paraguas!"),
  GRANIZO("¡Cuidado con el granizo! Protege tu auto y cabeza");

  private final String mensaje;

  AlertaMeteorologica(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }
}
