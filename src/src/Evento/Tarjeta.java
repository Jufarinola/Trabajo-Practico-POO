package Evento;

import Partido.Partido;
import Persona.Jugador;

public class Tarjeta extends Evento {

    private String color;

    public Tarjeta(int minuto, Jugador jugador, Partido partido) {
        super(minuto,jugador,partido);
        this.color = color;
    }

    @Override
    public String obtenerDescripcion() {
        return "Minuto " + getMinuto()
                + " - Tarjeta " + color
                + " para " + getJugador().getNombre();
    }
}