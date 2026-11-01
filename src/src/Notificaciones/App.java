package Notificaciones;

public class App implements CanalNotificaciones {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Notificación App: " + mensaje);
    }
}
