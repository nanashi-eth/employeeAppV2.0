package View;

import Controller.Controller;
import Exceptions.SueldoInvalidoException;
import Model.Analista;
import Model.DoublyLinkedList;
import Model.Empleado;
import Model.Programador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View extends JFrame {
    private static final int MARGIN_SIZE = 3;
    private static int STATE = 0;
    private DefaultListModel<String> empleadosListModel;
    private JList<String> empleadosList;
    private final CustomPanel sideBarPanel;
    private final JPanel mainPanel;
    private EmployeePanel employeePane;
    private DetailsPanel editPanel;
    private JPanel rightPanel;
    private JSplitPane splitPane;
    private JScrollPane listScrollPane;
    private CustomButton cargarButton;
    private CustomButton guardarButton;
    private CustomButton nuevoButton;
    private CustomButton creadoMasivoButton;
    private CustomButton employeesButton;
    private Controller controller;

    public View() {
        // Configuración de la interfaz gráfica
        setTitle("Employees");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../assets/images/app_icon1.png")));
        controller = new Controller(this);
        // Panel principal con BorderLayout
        mainPanel = new JPanel(new BorderLayout());

        // Barra lateral con BoxLayout en eje Y
        sideBarPanel = new CustomPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        applyMarginToPanel(sideBarPanel);

        // Botones en la barra lateral
        setupSideBarButtons();

        // Configurar el panel derecho (rightPanel)
        setupRightPanel();


        // Agregar sideBarPanel y rightPanel al BorderLayout principal
        mainPanel.add(sideBarPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Agregar panel principal al JFrame
        add(mainPanel);

        // Establecer el color del texto en blanco
        setTextColor(Color.WHITE, this);
        
        editPanel.save(e -> {
            controller.createEmpleadoFromData(editPanel.getEmployeeData());
            if (STATE == 0) {
                controller.saveCurrentEmployee();
            }
            if (STATE == 1) {
                controller.modifyEmployee(controller.getCurrentEmployee().getNumber(), controller.getCurrentEmployee());
            }
            editPanel.clearFields();
            editPanel.resetFields();
            employeePane.setEmployeeData(controller.getCurrentEmployee());
            updateJList();
            updateNavigationButtons();
            updateModButtons();
        });
        
        employeePane.addCalcularButtonListener(e -> {
            try {
                if (controller.getCurrentEmployee() instanceof Analista) {
                    ((Analista) controller.getCurrentEmployee()).actualizarSalarioConPlus();
                }
                if (controller.getCurrentEmployee() instanceof Programador) {
                    ((Programador) controller.getCurrentEmployee()).actualizarSalarioConPlus();
                }
            }
            catch (SueldoInvalidoException ex) {
                
            }
            
            updateJList();
            updateEmployee();
        });
        employeePane.addDeleteButtonListener(e -> {
            controller.deleteEmployee(controller.getCurrentEmployee().getNumber());
            if (controller.getEmployeeDoublyLinkedList().getIndexByItem(controller.getCurrentEmployee()) < controller.getEmployeeDoublyLinkedList().getSize() - 1) {
                controller.setCurrentEmployee(controller.getNextEmployee());
                updateEmployee();
            }
            else {
                controller.setCurrentEmployee(controller.getPreviousEmployee());
                updateEmployee();
            }
            if (controller.getEmployeeDoublyLinkedList().getSize() == 0) {
                employeePane.hideAllLabels();
            }
            updateJList();
            updateModButtons();
            updateNavigationButtons();
        });
        employeePane.addLastButtonListener(e -> {
            controller.setCurrentEmployee(controller.getLastEmployee());
            updateEmployee();
        });
        employeePane.addNextButtonListener(e -> {
            controller.setCurrentEmployee(controller.getNextEmployee());
            updateEmployee();
        });
        employeePane.addPrevButtonListener(e -> {
            controller.setCurrentEmployee(controller.getPreviousEmployee());
            updateEmployee();
        });
        employeePane.addFirstButtonListener(e -> {
            controller.setCurrentEmployee(controller.getFirstEmployee());
            updateEmployee();
        });
        employeePane.addEditButtonListener(e -> {
            editCurrentEmployee(controller.getCurrentEmployee());
        });
        employeePane.setNextButtonEnabled(false);
        employeePane.setLastButtonEnabled(false);
        employeePane.setPrevButtonEnabled(false);
        employeePane.setFirstButtonEnabled(false);
        updateModButtons();
        employeePane.hideAllLabels();
        setVisible(true);
    }

    private void editCurrentEmployee(Empleado currentEmployee) {
        STATE = 1;
        String [] fields = controller.extractDataFromEmpleado(currentEmployee);
        editPanel.resetFields();
        editPanel.fillFields(fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]);
        editPanel.repaitnDate(fields[2]);
        setFirstDistribution();
    }

    // Método para actualizar el estado de los botones de navegación
    public void updateNavigationButtons() {
        int currentIndex = controller.getEmployeeDoublyLinkedList().getIndexByItem(controller.getCurrentEmployee());
        int lastIndex = controller.getEmployeeDoublyLinkedList().getSize() - 1;
        // Actualizar el estado de los botones de avanzar y retroceder
        employeePane.setNextButtonEnabled(currentIndex < lastIndex);
        employeePane.setLastButtonEnabled(currentIndex < lastIndex);
        employeePane.setPrevButtonEnabled(currentIndex > 0 && lastIndex + 1 >= 1);
        employeePane.setFirstButtonEnabled(currentIndex > 0 && lastIndex + 1 >= 1);
        employeePane.setCalcularButtonEnabled(btonCalcular());
    }

    public void updateModButtons() {
        int size = controller.getEmployeeDoublyLinkedList().getSize();
        // Actualizar el estado de los botones de avanzar y retroceder
        employeePane.setEditButtonEnabled(size > 0);
        employeePane.setCalcularButtonEnabled(size > 0);
        employeePane.setDeleteButtonEnabled(size > 0);
    }
    public void updateEmployee(){
        employeePane.showAllLabels();
        Empleado e = controller.getCurrentEmployee();
        employeePane.setEmployeeData(e);
        updateNavigationButtons();
        updateModButtons();
    }

    private void setupSideBarButtons() {
        // Botones en la barra lateral
        cargarButton = new CustomButton("Cargar", "\uF093", e -> {
            applyPaintLine((CustomButton) e.getSource());
            controller.cargarDatosDesdeArchivo();
            updateJList();
        });
        sideBarPanel.add(cargarButton);

        guardarButton = new CustomButton("Guardar", "\uF0C7", e -> {
            applyPaintLine((CustomButton) e.getSource());
            controller.guardarDatosEnArchivo();
        });
        sideBarPanel.add(guardarButton);

        nuevoButton = new CustomButton("Nuevo", "\uF0C6", e -> {
            STATE = 0;
            editPanel.clearFields();
            editPanel.resetFields();
            setFirstDistribution();
            applyPaintLine((CustomButton) e.getSource());
        });
        sideBarPanel.add(nuevoButton);

        // Crear el botón "Employees"
        employeesButton = new CustomButton("Empleados", "\uf007", e -> {
            if (controller.getEmployeeDoublyLinkedList().getSize() > 0) {
                employeePane.showAllLabels();
            }
            setSecondDistribution();
            employeePane.setEmployeeData(controller.getCurrentEmployee());
            applyPaintLine((CustomButton) e.getSource());
        });
        // Agregar el botón al sideBarPanel
        sideBarPanel.add(employeesButton);

        creadoMasivoButton = new CustomButton("Creado Masivo", "\uf0ac", e -> {
            applyPaintLine((CustomButton) e.getSource());
            controller.sortEmployees();
            updateJList();
        });
        // Agregar espacio entre los botones y el botón "Creado Masivo" al final
        sideBarPanel.add(Box.createVerticalGlue());
        sideBarPanel.add(creadoMasivoButton);
    }

    private void applyPaintLine(CustomButton button) {
        cargarButton.paintLine(button);
        creadoMasivoButton.paintLine(button);
        nuevoButton.paintLine(button);
        guardarButton.paintLine(button);
        employeesButton.paintLine(button);
    }

    private void setupRightPanel() {
        // Configurar el panel derecho (rightPanel)
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(new EmptyBorder(0, 0, 0, MARGIN_SIZE));

        // Lista de empleados
        empleadosListModel = new DefaultListModel<>();
        empleadosList = new JList<>(empleadosListModel);

        // ScrollPane para la lista
        listScrollPane = new JScrollPane(empleadosList);
        Font font = FontManager.getCustomFont().deriveFont(Font.BOLD, 13F);
        setFontRecursively(listScrollPane, font);
        listScrollPane.setBorder(BorderFactory.createEmptyBorder());
        listScrollPane.getViewport().setOpaque(false);
        listScrollPane.setOpaque(false);

        // Custom cell renderer to center and add spacing
        empleadosList.setCellRenderer(new DefaultListCellRenderer() {
            private final int padding = 10;

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
                return label;
            }
        });

        // Ocultar barras de desplazamiento
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        listScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        CustomPanel panel = new CustomPanel();
        panel.add(listScrollPane);
        applyMarginToPanel(panel);
        editPanel = new DetailsPanel();
        applyMarginToPanel(editPanel);
        employeePane = new EmployeePanel();
        applyMarginToPanel(employeePane);

        // Crear JSplitPane y configurarlo como vertical
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editPanel, listScrollPane);
        splitPane.setResizeWeight(0.5);

        // Agregar el JSplitPane al rightPanel
        rightPanel.add(splitPane, BorderLayout.CENTER);
    }

    // Métodos para cambiar la distribución
    public void setFirstDistribution() {
        setDistribution(editPanel);
    }

    public void setSecondDistribution() {
        setDistribution(employeePane);
        updateNavigationButtons();
    }

    private void setDistribution(Component component) {
        splitPane.setTopComponent(component);
        splitPane.setBottomComponent(listScrollPane);
        splitPane.setResizeWeight(0.5);
        validate();
        repaint();
    }

    private void applyMarginToPanel(JPanel panel) {
        // Método para aplicar el margen a un panel dado
        panel.setBorder(new EmptyBorder(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE));
    }


    private void setTextColor(Color color, Component component) {
        if (component instanceof Container && !(component instanceof DetailsPanel)) {
            for (Component child : ((Container) component).getComponents()) {
                setTextColor(color, child);
            }
        }

        if (component instanceof JComponent) {
            component.setForeground(color);
        }
    }
    // Método para actualizar el JList con los datos de employeeDoublyLinkedList
    public void updateJList() {

        // Convertir la lista enlazada a un array para establecerlo en el JList
        Empleado[] employeeArray = controller.toArray();

        // Actualizar el modelo del JList con el nuevo array de empleados
        empleadosListModel.removeAllElements();
        for (Empleado empleado : employeeArray) {
            // Agregar al modelo del JList solo el nombre e ID de cada empleado
            empleadosListModel.addElement(empleado.getNombre() + " - ID: " + controller.getEmployeeDoublyLinkedList().getIndexByItem(empleado));
        }
    }
    public boolean btonCalcular(){
        if (controller.getCurrentEmployee() instanceof Analista) {
            return controller.fulfillsMonthAnalyst((Analista) controller.getCurrentEmployee(), 1);
        }
        if (controller.getCurrentEmployee() instanceof Programador) {
            return controller.fulfillsYearProgrammer((Programador) controller.getCurrentEmployee(), 1);
        }
        return false;
    }
    private void setFontRecursively(Container container, Font font) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                setFontRecursively((Container) component, font);
            }
            if (component instanceof JComponent && !(component instanceof JButton)) {
                component.setFont(font);
            }
        }
    }
    
    
}
