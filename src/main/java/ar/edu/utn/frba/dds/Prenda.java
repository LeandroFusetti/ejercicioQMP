package ar.edu.utn.frba.dds;

import java.util.Objects;

public class Prenda {
  private TipoPrenda tipo;

  private Material material;
  private Color colorPrimario;
  private Color colorSecundario;

  public Prenda(TipoPrenda tipo, Material material, Color colorPrimario, Color colorSecundario) {
    if (tipo == null) {
      throw new PrendaInvalidaException("Tipo de prenda no puede ser nulo");
    }
    if (material == null) {
      throw new PrendaInvalidaException("Material no puede ser nulo");

    }
    if (colorPrimario == null) {
      throw new PrendaInvalidaException("Color de prenda no puede ser nulo");
    }

    this.tipo = tipo;

    this.colorPrimario = colorPrimario;
    this.material = material;
    this.colorSecundario = colorSecundario;
  }


  public Color getColorSecundario() {
    if (this.colorSecundario == null) {
      throw new IllegalStateException("color secundario no esta definido");
    }
    return colorSecundario;
  }

  public class ColorSecundarioException extends RuntimeException {
    public ColorSecundarioException(String causa) {
      super("No se definio un color secundario porque: " + causa);
    }
  }

  public Color getColorPrimario() {

    return colorPrimario;
  }


  public TipoPrenda getTipo() {
    return this.tipo;
  }

  public Categoria getCategoria() {
    return this.tipo.getCategoria();
  }

  public Material getMaterial() {
    return this.material;
  }
}


enum Material {
  CUERO, CUERINA, ALGODON, PLASTICO
}
