package Main;

import Archivos.ArchivoClubes;
import Archivos.ArchivoJugadores;
import Archivos.ArchivoPartidos;
import Club.Club;
import Contrato.Contrato;
import Estadisticas.GeneradorEstadisticas;
import Evento.Gol;
import Interfaz.VentanaPrincipal;
import Partido.Partido;
import Persona.Arbitro;
import Persona.Jugador;
import Torneo.Torneo;

import javax.swing.SwingUtilities;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Ejemplos de prueba. Se reemplazan los TXT al ejecutar.
        Club boca = new Club("Boca Juniors", "1905", "Buenos Aires", "3");
        Club river = new Club("River Plate", "1901", "Buenos Aires", "1");
        Club racing = new Club("Racing Club", "1903", "Avellaneda", "1");
        List<Club> clubes = Arrays.asList(boca, river, racing);

        Date nacimiento = new Date(); // Fecha de ejemplo
        Jugador martin = new Jugador("10000001", "Martin", "Perez", nacimiento,
                "Delantero", "9", 1000000.0, 2,5);
        Jugador juan = new Jugador("10000002", "Juan", "Gomez", nacimiento,
                "Delantero", "10", 900000.0, 3,2);
        Jugador lucas = new Jugador("10000003", "Lucas", "Lopez", nacimiento,
                "Delantero", "11", 800000.0, 1,3);
        Jugador tomas = new Jugador("10000004", "Tomas", "Diaz", nacimiento,
                "Mediocampista", "8", 700000.0, 9,1);
        List<Jugador> jugadores = Arrays.asList(martin, juan, lucas, tomas);

        // Los contratos se vinculan al jugador y al club.
        contratar(martin, boca);
        contratar(juan, river);
        contratar(lucas, racing);
        contratar(tomas, boca);

        Torneo torneo = new Torneo("Liga FutbolStats 2026",
                LocalDate.of(2026, 1, 1), LocalDate.of(2026, 12, 31));
        for (Club club : clubes) {
            torneo.inscribirClub(club);
        }

        Arbitro principal = new Arbitro("20000001", "Carlos", "Ruiz", nacimiento, "Primera", "AFA");
        Arbitro asistente1 = new Arbitro("20000002", "Pedro", "Sosa", nacimiento, "Primera", "AFA");
        Arbitro asistente2 = new Arbitro("20000003", "Diego", "Vega", nacimiento, "Primera", "AFA");

        Partido partido1 = new Partido(LocalDateTime.of(2026, 10, 10, 18, 0),
                boca, river, principal, asistente1, asistente2, torneo);
        Partido partido2 = new Partido(LocalDateTime.of(2026, 10, 17, 18, 0),
                river, racing, principal, asistente1, asistente2, torneo);

        torneo.programarPartido(partido1);
        torneo.programarPartido(partido2);
        torneo.iniciarTorneo();

        partido1.iniciarPartido();
        partido1.registrarEvento(new Gol(15, "Jugada", martin, boca, partido1));
        partido1.registrarEvento(new Gol(34, "Jugada", juan, river, partido1));
        partido1.registrarEvento(new Gol(72, "Penal", martin, boca, partido1));
        partido1.finalizarPartido();

        partido2.iniciarPartido();
        partido2.registrarEvento(new Gol(28, "Jugada", juan, river, partido2));
        partido2.registrarEvento(new Gol(80, "Jugada", lucas, racing, partido2));
        partido2.finalizarPartido();

        List<Partido> partidos = new ArrayList<>();

        partidos.add(partido1);
        partidos.add(partido2);
        

        new ArchivoClubes().guardarClubes(clubes);
        new ArchivoJugadores().guardarJugadores(jugadores);

        // Este archivo agrega los resultados al final: lo vaciamos para no duplicar partidas.
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("resultados.txt"))) {
            // Se limpia el contenido anterior.
        } catch (IOException e) {
            System.out.println("No se pudo preparar resultados.txt: " + e.getMessage());
        }
        ArchivoPartidos archivoPartidos = new ArchivoPartidos();
        archivoPartidos.guardarPartidos(partidos);

        GeneradorEstadisticas generador = new GeneradorEstadisticas();
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("estadisticas.txt"))) {
            escritor.write("TORNEO: " + torneo.getNombre());
            escritor.newLine();
            escritor.newLine();
            escritor.write("===== TABLA DE POSICIONES =====");
            escritor.newLine();
            int posicion = 1;
            for (Map.Entry<Club, Integer> entrada : generador.obtenerTablaPosiciones(torneo).entrySet()) {
                escritor.write(posicion++ + ". " + entrada.getKey().getNombre()
                        + " - " + entrada.getValue() + " puntos");
                escritor.newLine();
            }
            escritor.newLine();
            escritor.write("===== TABLA DE GOLEADORES =====");
            escritor.newLine();
            posicion = 1;
            for (Map.Entry<Jugador, Integer> entrada : generador.obtenerGolesPorJugador(torneo).entrySet()) {
                escritor.write(posicion++ + ". " + entrada.getKey().getNombre()
                        + " - " + entrada.getValue() + " goles");
                escritor.newLine();
            }
            System.out.println("Estadisticas guardadas correctamente");
        } catch (IOException e) {
            System.out.println("Error al guardar estadisticas: " + e.getMessage());
        }

        // Mostrar la interfaz existente.
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal.main();
        });

    }

    private static void contratar(Jugador jugador, Club club) {
        Contrato contrato = new Contrato(new Date(0), new Date(4102444800000L), club, jugador);
        jugador.agregarContrato(contrato);
        club.agregarContrato(contrato);
    }
}
