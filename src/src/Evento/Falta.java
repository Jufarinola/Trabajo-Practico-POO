package Evento;

import Persona.Jugador;

public class Falta extends Evento {

    public Falta(int minuto, Jugador jugador) {
        super(minuto,jugador);
    }

    @Override
    public String obtenerDescripcion() {
        return "Falta cometida en el minuto " + getMinuto();
    }
}