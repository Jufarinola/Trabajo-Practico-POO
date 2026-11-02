package Evento;

import Persona.Jugador;

public abstract class Evento {
    private int minuto;
    private Jugador jugador;

    public Evento(int minuto, Jugador jugador){
        this.minuto = minuto;
        this.jugador = jugador;
    }

    public int getMinuto() {
        return minuto;
    }

    public Jugador getJugador(){
        return jugador;
    }

    public abstract String obtenerDescripcion();
}

