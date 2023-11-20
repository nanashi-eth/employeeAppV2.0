package View;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends CustomPanel {
    // Definir los campos y cualquier otro componente que necesites
    private JLabel nameField = new JLabel("nameField");
    private JLabel dateField = new JLabel("dateField");
    private JLabel salaryField = new JLabel("salaryField");
    private JLabel maxSalaryField = new JLabel("maxSalaryField");
    private JLabel departmentField = new JLabel("departmentField");
    private JLabel specialField1 = new JLabel("specialField1");
    private JLabel specialField2 = new JLabel("specialField2");

    // Constructor de la clase
    public EmployeePanel() {
        // Establecer el diseño del panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        add(dateField, gbc);

        // Row 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        add(salaryField, gbc);

        // Row 4
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Max Salary:"), gbc);
        gbc.gridx = 1;
        add(maxSalaryField, gbc);

        // Row 5
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        add(departmentField, gbc);

        // Row 6
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Special Field 1:"), gbc);
        gbc.gridx = 1;
        add(specialField1, gbc);

        // Row 7
        gbc.gridx = 0;
        gbc.gridy = 6;
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Panel Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EmployeePanel customPanel = new EmployeePanel();
        frame.add(customPanel);

        frame.setVisible(true);
    }
}
