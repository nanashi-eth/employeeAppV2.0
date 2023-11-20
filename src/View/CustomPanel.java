package View;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private static final int BORDER_RADIUS = 20;
    private static final int PADDING = 2;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Obtener dimensiones del panel
        int width = getWidth();
        int height = getHeight();

        
        g2d.setColor(this.getBackground().brighter());
        g2d.fillRoundRect(PADDING, PADDING, width - 2 * PADDING, height - 2 * PADDING, BORDER_RADIUS, BORDER_RADIUS);
    }

    // Constructor
    public CustomPanel() {
        // Puedes configurar otros aspectos del panel aquí si es necesario
        setOpaque(false); // Asegura que el fondo personalizado se renderice correctamente
    }
}
