package Evento;

import Partido.Partido;
import Persona.Jugador;

public class Falta extends Evento {

    public Falta(int minuto, Jugador jugador, Partido partido) {
        super(minuto,jugador,partido);
    }

    @Override
    public String obtenerDescripcion() {
        return "Falta cometida en el minuto " + getMinuto();
    }
}