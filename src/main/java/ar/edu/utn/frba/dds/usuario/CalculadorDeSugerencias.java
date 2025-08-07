package ar.edu.utn.frba.dds.usuario;

import java.util.ArrayList;
import java.util.List;

public class CalculadorDeSugerencias {
  List<Usuario> usuarios;

  public CalculadorDeSugerencias() {
    this.usuarios = new ArrayList<>();
  }

  public void agregarUsuario(Usuario usuario) {
    this.usuarios.add(usuario);
  }
  public void calcularSugerenciaDiaria() {
    this.usuarios.forEach(Usuario::obtenerSugerenciaDiaria);
  }

}
