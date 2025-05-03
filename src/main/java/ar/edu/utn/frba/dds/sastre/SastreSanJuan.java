package ar.edu.utn.frba.dds.sastre;

import ar.edu.utn.frba.dds.prenda.BorradorPrenda;
import ar.edu.utn.frba.dds.prenda.Color;
import ar.edu.utn.frba.dds.prenda.Material;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.prenda.TipoPrenda;

public class SastreSanJuan extends Sastre {
  @Override
  public Prenda fabricarParteSuperior() {
    Color verde = new Color(1, 2, 3);
    return new BorradorPrenda(TipoPrenda.CHOMBA)
        .especificarMaterial(Material.PIQUE)
        .especificarColorPrimario(verde)
        .crearPrenda();
  }

  @Override
  public Prenda fabricarParteInferior() {
    Color gris = new Color(10, 20, 30);
    return new BorradorPrenda(TipoPrenda.JOGGING)
        .especificarMaterial(Material.ACETATO)
        .especificarColorPrimario(gris)
        .crearPrenda();
  }

  @Override
  public Prenda fabricarCalzado() {
    Color blancas = new Color(15, 10, 20);
    return new BorradorPrenda(TipoPrenda.ZAPATILLAS)
        .especificarMaterial(Material.CUERINA)
        .especificarColorPrimario(blancas)
        .crearPrenda();
  }
}
