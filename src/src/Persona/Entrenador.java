package Persona;

import Club.Club;

import java.util.Date;

public class Entrenador extends Persona {
    private String especialidad;
    private String licencia;
    private Club club;

    public Entrenador(String dni, String nombre, String apellido, Date fechaNacimiento, String especialidad, String licencia){
        super(dni, nombre, apellido, fechaNacimiento);
        this.especialidad = especialidad;
        this.licencia = licencia;
    }

    public void ActualizarLicencia(String licenciaNueva){
        licencia = licenciaNueva;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Club getClub() {
        return club;
    }
}
