package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.apiclima.AccuWeatherAPI;
import ar.edu.utn.frba.dds.motorBusqueda.MotorAlertaMeteorologica;
import ar.edu.utn.frba.dds.motorBusqueda.MotorAtuendoATemperaturaActual;
import ar.edu.utn.frba.dds.motorBusqueda.MotorAtuendoInformalSenioresMayores;
import ar.edu.utn.frba.dds.prenda.BorradorPrenda;
import ar.edu.utn.frba.dds.prenda.Color;
import ar.edu.utn.frba.dds.prenda.Formalidad;
import ar.edu.utn.frba.dds.prenda.Material;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.prenda.TipoPrenda;
import ar.edu.utn.frba.dds.serviciosExternos.Correo;
import ar.edu.utn.frba.dds.serviciosExternos.CorreoAdapter;
import ar.edu.utn.frba.dds.serviciosExternos.MailSender;
import ar.edu.utn.frba.dds.serviciosExternos.NotificadorAdapter;
import ar.edu.utn.frba.dds.serviciosExternos.NotificadorSender;
import ar.edu.utn.frba.dds.sistemaMeteorologico.RegistroDeAlertas;
import ar.edu.utn.frba.dds.sistemaMeteorologico.ServicioMeteorologicoAccuWeather;
import ar.edu.utn.frba.dds.usuario.CalculadorDeSugerencias;
import ar.edu.utn.frba.dds.usuario.EnviarMail;
import ar.edu.utn.frba.dds.usuario.EnviarNotificacion;
import ar.edu.utn.frba.dds.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AtuendoTest {


  @Test
  public void elUsuarioTiene6Prendas() {

    assertEquals(6, usuarioMotorMayor().getPrendas().size());
  }

  @Test
  public void elMotorFiltra3PrendasFormalesPorEdadDeUsuario() {

    assertEquals(3, new MotorAtuendoInformalSenioresMayores().aplicarFiltro(usuarioMotorMayor()).size());
  }

  @Test
  public void elMotorFiltra6PrendasFormalesPorEdadDeUsuarioMenor() {

    assertEquals(6, new MotorAtuendoInformalSenioresMayores().aplicarFiltro(usuarioMotorMenor()).size());
  }

  @Test
  public void elMotorEstaDesactivado() {

    assertFalse(usuarioMotorMayorConMotorApagado().getMotor().isEstaActivado());
  }

  @Test
  public void elMotorFiltra6PrendasFormalesPorEdadDeUsuarioConMotorApagado() {

    assertEquals(6, usuarioMotorMayorConMotorApagado().getMotor().aplicarFiltro(usuarioMotorMayorConMotorApagado()).size());
  }

  @Test
  public void elUsuarioMayorPideCombinacionesDePrendas() {

    assertEquals(1, usuarioMotorMayor().recibirSugerenciasDeAtuendos().size());
  }

  @Test
  public void elUsuarioMenorPideCombinacionesDePrendas() {

    assertEquals(8, usuarioMotorMenor().recibirSugerenciasDeAtuendos().size());
  }

  //-----Test con mockApi---
  @Test
  @DisplayName("devuelve la combinacion segun la temperatura de 30°")
  public void elUsuarioMenorPideCombinacionesDePrendasTempActual() {
    assertEquals(1, usuarioMotorTemperaturaAPIMock().recibirSugerenciasDeAtuendos().size());
  }

  @Test
  @DisplayName("devuelve la combinacion del conjunto de lluvia")
  void elUsuarioMenorPideCombinacionesDePrendasConAlerta() {
    assertEquals(1, usuarioMotorConAlertaAPIMock().recibirSugerenciasDeAtuendos().size());
  }

  @Test
  @DisplayName("devuelve combinaciones sin el conjunto de lluvia porque no hay alertas")
  void elUsuarioMenorPideCombinacionesDePrendasSinAlerta() {
    assertEquals(8, usuarioMotorSinAlertaAPIMock().recibirSugerenciasDeAtuendos().size());
  }


  @Test
  @DisplayName("Da 2 sugerencias distintas ante la generacion posterior de una alerta")
  void devuelveCombinacionesDistintasAlSegundoLlamado() {
    // Configuración del mock
    AccuWeatherAPI mockApi = mock(AccuWeatherAPI.class);
    when(mockApi.getAlerts("Buenos Aires"))
        .thenReturn(Map.of("CurrentAlerts", List.of()))             // Primera llamada
        .thenReturn(Map.of("CurrentAlerts", List.of("Tormenta", "Granizo"))); // Segunda llamada

    Usuario user = new Usuario(20, conjuntoDePrendasYLluvia(),
        new MotorAlertaMeteorologica(new ServicioMeteorologicoAccuWeather(mockApi)));

    // Primera llamada a recibirSugerenciasDeAtuendos (debería usar la primera respuesta del mock)
    assertEquals(8, user.recibirSugerenciasDeAtuendos().size());


    // Segunda llamada a recibirSugerenciasDeAtuendos (debería usar la segunda respuesta del mock)
    assertEquals(1, user.recibirSugerenciasDeAtuendos().size());

    // Verifica que getAlerts se llamó exactamente 2 veces
    verify(mockApi, times(2)).getAlerts("Buenos Aires");
  }

  @Test
  @DisplayName("se disparan a los usuarios generacion de sugerencias")
  public void seDisparaAUsuariosGeneracionSugerencias() {
    CalculadorDeSugerencias calculador = new CalculadorDeSugerencias();
    Usuario user = usuarioMotorConAlertaAPIMock();
    Usuario user2 = usuarioMotorSinAlertaAPIMock();

    calculador.agregarUsuario(user);
    calculador.agregarUsuario(user2);

    assertEquals(0, user.getSugerenciaDiaria().size());
    assertEquals(0, user2.getSugerenciaDiaria().size());

    calculador.calcularSugerenciaDiaria();
    assertEquals(1, user.getSugerenciaDiaria().size());
    assertEquals(8, user2.getSugerenciaDiaria().size());

  }

  @Test
  void obtengoLasUltimasAlertas(){
    AccuWeatherAPI mockApi = mock(AccuWeatherAPI.class);
    when(mockApi.getAlerts("Buenos Aires"))
        .thenReturn(Map.of("CurrentAlerts", Arrays.asList("Tormenta", "Granizo")));
    ServicioMeteorologicoAccuWeather servicioMeteorologicoAccuWeather = new ServicioMeteorologicoAccuWeather(mockApi);

    RegistroDeAlertas registro= new RegistroDeAlertas(servicioMeteorologicoAccuWeather);
    assertEquals(2, servicioMeteorologicoAccuWeather.getAlertasMeteorologicas().size());

    registro.actualizarAlertas();
    assertEquals(2, registro.getAlertasActuales().size());


    assertEquals(2, usuarioMotorConAlertaAPIMock().consultarAlertas(registro).size());

  }
  @Test
  @DisplayName("Cambian las alertas al segundo llamado a la api")
  void actualizoAlertas() {
    // Configuración del mock
    AccuWeatherAPI mockApi = mock(AccuWeatherAPI.class);
    when(mockApi.getAlerts("Buenos Aires"))
        .thenReturn(Map.of("CurrentAlerts", List.of()))             // Primera llamada
        .thenReturn(Map.of("CurrentAlerts", List.of("Tormenta", "Granizo"))); // Segunda llamada
    ServicioMeteorologicoAccuWeather servicioMeteorologicoAccuWeather = new ServicioMeteorologicoAccuWeather(mockApi);

    RegistroDeAlertas registro= new RegistroDeAlertas(servicioMeteorologicoAccuWeather);
    registro.actualizarAlertas();
    assertEquals(0, registro.getAlertasActuales().size());
    registro.actualizarAlertas();

    assertEquals(2, registro.getAlertasActuales().size());
  }

  @Test
  @DisplayName("Se disparan las acciones ante llamado a la API por alertas, se quita una accion y no se dispara esa accion")
  void alertasDisparanAcciones() {
    // Configuración del mock
    AccuWeatherAPI mockApi = mock(AccuWeatherAPI.class);
    when(mockApi.getAlerts("Buenos Aires"))
        .thenReturn(Map.of("CurrentAlerts", List.of()))             // Primera llamada
        .thenReturn(Map.of("CurrentAlerts", List.of("Tormenta", "Granizo"))); // Segunda llamada
    ServicioMeteorologicoAccuWeather servicioMeteorologicoAccuWeather = new ServicioMeteorologicoAccuWeather(mockApi);

    Usuario user = new Usuario(20, conjuntoDePrendasYLluvia(), new MotorAlertaMeteorologica(servicioMeteorologicoAccuWeather));
    MailSender mailSender = mock(MailSender.class);
    NotificadorSender notificadorSender = mock(NotificadorSender.class);

    CorreoAdapter correo = new CorreoAdapter(mailSender);
    EnviarMail enviarMail = new EnviarMail(correo, "mensaje");

    NotificadorAdapter notificador = new NotificadorAdapter(notificadorSender);
    EnviarNotificacion enviarNotificacion = new EnviarNotificacion(notificador);

    user.agregarAccion(enviarMail);
    user.agregarAccion(enviarNotificacion);


    RegistroDeAlertas registro = new RegistroDeAlertas(servicioMeteorologicoAccuWeather);
    registro.suscribirUsuario(user);
    registro.actualizarAlertas(); // Primera llamada: lista vacía
    assertEquals(0, registro.getAlertasActuales().size());

    registro.actualizarAlertas(); // Segunda llamada: lista con alertas
    assertEquals(2, registro.getAlertasActuales().size());

    // Verificar que mailSender fue llamado dos veces
    verify(mailSender, times(2)).sendMail(anyString(), anyString());

    // Verificar que notificadorSender fue llamado dos veces
    verify(notificadorSender, times(2)).notify(anyString());

    user.quitarAccion(enviarNotificacion);
    registro.actualizarAlertas();

    //se llama una tercera vez
    verify(mailSender, times(3)).sendMail(anyString(), anyString());
    //no se llama porque se quito
    verify(notificadorSender, times(2)).notify(anyString());


  }



  //-----------USUARIOS-----------
  public static Usuario usuarioMotorMayor() {
    return new Usuario(60, conjuntoDePrendas(), new MotorAtuendoInformalSenioresMayores());
  }

  //---------Mock De Accuweather-----
  public static Usuario usuarioMotorTemperaturaAPIMock() {
    AccuWeatherAPI mockApi = mock(AccuWeatherAPI.class);
    when(mockApi.getWeather(anyString()))
        .thenReturn(List.of(Map.of("Temperature", Map.of("Value", 30.0))));
    return new Usuario(20, conjuntoDePrendas(),
        new MotorAtuendoATemperaturaActual(new ServicioMeteorologicoAccuWeather(mockApi)));
  }


  public static Usuario usuarioMotorConAlertaAPIMock() {
    AccuWeatherAPI mockApi = mock(AccuWeatherAPI.class);
    when(mockApi.getAlerts("Buenos Aires"))
        .thenReturn(Map.of("CurrentAlerts", Arrays.asList("Tormenta", "Granizo")));
    return new Usuario(20, conjuntoDePrendasYLluvia(),
        new MotorAlertaMeteorologica(new ServicioMeteorologicoAccuWeather(mockApi)));
  }

  //------sin alertas-----
  public static Usuario usuarioMotorSinAlertaAPIMock() {
    AccuWeatherAPI mockApi = mock(AccuWeatherAPI.class);
    when(mockApi.getAlerts("Buenos Aires"))
        .thenReturn(Map.of("CurrentAlerts", List.of()));
    return new Usuario(20, conjuntoDePrendasYLluvia(),
        new MotorAlertaMeteorologica(new ServicioMeteorologicoAccuWeather(mockApi)));
  }

  //------sin alertas, al segundo llamado si-----
  public static Usuario usuarioMotorConAlertaAlSegundoLlmadoAPIMock() {
    AccuWeatherAPI mockApi = mock(AccuWeatherAPI.class);
    when(mockApi.getAlerts("Buenos Aires"))
        .thenReturn(Map.of("CurrentAlerts", List.of()))
        .thenReturn(Map.of("CurrentAlerts", Arrays.asList("Tormenta", "Granizo")));
    return new Usuario(20, conjuntoDePrendasYLluvia(),
        new MotorAlertaMeteorologica(new ServicioMeteorologicoAccuWeather(mockApi)));
  }


  public static Usuario usuarioMotorMayorConMotorApagado() {
    Usuario usuario = new Usuario(60, conjuntoDePrendas(), new MotorAtuendoInformalSenioresMayores());
    usuario.getMotor().desactivar();
    return usuario;
  }

  public static Usuario usuarioMotorMenor() {
    return new Usuario(20, conjuntoDePrendas(), new MotorAtuendoInformalSenioresMayores());
  }


  //-----------CONJUNTO DE PRENDAS-----------


  public static List<Prenda> conjuntoDePrendasInvierno() {
    List<Prenda> prendas = new ArrayList<Prenda>();
    prendas.add(zapatosNegros());
    prendas.add(zapatillasBlancas());
    prendas.add(camisaMangaLargaNegra());
    prendas.add(pantalonJean());
    return prendas;
  }

  public static List<Prenda> conjuntoDePrendas() {
    List<Prenda> prendas = new ArrayList<Prenda>();
    prendas.add(zapatosNegros());
    prendas.add(zapatillasBlancas());
    prendas.add(camisaMangaLargaNegra());
    prendas.add(camisaMangaCortaNegra());
    prendas.add(pantalonJogging());
    prendas.add(pantalonJean());
    return prendas;
  }

  public static List<Prenda> conjuntoDePrendasYLluvia() {
    List<Prenda> prendas = new ArrayList<Prenda>();
    prendas.add(zapatosNegros());
    prendas.add(zapatillasBlancas());
    prendas.add(camisaMangaLargaNegra());
    prendas.add(camisaMangaCortaNegra());
    prendas.add(pantalonJogging());
    prendas.add(pantalonJean());
    prendas.add(camperaDeLluvia());
    prendas.add(pantalonDeLluvia());
    prendas.add(botasDeLluvia());
    return prendas;
  }

  //-----------PRENDAS-----------
  public static Prenda zapatosNegros() {
    Color negro = new Color(1, 2, 3);

    Prenda zapatosNegros = new BorradorPrenda(TipoPrenda.ZAPATOS)
        .especificarColorPrimario(negro)
        .especificarMaterial(Material.CUERO)
        .especificarFormalidad(Formalidad.FORMAL)
        .crearPrenda();
    return zapatosNegros;
  }

  public static Prenda zapatillasBlancas() {
    Color blanco = new Color(1, 2, 3);
    Prenda zapatillasBlancas = new BorradorPrenda(TipoPrenda.ZAPATILLAS)
        .especificarColorPrimario(blanco)
        .especificarMaterial(Material.CUERINA)
        .especificarFormalidad(Formalidad.INFORMAL)
        .crearPrenda();
    return zapatillasBlancas;
  }

  public static Prenda camisaMangaLargaNegra() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.CAMISA_MANGA_LARGA)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.SEDA)
        .especificarFormalidad(Formalidad.FORMAL)
        .crearPrenda();
  }


  public static Prenda camisaMangaCortaNegra() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.CAMISA_MANGA_CORTA)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.SEDA)
        .especificarFormalidad(Formalidad.INFORMAL)
        .crearPrenda();
  }

  public static Prenda pantalonJogging() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.JOGGING)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.ACETATO)
        .especificarFormalidad(Formalidad.INFORMAL)
        .crearPrenda();
  }

  public static Prenda pantalonJean() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.JEAN)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.ACETATO)
        .especificarFormalidad(Formalidad.FORMAL)
        .crearPrenda();

  }

  //------------Conjunto de lluvia--------------------
  public static Prenda camperaDeLluvia() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.CAMPERA_DE_LLUVIA)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.IMPERMEABLE)
        .especificarFormalidad(Formalidad.NEUTRA)
        .crearPrenda();
  }

  public static Prenda pantalonDeLluvia() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.PANTALON_DE_LLUVIA)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.IMPERMEABLE)
        .especificarFormalidad(Formalidad.NEUTRA)
        .crearPrenda();
  }

  public static Prenda botasDeLluvia() {
    Color negro = new Color(1, 2, 3);
    Color azul = new Color(2, 3, 4);
    return new BorradorPrenda(TipoPrenda.BOTAS_DE_LLUVIA)
        .especificarColorPrimario(negro)
        .especificarColorSecundario(azul)
        .especificarMaterial(Material.IMPERMEABLE)
        .especificarFormalidad(Formalidad.NEUTRA)
        .crearPrenda();
  }

}
