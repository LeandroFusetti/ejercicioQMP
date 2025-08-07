package ar.edu.utn.frba.dds.serviciosExternos;

import ar.edu.utn.frba.dds.usuario.Usuario;

public class CorreoAdapter implements Correo {
  private MailSender mailSender;

  public CorreoAdapter(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void enviarCorreo(Usuario usuario, String mensaje) {
    mailSender.sendMail(usuario.getMail(), mensaje);
  }
}
