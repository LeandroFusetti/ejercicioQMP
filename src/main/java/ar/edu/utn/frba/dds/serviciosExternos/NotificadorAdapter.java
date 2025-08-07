package ar.edu.utn.frba.dds.serviciosExternos;

public class NotificadorAdapter implements Notificador {
  NotificadorSender notificadorSender;

  public NotificadorAdapter(NotificadorSender notificadorSender) {
    this.notificadorSender = notificadorSender;
  }

  @Override
  public void notificar(String mensaje) {
    this.notificadorSender.notify(mensaje);
    System.out.println(mensaje);
  }


}
