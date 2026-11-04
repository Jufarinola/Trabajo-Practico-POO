package Evento;

import Persona.Jugador;
import Partido.Partido;

public abstract class Evento {
    private int minuto;
    private Jugador jugador;
    private Partido partido;

    public Evento(int minuto, Jugador jugador, Partido partido){
        if (minuto < 0 || minuto > 120) {
            throw new IllegalArgumentException("El minuto debe estar entre 0 y 120");
        }
        if (jugador == null || partido == null) {
            throw new IllegalArgumentException("Jugador y partido obligatorios");
        }
        this.minuto = minuto;
        this.jugador = jugador;
        this.partido = partido;
    }

    public int getMinuto() {
        return minuto;
    }

    public Jugador getJugador(){
        return jugador;
    }

    public Partido getPartido(){return partido;}

    public abstract String obtenerDescripcion();
}

