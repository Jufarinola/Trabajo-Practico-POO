package Evento;

import Club.Club;
import Partido.Partido;
import Persona.Jugador;

public class Gol extends Evento {

    private String tipoGol;
    private Club club;

    public Gol(int minuto, Jugador jugador, Partido partido) {
        super(minuto,jugador,partido);
        this.tipoGol = tipoGol;
        this.club = club;
    }

    public Club getClub() {
        return club;
    }

    @Override
    public String obtenerDescripcion() {
        return "Minuto " + getMinuto()
                + " - Gol de " + getJugador().getNombre()
                + " (" + club.getNombre() + ")"
                + " - Tipo: " + tipoGol;
    }
}