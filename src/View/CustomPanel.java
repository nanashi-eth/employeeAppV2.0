package View;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obtener dimensiones del panel
        int width = getWidth();
        int height = getHeight();

        // Crear un rectángulo con bordes redondeados
        int borderRadius = 20; // Puedes ajustar este valor según tu preferencia
        g.setColor(Color.GRAY);
        g.fillRoundRect(10, 10, width - 10, height - 10, borderRadius, borderRadius);
    }

    // Constructor
    public CustomPanel() {
        // Puedes configurar otros aspectos del panel aquí si es necesario
        setOpaque(false); // Asegura que el fondo personalizado se renderice correctamente
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        JFrame frame = new JFrame("Ejemplo JPanel Personalizado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de tu JPanel personalizado
        CustomPanel panelPersonalizado = new CustomPanel();
        panelPersonalizado.setPreferredSize(new Dimension(300, 200)); // Ajusta las dimensiones según tu necesidad

        // Agregar el panel personalizado al marco
        frame.getContentPane().add(panelPersonalizado);

        // Configurar el marco
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
