package Evento;

import Partido.Partido;
import Persona.Jugador;

public class Tarjeta extends Evento {

    private String color;

    public Tarjeta(int minuto, Jugador jugador, String color, Partido partido) {
        super(minuto,jugador,partido);
        if (!"Amarilla".equalsIgnoreCase(color) && !"Roja".equalsIgnoreCase(color)) {
            throw new IllegalArgumentException("La tarjeta debe ser Amarilla o Roja");
        }
        this.color = color;
    }

    public String getColor() { return color; }

    @Override
    public String obtenerDescripcion() {
        return "Minuto " + getMinuto()
                + " - Tarjeta " + color
                + " para " + getJugador().getNombre();
    }
}
