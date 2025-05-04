package ar.edu.utn.frba.dds.sastre;

import ar.edu.utn.frba.dds.prenda.Prenda;

public abstract class Sastre {


  public Atuendo fabricarUniforme() {
    return new Atuendo(this.fabricarParteSuperior(), this.fabricarParteInferior(), this.fabricarCalzado());

  }

  abstract Prenda fabricarParteSuperior();

  abstract Prenda fabricarParteInferior();

  abstract Prenda fabricarCalzado();
}

