package Persona;

import java.util.Date;

public class Arbitro extends Persona{
    private String categoria;
    private String colegio;

    public Arbitro(String dni, String nombre, String apellido, Date fechaNacimiento, String categoria, String colegio) {
        super(dni, nombre, apellido, fechaNacimiento);
        this.categoria = categoria;
        this.colegio = colegio;
    }

    public void ActualizarCategoria(String categoria_nueva){
        categoria = categoria_nueva;
    }
}
