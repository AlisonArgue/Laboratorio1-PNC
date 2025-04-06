package org.example.utils;

import javax.swing.*;

public class BotonRandom {
    public static void mostrarVentana() {
        JFrame frame = new JFrame("Ventanita");
        JButton boton = new JButton("Mundo Salva Vidas");

        boton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Feliz día del progreso");
            frame.dispose();
        });

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(null);

        int botonAncho = 160;
        int botonAlto = 30;
        boton.setBounds((frame.getWidth() - botonAncho) / 2, (frame.getHeight() - botonAlto) / 2 - 10, botonAncho, botonAlto);

        frame.add(boton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}