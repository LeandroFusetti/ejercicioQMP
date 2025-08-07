package ar.edu.utn.frba.dds.usuario;

import ar.edu.utn.frba.dds.sistemaMeteorologico.AlertaMeteorologica;

import java.util.List;

public class RecalcularSugerencias implements AccionConfigurable{
  @Override
  public void anteNuevasAlertas(Usuario usuario, List<AlertaMeteorologica> alertas) {
    usuario.obtenerSugerenciaDiaria();
  }
}
