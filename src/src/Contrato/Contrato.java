package Contrato;

import Club.Club;
import Persona.Jugador;

import java.util.Date;

public class Contrato {
    private Date fechaInicio;
    private Date fechaFin;
    private Jugador jugador;
    private Club club;

    public Contrato(Date fechaInicio, Date fechaFin, Club club, Jugador jugador){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jugador = jugador;
        this.club = club;
    }




    public boolean estaVigente() {
        Date hoy = new Date();

        return !hoy.before(fechaInicio)
                && !hoy.after(fechaFin);
    }

    public void finalizarContrato() {
        this.fechaFin = new Date();
    }

    public Club getClub() {
        return club;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
