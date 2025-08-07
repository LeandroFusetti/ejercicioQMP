package ar.edu.utn.frba.dds.usuario;

import ar.edu.utn.frba.dds.sistemaMeteorologico.AlertaMeteorologica;

import java.util.List;

public interface AccionConfigurable {
  public void anteNuevasAlertas(Usuario usuario, List<AlertaMeteorologica> alertas);

}
