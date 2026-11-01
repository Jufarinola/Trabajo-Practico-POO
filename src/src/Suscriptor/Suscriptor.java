package Suscriptor;

import Club.Club;
import Notificaciones.CanalNotificaciones;
import java.util.ArrayList;
import java.util.List;


public class Suscriptor {

    private String nombre;
    private List<CanalNotificaciones> canales;
    private List<Club> clubesSeguidos;

    public Suscriptor(String nombre) {
        this.nombre = nombre;
        this.canales = new ArrayList<>();
        this.clubesSeguidos = new ArrayList<>();
    }

    public void seguirClub(Club club) {
        if (!clubesSeguidos.contains(club)) {
            clubesSeguidos.add(club);
        }
    }

    public void dejarDeSeguirClub(Club club) {
        clubesSeguidos.remove(club);
    }

    public void habilitarCanal(CanalNotificaciones canal) {
        if (!canales.contains(canal)) {
            canales.add(canal);
        }
    }

    public void deshabilitarCanal(CanalNotificaciones canal) {
        canales.remove(canal);
    }

    public void recibirNotificacion(String mensaje) {
        for (CanalNotificaciones canal : canales) {
            canal.enviar(mensaje);
        }
    }
}
