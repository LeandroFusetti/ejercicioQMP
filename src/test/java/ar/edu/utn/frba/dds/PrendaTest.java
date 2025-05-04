package ar.edu.utn.frba.dds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ar.edu.utn.frba.dds.prenda.BorradorException;
import ar.edu.utn.frba.dds.prenda.BorradorPrenda;
import ar.edu.utn.frba.dds.prenda.Color;
import ar.edu.utn.frba.dds.prenda.Material;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.prenda.PrendaInvalidaException;
import ar.edu.utn.frba.dds.prenda.TipoPrenda;
import ar.edu.utn.frba.dds.sastre.Atuendo;
import ar.edu.utn.frba.dds.sastre.SastreSanJuan;

import org.junit.jupiter.api.Test;

public class PrendaTest {
  @Test
  public void elTipoDePrendaDeZapatosEsZAPATOS(){
    assertEquals("ZAPATOS",zapatosNegros().getTipo().toString());
  }

  @Test
  public void laCategoriaDeCamisaMangaLargaEsPARTE_SUPERIOR(){
    assertEquals("PARTE_SUPERIOR",camisaMangaLargaNegra().getCategoria().toString());
  }

  @Test
  public void elMaterialDeLentesDeSolEsPLASTICO(){
    assertEquals("PLASTICO",lentesNegrosPlastico().getMaterial().toString());
  }

  @Test
  public void lanzarExcepcionSiTipoEsInvalido() {
    Exception excepcion = assertThrows(PrendaInvalidaException.class, () -> {
      new Prenda(TipoPrenda.ZAPATOS,null,new Color(1,2,3),null,null,null);
    });

    assertEquals("La prenda es invalida: Material no puede ser nulo",excepcion.getMessage());
  }

  @Test
  public void lanzarExcepcionNoTieneColor() {
    Exception excepcion = assertThrows(PrendaInvalidaException.class, () -> {
      new BorradorPrenda(TipoPrenda.ZAPATOS)
          .especificarMaterial(Material.CUERO)
          .crearPrenda();
    });

    assertEquals("La prenda es invalida: Color de prenda no puede ser nulo",excepcion.getMessage());
  }
  @Test
  public void lanzarExcepcionSiMaterialNoEsCompatible() {
    assertThrows(BorradorException.class, () -> {
      new BorradorPrenda(TipoPrenda.ZAPATOS)
          .especificarMaterial(Material.SEDA)
          .especificarColorPrimario(new Color(1,2,3))
          .crearPrenda();
    });
  }

  @Test
  public void uniformeSanJuanEsValido(){
    Atuendo uniformeSanJuan = uniformeSanJuan();
    assertEquals("ZAPATILLAS",uniformeSanJuan.getPrendaCalzado().getTipo().toString());
    assertEquals("JOGGING",uniformeSanJuan.getPrendaInferior().getTipo().toString());
    assertEquals("ACETATO",uniformeSanJuan.getPrendaInferior().getMaterial().toString());
    assertEquals("PIQUE",uniformeSanJuan.getPrendaSuperior().getMaterial().toString());
    assertEquals("CHOMBA",uniformeSanJuan.getPrendaSuperior().getTipo().toString());
  }


  public Prenda zapatosNegros(){
    Color negro= new Color(1,2,3);
    //BorradorPrenda borrador = new BorradorPrenda(TipoPrenda.ZAPATOS);

    Prenda zapatosNegros = new BorradorPrenda(TipoPrenda.ZAPATOS)
        .especificarColorPrimario(negro)
        .especificarMaterial(Material.CUERO)
        .crearPrenda();
    return zapatosNegros;
  }

  public Prenda camisaMangaLargaNegra(){
    Color negro= new Color(1,2,3);
    Color azul= new Color(2,3,4);
    return new BorradorPrenda(TipoPrenda.CAMISA_MANGA_LARGA)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.SEDA)
        .crearPrenda();

  }

  public Prenda lentesNegrosPlastico(){
    Color negro= new Color(1,2,3);
    return new BorradorPrenda(TipoPrenda.ANTEOJOS_DE_SOL)
        .especificarMaterial(Material.PLASTICO)
        .especificarColorPrimario(negro)
        .crearPrenda();
  }

  public Atuendo uniformeSanJuan(){
    return new SastreSanJuan().fabricarUniforme();
  }

}
