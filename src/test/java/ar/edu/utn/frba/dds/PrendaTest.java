package ar.edu.utn.frba.dds;
import static org.junit.jupiter.api.Assertions.assertEquals;
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


  public Prenda zapatosNegros(){
    Color negro= new Color(1,2,3);
    Prenda zapatosNegros = new Prenda(TipoPrenda.ZAPATOS,Material.CUERO,negro,null);
    return zapatosNegros;
  }


  public Prenda camisaMangaLargaNegra(){
    Color negro= new Color(1,2,3);
    Prenda camisaMangaLargaNegra= new Prenda(TipoPrenda.CAMISA_MANGA_LARGA,Material.ALGODON,negro,null);
    return camisaMangaLargaNegra;
  }
  public Prenda lentesNegrosPlastico(){
    Color negro= new Color(1,2,3);
    Prenda lentesNegrosPlastico= new Prenda(TipoPrenda.ANTEOJOS_DE_SOL,Material.PLASTICO,negro,null);
    return lentesNegrosPlastico;
  }
}
