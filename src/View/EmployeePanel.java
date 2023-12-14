package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EmployeePanel extends CustomPanel {
    // Definir los campos y cualquier otro componente que necesites
    private Font font = FontManager.getCustomFont().deriveFont(Font.BOLD, 13f);
    private Font iconFont = FontManager.getCustomIconFont().deriveFont(Font.PLAIN, 15f);
    private static final int INTERNAL_PADDING = 3;
    private static final Color FG = new Color(19, 218, 43);
    private final JLabel nameField = new JLabel("nameField");
    private final JLabel dateField = new JLabel("dateField");
    private final JLabel salaryField = new JLabel("salaryField");
    private final JLabel maxSalaryField = new JLabel("maxSalaryField");
    private final JLabel departmentField = new JLabel("departmentField");
    private final JLabel specialField1 = new JLabel("specialField1");
    private final JLabel specialField2 = new JLabel("specialField2");

    private final JLabel specialField1Label = new JLabel("Special Field 1:");
    private final JLabel specialField2Label = new JLabel("Special Field 2:");

    // Agregamos botones de editar y borrar
    private final JButton editButton = createIconButton("\uf044");
    private final JButton deleteButton = createIconButton("\uf1f8");

    // Constructor de la clase
    public EmployeePanel() {
        // Establecer el diseño del panel
        setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // CustomPanel para los botones (arriba)
        CustomPanel buttonPanel = new CustomPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alineación a la izquierda
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setBorder(new EmptyBorder(0, 0, INTERNAL_PADDING, 0));

        add(buttonPanel, BorderLayout.NORTH);

        // CustomPanel para etiquetas y campos (abajo)
        CustomPanel infoPanel = new CustomPanel();
        infoPanel.setLayout(new GridBagLayout());

        // Row 1 (Name)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alineación a la izquierda
        infoPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(nameField, gbc);

        // Row 2 (Date)
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(dateField, gbc);

        // Row 3 (Salary)
        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(salaryField, gbc);

        // Row 4 (Max Salary)
        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(new JLabel("Max Salary:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(maxSalaryField, gbc);

        // Row 5 (Department)
        gbc.gridx = 0;
        gbc.gridy = 4;
        infoPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(departmentField, gbc);

        // Row 6 (Special Field 1)
        gbc.gridx = 0;
        gbc.gridy = 5;
        infoPanel.add(specialField1Label, gbc);
        gbc.gridx = 1;
        infoPanel.add(specialField1, gbc);

        // Row 7 (Special Field 2)
        gbc.gridx = 0;
        gbc.gridy = 6;
        infoPanel.add(specialField2Label, gbc);
        gbc.gridx = 1;
        infoPanel.add(specialField2, gbc);

        add(infoPanel, BorderLayout.CENTER);
        setFontRecursively(this, font);
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
            if (component instanceof JLabel) {
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

    // Método para cambiar el texto del Special Field 1
    public void setSpecialField1LabelText(String text) {
        specialField1Label.setText(text);
    }

    // Método para cambiar el texto del Special Field 2
    public void setSpecialField2LabelText(String text) {
        specialField2Label.setText(text);
    }

    // Método para ocultar ambos Special Fields
    public void hideSpecialFields() {
        specialField1Label.setVisible(false);
        specialField1.setVisible(false);
        specialField2Label.setVisible(false);
        specialField2.setVisible(false);
    }

    private void addEventListeners() {
        addEditButtonListener();
        addDeleteButtonListener();
    }

    private void addEditButtonListener() {
        editButton.addActionListener(e -> {
            // Acciones al hacer clic en el botón de editar
            System.out.println("Edit button clicked");
            // Puedes realizar acciones adicionales al hacer clic en el botón de editar
        });
    }

    private void addDeleteButtonListener() {
        deleteButton.addActionListener(e -> {
            // Acciones al hacer clic en el botón de borrar
            System.out.println("Delete button clicked");
            // Puedes realizar acciones adicionales al hacer clic en el botón de borrar
        });
    }
}
