
package Main;

import Club.Club;
import Evento.*;
import Partido.Partido;
import Persona.Arbitro;
import Persona.Jugador;
import Persistencia.ArchivoPartidos;

import java.time.LocalDateTime;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // 1. CREAR CLUBES
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

        // 2. CREAR JUGADORES
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

        // 3. CREAR ARBITROS
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

        // 4. CREAR PARTIDO
        Partido partido = new Partido(
                LocalDateTime.of(2026, 11, 2, 20, 0),
                river,
                boca,
                arbitroPrincipal,
                asistente1,
                asistente2
        );

        System.out.println("INICIANDO PARTIDO...");
        partido.iniciarPartido();

        // 5. REGISTRAR EVENTOS

        partido.registrarEvento(
                new Gol(15, "Normal", jugador1, river)
        );

        partido.registrarEvento(
                new Remate(23, jugador3)
        );

        partido.registrarEvento(
                new Falta(30, jugador4)
        );

        partido.registrarEvento(
                new Tarjeta(35, jugador4, "Amarilla")
        );

        partido.registrarEvento(
                new Gol(60, "Cabeza", jugador2, river)
        );

        partido.registrarEvento(
                new Cambio(70, jugador1, jugador2)
        );

        partido.registrarEvento(
                new Gol(85, "Penal", jugador3, boca)
        );

        // 6. FINALIZAR PARTIDO
        partido.finalizarPartido();

        System.out.println("\nPARTIDO FINALIZADO");

        System.out.println(
                "River Plate " + partido.obtenerMarcador()
                        + " Boca Juniors"
        );

        // 7. MOSTRAR EVENTOS
        System.out.println("\nEVENTOS DEL PARTIDO:");

        for (Evento evento : partido.getEventos()) {
            System.out.println(evento.obtenerDescripcion());
        }

        // 8. GUARDAR EN ARCHIVO
        ArchivoPartidos archivo = new ArchivoPartidos();

        archivo.guardarPartido(partido);

        // 9. LEER ARCHIVO
        System.out.println("\nRESULTADOS GUARDADOS:");

        archivo.leerPartidos();
    }
}
