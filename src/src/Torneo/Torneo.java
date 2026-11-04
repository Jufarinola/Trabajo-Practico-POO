package Torneo;

import Club.Club;
import Partido.Partido;
import Partido.EstadoPartido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Torneo {

    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoTorneo estado;

    private List<Club> clubes;
    private List<Partido> partidos;

    public Torneo(String nombre,
                  LocalDate fechaInicio,
                  LocalDate fechaFin) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El torneo debe tener un nombre"
            );
        }

        if (fechaInicio == null || fechaFin == null
                || fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException(
                    "Las fechas del torneo son invalidas"
            );
        }

        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = EstadoTorneo.PROGRAMADO;

        this.clubes = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public void inscribirClub(Club club) {
        if (estado != EstadoTorneo.PROGRAMADO) {
            throw new IllegalStateException(
                    "La inscripcion del torneo esta cerrada"
            );
        }

        if (club == null) {
            throw new IllegalArgumentException("Club nulo");
        }

        if (clubes.contains(club)) {
            throw new IllegalArgumentException(
                    "El club ya esta inscripto"
            );
        }

        clubes.add(club);
    }

    public void eliminarClub(Club club) {
        if (estado != EstadoTorneo.PROGRAMADO) {
            throw new IllegalStateException(
                    "No se pueden eliminar clubes con el torneo iniciado"
            );
        }

        if (!clubes.contains(club)) {
            throw new IllegalArgumentException(
                    "El club no esta inscripto"
            );
        }

        for (Partido partido : partidos) {
            if (partido.getClubLocal() == club
                    || partido.getClubVisitante() == club) {
                throw new IllegalStateException(
                        "El club tiene partidos programados"
                );
            }
        }

        // Se elimina su inscripcion, no el objeto Club.
        clubes.remove(club);
    }

    public void programarPartido(Partido partido) {
        if (estado == EstadoTorneo.FINALIZADO) {
            throw new IllegalStateException(
                    "El torneo ya finalizo"
            );
        }

        if (partido == null) {
            throw new IllegalArgumentException("Partido nulo");
        }

        if (partido.getTorneo() != this) {
            throw new IllegalArgumentException(
                    "El partido pertenece a otro torneo"
            );
        }

        if (partidos.contains(partido)) {
            throw new IllegalArgumentException(
                    "El partido ya esta programado"
            );
        }

        if (partido.getEstado() != EstadoPartido.PROGRAMADO) {
            throw new IllegalStateException(
                    "El partido debe estar en estado PROGRAMADO"
            );
        }

        if (!clubes.contains(partido.getClubLocal())
                || !clubes.contains(partido.getClubVisitante())) {
            throw new IllegalArgumentException(
                    "Ambos clubes deben estar inscriptos en el torneo"
            );
        }

        LocalDate fechaPartido = partido.getFechaHora().toLocalDate();

        if (fechaPartido.isBefore(fechaInicio)
                || fechaPartido.isAfter(fechaFin)) {
            throw new IllegalArgumentException(
                    "La fecha del partido queda fuera del torneo"
            );
        }

        partidos.add(partido);
    }

    public void iniciarTorneo() {
        if (estado != EstadoTorneo.PROGRAMADO) {
            throw new IllegalStateException(
                    "El torneo no esta en estado PROGRAMADO"
            );
        }

        if (clubes.size() < 2) {
            throw new IllegalStateException(
                    "Se necesitan al menos dos clubes"
            );
        }

        estado = EstadoTorneo.EN_CURSO;
    }

    public void finalizarTorneo() {
        if (estado != EstadoTorneo.EN_CURSO) {
            throw new IllegalStateException(
                    "El torneo no esta en curso"
            );
        }

        for (Partido partido : partidos) {
            if (partido.getEstado() != EstadoPartido.FINALIZADO) {
                throw new IllegalStateException(
                        "Todavia hay partidos sin finalizar"
                );
            }
        }

        estado = EstadoTorneo.FINALIZADO;
    }

    public List<Partido> obtenerPartidos() {
        return new ArrayList<>(partidos);
    }

    public List<Club> obtenerClubes() {
        return new ArrayList<>(clubes);
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public EstadoTorneo getEstado() {
        return estado;
    }
}