package Evento;

import Club.Club;
import Persona.Jugador;

public class Gol extends Evento {

    private String tipoGol;
    private Club club;

    public Gol(int minuto, String tipoGol,
               Jugador jugador, Club club) {

        super(minuto,jugador);
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