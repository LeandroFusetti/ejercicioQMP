package ar.edu.utn.frba.dds.usuario;

import ar.edu.utn.frba.dds.motorBusqueda.MotorSugerencia;
import ar.edu.utn.frba.dds.prenda.Guardarropa;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.sastre.Atuendo;
import ar.edu.utn.frba.dds.sistemaMeteorologico.AlertaMeteorologica;
import ar.edu.utn.frba.dds.sistemaMeteorologico.RegistroDeAlertas;

import java.util.ArrayList;
import java.util.List;


public class Usuario {
  private String mail;
  private Integer edad;
  private List<Atuendo> sugerenciaDiaria;
  private MotorSugerencia motor;
  private List<Prenda> prendas;
  private List<Guardarropa> guardarropas;
  private List<AccionConfigurable> accionesConfigurables;

  public Usuario(Integer edad, List<Prenda> prendas, MotorSugerencia motor) {
    this.edad = edad;
    this.prendas = prendas;
    this.motor = motor;
    this.guardarropas = new ArrayList<>();
    this.sugerenciaDiaria = new ArrayList<>();
    this.mail = "<EMAIL>";
    this.accionesConfigurables = new ArrayList<>();
  }

  public void agregarAccion(AccionConfigurable accion) {
    this.accionesConfigurables.add(accion);
  }

  public void quitarAccion(AccionConfigurable accion) {
    this.accionesConfigurables.remove(accion);
  }

  public List<AlertaMeteorologica> consultarAlertas(RegistroDeAlertas registroDeAlertas) {
    return registroDeAlertas.getAlertasActuales();
  }

  public void realizarAccionAnteAlertas(List<AlertaMeteorologica> alertas) {
    this.accionesConfigurables.forEach(a -> a.anteNuevasAlertas(this, alertas));
  }

  public String getMail() {
    return mail;
  }

  public void obtenerSugerenciaDiaria() {
    this.sugerenciaDiaria = this.recibirSugerenciasDeAtuendos();
  }

  public List<Atuendo> getSugerenciaDiaria() {
    return this.sugerenciaDiaria;
  }


  public List<Prenda> getPrendas() {
    return prendas;
  }

  public Integer getEdad() {
    return edad;
  }

  public void agregarGuardarropa(Guardarropa guardarropa) {
    this.guardarropas.add(guardarropa);
  }

  public void quitarGuardarropa(Guardarropa guardarropa) {
    this.guardarropas.remove(guardarropa);
  }

  public List<Guardarropa> getGuardarropas() {
    return guardarropas;
  }

  public Guardarropa[] getGuardarropasArray() {
    return this.guardarropas.toArray(new Guardarropa[0]);
  }

  public MotorSugerencia getMotor() {
    return motor;
  }

  public List<Atuendo> recibirSugerenciasDeAtuendos() {
    return this.motor.generarSugerencias(this);
  }

}
