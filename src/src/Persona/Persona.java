package Persona;

import java.util.Date;

public abstract class Persona {
    private String dni;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;

    public Persona(String dni, String nombre, String apellido, Date fechaNacimiento){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void ObtenerNombreCompleto(){
        System.out.println("Nombre completo: " + nombre + " " + apellido);
    }

    public String getNombre(){
        return nombre;
    }
}