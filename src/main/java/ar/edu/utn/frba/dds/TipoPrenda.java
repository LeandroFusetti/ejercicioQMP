package ar.edu.utn.frba.dds;

public enum TipoPrenda {
  CAMISA_MANGA_LARGA(Categoria.PARTE_SUPERIOR),
  CAMISA_MANGA_CORTA(Categoria.PARTE_SUPERIOR),
  JEAN(Categoria.PARTE_INFERIOR),
  JOGGING(Categoria.PARTE_INFERIOR),
  ZAPATOS(Categoria.CALZADO),
  ZAPATILLAS(Categoria.CALZADO),
  GORRA(Categoria.ACCESORIOS),
  ANTEOJOS_DE_SOL(Categoria.ACCESORIOS);


  //la palabra clave final se usa para indicar que un elemento no puede ser modificado después de su inicialización.

  private final Categoria categoria;

  public Categoria getCategoria() {
    return this.categoria;
  }


  //el constructor es privado
  TipoPrenda(Categoria categoria) {
    this.categoria = categoria;


  }


}


enum Categoria {
  PARTE_SUPERIOR,
  CALZADO,
  PARTE_INFERIOR,
  ACCESORIOS
}
