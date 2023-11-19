package View;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class CustomDateChooser extends JDateChooser {
    static {
        // Configura el Look and Feel con el color del efecto de focus
        UIManager.put("Button.focus", new Color(19, 218, 43)); // Cambia "new Color(0, 0, 0, 0)" al color que desees
    }
    private Font iconFont = FontManager.getCustomIconFont();
    private Font font = FontManager.getCustomFont();
    public static final Color FG = new Color(19, 218, 43);
    private JTextField textDate;
    private JButton originalButton;
    private String defaultText = "Date";

    public CustomDateChooser() {
        super();
        setLocale(new Locale("es", "ES"));
        // Obtén el componente JCalendar interno
        JCalendar calendar = this.getJCalendar();
        jcalendar.setDecorationBackgroundColor(jcalendar.getDecorationBackgroundColor().darker());
        jcalendar.getDayChooser().setDecorationBackgroundColor(Color.WHITE.darker());
        jcalendar.getDayChooser().setWeekdayForeground(new Color(103, 109, 137));
        jcalendar.getYearChooser().addPropertyChangeListener(v->{
            if (!jcalendar.getYearChooser().hasFocus()) {
                jcalendar.getYearChooser().setForeground(Color.WHITE);
            }
        });
        getDateEditor().addPropertyChangeListener(v->{
            if (!getDateEditor().getUiComponent().hasFocus()) {
                getDateEditor().getUiComponent().setForeground(Color.WHITE);
            }
        });
        getDateEditor().getUiComponent().setForeground(Color.WHITE);
        
        // Accede al JSpinField (YearChooser) y personaliza el color del texto
        JSpinField yearChooser = calendar.getYearChooser();

        // Obtiene el botón original del JDateChooser
        originalButton = ((JButton) this.getComponent(0));
        textDate = ((JTextField) this.getComponent(1));
        textDate.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        textDate.setOpaque(false);
        
        
        originalButton.setContentAreaFilled(false);

        // Crea un icono a partir del texto
        Icon customIcon = createIcon(" \uf073", FG, 15f);
        Icon hoverIcon = createIcon(" \uf073", FG, 17f);

        // Establece el nuevo icono en el botón
        originalButton.setIcon(customIcon);

        // Agrega un manejador de eventos para cambiar el tamaño del icono al pasar el cursor
        originalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                originalButton.setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                originalButton.setIcon(customIcon);
            }
        });
        // Agrega el manejador de eventos focusGained a todos los componentes
        addFocusGainedListenerToAllComponents(this.getComponent(1));
        addFocusGainedListenerToAllComponents(calendar);
    }

    private void addFocusGainedListenerToAllComponents(Component component) {
        if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    defaultText = "";
                    repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if ((defaultText.isEmpty() || defaultText.equals("Date")) && textDate.getText().isEmpty())
                        defaultText = "Date";
                    repaint();
                }
            });
        }
        if (component instanceof JButton) {
            Color background = component.getBackground();
            component.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    component.setBackground(FG.darker());
                    ((JButton) component).setBorderPainted(false);
                }

                public void mouseExited(MouseEvent evt) {
                    ((JButton) component).setBackground(background);
                }
            });
            ((JButton) component).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    defaultText = "";
                    repaint();
                }
            });
        }

        if (component instanceof Container) {
            Component[] components = ((Container) component).getComponents();
            for (Component child : components) {
                addFocusGainedListenerToAllComponents(child);
            }
        }
    }
    private Icon createIcon(String text, Color color, float fontSize) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setFont(iconFont.deriveFont(fontSize));
                g2.setColor(color);
                g2.drawString(text, x, y + g2.getFontMetrics().getAscent());
                g2.dispose();
            }

            @Override
            public int getIconWidth() {
                return 24; // Ajusta el ancho según tus necesidades
            }

            @Override
            public int getIconHeight() {
                return 16; // Ajusta el alto según tus necesidades
            }
        };
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font font = getFont().deriveFont(Font.BOLD, 14);
        g2.setFont(font);
        g2.setColor(Color.GRAY);

        // Ajusta la posición del texto según tus necesidades
        int x = getInsets().left;
        int y = (getHeight() - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent();
        g2.drawString(defaultText, x, y);
        g2.dispose();
    }
    
}