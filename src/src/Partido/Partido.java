package Partido;

import Club.Club;
import Estadio.Estadio;
import Evento.Evento;
import Persona.Arbitro;
import Evento.Gol;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Partido {

    private LocalDateTime fechaHora;
    private EstadoPartido estado;

    private Club clubLocal;
    private Club clubVisitante;

    private int golesLocal;
    private int golesVisitante;

    private Arbitro arbitroPrincipal;
    private Arbitro arbitroAsistente1;
    private Arbitro arbitroAsistente2;

    private Estadio estadio;
    private List<Evento> eventos;

    public Partido(LocalDateTime fechaHora,
                   Club clubLocal,
                   Club clubVisitante,
                   Arbitro arbitroPrincipal,
                   Arbitro arbitroAsistente1,
                   Arbitro arbitroAsistente2) {

        if (clubLocal == null || clubVisitante == null
                || clubLocal == clubVisitante) {
            throw new IllegalArgumentException("Clubes invalidos");
        }

        this.fechaHora = fechaHora;
        this.clubLocal = clubLocal;
        this.clubVisitante = clubVisitante;

        this.arbitroPrincipal = arbitroPrincipal;
        this.arbitroAsistente1 = arbitroAsistente1;
        this.arbitroAsistente2 = arbitroAsistente2;

        this.estado = EstadoPartido.PROGRAMADO;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.eventos = new ArrayList<>();
    }

    public void iniciarPartido() {
        if (estado != EstadoPartido.PROGRAMADO) {
            throw new IllegalStateException("No se puede iniciar");
        }
        estado = EstadoPartido.EN_CURSO;
    }

    public void finalizarPartido() {
        if (estado != EstadoPartido.EN_CURSO) {
            throw new IllegalStateException("No se puede finalizar");
        }
        estado = EstadoPartido.FINALIZADO;
    }

    public void registrarEvento(Evento evento) {

        if (estado != EstadoPartido.EN_CURSO) {
            throw new IllegalStateException("El partido no esta en curso");
        }

        if (evento == null) {
            throw new IllegalArgumentException("Evento nulo");
        }

        if (eventos.contains(evento)) {
            throw new IllegalArgumentException("Evento ya registrado");
        }

        if (evento instanceof Gol) {
            Gol gol = (Gol) evento;

            if (gol.getClub() == clubLocal) {
                golesLocal++;
            } else if (gol.getClub() == clubVisitante) {
                golesVisitante++;
            } else {
                throw new IllegalArgumentException(
                        "El gol no corresponde a este partido"
                );
            }
        }

        eventos.add(evento);
    }

    public void asignarEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public String obtenerMarcador() {
        return golesLocal + " - " + golesVisitante;
    }

    public List<Evento> getEventos() {
        return new ArrayList<>(eventos);
    }

    public Estadio getEstadio() {
        return estadio;
    }
}