package Notificaciones;

public class Email implements CanalNotificaciones{

    @Override
    public void enviar(String mensaje) {
        System.out.println("Email enviado: " + mensaje);
    }
}
