package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class DetailsPanel extends CustomPanel {

    private static final Color FG = new Color(19, 218, 43);
    private JComboBox<String> typeComboBox;
    private PlaceholderTextField nameField, salaryField, maxSalaryField, departmentField, specialField1, specialField2;
    private Font font = FontManager.getCustomFont().deriveFont(Font.BOLD, 13), iconFont = FontManager.getCustomIconFont().deriveFont(Font.PLAIN, 15);;
    private CustomDateChooser dateChooser;
    JButton deleteButton, calendarButton;
    private static final int INTERNAL_PADDING = 3;

    public DetailsPanel() {
        super();
        initializeUI();
        createTopPanel();
        createCenterPanel();
        createBottomPanel();
        customizeAppearance();
        addEventListeners();
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
        calendarButton = createIconButton("\uf073");
        dateChooser = new CustomDateChooser();
        dateChooser.setOpaque(false);
        dateChooser.setMinimumSize(new Dimension(200, 30));

        topPanel.add(new JLabel("Type:"));
        topPanel.add(typeComboBox);
        topPanel.add(deleteButton);
        topPanel.add(calendarButton);

        add(topPanel, BorderLayout.NORTH);
    }

    private void createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(new EmptyBorder(10, INTERNAL_PADDING, 0, INTERNAL_PADDING));
        centerPanel.setBackground(getBackground().brighter());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Name:", "Date:", "Salary:", "Max Salary:", "Department:", "Special Field1", "Special Field2:"};
        PlaceholderTextField[] fields = {nameField, null, salaryField, maxSalaryField, departmentField, specialField1, specialField2};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            centerPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            gbc.gridy = i;

            if (i == 1) {
                centerPanel.add(dateChooser, gbc);
            } else {
                fields[i] = createPlaceholderTextField(labels[i].replace(":", ""));
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
    }

    private void addComboBoxListeners() {
        typeComboBox.addActionListener(e -> {
            // Acciones al cambiar el valor en el JComboBox
            String selectedType = (String) typeComboBox.getSelectedItem();
            // Puedes realizar acciones adicionales según el tipo seleccionado
            System.out.println("Selected Type: " + selectedType);
        });
    }

    private void addButtonListeners() {
        // Agregar listeners para los botones, por ejemplo, al hacer clic en Aceptar o Cancelar
        JButton acceptButton = getAcceptButton();
        JButton cancelButton = getCancelButton();

        acceptButton.addActionListener(e -> {
            // Acciones al hacer clic en Aceptar
            System.out.println("Accept button clicked");
            // Puedes realizar acciones adicionales al hacer clic en Aceptar
        });

        cancelButton.addActionListener(e -> {
            // Acciones al hacer clic en Cancelar
            System.out.println("Cancel button clicked");
            // Puedes realizar acciones adicionales al hacer clic en Cancelar
        });

        // Agregar más listeners para otros botones si es necesario
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
            if (component instanceof AbstractButton && !component.equals(deleteButton) && !component.equals(calendarButton)) {
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
            if (component instanceof JComponent && !component.equals(deleteButton) && !component.equals(calendarButton)) {
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
        nameField.setText(name);
        ((JTextField) dateChooser.getComponent(1)).setText(date);
        salaryField.setText(salary);
        maxSalaryField.setText(maxSalary);
        departmentField.setText(department);
        specialField1.setText(special1);
        specialField2.setText(special2);
    }

    // Método para vaciar todos los PlaceholderTextField
    public void clearFields() {
        nameField.setText("");
        // Puedes seguir este patrón para los demás campos
        ((JTextField) dateChooser.getComponent(1)).setText("");
        salaryField.setText("");
        maxSalaryField.setText("");
        departmentField.setText("");
        specialField1.setText("");
        specialField2.setText("");
    }
}
