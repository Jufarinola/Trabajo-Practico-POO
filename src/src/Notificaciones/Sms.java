package Notificaciones;

public class Sms implements CanalNotificaciones {

    @Override
    public void enviar(String mensaje) {
        System.out.println("SMS enviado: " + mensaje);
    }
}
