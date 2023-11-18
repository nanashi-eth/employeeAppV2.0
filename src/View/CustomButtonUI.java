package View;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomButtonUI extends BasicButtonUI {
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 30;
    private static final int HOVER_LINE_HEIGHT = 2;
    private static final int BORDER_RADIUS = 20; // Ajusta según sea necesario

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Definir la forma redondeada del botón
        RoundRectangle2D shape = new RoundRectangle2D.Float(0, 0, c.getWidth(), c.getHeight(), BORDER_RADIUS, BORDER_RADIUS);

        if (model.isPressed()) {
            g2d.setColor(button.getBackground().brighter());
        } else if (model.isRollover()) {
            g2d.setColor(button.getBackground().darker());
        } else {
            g2d.setColor(button.getBackground());
        }

        g2d.fill(shape);
        super.paint(g2d, c);

        // Pintar línea de hover en la parte inferior
        if (model.isRollover()) {
            g2d.setColor(Color.WHITE); // Puedes ajustar el color de la línea según tus preferencias

            // Ajustar las coordenadas para que estén dentro del área del botón redondeado
            int yOffset = (int) (c.getHeight() - HOVER_LINE_HEIGHT);
            g2d.fillRect(0, yOffset, c.getWidth(), HOVER_LINE_HEIGHT);
        }
    }
}

