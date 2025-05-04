package ar.edu.utn.frba.dds.sastre;

import ar.edu.utn.frba.dds.prenda.Categoria;
import ar.edu.utn.frba.dds.prenda.Prenda;


public class Atuendo  {
  private Prenda prendaSuperior;
  private Prenda prendaInferior;
  private Prenda prendaCalzado;

  public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda prendaCalzado) {
    validarAtuendoDePrendaQueCorrespondaCategoria(prendaSuperior,Categoria.PARTE_SUPERIOR);
    validarAtuendoDePrendaQueCorrespondaCategoria(prendaInferior,Categoria.PARTE_INFERIOR);
    validarAtuendoDePrendaQueCorrespondaCategoria(prendaCalzado,Categoria.CALZADO);

    this.prendaSuperior = prendaSuperior;
    this.prendaInferior = prendaInferior;
    this.prendaCalzado = prendaCalzado;
  }

  public void validarAtuendoDePrendaQueCorrespondaCategoria(Prenda prenda, Categoria categoria) {
    if(prenda.getCategoria()!= categoria){
      throw new CategoriaInvalidaException ("La categoria de prenda debe ser: " + categoria.toString());
    }
    if(prenda==null){
      throw new CategoriaInvalidaException ("La categoria no puede ser invalida");
    }
  }
  public class CategoriaInvalidaException extends RuntimeException {

    public CategoriaInvalidaException(String mensaje) {
      super( mensaje);
    }
  }

  public Prenda getPrendaSuperior() {
    return prendaSuperior;
  }

  public Prenda getPrendaInferior() {
    return prendaInferior;
  }

  public Prenda getPrendaCalzado() {
    return prendaCalzado;
  }
}
