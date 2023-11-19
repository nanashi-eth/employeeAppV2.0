package View;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private DefaultListModel<String> empleadosListModel;
    private JList<String> empleadosList;
    private JPanel sideBarPanel;
    private JPanel mainPanel;
    private JPanel employeePane;
    private DetailsPanel editPanel;
    private JPanel rightPanel;
    private JSplitPane splitPane;
    private JScrollPane listScrollPane;

    public View() {
        // Configuración de la interfaz gráfica
        setTitle("Employees");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        mainPanel = new JPanel(new BorderLayout());

        // Barra lateral con BoxLayout en eje Y
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));

        // Botones en la barra lateral
        setupSideBarButtons();

        // Configurar el ActionListener para el botón "Employees"
        setupEmployeesButton();

        // Configurar el panel derecho (rightPanel)
        setupRightPanel();

        // Agregar sideBarPanel y rightPanel al BorderLayout principal
        mainPanel.add(sideBarPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Agregar panel principal al JFrame
        add(mainPanel);

        // Establecer el color del texto en blanco
        setTextColor(Color.WHITE, this);

        setVisible(true);
    }

    private void setupSideBarButtons() {
        // Botones en la barra lateral
        sideBarPanel.add(new CustomButton("Cargar", "\uF093", e -> {}));
        sideBarPanel.add(new CustomButton("Guardar", "\uF0C7", e -> {}));
        sideBarPanel.add(new CustomButton("Nuevo o Insertar", "\uF0C6", v -> setFirstDistribution()));
        sideBarPanel.add(new CustomButton("Creado Masivo", "\uf0ac", e -> {}));
    }

    private void setupEmployeesButton() {
        // Crear el botón "Employees"
        CustomButton employeesButton = new CustomButton("Employees", "\uf007", e -> setSecondDistribution());

        // Agregar el botón al sideBarPanel
        sideBarPanel.add(employeesButton);
    }

    private void setupRightPanel() {
        // Configurar el panel derecho (rightPanel)
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // Lista de empleados
        empleadosListModel = new DefaultListModel<>();
        empleadosList = new JList<>(empleadosListModel);

        // ScrollPane para la lista
        listScrollPane = new JScrollPane(empleadosList);
        editPanel = new DetailsPanel();
        employeePane = new JPanel();

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
    }

    private void setDistribution(Component component) {
        splitPane.setTopComponent(component);
        splitPane.setBottomComponent(listScrollPane);
        splitPane.setResizeWeight(0.5);
        validate();
        repaint();
    }

    private void setTextColor(Color color, Component component) {
        if (component instanceof Container && !(component instanceof DetailsPanel)) {
            for (Component child : ((Container) component).getComponents()) {
                setTextColor(color, child);
            }
        }

        if (component instanceof JComponent) {
            ((JComponent) component).setForeground(color);
        }
    }
}
