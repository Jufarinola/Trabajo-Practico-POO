
package Estadisticas;

import Club.Club;
import Evento.Evento;
import Evento.Gol;
import Partido.Partido;
import Partido.EstadoPartido;
import Persona.Jugador;
import Torneo.Torneo;

import java.util.*;

public class GeneradorEstadisticas {

    public Map<Club, Integer> obtenerTablaPosiciones(Torneo torneo) {

        Map<Club, Integer> puntos = new HashMap<>();

        for (Club club : torneo.obtenerClubes()) {
            puntos.put(club, 0);
        }

        for (Partido partido : torneo.obtenerPartidos()) {

            if (partido.getEstado() != EstadoPartido.FINALIZADO) {
                continue;
            }

            Club local = partido.getClubLocal();
            Club visitante = partido.getClubVisitante();

            int golesLocal = partido.getGolesLocal();
            int golesVisitante = partido.getGolesVisitante();

            if (golesLocal > golesVisitante) {
                puntos.put(local, puntos.get(local) + 3);

            } else if (golesVisitante > golesLocal) {
                puntos.put(visitante, puntos.get(visitante) + 3);

            } else {
                puntos.put(local, puntos.get(local) + 1);
                puntos.put(visitante, puntos.get(visitante) + 1);
            }
        }

        List<Map.Entry<Club, Integer>> lista =
                new ArrayList<>(puntos.entrySet());

        lista.sort((a, b) ->
                Integer.compare(b.getValue(), a.getValue()));

        Map<Club, Integer> tabla = new LinkedHashMap<>();

        for (Map.Entry<Club, Integer> entrada : lista) {
            tabla.put(entrada.getKey(), entrada.getValue());
        }

        return tabla;
    }

    public Map<Jugador, Integer> obtenerGolesPorJugador(Torneo torneo) {

        Map<Jugador, Integer> goles = new HashMap<>();

        for (Partido partido : torneo.obtenerPartidos()) {

            if (partido.getEstado() != EstadoPartido.FINALIZADO) {
                continue;
            }

            for (Evento evento : partido.getEventos()) {

                if (evento instanceof Gol) {

                    Jugador jugador = evento.getJugador();

                    goles.put(
                            jugador,
                            goles.getOrDefault(jugador, 0) + 1
                    );
                }
            }
        }

        List<Map.Entry<Jugador, Integer>> lista =
                new ArrayList<>(goles.entrySet());

        lista.sort((a, b) ->
                Integer.compare(b.getValue(), a.getValue()));

        Map<Jugador, Integer> ranking = new LinkedHashMap<>();

        for (Map.Entry<Jugador, Integer> entrada : lista) {
            ranking.put(entrada.getKey(), entrada.getValue());
        }

        return ranking;
    }

    public void generarTablaPosiciones(Torneo torneo) {

        obtenerTablaPosiciones(torneo).forEach((club, puntos) ->
                System.out.println(
                        club.getNombre() + ": " + puntos + " puntos"
                )
        );
    }

    public List<Jugador> obtenerGoleadores(Torneo torneo) {

        return new ArrayList<>(
                obtenerGolesPorJugador(torneo).keySet()
        );
    }
}

