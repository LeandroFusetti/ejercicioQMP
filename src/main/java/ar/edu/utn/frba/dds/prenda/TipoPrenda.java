package ar.edu.utn.frba.dds.prenda;

import java.util.Arrays;
import java.util.List;

public enum TipoPrenda {
  CAMISA_MANGA_LARGA(Categoria.PARTE_SUPERIOR,
      Arrays.asList(Material.LANA, Material.ALGODON,
          Material.SEDA, Material.ACETATO, Material.PIQUE), 0.00, 22.00),
  CAMISA_MANGA_CORTA(Categoria.PARTE_SUPERIOR,
      Arrays.asList(Material.LANA, Material.ALGODON, Material.SEDA,
          Material.ACETATO, Material.PIQUE), 21.00, 35.00),
  CHOMBA(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.LANA, Material.ALGODON,
      Material.SEDA, Material.ACETATO, Material.PIQUE), 21.00, 35.00),

  JEAN(Categoria.PARTE_INFERIOR, Arrays.asList(Material.LANA, Material.ALGODON,
      Material.SEDA, Material.ACETATO), 0.00, 21.00),
  JOGGING(Categoria.PARTE_INFERIOR, Arrays.asList(Material.LANA, Material.ALGODON,
      Material.SEDA, Material.ACETATO), 10.00, 30.00),

  ZAPATOS(Categoria.CALZADO, Arrays.asList(Material.CUERO, Material.CUERINA), 10.00, 20.00),
  ZAPATILLAS(Categoria.CALZADO, Arrays.asList(Material.CUERO, Material.CUERINA), 0.00, 35.00),

  GORRA(Categoria.ACCESORIOS, Arrays.asList(Material.LANA, Material.ALGODON), 5.00, 35.00),
  ANTEOJOS_DE_SOL(Categoria.ACCESORIOS, List.of(Material.PLASTICO), 0.00, 35.00);


  //la palabra clave final se usa para indicar que un elemento
  // no puede ser modificado después de su inicialización.

  private final Categoria categoria;
  private final List<Material> materialesCompatibles;
  private final Double esAptaDesdeLaTempDe;
  private final Double esAptaHastaLaTempDe;

  public Categoria getCategoria() {
    return this.categoria;
  }

  public List<Material> getMaterialesCompatibles() {
    return materialesCompatibles;
  }

  public Double getEsAptaDesdeLaTempDe() {
    return esAptaDesdeLaTempDe;
  }

  public Double getEsAptaHastaLaTempDe() {
    return esAptaHastaLaTempDe;
  }

  //el constructor es privado
  TipoPrenda(Categoria categoria, List<Material> materialesCompatibles, Double esAptaDesdeLaTempDe, Double esAptaHastaLaTempDe) {
    this.categoria = categoria;
    this.materialesCompatibles = materialesCompatibles;

    this.esAptaDesdeLaTempDe = esAptaDesdeLaTempDe;
    this.esAptaHastaLaTempDe = esAptaHastaLaTempDe;

  }


}



