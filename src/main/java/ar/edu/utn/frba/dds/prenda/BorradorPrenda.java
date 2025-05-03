package ar.edu.utn.frba.dds.prenda;



public class BorradorPrenda {
  private TipoPrenda tipo;
  private Material material;
  private Color colorPrimario;
  private Color colorSecundario;
  private Trama trama;

  public BorradorPrenda(TipoPrenda tipo) {
    if (tipo == null) {
      throw new BorradorException("Tipo de prenda no puede ser nulo");
    }
    this.tipo = tipo;
  }

  public BorradorPrenda especificarColorPrimario(Color colorPrimario) {
    if (colorPrimario == null) {
      throw new BorradorException("Color de primario no puede ser nulo");
    }
    this.colorPrimario = colorPrimario;
    return this;
  }

  public BorradorPrenda especificarColorSecundario(Color colorSecundario) {
    if (colorSecundario == null) {
      throw new BorradorException("Color de secundario no puede ser nulo");
    }
    this.colorSecundario = colorSecundario;
    return this;
  }


  public BorradorPrenda especificarTipo(TipoPrenda tipo) {
    if (tipo == null) {
      throw new BorradorException("Tipo de prenda no puede ser nulo");
    }
    this.tipo = tipo;
    return this;
  }

  public BorradorPrenda especificarTrama(Trama trama) {
    if(trama == null) {
      throw new BorradorException("Trama no puede ser nulo");
    }
    this.trama = trama;
    return this;
  }

  public BorradorPrenda especificarMaterial(Material material) {
    if(material == null) {
      throw new BorradorException("Material no puede ser nulo");
    }
    if(esCompatible(material)){
    this.material = material;
    return this;

    }else throw new BorradorException("El material tiene que ser compatible");
  }


  private void telaLisaPorDefecto() {
    if (trama == null && (this.tipo.getCategoria() == Categoria.PARTE_SUPERIOR || this.tipo.getCategoria() == Categoria.PARTE_INFERIOR)) {
      this.trama = Trama.LISA;
    }
  }

  private boolean esCompatible(Material material) {
    return this.tipo.getMaterialesCompatibles().contains(material);
  }

  public Prenda crearPrenda() {
    this.telaLisaPorDefecto();

    return new Prenda(this.tipo, this.material, this.colorPrimario, this.colorSecundario, this.trama);
  }
}
