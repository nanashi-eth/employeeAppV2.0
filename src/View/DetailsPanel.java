package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class DetailsPanel extends JPanel {

    public static final Color FG = new Color(19, 218, 43);
    private JComboBox<String> typeComboBox;
    private PlaceholderTextField nameField;
    private PlaceholderTextField dateField;
    private PlaceholderTextField salaryField;
    private PlaceholderTextField maxSalaryField;
    private PlaceholderTextField departmentField;
    private PlaceholderTextField specialField1;
    private PlaceholderTextField specialField2;
    Font font = FontManager.getCustomFont();
    Font iconFont = FontManager.getCustomIconFont();
    private CustomDateChooser dateChooser;

    public DetailsPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 400));

        // Panel superior con ComboBox y botones
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typeComboBox = new JComboBox<>(new String[]{"Analista", "Programador"});
        addComboBoxFocusListener(typeComboBox);
        JButton deleteButton = new JButton("\uf1f8");
        JButton calendarButton = new JButton("\uf073");
        // Crear el DateChooser
        dateChooser = new CustomDateChooser();
        // Establece el tamaño mínimo del JDateChooser
        Dimension minimumSize = new Dimension(200, 30);
        dateChooser.setMinimumSize(minimumSize);

        topPanel.add(new JLabel("Type:"));
        topPanel.add(typeComboBox);
        topPanel.add(deleteButton);
        topPanel.add(calendarButton);

        nameField = createPlaceholderTextField("Name");
        salaryField = createPlaceholderTextField("Salary");
        maxSalaryField = createPlaceholderTextField("Max Salary");
        departmentField = createPlaceholderTextField("Department");
        specialField1 = createPlaceholderTextField("Special Field 1");
        specialField2 = createPlaceholderTextField("Special Field 2");

        // Panel central con campos de texto y etiquetas
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Date:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(dateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Salary:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(new JLabel("Max Salary:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        centerPanel.add(maxSalaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        centerPanel.add(new JLabel("Department:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        centerPanel.add(departmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        centerPanel.add(new JLabel("Special Field 1:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        centerPanel.add(specialField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        centerPanel.add(new JLabel("Special Field 2:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        centerPanel.add(specialField2, gbc);

        // Panel inferior con botones Aceptar y Cancelar
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar lógica para manejar la acción de Aceptar
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar lógica para manejar la acción de Cancelar
            }
        });

        bottomPanel.add(acceptButton);
        bottomPanel.add(cancelButton);

        // Agregar los paneles al diálogo
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setBackground(this.getBackground().darker());
        font = font.deriveFont(Font.BOLD, 13);
        iconFont = iconFont.deriveFont(Font.PLAIN, 15);
        // Recorrer todos los componentes y establecer la fuente
        setFontRecursively(this, font);
        // Cambiar el fondo de todos los botones
        setButtonBackground(this, FG, Color.WHITE);
        
        deleteButton.setFont(iconFont);
        calendarButton.setFont(iconFont);
        deleteButton.setContentAreaFilled(false);
        applyHoverEffect(deleteButton);
        applyHoverEffect(calendarButton);
        calendarButton.setContentAreaFilled(false);
        deleteButton.setForeground(FG);
        calendarButton.setForeground(FG);
        calendarButton.addActionListener(v->{
            if (v.getSource() == calendarButton) {
                // Abre el DatePicker al hacer clic en el botón
                dateChooser.setDateFormatString("dd/MM/yyyy");
                // Puedes realizar acciones adicionales aquí según tus necesidades
            }
        });
    }

    // Método para cambiar el fondo de todos los botones
    private void setButtonBackground(Container container, Color backgroundColor, Color textColor) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                component.setBackground(backgroundColor);
                component.setForeground(textColor);
                ((JButton) component).setBorderPainted(false);
            }
            if (component instanceof Container) {
                setButtonBackground((Container) component, backgroundColor, textColor);
            }
        }
    }

    
    // Método para establecer la fuente en forma recursiva
    private void setFontRecursively(Container container, Font font) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                setFontRecursively((Container) component, font);
            }
            if (component instanceof JComponent) {
                ((JComponent) component).setFont(font);
            }
        }
    }

    private PlaceholderTextField createPlaceholderTextField(String placeholder) {
        return new PlaceholderTextField(this, placeholder, 15);
    }

    // Método externo para aplicar el efecto de hover a un JButton
    private static void applyHoverEffect(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setFont(button.getFont().deriveFont(Font.PLAIN, 17f));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setFont(button.getFont().deriveFont(Font.PLAIN, 15f));
            }
        });
    }
    // Método para agregar un FocusListener al JComboBox
    private static void addComboBoxFocusListener(JComboBox<?> comboBox) {
        comboBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                comboBox.setBorder(BorderFactory.createLineBorder(FG, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboBox.setBorder(UIManager.getBorder("ComboBox.border")); // Restaurar el borde predeterminado
            }
        });
    }

}
