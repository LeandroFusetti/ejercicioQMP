package ar.edu.utn.frba.dds.usuario;

import ar.edu.utn.frba.dds.motorBusqueda.MotorSugerencia;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.sastre.Atuendo;

import java.util.List;

public class Usuario {
  private Integer edad;

  private MotorSugerencia motor;
  private List<Prenda> prendas;


  public Usuario(Integer edad,List<Prenda> prendas, MotorSugerencia motor) {
    this.edad = edad;
    this.prendas = prendas;
    this.motor = motor;

  }
  public List<Prenda> getPrendas() {
    return prendas;
  }
  public Integer getEdad() {
    return edad;
  }


  public MotorSugerencia getMotor() {
    return motor;
  }

  public List<Atuendo> recibirSugerenciasDeAtuendos(){
    return this.motor.generarSugerencias(this);
  }

}
