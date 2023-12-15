package View;

import Model.Analista;
import Model.Empleado;
import Model.Programador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private final JLabel nameLabel = new JLabel("Name:");
    private final JLabel dateLabel = new JLabel("Date:");
    private final JLabel salaryLabel = new JLabel("Salary:");
    private final JLabel maxSalaryLabel = new JLabel("Max Salary:");
        private final JLabel departmentLabel = new JLabel("Type:");

    // Agregamos botones de editar y borrar
    private final JButton editButton = createIconButton("\uf044");
    private final JButton deleteButton = createIconButton("\uf1f8");
    private final JButton calcularBtn = createIconButton("\uf1ec");
    private final JButton nextBtn = createIconButton("\uf105");
    private final JButton prevBtn = createIconButton("\uf104");
    private final JButton lastBtn = createIconButton("\uf101");
    private final JButton firstBtn = createIconButton("\uf100");

    // Constructor de la clase
    public EmployeePanel() {
        // Establecer el diseño del panel
        setLayout(new BorderLayout());

        // CustomPanel para los botones (arriba)
        CustomPanel buttonPanel = new CustomPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Alineación a la izquierda
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(calcularBtn);
        buttonPanel.setBorder(new EmptyBorder(0, 0, INTERNAL_PADDING, 0));
        buttonPanel.setPreferredSize(new Dimension(0, 35));

        add(buttonPanel, BorderLayout.NORTH);

        // CustomPanel para etiquetas y campos (abajo)
        CustomPanel infoPanel = new CustomPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Row 1 (Name)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alineación a la izquierda
        infoPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        infoPanel.add(nameField, gbc);

        // Row 2 (Date)
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        infoPanel.add(dateField, gbc);

        // Row 3 (Salary)
        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(salaryLabel, gbc);
        gbc.gridx = 1;
        infoPanel.add(salaryField, gbc);

        // Row 4 (Max Salary)
        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(maxSalaryLabel, gbc);
        gbc.gridx = 1;
        infoPanel.add(maxSalaryField, gbc);

        // Row 5 (Department)
        gbc.gridx = 0;
        gbc.gridy = 4;
        infoPanel.add(departmentLabel, gbc);
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
        infoPanel.setBorder(new EmptyBorder(10, INTERNAL_PADDING, 0, INTERNAL_PADDING));

        add(infoPanel, BorderLayout.CENTER);

        // CustomPanel para los nuevos botones (abajo)
        CustomPanel bottomButtonPanel = new CustomPanel();
        bottomButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Alineación al centro
        bottomButtonPanel.setBorder(new EmptyBorder(INTERNAL_PADDING, 0, 0, 0));

        bottomButtonPanel.add(firstBtn);
        bottomButtonPanel.add(prevBtn);
        bottomButtonPanel.add(nextBtn);
        bottomButtonPanel.add(lastBtn);
        bottomButtonPanel.setBorder(new EmptyBorder(3, INTERNAL_PADDING, 0, INTERNAL_PADDING));
        bottomButtonPanel.setPreferredSize(new Dimension(0, 35));
        add(bottomButtonPanel, BorderLayout.SOUTH);

        setFontRecursively(this, font);
    }

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
        button.setPreferredSize(new Dimension(25, 30));
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
        });
    }

    private void addDeleteButtonListener() {
        deleteButton.addActionListener(e -> {
        });
    }

    public void setEmployeeData(Empleado employee) {
        // Verifica si el objeto 'employee' no es nulo antes de acceder a sus propiedades
        if (employee != null) {
            setNameFieldValue(employee.getNombre());
            setDateFieldValue(formatDate(employee.getFechaAlta())); // Asegúrate de tener un método formatDate implementado
            setSalaryFieldValue(String.valueOf(employee.getSueldo()));
            setMaxSalaryFieldValue(String.valueOf(employee.getSueldoMaximo()));

            // Verifica el tipo de empleado y muestra u oculta los campos especiales según sea necesario
            if (employee instanceof Analista) {
                setDepartmentFieldValue("Analista");
                setSpecialField1LabelText("Plus Anual:");
                setSpecialField1Value(String.valueOf(((Analista) employee).getPlusAnual()));
                setSpecialField2LabelText("Tipo Analisis: "); // Oculta el segundo campo especial para Analista
                setSpecialField2Value(((Analista) employee).getTipoAnalisis());
            } else if (employee instanceof Programador) {
                setDepartmentFieldValue("Programador");
                setSpecialField1LabelText("Sueldo Extra Mensual:");
                setSpecialField1Value(String.valueOf(((Programador) employee).getSueldoExtraMensual()));
                setSpecialField2LabelText("Lenguaje principal: "); // Oculta el segundo campo especial para Programador
                setSpecialField2Value(((Programador) employee).getLenguajePrincipal());
            } else {
                // Oculta ambos campos especiales si el tipo de empleado no es Analista ni Programador
                hideSpecialFields();
            }
        }
    }

    // Método adicional para formatear la fecha (puedes ajustarlo según tus necesidades)
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public void setDateFieldValue(String value) {
        dateField.setText(value);
    }

    public void setSalaryFieldValue(String value) {
        salaryField.setText(value);
    }

    public void setMaxSalaryFieldValue(String value) {
        maxSalaryField.setText(value);
    }

    public void setDepartmentFieldValue(String value) {
        departmentField.setText(value);
    }

    public void setSpecialField1Value(String value) {
        specialField1.setText(value);
    }

    public void setSpecialField2Value(String value) {
        specialField2.setText(value);
    }

    // Métodos públicos para configurar listeners desde una clase externa
    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addCalcularButtonListener(ActionListener listener) {
        calcularBtn.addActionListener(listener);
    }

    // Métodos adicionales para configurar listeners de botones adicionales
    public void addFirstButtonListener(ActionListener listener) {
        firstBtn.addActionListener(listener);
    }

    public void addPrevButtonListener(ActionListener listener) {
        prevBtn.addActionListener(listener);
    }

    public void addNextButtonListener(ActionListener listener) {
        nextBtn.addActionListener(listener);
    }

    public void addLastButtonListener(ActionListener listener) {
        lastBtn.addActionListener(listener);
    }

    // Métodos públicos para habilitar/deshabilitar botones
    public void setEditButtonEnabled(boolean enabled) {
        editButton.setEnabled(enabled);
    }

    public void setDeleteButtonEnabled(boolean enabled) {
        deleteButton.setEnabled(enabled);
    }

    public void setCalcularButtonEnabled(boolean enabled) {
        calcularBtn.setEnabled(enabled);
    }

    public void setFirstButtonEnabled(boolean enabled) {
        firstBtn.setEnabled(enabled);
    }

    public void setPrevButtonEnabled(boolean enabled) {
        prevBtn.setEnabled(enabled);
    }

    public void setNextButtonEnabled(boolean enabled) {
        nextBtn.setEnabled(enabled);
    }

    public void setLastButtonEnabled(boolean enabled) {
        lastBtn.setEnabled(enabled);
    }
    public void hideAllLabels() {
        hideLabelsRecursively(this);
    }

    private void hideLabelsRecursively(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                hideLabelsRecursively((Container) component);
            }
            if (component instanceof JLabel) {
                component.setVisible(false);
            }
        }
    }

    public void showAllLabels() {
        showLabelsRecursively(this);
    }

    private void showLabelsRecursively(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                showLabelsRecursively((Container) component);
            }
            if (component instanceof JLabel) {
                component.setVisible(true);
            }
        }
    }
}
