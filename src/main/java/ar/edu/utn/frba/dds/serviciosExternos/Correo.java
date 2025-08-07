package ar.edu.utn.frba.dds.serviciosExternos;

import ar.edu.utn.frba.dds.usuario.Usuario;

public interface Correo {

  public void enviarCorreo(Usuario usuario, String mensaje);

}
