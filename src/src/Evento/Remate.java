package Evento;


import Persona.Jugador;

public class Remate extends Evento {

    public Remate(int minuto, Jugador jugador) {
        super(minuto, jugador);
    }

    @Override
    public String obtenerDescripcion() {
        return "Minuto " + getMinuto()
                + " - Remate de " + getJugador().getNombre();
    }


}