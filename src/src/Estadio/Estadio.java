package Estadio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Estadio {

    private String nombre;
    private int capacidad;
    private String direccion;
    private String climaHabitual;
    private String condicionesTerreno;

    public Estadio(String nombre,
                   int capacidad,
                   String direccion,
                   String climaHabitual,
                   String condicionesTerreno) {

        this.nombre = nombre;
        this.capacidad = capacidad;
        this.direccion = direccion;
        this.climaHabitual = climaHabitual;
        this.condicionesTerreno = condicionesTerreno;
    }

    public boolean consultarDisponibilidad(LocalDate fecha) {
        // Requiere consultar los partidos programados
        // para determinar si el estadio está disponible.
        throw new UnsupportedOperationException(
                "Pendiente de implementar la consulta de disponibilidad"
        );
    }
}