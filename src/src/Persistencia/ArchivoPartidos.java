
package Persistencia;

import Partido.Partido;
import Evento.Evento;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class ArchivoPartidos {

    private static final Path RUTA =
            Path.of("resultados.txt");

    public void guardarPartido(Partido partido) {

        try (BufferedWriter escritor = Files.newBufferedWriter(
                RUTA,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            escritor.write("================================");
            escritor.newLine();

            escritor.write("PARTIDO: "
                    + partido.getClubLocal().getNombre()
                    + " vs "
                    + partido.getClubVisitante().getNombre());
            escritor.newLine();

            escritor.write("Fecha: " + partido.getFechaHora());
            escritor.newLine();

            escritor.write("RESULTADO: "
                    + partido.obtenerMarcador());
            escritor.newLine();

            escritor.write("EVENTOS:");
            escritor.newLine();

            for (Evento evento : partido.getEventos()) {
                escritor.write(evento.obtenerDescripcion());
                escritor.newLine();
            }

            escritor.write("================================");
            escritor.newLine();
            escritor.newLine();

            System.out.println("Partido guardado correctamente");

        } catch (IOException e) {
            System.out.println(
                    "Error al guardar: " + e.getMessage()
            );
        }
    }

    public void leerPartidos() {

        try {
            if (Files.notExists(RUTA)) {
                System.out.println("No hay partidos guardados");
                return;
            }

            List<String> lineas = Files.readAllLines(
                    RUTA, StandardCharsets.UTF_8);

            for (String linea : lineas) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println(
                    "Error al leer: " + e.getMessage()
            );
        }
    }
}
