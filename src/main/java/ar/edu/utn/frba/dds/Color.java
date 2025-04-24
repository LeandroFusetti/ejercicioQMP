package ar.edu.utn.frba.dds;

public class Color {
  int rojo, azul, verde;

  public Color(int rojo, int verde, int azul) {
    // Validar que los valores estén entre 0 y 255
    this.rojo = validarComponente(rojo);
    this.verde = validarComponente(verde);
    this.azul = validarComponente(azul);
  }


  private int validarComponente(int componente) {
    return Math.min(255, Math.max(0, componente));
  }

  public int getRojo() {
    return rojo;
  }

  public int getVerde() {
    return verde;
  }

  public int getAzul() {
    return azul;
  }
}

