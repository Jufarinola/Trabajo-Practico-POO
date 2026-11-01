package Evento;

import Club.Club;
import Persona.Jugador;

public class Gol extends Evento {

    private String tipoGol;
    private Jugador autor;
    private Club club;

    public Gol(int minuto, String tipoGol,
               Jugador autor, Club club) {

        super(minuto);
        this.tipoGol = tipoGol;
        this.autor = autor;
        this.club = club;
    }

    public Club getClub() {
        return club;
    }

    public Jugador getAutor() {
        return autor;
    }

    @Override
    public String obtenerDescripcion() {
        return "Gol de tipo " + tipoGol
                + " en el minuto " + getMinuto();
    }
}