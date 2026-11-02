package Evento;


import Partido.Partido;
import Persona.Jugador;

public class Remate extends Evento {

    public Remate(int minuto, Jugador jugador, Partido partido) {
        super(minuto,jugador,partido);
    }

    @Override
    public String obtenerDescripcion() {
        return "Minuto " + getMinuto()
                + " - Remate de " + getJugador().getNombre();
    }


}