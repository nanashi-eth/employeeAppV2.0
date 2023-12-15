package View;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CustomDateChooser extends JDateChooser {

    private static final Color FOCUS_COLOR = new Color(19, 218, 43);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color DECO_BACKGROUND_COLOR = Color.WHITE.darker();
    private static final Color WEEKDAY_FOREGROUND_COLOR = new Color(103, 109, 137);

    private Font iconFont = FontManager.getCustomIconFont();
    private Font font = FontManager.getCustomFont();

    private JTextField textDate;
    private JButton originalButton;
    private String defaultText = "Date";

    public CustomDateChooser() {
        super();
        setLocale(new Locale("es", "ES"));
        customizeCalendar();
        customizeComponents();
    }

    private void customizeCalendar() {
        JCalendar calendar = this.getJCalendar();
        calendar.setDecorationBackgroundColor(calendar.getDecorationBackgroundColor().darker());
        calendar.getDayChooser().setDecorationBackgroundColor(DECO_BACKGROUND_COLOR);
        calendar.getDayChooser().setWeekdayForeground(WEEKDAY_FOREGROUND_COLOR);

        calendar.getYearChooser().addPropertyChangeListener(v -> {
            if (!calendar.getYearChooser().hasFocus()) {
                calendar.getYearChooser().setForeground(TEXT_COLOR);
            }
        });

        getDateEditor().addPropertyChangeListener(v -> {
            if (!getDateEditor().getUiComponent().hasFocus()) {
                getDateEditor().getUiComponent().setForeground(Color.GRAY);
            }
        });

        // Establecer el formato de fecha personalizado "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.setDateFormatString(dateFormat.toPattern());
    }

    private void customizeComponents() {
        originalButton = ((JButton) this.getComponent(0));
        textDate = ((JTextField) this.getComponent(1));

        textDate.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, TEXT_COLOR));
        textDate.setOpaque(false);

        originalButton.setContentAreaFilled(false);

        Icon customIcon = createIcon(" \uf073", FOCUS_COLOR, 15f);
        Icon hoverIcon = createIcon(" \uf073", FOCUS_COLOR, 17f);

        originalButton.setIcon(customIcon);

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

        addFocusGainedListenerToAllComponents(this.getComponent(1));
        addFocusGainedListenerToAllComponents(getJCalendar());
    }

    private void addFocusGainedListenerToAllComponents(Component component) {
        if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            textField.addFocusListener(new FocusAdapter() {
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
            JButton button = (JButton) component;
            Color background = button.getBackground();
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(FOCUS_COLOR.darker());
                    button.setBorderPainted(false);
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBackground(background);
                }
            });
            button.addActionListener(e -> {
                defaultText = "";
                repaint();
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
                return 24;
            }

            @Override
            public int getIconHeight() {
                return 16;
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

        int x = getInsets().left;
        int y = (getHeight() - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent();
        g2.drawString(defaultText, x, y);
        g2.dispose();
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }
}
