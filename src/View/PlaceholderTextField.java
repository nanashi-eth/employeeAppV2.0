package View;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class PlaceholderTextField extends JTextField {
    private final String placeholder;
    private final JLabel iconLabel;
    private Font font = FontManager.getCustomIconFont();
    Color containerBackground;

    public PlaceholderTextField(Container parent, String text, int columns) {
        super(columns);
        font = font.deriveFont(Font.BOLD, 14);
        this.placeholder = text;
        this.iconLabel = new JLabel();
        this.iconLabel.setFont(font);
        setLayout(new BorderLayout());
        setOpaque(false);
        add(iconLabel, BorderLayout.EAST);

        // Eliminar el borde redondeado y utilizar una línea sólida en la parte inferior
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

        setMargin(new Insets(5, 5, 5, 5));
        setOpaque(false);
        // Obtener el color de fondo del componente contenedor
        containerBackground = parent.getBackground();

        setForeground(Color.GRAY);
        setText(placeholder);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(containerBackground.darker().brighter());
                if (getText().equals(placeholder)) {
                    setText("");
                } else {
                    setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                }
                else {
                    setForeground(Color.WHITE);
                }
            }
        });
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setForeground(Color.WHITE);            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setForeground(Color.WHITE);            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setForeground(Color.WHITE);            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !hasFocus()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }

    public void addExternalDocumentListener(DocumentListener listener) {
        this.getDocument().addDocumentListener(listener);
    }
    // Método para cambiar el color de fondo
    public void setCustomBackgroundColor(Color color) {
        setBackground(color);
        repaint(); // Es posible que necesites repintar el componente para que los cambios sean visibles
    }

    public void valid(){
        iconLabel.setText("\uF14A");
        iconLabel.setForeground(new Color(102, 204, 255));
    }
    public void invalid(){
        iconLabel.setText("");
        if (!this.placeholder.equals(this.getText()))
       setForeground(new Color(204, 0, 0));
    }
    
    public void reset() {
        iconLabel.setText("");
    }
}
