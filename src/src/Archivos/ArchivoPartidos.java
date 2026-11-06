package Archivos;

import Partido.Partido;
import Evento.Evento;
import Evento.Gol;
import java.util.List;

import java.io.*;

public class ArchivoPartidos {

    private static final String RUTA = "resultados.txt";

    // GUARDAR PARTIDO
    public void guardarPartidos(List<Partido> partidos) {

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter("resultados.txt"))) {

            escritor.write(
                    "ID;LOCAL;VISITANTE;FECHA;GOLES_LOCAL;GOLES_VISITANTE;EVENTOS"
            );
            escritor.newLine();

            int id = 1;

            for (Partido partido : partidos) {

                StringBuilder eventos = new StringBuilder();

                for (Evento evento : partido.getEventos()) {

                    if (evento instanceof Gol) {

                        Gol gol = (Gol) evento;

                        if (eventos.length() > 0) {
                            eventos.append("|");
                        }

                        eventos.append("Gol:")
                                .append(gol.getJugador().getNombre())
                                .append(":")
                                .append(gol.getMinuto());
                    }
                }

                escritor.write(
                        id + ";" +
                                partido.getClubLocal().getNombre() + ";" +
                                partido.getClubVisitante().getNombre() + ";" +
                                partido.getFecha() + ";" +
                                partido.getGolesLocal() + ";" +
                                partido.getGolesVisitante() + ";" +
                                eventos
                );

                escritor.newLine();
                id++;
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // LEER PARTIDOS
    public void leerPartidos() {

        File archivo = new File(RUTA);

        if (!archivo.exists()) {
            System.out.println("No hay partidos guardados");
            return;
        }

        try (BufferedReader lector = new BufferedReader(
                new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println(
                    "Error al leer: " + e.getMessage()
            );
        }
    }
}
