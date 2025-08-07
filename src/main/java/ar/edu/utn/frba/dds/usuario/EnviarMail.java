package ar.edu.utn.frba.dds.usuario;

import ar.edu.utn.frba.dds.serviciosExternos.Correo;
import ar.edu.utn.frba.dds.sistemaMeteorologico.AlertaMeteorologica;

import java.util.List;

public class EnviarMail implements AccionConfigurable{
  private Correo correo;
  private String mensaje;

  public EnviarMail(Correo correo, String mensaje) {
    this.correo = correo;
    this.mensaje = mensaje;
  }
  @Override
  public void anteNuevasAlertas(Usuario usuario, List<AlertaMeteorologica> alertas) {
    this.correo.enviarCorreo(usuario, this.mensaje);
  }
}
