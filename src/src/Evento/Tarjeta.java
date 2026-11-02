package Evento;

import Persona.Jugador;

public class Tarjeta extends Evento {

    private String color;

    public Tarjeta(int minuto, Jugador jugador, String color) {
        super(minuto, jugador);
        this.color = color;
    }

    @Override
    public String obtenerDescripcion() {
        return "Minuto " + getMinuto()
                + " - Tarjeta " + color
                + " para " + getJugador().getNombre();
    }
}