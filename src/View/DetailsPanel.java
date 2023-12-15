package View;

import Controller.Controller;
import Exceptions.SueldoInvalidoException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DetailsPanel extends CustomPanel {

    private static final Color FG = new Color(19, 218, 43);
    private JComboBox<String> typeComboBox;
    private PlaceholderTextField nameField, salaryField, maxSalaryField, departmentField, specialField1, specialField2;
    PlaceholderTextField[] fields = {nameField, null, salaryField, maxSalaryField, departmentField, specialField1, specialField2};;
    JLabel [] labels;
    private Font font = FontManager.getCustomFont().deriveFont(Font.BOLD, 13), iconFont = FontManager.getCustomIconFont().deriveFont(Font.PLAIN, 15);;
    private CustomDateChooser dateChooser;
    JButton deleteButton;
    private static final int INTERNAL_PADDING = 3;

    public DetailsPanel() {
        super();
        initializeUI();
        createTopPanel();
        createCenterPanel();
        createBottomPanel();
        customizeAppearance();
        addEventListeners();
        typeComboBox.setSelectedItem("Analista");
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
    }

    private void createTopPanel() {
        CustomPanel topPanel = new CustomPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(new EmptyBorder(0, 0, INTERNAL_PADDING, 0));
        typeComboBox = new JComboBox<>(new String[]{"Analista", "Programador"});
        typeComboBox.setBorder(new LineBorder(Color.WHITE, 1));
        addComboBoxFocusListener(typeComboBox);

        deleteButton = createIconButton("\uf51a");
        dateChooser = new CustomDateChooser();
        dateChooser.setOpaque(false);
        dateChooser.setMinimumSize(new Dimension(200, 30));

        topPanel.add(new JLabel("Type:"));
        topPanel.add(typeComboBox);
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
    }

    private void createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(new EmptyBorder(10, INTERNAL_PADDING, 0, INTERNAL_PADDING));
        centerPanel.setBackground(getBackground().brighter());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labelsStr = {"Name:", "Date:", "Salary:", "Max Salary:", "Department:", "Special Field1", "Special Field2:"};
        // Inicializar el array de JLabels
        labels = new JLabel[labelsStr.length];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;

            // Crear y agregar el JLabel al array
            labels[i] = new JLabel(labelsStr[i]);
            centerPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.gridy = i;

            if (i == 1) {
                centerPanel.add(dateChooser, gbc);
            } else {
                fields[i] = createPlaceholderTextField(labelsStr[i].replace(":", ""));
                centerPanel.add(fields[i], gbc);
            }
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        CustomPanel bottomPanel = new CustomPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");

        bottomPanel.add(acceptButton);
        bottomPanel.add(cancelButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void customizeAppearance() {
        setFontRecursively(this, font);
        setButtonBackground(this, FG, Color.WHITE);
    }

    private void addEventListeners() {
        // Agregar tus listeners aquí
        addComboBoxListeners();
        addButtonListeners();
        addDocumentListeners();
        addFocusLostListeners();
    }

    private void addComboBoxListeners() {
        typeComboBox.addActionListener(e -> {
            // Acciones al cambiar el valor en el JComboBox
            String selectedType = (String) typeComboBox.getSelectedItem();

            // Actualizar los textos de los JLabel según el tipo seleccionado
            if (selectedType != null) {
                switch (selectedType) {
                    case "Analista":
                        labels[5].setText("Plus Anual:");
                        labels[6].setText("Tipo de Análisis:");
                        fields[5].setPlaceholder("Plus Anual");
                        fields[6].setPlaceholder("Tipo de Análisis");
                        break;
                    case "Programador":
                        labels[5].setText("Sueldo Extra:");
                        labels[6].setText("Lenguaje Principal:");
                        fields[5].setPlaceholder("Sueldo Extra");
                        fields[6].setPlaceholder("Lenguaje Principal");
                        break;
                    // Agregar más casos según sea necesario
                    default:
                        // Restablecer los textos a valores predeterminados o en blanco si no coincide con ningún caso
                        labels[5].setVisible(false);
                        labels[6].setVisible(false);
                        break;
                }
            }
        });
    }

    private void addButtonListeners() {
        // Agregar listeners para los botones, por ejemplo, al hacer clic en Aceptar o Cancelar
        JButton cancelButton = getCancelButton();
        
        deleteButton.addActionListener(e -> {
            clearFields();
        });

        cancelButton.addActionListener(e -> {
        });
        
    }
    
    public void save(ActionListener listener) {
        JButton acceptButton = getAcceptButton();
        acceptButton.addActionListener(listener);
    }

    private JButton getAcceptButton() {
        for (Component component : getComponents()) {
            if (component instanceof CustomPanel) {
                for (Component subComponent : ((CustomPanel) component).getComponents()) {
                    if (subComponent instanceof JButton && ((JButton) subComponent).getText().equals("Aceptar")) {
                        return (JButton) subComponent;
                    }
                }
            }
        }
        return null;
    }

    private JButton getCancelButton() {
        for (Component component : getComponents()) {
            if (component instanceof CustomPanel) {
                for (Component subComponent : ((CustomPanel) component).getComponents()) {
                    if (subComponent instanceof JButton && ((JButton) subComponent).getText().equals("Cancelar")) {
                        return (JButton) subComponent;
                    }
                }
            }
        }
        return null;
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
            if (component instanceof AbstractButton && !component.equals(deleteButton)) {
                component.setBackground(backgroundColor);
                component.setForeground(textColor);
                ((JButton) component).setBorderPainted(false);
            }
            if (component instanceof Container) {
                setButtonBackground((Container) component, backgroundColor, textColor);
            }
        }
    }

    private void setFontRecursively(Container container, Font font) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                setFontRecursively((Container) component, font);
            }
            if (component instanceof JComponent && !component.equals(deleteButton)) {
                component.setFont(font);
            }
        }
    }

    private void addComboBoxFocusListener(JComboBox<?> comboBox) {
        comboBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                comboBox.setBorder(BorderFactory.createLineBorder(FG, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboBox.setBorder(new LineBorder(Color.WHITE, 1)); // Restore the default border
            }
        });
    }

    private PlaceholderTextField createPlaceholderTextField(String placeholder) {
        return new PlaceholderTextField(this, placeholder, 15);
    }

    // Método para rellenar los PlaceholderTextField con los parámetros dados
    public void fillFields(String name, String date, String salary, String maxSalary, String department, String special1, String special2) {
        fields[0].setText(name);
        ((JTextField) dateChooser.getComponent(1)).setText(date);
        fields[2].setText(salary);
        fields[3].setText(maxSalary);
        fields[4].setText(department);
        fields[5].setText(special1);
        fields[6].setText(special2);
    }

    // Método para vaciar todos los PlaceholderTextField
    public void clearFields() {
        for (int i = 0; i < fields.length; i++) {
            if (i == 1) {
                continue;
            }
            fields[i].setText("");
        }
        ((JTextField) dateChooser.getComponent(1)).setText("");
        dateChooser.setDefaultText("Date");
        dateChooser.getComponent(1).repaint();
    }
    
    public void repaitnDate(String date){
        ((JTextField) dateChooser.getComponent(1)).setText(date);
        dateChooser.setDefaultText("");
        dateChooser.getComponent(1).repaint();
    }

    public String[] getEmployeeData() {
        String[] employeeData = new String[fields.length + 1]; // +1 para la fecha

        employeeData[0] = (String) typeComboBox.getSelectedItem(); // Tipo de empleado

        for (int i = 0; i < fields.length; i++) {
            if (i == 1) {
                // La fecha se obtiene de manera diferente
                employeeData[i + 1] = ((JTextField) dateChooser.getComponent(1)).getText();
            } else {
                employeeData[i + 1] = fields[i].getText();
            }
        }

        return employeeData;
    }


    private boolean isValidDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidDecimal(String decimalString) {
        try {
            double value = Double.parseDouble(decimalString);
            // Puedes agregar condiciones adicionales según tus requisitos (por ejemplo, valor positivo)
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidText(String text) {
        // Puedes agregar condiciones adicionales según tus requisitos
        return text != null && !text.trim().isEmpty();
    }


    private boolean validateFields() {
        if (!isValidText(fields[0].getText())) {
            return false;
        }
        if (!isValidText(fields[4].getText())) {
            return false;
        }
        if (!isValidText(fields[6].getText())) {
            return false;
        }
        if (!isValidDecimal(fields[2].getText())) {
            return false;
        }
        if (!isValidDecimal(fields[3].getText())) {
            return false;
        }
        if (!isValidDecimal(fields[5].getText())) {
            return false;
        }
        if (!isValidDate(((JTextField) dateChooser.getComponent(1)).getText())) {
            return false;
        }
        try {
            double sueldo = Double.parseDouble(fields[2].getText());
            double sueldoMax = Double.parseDouble(fields[3].getText());
            SueldoInvalidoException.verificarSueldo(sueldo, sueldoMax);
        }
        catch (SueldoInvalidoException e) {
            return false;
        }
        return true;
    }
    // Método para habilitar o deshabilitar el botón "Aceptar" según la validación de los campos
    private void updateAcceptButtonState() {
        JButton acceptButton = getAcceptButton();
        if (acceptButton != null) {
            acceptButton.setEnabled(validateFields());
        }
    }

    // Método para agregar DocumentListeners a los campos de texto
    private void addDocumentListeners() {
        for (PlaceholderTextField field : fields) {
            if (field != null) {
                field.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateAcceptButtonState();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateAcceptButtonState();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateAcceptButtonState();
                    }
                });
            }
        }
        nameListener();
        salaryListener();
        maxSalaryListener();
        departmentListener();
        specialField1Listener();
        specialField2Listener();
    }
    
    private void nameListener() {
        fields[0].getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isValidText(fields[0].getText()) && !fields[0].getText().equals(fields[0].getPlaceholder())) {
                    fields[0].valid();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isValidText(fields[0].getText()) && !fields[0].getText().equals(fields[0].getPlaceholder())) {
                    fields[0].valid();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método es generalmente para elementos que pueden cambiar su tamaño.
                // No es necesario implementarlo para un JTextField.
            }
        });
    } 
    private void salaryListener() {
        fields[2].getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isValidDecimal(fields[2].getText())) {
                    fields[2].valid();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isValidDecimal(fields[2].getText())) {
                    fields[2].valid();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método es generalmente para elementos que pueden cambiar su tamaño.
                // No es necesario implementarlo para un JTextField.
            }
        });
    }
    private void maxSalaryListener() {
        fields[3].getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isValidDecimal(fields[3].getText())) {
                    fields[3].valid();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isValidDecimal(fields[3].getText())) {
                    fields[3].valid();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método es generalmente para elementos que pueden cambiar su tamaño.
                // No es necesario implementarlo para un JTextField.
            }
        });
    }

    private void departmentListener() {
        fields[4].getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isValidText(fields[4].getText()) && !fields[4].getText().equals(fields[4].getPlaceholder())) {
                    fields[4].valid();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isValidText(fields[4].getText()) && !fields[4].getText().equals(fields[4].getPlaceholder())) {
                    fields[4].valid();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método es generalmente para elementos que pueden cambiar su tamaño.
                // No es necesario implementarlo para un JTextField.
            }
        });
    }

    private void specialField2Listener() {
        fields[6].getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isValidText(fields[6].getText()) && !fields[6].getText().equals(fields[6].getPlaceholder())) {
                    fields[6].valid();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isValidText(fields[6].getText()) && !fields[6].getText().equals(fields[6].getPlaceholder())) {
                    fields[6].valid();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método es generalmente para elementos que pueden cambiar su tamaño.
                // No es necesario implementarlo para un JTextField.
            }
        });
    }

    private void specialField1Listener() {
        fields[5].getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isValidDecimal(fields[5].getText())) {
                    fields[5].valid();
                }else {
                    fields[5].invalid();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isValidDecimal(fields[5].getText())) {
                    fields[5].valid();
                }else {
                    fields[5].invalid();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método es generalmente para elementos que pueden cambiar su tamaño.
                // No es necesario implementarlo para un JTextField.
            }
        });
    }

    private void addFocusLostListeners() {
        addFocusLostListener(fields[0], value -> {
            if (!isValidText(value)) {
                fields[0].invalid();
            }
        });

        addFocusLostListener(fields[2], value -> {
            if (!isValidDecimal(value)) {
                fields[2].invalid();
            }
        });

        addFocusLostListener(fields[3], value -> {
            if (!isValidDecimal(value)) {
                fields[3].invalid();
            }
        });

        addFocusLostListener(fields[4], value -> {
            if (!isValidText(value)) {
                fields[4].invalid();
            }
        });

        addFocusLostListener(fields[5], value -> {
            if (!isValidDecimal(value)) {
                fields[5].invalid();
            }
        });

        addFocusLostListener(fields[6], value -> {
            if (!isValidText(value)) {
                fields[6].invalid();
            }
        });
    }

    private void addFocusLostListener(PlaceholderTextField field, ValueHandler handler) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                handler.handle(field.getText());
            }
        });
    }

    // Interfaz funcional para manejar el valor
    @FunctionalInterface
    private interface ValueHandler {
        void handle(String value);
    }

    // Método para restablecer todos los PlaceholderTextField a su estado original
    public void resetFields() {
        for (PlaceholderTextField field : fields) {
            if (field != null) {
                field.reset(); // Agrega un método reset() en tu clase PlaceholderTextField
            }
        }
        // También puedes restablecer otros componentes si es necesario, como el JComboBox y el CustomDateChooser
        typeComboBox.setSelectedItem("Analista");
        dateChooser.setDefaultText("Date");
        dateChooser.getComponent(1).repaint();
    }
}
