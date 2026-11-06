
package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PanelPartidos extends JPanel {

    private JPanel listaPartidos;

    public PanelPartidos() {

        setLayout(new BorderLayout());
        setBackground(new Color(10, 33, 60));

        // Título principal
        JLabel titulo = new JLabel("Partidos recientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        add(titulo, BorderLayout.NORTH);

        // Contenedor de las tarjetas
        listaPartidos = new JPanel();
        listaPartidos.setLayout(
                new BoxLayout(listaPartidos, BoxLayout.Y_AXIS)
        );
        listaPartidos.setBackground(new Color(10, 33, 60));

        // Barra de desplazamiento
        JScrollPane scroll = new JScrollPane(listaPartidos);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

        // Leer los partidos del archivo TXT
        leerResultados();
    }

    // LEER RESULTADOS DEL ARCHIVO
    private void leerResultados() {

        try (BufferedReader lector = new BufferedReader(
                new FileReader("resultados.txt"))) {

            String linea;

            // Saltamos los encabezados
            lector.readLine();

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";", -1);

                if (datos.length != 7) {
                    continue;
                }

                // Datos principales
                String local = datos[1].trim();
                String visitante = datos[2].trim();
                String fecha = datos[3].trim();

                int golesLocal = Integer.parseInt(datos[4].trim());
                int golesVisitante = Integer.parseInt(datos[5].trim());

                StringBuilder goleadoresLocal = new StringBuilder();
                StringBuilder goleadoresVisitante = new StringBuilder();

                // Leer eventos
                String[] eventos = datos[6].split("\\|");

                for (String evento : eventos) {

                    String[] partes = evento.split(":");

                    if (partes.length < 3) {
                        continue;
                    }

                    String tipo = partes[0].trim();

                    if (!tipo.equalsIgnoreCase("Gol")) {
                        continue;
                    }

                    String jugador = partes[1].trim();
                    String club = "";
                    String minuto;

                    if (partes.length == 4) {
                        club = partes[2].trim();
                        minuto = partes[3].trim();
                    } else {
                        minuto = partes[2].trim();
                    }

                    String descripcion = jugador + " " + minuto + "'";

                    // Asociar gol con el equipo
                    if (club.equalsIgnoreCase(local)) {

                        if (goleadoresLocal.length() > 0) {
                            goleadoresLocal.append(", ");
                        }

                        goleadoresLocal.append(descripcion);

                    } else if (club.equalsIgnoreCase(visitante)) {

                        if (goleadoresVisitante.length() > 0) {
                            goleadoresVisitante.append(", ");
                        }

                        goleadoresVisitante.append(descripcion);
                    }
                }

                // Crear la tarjeta
                agregarPartido(
                        local,
                        visitante,
                        golesLocal,
                        golesVisitante,
                        fecha,
                        goleadoresLocal.toString(),
                        goleadoresVisitante.toString(),
                        "Estadio no registrado"
                );
            }

        } catch (IOException | NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al leer resultados: " + e.getMessage()
            );
        }
    }

    // CREAR TARJETA DE PARTIDO
    public void agregarPartido(
            String local,
            String visitante,
            int golesLocal,
            int golesVisitante,
            String fecha,
            String goleadoresLocal,
            String goleadoresVisitante,
            String estadio) {

        // Tarjeta
        JPanel tarjeta = new JPanel(new BorderLayout(10, 10));

        tarjeta.setBackground(new Color(235, 242, 250));

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        tarjeta.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 190)
        );

        tarjeta.setPreferredSize(new Dimension(450, 190));

        // Fecha
        JLabel fechaPartido = new JLabel(
                fecha, SwingConstants.CENTER
        );

        fechaPartido.setFont(new Font("Arial", Font.PLAIN, 13));

        tarjeta.add(fechaPartido, BorderLayout.NORTH);

        // Zona central
        JPanel centro = new JPanel(new GridLayout(1, 3, 10, 0));
        centro.setOpaque(false);

        // Equipo local
        JLabel equipoLocal = new JLabel(
                local, SwingConstants.CENTER
        );

        equipoLocal.setFont(new Font("Arial", Font.BOLD, 15));

        // Resultado
        JLabel resultado = new JLabel(
                golesLocal + " - " + golesVisitante,
                SwingConstants.CENTER
        );

        resultado.setFont(new Font("Arial", Font.BOLD, 30));
        resultado.setForeground(new Color(10, 33, 60));

        // Equipo visitante
        JLabel equipoVisitante = new JLabel(
                visitante, SwingConstants.CENTER
        );

        equipoVisitante.setFont(new Font("Arial", Font.BOLD, 15));

        centro.add(equipoLocal);
        centro.add(resultado);
        centro.add(equipoVisitante);

        tarjeta.add(centro, BorderLayout.CENTER);

        // Información inferior
        JPanel informacion = new JPanel(
                new GridLayout(3, 1, 0, 5)
        );

        informacion.setOpaque(false);

        JLabel golesL = new JLabel(
                "Local: " + goleadoresLocal
        );

        JLabel golesV = new JLabel(
                "Visitante: " + goleadoresVisitante
        );

        JLabel lugar = new JLabel(
                "Estadio: " + estadio
        );

        golesL.setFont(new Font("Arial", Font.PLAIN, 12));
        golesV.setFont(new Font("Arial", Font.PLAIN, 12));
        lugar.setFont(new Font("Arial", Font.PLAIN, 12));

        informacion.add(golesL);
        informacion.add(golesV);
        informacion.add(lugar);

        tarjeta.add(informacion, BorderLayout.SOUTH);

        // Agregar tarjeta a la lista
        listaPartidos.add(tarjeta);
        listaPartidos.add(Box.createVerticalStrut(10));

        listaPartidos.revalidate();
        listaPartidos.repaint();
    }
}
