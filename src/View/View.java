package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View extends JFrame {
    private static final int MARGIN_SIZE = 3;
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

    public View() {
        // Configuración de la interfaz gráfica
        setTitle("Employees");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        setVisible(true);
    }

    private void setupSideBarButtons() {
        // Botones en la barra lateral
        cargarButton = new CustomButton("Cargar", "\uF093", e -> {
            applyPaintLine((CustomButton) e.getSource());
        });
        sideBarPanel.add(cargarButton);

        guardarButton = new CustomButton("Guardar", "\uF0C7", e -> {
            applyPaintLine((CustomButton) e.getSource());
        });
        sideBarPanel.add(guardarButton);

        nuevoButton = new CustomButton("Nuevo o Insertar", "\uF0C6", e -> {
            setFirstDistribution();
            applyPaintLine((CustomButton) e.getSource());
        });
        sideBarPanel.add(nuevoButton);

        creadoMasivoButton = new CustomButton("Creado Masivo", "\uf0ac", e -> {
            applyPaintLine((CustomButton) e.getSource());
        });
        sideBarPanel.add(creadoMasivoButton);
        // Crear el botón "Employees"
        employeesButton = new CustomButton("Employees", "\uf007", e -> {
            setSecondDistribution();
            applyPaintLine((CustomButton) e.getSource());
        });

        // Agregar el botón al sideBarPanel
        sideBarPanel.add(employeesButton);
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
        CustomPanel panel = new CustomPanel();
        panel.add(listScrollPane);
        applyMarginToPanel(panel);
        editPanel = new DetailsPanel();
        applyMarginToPanel(editPanel);
        employeePane = new EmployeePanel();
        applyMarginToPanel(employeePane);

        // Crear JSplitPane y configurarlo como vertical
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editPanel, panel);
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
}
