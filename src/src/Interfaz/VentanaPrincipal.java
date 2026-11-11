
package Interfaz;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public static void main() {

        JFrame ventana = new JFrame("FutbolStats");

        ventana.setSize(550, 450);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        Color fondo = new Color(10, 33, 60);
        Color azul = new Color(37, 99, 235);
        Color gris = new Color(148, 163, 184);

        ventana.getContentPane().setBackground(fondo);

        // Encabezado
        JLabel encabezado = new JLabel("FUTBOLSTATS");
        encabezado.setBounds(30, 15, 250, 30);
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 15));
        encabezado.setForeground(Color.WHITE);
        ventana.add(encabezado);

        JLabel temporada = new JLabel("TEMPORADA 2026");
        temporada.setBounds(370, 15, 150, 30);
        temporada.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        temporada.setForeground(gris);
        ventana.add(temporada);

        // Titulo principal
        JLabel titulo = new JLabel("FutbolStats");
        titulo.setBounds(0, 100, 550, 55);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        titulo.setForeground(Color.WHITE);
        ventana.add(titulo);

        // Subtitulo
        JLabel subtitulo = new JLabel(
                "Sistema de gestion y estadisticas de futbol"
        );

        subtitulo.setBounds(0, 160, 550, 30);
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(gris);
        ventana.add(subtitulo);

        // Informacion
        JLabel informacion = new JLabel(
                "Torneos  |  Clubes  |  Jugadores  |  Partidos"
        );

        informacion.setBounds(0, 215, 550, 30);
        informacion.setHorizontalAlignment(SwingConstants.CENTER);
        informacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        informacion.setForeground(Color.WHITE);
        ventana.add(informacion);

        // Boton ingresar
        JButton botonIngresar = new JButton(
                "INGRESAR AL SISTEMA  >"
        );

        botonIngresar.setBounds(145, 280, 260, 48);
        botonIngresar.setBackground(azul);
        botonIngresar.setForeground(Color.WHITE);
        botonIngresar.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        botonIngresar.setFocusPainted(false);
        botonIngresar.setBorderPainted(false);
        botonIngresar.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        ventana.add(botonIngresar);

        // Efecto al pasar el mouse
        botonIngresar.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        botonIngresar.setBackground(
                                new Color(59, 130, 246)
                        );
                    }

                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        botonIngresar.setBackground(azul);
                    }
                }
        );

        // Abrir ventana del torneo
        botonIngresar.addActionListener(e -> {

            VentanaTorneo torneo = new VentanaTorneo();

            torneo.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            torneo.setVisible(true);
            ventana.dispose();
        });

        // Pie de pagina
        JLabel pie = new JLabel("FutbolStats 2026");

        pie.setBounds(0, 365, 550, 25);
        pie.setHorizontalAlignment(SwingConstants.CENTER);
        pie.setForeground(gris);
        pie.setFont(
                new Font("Segoe UI", Font.PLAIN, 11)
        );

        ventana.add(pie);

        ventana.setVisible(true);
    }
}
