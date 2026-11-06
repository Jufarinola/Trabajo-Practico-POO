
package Archivos;

import Persona.Jugador;
import Club.Club;

import java.io.*;
import java.util.List;

public class ArchivoJugadores {

    private static final String RUTA = "jugadores.txt";

    // GUARDAR TODOS LOS JUGADORES
    public void guardarJugadores(List<Jugador> jugadores) {

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(RUTA))) {

            escritor.write("NOMBRE;CLUB;GOLES;NUMERO;TARJETAS");
            escritor.newLine();

            for (Jugador jugador : jugadores) {

                Club club = jugador.ObtenerClubActual();

                String nombreClub;

                if (club != null) {
                    nombreClub = club.getNombre();
                } else {
                    nombreClub = "Sin club";
                }

                escritor.write(
                        jugador.getNombre() + " " + jugador.getApellido() +";" +
                                nombreClub + ";" +
                                jugador.getCantidadGoles() + ";" +
                                jugador.getNumeroCamiseta() + ";" +
                                jugador.getCantidadTarjetas()
                );

                escritor.newLine();
            }

            System.out.println("Jugadores guardados correctamente");

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar jugadores: " + e.getMessage()
            );
        }
    }

    // LEER JUGADORES
    public String leerJugadores() {

        File archivo = new File(RUTA);

        if (!archivo.exists()) {
            return "No hay jugadores guardados";
        }

        StringBuilder contenido = new StringBuilder();

        try (BufferedReader lector = new BufferedReader(
                new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

        } catch (IOException e) {

            return "Error al leer jugadores: " + e.getMessage();
        }

        return contenido.toString();
    }
}