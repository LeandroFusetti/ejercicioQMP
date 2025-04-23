package ar.edu.utn.frba.dds;

import java.util.Objects;

public class Prenda {
  private TipoPrenda tipo;

  private Material material;
  private Color colorPrimario;
  private Color colorSecundario;

  public Prenda(TipoPrenda tipo,Material material, Color colorPrimario, Color colorSecundario) {
    this.tipo = Objects.requireNonNull(tipo,"tipo de prenda es obligatorio");

    this.colorPrimario = Objects.requireNonNull(colorPrimario,"tipo de prenda es obligatorio");
    this.material = Objects.requireNonNull(material,"material es obligatorio");;
    this.colorSecundario = colorSecundario;
  }


  public Color getColorSecundario() {
    if(this.colorSecundario ==null){
      throw new IllegalStateException("color secundario no esta definido");
    }
    return colorSecundario;
  }

  public class ColorSecundarioException extends RuntimeException{
    public ColorSecundarioException(String causa){
      super("No se definio un color secundario porque: " + causa );
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


enum Material{
  CUERO,CUERINA,ALGODON,PLASTICO
}
