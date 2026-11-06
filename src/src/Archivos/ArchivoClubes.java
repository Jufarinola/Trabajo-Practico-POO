package Archivos;

import Club.Club;
import java.io.*;

public class ArchivoClubes {

    private static final String RUTA = "clubes.txt";

    // GUARDAR CLUBES
    public void guardarClubes(java.util.List<Club> clubes) {

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(RUTA))) {

            for (Club club : clubes) {

                escritor.write(club.getNombre()  + ";" + club.getPuntos());
                escritor.newLine();
            }

            System.out.println("Clubes guardados correctamente");

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar clubes: " + e.getMessage()
            );
        }
    }

    // LEER CLUBES
    public String leerClubes() {

        File archivo = new File(RUTA);

        if (!archivo.exists()) {
            return "No hay clubes guardados";
        }

        StringBuilder contenido = new StringBuilder();

        try (BufferedReader lector = new BufferedReader(
                new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

        } catch (IOException e) {

            return "Error al leer clubes: " + e.getMessage();
        }

        if (contenido.length() == 0) {
            return "No hay clubes guardados";
        }

        return contenido.toString();
    }
}