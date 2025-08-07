package ar.edu.utn.frba.dds.sistemaMeteorologico;

import ar.edu.utn.frba.dds.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RegistroDeAlertas {
  public ServicioMeteorologico servicioMeteorologico;
  public List<AlertaMeteorologica> alertasActuales;
  public List<Usuario> usuariosSuscritos;

  public RegistroDeAlertas(ServicioMeteorologico servicioMeteorologico) {
    this.alertasActuales = new ArrayList<>();
    this.servicioMeteorologico = servicioMeteorologico;
    this.usuariosSuscritos = new ArrayList<>();
  }

  public void suscribirUsuario(Usuario usuario) {
    this.usuariosSuscritos.add(usuario);
  }
  public void actualizarAlertas() {
    this.alertasActuales = this.servicioMeteorologico.getAlertasMeteorologicas();
    this.realizarAccionesSobreAlertas(this.alertasActuales);

  }

  private void realizarAccionesSobreAlertas(List<AlertaMeteorologica> alertasActuales) {
    this.usuariosSuscritos.forEach(u -> u.realizarAccionAnteAlertas(alertasActuales));
  }



  public List<AlertaMeteorologica> getAlertasActuales() {
    return alertasActuales;
  }

}
