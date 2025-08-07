package ar.edu.utn.frba.dds.usuario;

import ar.edu.utn.frba.dds.serviciosExternos.Notificador;
import ar.edu.utn.frba.dds.sistemaMeteorologico.AlertaMeteorologica;

import java.util.List;

public class EnviarNotificacion implements AccionConfigurable {
  private Notificador notificador;

  public EnviarNotificacion(Notificador notificador) {
    this.notificador = notificador;
  }

  @Override
  public void anteNuevasAlertas(Usuario usuario, List<AlertaMeteorologica> alertas) {
    alertas.forEach(a -> notificador.notificar(a.getMensaje()));
  }
}
