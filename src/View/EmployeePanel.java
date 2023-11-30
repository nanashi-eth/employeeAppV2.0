package View;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends CustomPanel {
    // Definir los campos y cualquier otro componente que necesites
    private Font font = FontManager.getCustomFont();
    private Font iconFont = FontManager.getCustomIconFont();
    private static final Color FG = new Color(19, 218, 43);
    private final JLabel nameField = new JLabel("nameField");
    private final JLabel dateField = new JLabel("dateField");
    private final JLabel salaryField = new JLabel("salaryField");
    private final JLabel maxSalaryField = new JLabel("maxSalaryField");
    private final JLabel departmentField = new JLabel("departmentField");
    private final JLabel specialField1 = new JLabel("specialField1");
    private final JLabel specialField2 = new JLabel("specialField2");

    // Agregamos botones de editar y borrar
    private final JButton editButton = createIconButton("")
    private final JButton deleteButton = new JButton("Borrar");

    // Constructor de la clase
    public EmployeePanel() {
        // Establecer el diseño del panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(editButton, gbc);

        gbc.gridx = 1;
        add(deleteButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        add(dateField, gbc);

        // Row 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        add(salaryField, gbc);

        // Row 4
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Max Salary:"), gbc);
        gbc.gridx = 1;
        add(maxSalaryField, gbc);

        // Row 5
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        add(departmentField, gbc);

        // Row 6
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Special Field 1:"), gbc);
        gbc.gridx = 1;
        add(specialField1, gbc);

        // Row 7
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Special Field 2:"), gbc);
        gbc.gridx = 1;
        add(specialField2, gbc);
    }

    // Puedes añadir métodos adicionales para realizar modificaciones específicas si es necesario
    // Por ejemplo, getters y setters para acceder y modificar los campos

    // Ejemplo de método para obtener el contenido del campo 'nameField'
    public String getNameFieldValue() {
        return nameField.getText();
    }

    // Ejemplo de método para establecer el contenido del campo 'nameField'
    public void setNameFieldValue(String value) {
        nameField.setText(value);
    }

    private void setFontRecursively(Container container, Font font) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                setFontRecursively((Container) component, font);
            }
            if (component instanceof JComponent && !component.equals(deleteButton) && !component.equals(editButton)) {
                component.setFont(font);
            }
        }
    }

    private JButton createIconButton(String iconUnicode) {
        JButton button = new JButton();
        button.setText(iconUnicode);
        button.setFont(iconFont);
        button.setContentAreaFilled(false);
        applyHoverEffect(button);
        button.setForeground(FG);
        return button;
    }

    private static void applyHoverEffect(AbstractButton button) {
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

    private void setButtonBackground(Container container, Color backgroundColor, Color textColor) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof AbstractButton && !component.equals(deleteButton) && !component.equals(editButton)) {
                component.setBackground(backgroundColor);
                component.setForeground(textColor);
                ((JButton) component).setBorderPainted(false);
            }
            if (component instanceof Container) {
                setButtonBackground((Container) component, backgroundColor, textColor);
            }
        }
    }
}
