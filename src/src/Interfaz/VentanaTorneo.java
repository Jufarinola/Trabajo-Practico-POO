
package Interfaz;

import javax.swing.*;
import java.awt.*;

public class VentanaTorneo extends JFrame {

    public VentanaTorneo() {

        // COLORES
        Color fondo = new Color(10, 33, 60);
        Color panel = new Color(16, 43, 72);
        Color azul = new Color(37, 99, 235);
        Color gris = new Color(148, 163, 184);

        // VENTANA
        setTitle("FutbolStats - Gestion de Torneos");
        setSize(900, 600);
        setMinimumSize(new Dimension(650, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(fondo);
        setContentPane(principal);

        // ENCABEZADO
        JPanel encabezado = new JPanel();
        encabezado.setLayout(
                new BoxLayout(encabezado, BoxLayout.Y_AXIS)
        );
        encabezado.setBackground(fondo);
        encabezado.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 15, 25)
        );

        JLabel nombre = new JLabel("FUTBOLSTATS");
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 25));
        nombre.setForeground(Color.WHITE);
        nombre.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descripcion = new JLabel(
                "Panel de gestion y estadisticas deportivas"
        );
        descripcion.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );
        descripcion.setForeground(gris);
        descripcion.setAlignmentX(Component.LEFT_ALIGNMENT);

        encabezado.add(nombre);
        encabezado.add(Box.createVerticalStrut(5));
        encabezado.add(descripcion);

        principal.add(encabezado, BorderLayout.NORTH);

        // PESTAÑAS
        JTabbedPane pestanias = new JTabbedPane();

        pestanias.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        pestanias.setBackground(panel);
        pestanias.setForeground(Color.WHITE);

        pestanias.setFocusable(false);

        // AGREGAR LOS PANELES ORIGINALES
        pestanias.addTab("Torneo", new PanelTorneo());
        pestanias.addTab("Jugadores", new PanelJugadores());
        pestanias.addTab("Partidos", new PanelPartidos());

        // COLORES DE LAS PESTAÑAS
        for (int i = 0; i < pestanias.getTabCount(); i++) {
            pestanias.setBackgroundAt(i, panel);
            pestanias.setForegroundAt(i, Color.WHITE);
        }

        pestanias.setSelectedIndex(0);

        // CONTENEDOR CENTRAL
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(fondo);
        centro.setBorder(
                BorderFactory.createEmptyBorder(0, 25, 15, 25)
        );

        centro.add(pestanias, BorderLayout.CENTER);
        principal.add(centro, BorderLayout.CENTER);

        // PIE DE PAGINA
        JPanel pie = new JPanel(new BorderLayout());
        pie.setBackground(fondo);
        pie.setBorder(
                BorderFactory.createEmptyBorder(5, 25, 15, 25)
        );

        JLabel copyright = new JLabel("FutbolStats 2026");
        copyright.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );
        copyright.setForeground(gris);

        JLabel estado = new JLabel("Sistema activo");
        estado.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );
        estado.setForeground(new Color(74, 222, 128));

        pie.add(copyright, BorderLayout.WEST);
        pie.add(estado, BorderLayout.EAST);

        principal.add(pie, BorderLayout.SOUTH);
    }
}
