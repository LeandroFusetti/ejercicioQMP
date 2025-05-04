package ar.edu.utn.frba.dds.motorBusqueda;

import ar.edu.utn.frba.dds.prenda.Formalidad;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.usuario.Usuario;

import java.util.List;

public class MotorAtuendoInformalSenioresMayores extends MotorSugerencia {

  public MotorAtuendoInformalSenioresMayores() {
    super();
  }
  @Override
  public List<Prenda> filtrarSegunCriterio(Usuario usuario) {
    return usuario.getEdad()>55 ? usuario.getPrendas().stream().filter(p-> p.getFormalidad()!= Formalidad.INFORMAL).toList() : usuario.getPrendas();
  }
}

