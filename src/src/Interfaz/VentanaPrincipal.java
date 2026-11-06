
package Interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public static void main() {
        JFrame ventana = new JFrame("FutbolStats");
        ventana.setSize(500,400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);

        //Color de la ventanaPrincipal
        ventana.getContentPane().setBackground(new Color(10, 33, 60));

        //Titulo
        JLabel titulo = new JLabel("FutbolStats");
        titulo.setBounds(0, 30, 500, 50);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(new Color(255, 255, 255));
        titulo.setFont(new Font("impact", Font.BOLD, 30));
        ventana.add(titulo);

        //Creamos boton ingresar
        JButton botoningresar = new JButton("Ingresar");
        botoningresar.setBounds(150, 150, 200, 40);

        botoningresar.setBackground(new Color(255, 255, 255));
        botoningresar.setForeground(new Color(0, 0, 0));


        botoningresar.addActionListener(e -> {
            VentanaTorneo ventanaTorneo = new VentanaTorneo();
            ventanaTorneo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaTorneo.setVisible(true);
        });


        ventana.add(botoningresar);
        ventana.setVisible(true);
    }




}