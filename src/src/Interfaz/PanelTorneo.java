package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class PanelTorneo extends JPanel {

    public PanelTorneo() {

        setLayout(new BorderLayout());
        setBackground(new Color(10, 33, 60));

        //Columna der la tabla
        String[] columnas = {"Club", "Pts"};

        //Leer los datos del archivo
        Object[][] datos = leerEstadisticas();

        // Creamos la tabla
        JTable tablaTorneo = new JTable(datos,columnas);

        tablaTorneo.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaTorneo.setRowHeight(30);
        tablaTorneo.setBackground(new Color(3, 0, 53));
        tablaTorneo.setForeground(Color.WHITE);
        tablaTorneo.setGridColor(Color.GRAY);

        // Color de encabezados
        tablaTorneo.getTableHeader().setBackground(new Color(0, 120, 215));
        tablaTorneo.getTableHeader().setForeground(Color.WHITE);

        // Agregar barra de desplazamiento
        JScrollPane scroll = new JScrollPane(tablaTorneo);

        // Agregar tabla directamente a este panel
        add(scroll, BorderLayout.CENTER);
    }

    //Metodo para leer el archivo
    private Object[][] leerEstadisticas() {

        ArrayList<Object[]> clubes = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(
                new FileReader("clubes.txt"))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos.length == 2) {

                    String nombreClub = datos[0].trim();
                    int puntos = Integer.parseInt(datos[1].trim());

                    clubes.add(new Object[]{
                            nombreClub, puntos
                    });
                }
            }

        } catch (IOException | NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al leer clubes: " + e.getMessage()
            );
        }

        return clubes.toArray(new Object[0][]);
    }
}