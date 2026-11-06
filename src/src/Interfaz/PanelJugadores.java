package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class PanelJugadores extends JPanel {

    public PanelJugadores(){

        setLayout(new BorderLayout());
        setBackground(new Color(10, 33, 60));

        //Columna der la tabla
        String[] columnas = {"Nombre","Club", "Goles", "Numero", "Tarjetas"};

        //Leer los datos del archivo
        Object[][] datos = leerEstadisticas();

        // Creamos la tabla
        JTable tablaJugadores = new JTable(datos,columnas);

        tablaJugadores.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaJugadores.setRowHeight(30);
        tablaJugadores.setBackground(new Color(3, 0, 53));
        tablaJugadores.setForeground(Color.WHITE);
        tablaJugadores.setGridColor(Color.GRAY);

        // Color de encabezados
        tablaJugadores.getTableHeader().setBackground(new Color(0, 120, 215));
        tablaJugadores.getTableHeader().setForeground(Color.WHITE);

        // Agregar barra de desplazamiento
        JScrollPane scroll = new JScrollPane(tablaJugadores);

        // Agregar tabla directamente a este panel
        add(scroll, BorderLayout.CENTER);
    }

    private Object[][] leerEstadisticas() {

        ArrayList<Object[]> jugadores = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(
                new FileReader("jugadores.txt"))) {

            String linea;

            // Saltamos la primera línea (encabezados)
            lector.readLine();

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos.length == 5) {

                    String nombre = datos[0].trim();
                    String club = datos[1].trim();
                    Integer goles = Integer.parseInt(datos[2].trim());
                    String numero = datos[3].trim();
                    Integer tarjetas = Integer.parseInt(datos[4].trim());

                    jugadores.add(new Object[]{
                            nombre, club, goles, numero, tarjetas
                    });
                }
            }

        } catch (IOException | NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al leer jugadores: " + e.getMessage()
            );
        }

        return jugadores.toArray(new Object[0][]);
    }

}
