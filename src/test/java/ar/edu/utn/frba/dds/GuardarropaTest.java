package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.prenda.Estado;
import ar.edu.utn.frba.dds.prenda.Guardarropa;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.prenda.SolicitudAgregarAGuardarropa;
import ar.edu.utn.frba.dds.prenda.SolicitudQuitarDeGuardarropa;
import ar.edu.utn.frba.dds.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static ar.edu.utn.frba.dds.AtuendoTest.camisaMangaCortaNegra;
import static ar.edu.utn.frba.dds.AtuendoTest.conjuntoDePrendas;
import static ar.edu.utn.frba.dds.AtuendoTest.conjuntoDePrendasInvierno;
import static ar.edu.utn.frba.dds.AtuendoTest.usuarioMotorMayor;
import static ar.edu.utn.frba.dds.AtuendoTest.usuarioMotorMenor;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuardarropaTest {

  private Guardarropa guardarropaCompleto = new Guardarropa(conjuntoDePrendas());
  private Guardarropa guardarropaInvierno = new Guardarropa(conjuntoDePrendasInvierno());

  public Usuario usuarioMayorCon2guardarropas() {
    Usuario usuario = usuarioMotorMayor();
    usuario.agregarGuardarropa(guardarropaCompleto);
    usuario.agregarGuardarropa(guardarropaInvierno);
    return usuario;
  }

  public Usuario usuarioMenorCon1guardarropas() {
    Usuario usuario = usuarioMotorMenor();
    usuario.agregarGuardarropa(this.guardarropaInvierno);
    return usuario;
  }

  @Test
  public void cargoRopaEn2Guardarropas() {
    Usuario usuario = usuarioMayorCon2guardarropas();
    assertEquals(6, usuario.getGuardarropas().get(0).getPrendas().size());
    assertEquals(4, usuario.getGuardarropas().get(1).getPrendas().size());

  }

  @Test
  @DisplayName("Se hace solicitud de agregado de prenda y se acepta")
  public void aceptarSolicutudDeAgregadoDeRopa() {
    Usuario usuarioGuardarropaInvierno = usuarioMenorCon1guardarropas();
    assertEquals(4, usuarioGuardarropaInvierno.getGuardarropas().get(0).getPrendas().size());
    SolicitudAgregarAGuardarropa solicitudAgregar = usuarioGuardarropaInvierno
        .getGuardarropas().get(0).crearSolicitudAgregarAGuardarropa(camisaMangaCortaNegra());
    solicitudAgregar.aceptarSolicitudEn(usuarioGuardarropaInvierno.getGuardarropas().get(0));

    assertEquals(5, usuarioGuardarropaInvierno.getGuardarropas().get(0).getPrendas().size());


  }

  @Test
  @DisplayName("Se acepta solicitud de agregado y se deshace la operacion")
  public void deshacerSolicitudDeAgregadoDeRopa() {
    Usuario usuarioGuardarropaInvierno = usuarioMenorCon1guardarropas();
    SolicitudAgregarAGuardarropa solicitudAgregar = usuarioGuardarropaInvierno
        .getGuardarropas().get(0).crearSolicitudAgregarAGuardarropa(camisaMangaCortaNegra());
    solicitudAgregar.aceptarSolicitudEn(usuarioGuardarropaInvierno.getGuardarropas().get(0));

    assertEquals(5, usuarioGuardarropaInvierno.getGuardarropas().get(0).getPrendas().size());
    assertEquals(Estado.ACEPTADO, solicitudAgregar.getEstado());

    solicitudAgregar.deshacerModificacion(usuarioGuardarropaInvierno.getGuardarropas().get(0));
    assertEquals(4, usuarioGuardarropaInvierno.getGuardarropas().get(0).getPrendas().size());
    assertEquals(Estado.PENDIENTE, solicitudAgregar.getEstado());

  }
  @Test
  public void rechazarSolicitud(){
    Usuario usuarioGuardarropaInvierno = usuarioMenorCon1guardarropas();

    SolicitudAgregarAGuardarropa solicitudAgregar = usuarioGuardarropaInvierno
        .getGuardarropas().get(0).crearSolicitudAgregarAGuardarropa(camisaMangaCortaNegra());
    assertEquals(Estado.PENDIENTE, solicitudAgregar.getEstado());

    solicitudAgregar.rechazarSolicitud();

    assertEquals(Estado.RECHAZADO, solicitudAgregar.getEstado());
  }

  @Test
  @DisplayName("un usuario crea solicitud de quita en el segundo guardaropa, " +
      "el otro usuario la acepta y se elimina la ropa ")
  public void aceptarSolicitudDeQuitarDeRopa() {
    Usuario usuario = usuarioMayorCon2guardarropas();
    Usuario usuario2 = usuarioMayorCon2guardarropas();

    assertEquals(4, usuario.getGuardarropas().get(1).getPrendas().size());

    Prenda camisa = camisaMangaCortaNegra();
    usuario.getGuardarropas().get(1).agregarPrenda(camisa);
    assertEquals(5, usuario.getGuardarropas().get(1).getPrendas().size());


    SolicitudQuitarDeGuardarropa solicitudQuita = usuario2.getGuardarropas().get(1).
        crearSolicitudQuitarDeGuardarropa(camisa);
    assertEquals(1, usuario.getGuardarropas()
        .get(1).getSolicitudModificacionGuardarropa().size());

    assertEquals(Estado.PENDIENTE, solicitudQuita.getEstado());
    solicitudQuita.aceptarSolicitudEn(usuario2.getGuardarropas().get(0));
    assertEquals(Estado.ACEPTADO, solicitudQuita.getEstado());

    assertEquals(6, usuario.getGuardarropas().get(0).getPrendas().size());
  }


}
