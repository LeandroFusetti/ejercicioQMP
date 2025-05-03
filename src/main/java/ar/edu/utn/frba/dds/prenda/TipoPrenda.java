package ar.edu.utn.frba.dds.prenda;

import java.util.Arrays;
import java.util.List;

public enum TipoPrenda {
  CAMISA_MANGA_LARGA(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.LANA, Material.ALGODON, Material.SEDA,Material.ACETATO,Material.PIQUE)),
  CAMISA_MANGA_CORTA(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.LANA, Material.ALGODON, Material.SEDA,Material.ACETATO,Material.PIQUE)),
  CHOMBA(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.LANA, Material.ALGODON, Material.SEDA,Material.ACETATO,Material.PIQUE)),

  JEAN(Categoria.PARTE_INFERIOR, Arrays.asList(Material.LANA, Material.ALGODON, Material.SEDA,Material.ACETATO)),
  JOGGING(Categoria.PARTE_INFERIOR, Arrays.asList(Material.LANA, Material.ALGODON, Material.SEDA,Material.ACETATO)),
  ZAPATOS(Categoria.CALZADO, Arrays.asList(Material.CUERO, Material.CUERINA)),
  ZAPATILLAS(Categoria.CALZADO, Arrays.asList(Material.CUERO, Material.CUERINA)),
  GORRA(Categoria.ACCESORIOS, Arrays.asList(Material.LANA, Material.ALGODON)),
  ANTEOJOS_DE_SOL(Categoria.ACCESORIOS, Arrays.asList(Material.PLASTICO));


  //la palabra clave final se usa para indicar que un elemento no puede ser modificado después de su inicialización.

  private final Categoria categoria;
  private final List<Material> materialesCompatibles;

  public Categoria getCategoria() {
    return this.categoria;
  }

  public List<Material> getMaterialesCompatibles() {
    return materialesCompatibles;
  }

  //el constructor es privado
  TipoPrenda(Categoria categoria, List<Material> materialesCompatibles) {
    this.categoria = categoria;
    this.materialesCompatibles = materialesCompatibles;


  }


}



