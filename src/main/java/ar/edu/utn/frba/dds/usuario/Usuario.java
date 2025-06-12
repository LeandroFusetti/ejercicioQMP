package ar.edu.utn.frba.dds.usuario;

import ar.edu.utn.frba.dds.motorBusqueda.MotorSugerencia;
import ar.edu.utn.frba.dds.prenda.Guardarropa;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.sastre.Atuendo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Usuario {
  private Integer edad;

  private MotorSugerencia motor;
  private List<Prenda> prendas;
  private List<Guardarropa> guardarropas;

  public Usuario(Integer edad, List<Prenda> prendas, MotorSugerencia motor) {
    this.edad = edad;
    this.prendas = prendas;
    this.motor = motor;
    this.guardarropas = new ArrayList<>();
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

  public List<Atuendo> recibirSugerenciasDeAtuendos() throws IOException {
    return this.motor.generarSugerencias(this);
  }

}
