package Evento;

import Club.Club;
import Partido.Partido;
import Persona.Jugador;

public class Gol extends Evento {

    private String tipoGol;
    private Club club;

    public Gol(int minuto, String tipoGol, Jugador jugador, Club club, Partido partido) {
        super(minuto, jugador, partido);
        if (tipoGol == null || tipoGol.isBlank()) {
            throw new IllegalArgumentException("El tipo de gol es obligatorio");
        }
        if (club == null || (club != partido.getClubLocal() && club != partido.getClubVisitante())) {
            throw new IllegalArgumentException("El club del gol debe participar en el partido");
        }
        this.tipoGol = tipoGol;
        this.club = club;
    }

    public String getTipoGol() { return tipoGol; }

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
