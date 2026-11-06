package Interfaz;

import javax.swing.*;
import java.awt.*;

public class VentanaTorneo extends JFrame {

    public VentanaTorneo() {

        // Configuramos la ventana
        setTitle("Gestion Torneo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(10, 33, 60));

        // Creamos las pestañas
        JTabbedPane pestanias = new JTabbedPane();

        pestanias.setFont(new Font("Arial", Font.BOLD, 15));

        // Agregamos los paneles a las pestañas
        pestanias.addTab("Torneo", new PanelTorneo());
        pestanias.addTab("Jugadores", new PanelJugadores());
        pestanias.addTab("Partidos", new PanelPartidos());

        // Agregamos las pestañas a la ventana
        add(pestanias);

    }
}