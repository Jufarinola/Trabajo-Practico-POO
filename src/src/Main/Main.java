
package Main;

import Club.Club;
import Evento.*;
import Partido.Partido;
import Persona.Arbitro;
import Persona.Jugador;
import Persistencia.ArchivoPartidos;

import java.time.LocalDateTime;
import java.time.LocalDate;
import Torneo.Torneo;
import Torneo.GestorTorneo;
import Estadio.Estadio;
import Estadisticas.GeneradorEstadisticas;
import Persistencia.ArchivoEstadisticas;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Club river = new Club(
                "River Plate",
                "1901",
                "Buenos Aires"
        );

        Club boca = new Club(
                "Boca Juniors",
                "1905",
                "Buenos Aires"
        );

        Jugador jugador1 = new Jugador(
                "11111111", "Julian", "Alvarez",
                new Date(), "Delantero", "9", 50000000.0
        );

        Jugador jugador2 = new Jugador(
                "22222222", "Miguel", "Borja",
                new Date(), "Delantero", "19", 10000000.0
        );

        Jugador jugador3 = new Jugador(
                "33333333", "Edinson", "Cavani",
                new Date(), "Delantero", "10", 5000000.0
        );

        Jugador jugador4 = new Jugador(
                "44444444", "Miguel", "Merentiel",
                new Date(), "Delantero", "16", 6000000.0
        );

        Arbitro arbitroPrincipal = new Arbitro(
                "55555555", "Dario", "Herrera",
                new Date(), "Internacional", "AFA"
        );

        Arbitro asistente1 = new Arbitro(
                "66666666", "Juan", "Perez",
                new Date(), "Asistente", "AFA"
        );

        Arbitro asistente2 = new Arbitro(
                "77777777", "Pedro", "Gomez",
                new Date(), "Asistente", "AFA"
        );

        Torneo torneo = new Torneo("Liga de demostracion",
                LocalDate.of(2026, 11, 1), LocalDate.of(2026, 12, 31));
        GestorTorneo gestor = new GestorTorneo();
        gestor.inscribirClub(torneo, river);
        gestor.inscribirClub(torneo, boca);
        gestor.inscribirClub(torneo, new Club("Racing", "1903", "Avellaneda"));
        gestor.inscribirClub(torneo, new Club("Independiente", "1905", "Avellaneda"));

        Partido partido = new Partido(
                LocalDateTime.of(2026, 11, 2, 20, 0),
                river,
                boca,
                arbitroPrincipal,
                asistente1,
                asistente2,
                torneo
        );

        partido.asignarEstadio(new Estadio("Estadio de demostracion", 10000,
                "Buenos Aires", "Templado", "Cesped natural"));
        gestor.programarPartido(torneo, partido);
        gestor.iniciarTorneo(torneo);
        System.out.println("INICIANDO PARTIDO...");
        partido.iniciarPartido();


        partido.registrarEvento(
                new Gol(15, "Normal", jugador1, river, partido)
        );

        partido.registrarEvento(
                new Remate(23, jugador3, partido)
        );

        partido.registrarEvento(
                new Falta(30, jugador4, partido)
        );

        partido.registrarEvento(
                new Tarjeta(35, jugador4, "Amarilla", partido)
        );

        partido.registrarEvento(
                new Gol(60, "Cabeza", jugador2, river, partido)
        );

        partido.registrarEvento(
                new Cambio(70, jugador1, jugador2, partido)
        );

        partido.registrarEvento(
                new Gol(85, "Penal", jugador3, boca, partido)
        );

        partido.finalizarPartido();

        System.out.println("\nPARTIDO FINALIZADO");

        System.out.println(
                "River Plate " + partido.obtenerMarcador()
                        + " Boca Juniors"
        );

        System.out.println("\nEVENTOS DEL PARTIDO:");

        for (Evento evento : partido.getEventos()) {
            System.out.println(evento.obtenerDescripcion());
        }

        ArchivoPartidos archivo = new ArchivoPartidos();

        archivo.guardarPartido(partido);

        System.out.println("\nRESULTADOS GUARDADOS:");

        archivo.leerPartidos();

        GeneradorEstadisticas generador = new GeneradorEstadisticas();
        generador.generarTablaPosiciones(torneo);
        ArchivoEstadisticas archivoEstadisticas = new ArchivoEstadisticas();
        archivoEstadisticas.guardarEstadisticas(torneo);
        archivoEstadisticas.leerEstadisticas();
        gestor.finalizarTorneo(torneo);
        System.out.println("Estado del torneo: " + torneo.getEstado());
    }
}
