
package Persistencia;

import Estadisticas.GeneradorEstadisticas;
import Club.Club;
import Persona.Jugador;
import Torneo.Torneo;

import java.io.*;
import java.util.Map;

public class ArchivoEstadisticas {

    private GeneradorEstadisticas generador =
            new GeneradorEstadisticas();

    private String nombreArchivo = "estadisticas.txt";

    public void guardarEstadisticas(Torneo torneo) {

        try (BufferedWriter escritor =
                     new BufferedWriter(new FileWriter(nombreArchivo))) {

            escritor.write("TORNEO: " + torneo.getNombre());
            escritor.newLine();

            escritor.write("\n===== TABLA DE POSICIONES =====");
            escritor.newLine();

            Map<Club, Integer> tabla =
                    generador.obtenerTablaPosiciones(torneo);

            int posicion = 1;

            for (Map.Entry<Club, Integer> entrada : tabla.entrySet()) {

                escritor.write(
                        posicion + ". " +
                                entrada.getKey().getNombre() +
                                " - " + entrada.getValue() + " puntos"
                );

                escritor.newLine();
                posicion++;
            }

            escritor.write("\n===== TABLA DE GOLEADORES =====");
            escritor.newLine();

            Map<Jugador, Integer> goleadores =
                    generador.obtenerGolesPorJugador(torneo);

            posicion = 1;

            for (Map.Entry<Jugador, Integer> entrada : goleadores.entrySet()) {

                escritor.write(
                        posicion + ". " +
                                entrada.getKey().getNombre() +
                                " - " + entrada.getValue() + " goles"
                );

                escritor.newLine();
                posicion++;
            }

            System.out.println("Estadisticas guardadas correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar: " + e.getMessage()
            );
        }
    }

    public void leerEstadisticas() {

        try (BufferedReader lector =
                     new BufferedReader(new FileReader(nombreArchivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (FileNotFoundException e) {

            System.out.println("El archivo todavia no existe.");

        } catch (IOException e) {

            System.out.println(
                    "Error al leer: " + e.getMessage()
            );
        }
    }
}
