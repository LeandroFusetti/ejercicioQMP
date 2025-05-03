package ar.edu.utn.frba.dds.prenda;

public class Prenda {
  private TipoPrenda tipo;
  private Trama trama;
  private Material material;
  private Color colorPrimario;
  private Color colorSecundario;

  public Prenda(TipoPrenda tipo, Material material, Color colorPrimario, Color colorSecundario, Trama trama) {
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
    this.trama = trama;
    this.colorPrimario = colorPrimario;
    this.material = material;
    this.colorSecundario = colorSecundario;
  }


  public Color getColorSecundario() {
    if (this.colorSecundario == null) {
      throw new ColorSecundarioException("color secundario no esta definido");
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



