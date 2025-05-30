package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.motorBusqueda.MotorAtuendoATemperaturaActual;
import ar.edu.utn.frba.dds.motorBusqueda.MotorAtuendoInformalSenioresMayores;
import ar.edu.utn.frba.dds.prenda.BorradorPrenda;
import ar.edu.utn.frba.dds.prenda.Color;
import ar.edu.utn.frba.dds.prenda.Formalidad;
import ar.edu.utn.frba.dds.prenda.Material;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.prenda.TipoPrenda;
import ar.edu.utn.frba.dds.sistemaMeteorologico.ServicioMeteorologicoOpenWeather;
import ar.edu.utn.frba.dds.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class AtuendoTest {

  @Test
  public void elUsuarioTiene6Prendas() {

    assertEquals(6, usuarioMotorMayor().getPrendas().size());
  }

  @Test
  public void elMotorFiltra3PrendasFormalesPorEdadDeUsuario() throws IOException {

    assertEquals(3, new MotorAtuendoInformalSenioresMayores().aplicarFiltro(usuarioMotorMayor()).size());
  }

  @Test
  public void elMotorFiltra6PrendasFormalesPorEdadDeUsuarioMenor() throws IOException {

    assertEquals(6, new MotorAtuendoInformalSenioresMayores().aplicarFiltro(usuarioMotorMenor()).size());
  }

  @Test
  public void elMotorEstaDesactivado() {

    assertFalse(usuarioMotorMayorConMotorApagado().getMotor().isEstaActivado());
  }

  @Test
  public void elMotorFiltra6PrendasFormalesPorEdadDeUsuarioConMotorApagado() throws IOException {

    assertEquals(6, usuarioMotorMayorConMotorApagado().getMotor().aplicarFiltro(usuarioMotorMayorConMotorApagado()).size());
  }

  @Test
  public void elUsuarioMayorPideCombinacionesDePrendas() throws IOException {

    assertEquals(1, usuarioMotorMayor().recibirSugerenciasDeAtuendos().size());
  }

  @Test
  public void elUsuarioMenorPideCombinacionesDePrendas() throws IOException {

    assertEquals(8, usuarioMotorMenor().recibirSugerenciasDeAtuendos().size());
  }

  /*CON API GRATUITA DEL CLIMA
  @Test
  public void elUsuarioMenorPideCombinacionesDePrendasTempActual() throws IOException {
    assertEquals(1, usuarioMotorTemperaturaAPIGratuita().recibirSugerenciasDeAtuendos().size());
  }
  */
  @Test
  public void elUsuarioMenorPideCombinacionesDePrendasTempActual() throws IOException {
    assertEquals(1, usuarioMotorTemperaturaAPIGratuita().recibirSugerenciasDeAtuendos().size());
  }
  //-----------USUARIOS-----------
  public Usuario usuarioMotorMayor() {
    return new Usuario(60, conjuntoDePrendas(), new MotorAtuendoInformalSenioresMayores());
  }

  public Usuario usuarioMotorTemperaturaAPIGratuita() {
    return new Usuario(20, conjuntoDePrendas(), new MotorAtuendoATemperaturaActual(new ServicioMeteorologicoOpenWeather()));
  }



  public Usuario usuarioMotorMayorConMotorApagado() {
    Usuario usuario = new Usuario(60, conjuntoDePrendas(), new MotorAtuendoInformalSenioresMayores());
    usuario.getMotor().desactivar();
    //System.out.println(usuario.getMotor().isEstaActivado());
    return usuario;
  }

  public Usuario usuarioMotorMenor() {
    return new Usuario(20, conjuntoDePrendas(), new MotorAtuendoInformalSenioresMayores());
  }

  //-----------CONJUNTO DE PRENDAS-----------
  public List<Prenda> conjuntoDePrendasInvierno() {
    List<Prenda> prendas = new ArrayList<Prenda>();
    prendas.add(zapatosNegros());
    prendas.add(zapatillasBlancas());
    prendas.add(camisaMangaLargaNegra());
    prendas.add(pantalonJean());
    return prendas;
  }

  public List<Prenda> conjuntoDePrendas() {
    List<Prenda> prendas = new ArrayList<Prenda>();
    prendas.add(zapatosNegros());
    prendas.add(zapatillasBlancas());
    prendas.add(camisaMangaLargaNegra());
    prendas.add(camisaMangaCortaNegra());
    prendas.add(pantalonJogging());
    prendas.add(pantalonJean());
    return prendas;
  }

  //-----------PRENDAS-----------
  public Prenda zapatosNegros() {
    Color negro = new Color(1, 2, 3);

    Prenda zapatosNegros = new BorradorPrenda(TipoPrenda.ZAPATOS)
        .especificarColorPrimario(negro)
        .especificarMaterial(Material.CUERO)
        .especificarFormalidad(Formalidad.FORMAL)
        .crearPrenda();
    return zapatosNegros;
  }

  public Prenda zapatillasBlancas() {
    Color blanco = new Color(1, 2, 3);
    Prenda zapatillasBlancas = new BorradorPrenda(TipoPrenda.ZAPATILLAS)
        .especificarColorPrimario(blanco)
        .especificarMaterial(Material.CUERINA)
        .especificarFormalidad(Formalidad.INFORMAL)
        .crearPrenda();
    return zapatillasBlancas;
  }

  public Prenda camisaMangaLargaNegra() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.CAMISA_MANGA_LARGA)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.SEDA)
        .especificarFormalidad(Formalidad.FORMAL)
        .crearPrenda();
  }

  public Prenda camisaMangaCortaNegra() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.CAMISA_MANGA_CORTA)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.SEDA)
        .especificarFormalidad(Formalidad.INFORMAL)
        .crearPrenda();
  }

  public Prenda pantalonJogging() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.JOGGING)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.ACETATO)
        .especificarFormalidad(Formalidad.INFORMAL)
        .crearPrenda();
  }

  public Prenda pantalonJean() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.JEAN)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.ACETATO)
        .especificarFormalidad(Formalidad.FORMAL)
        .crearPrenda();

  }


}
