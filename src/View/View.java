package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

public class View extends JFrame {
    private final String iconUrl = "../assets/icon.svg";
    private DefaultListModel<String> empleadosListModel;
    private JList<String> empleadosList;

    public View() {
        // Configuración de la interfaz gráfica
        setTitle("Employees");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
        
        
        

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Barra lateral con BoxLayout en eje Y
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));

        // Botón "Cargar"
        CustomButton cargarButton = new CustomButton("Cargar", "\uF093");
        sideBarPanel.add(cargarButton);

        // Botón "Guardar"
        CustomButton guardarButton = new CustomButton("Guardar", "\uF0C7");
        sideBarPanel.add(guardarButton);

        // Botón "Nuevo o Insertar"
        CustomButton nuevoButton = new CustomButton( "Nuevo o Insertar", "\uF0C6");
        sideBarPanel.add(nuevoButton);
        
//        nuevoButton.addActionListener(v->
//            new DetailsDialog(this).setVisible(true));

        // Botón "Creado Masivo"
        CustomButton creadoMasivoButton = new CustomButton( "Creado Masivo", "\uf0ac");
        sideBarPanel.add(creadoMasivoButton);
        
        // Lista de empleados
        empleadosListModel = new DefaultListModel<>();
        empleadosList = new JList<>(empleadosListModel);

        // ScrollPane para la lista
        JScrollPane listScrollPane = new JScrollPane(empleadosList);
        mainPanel.add(new DetailsPanel(), BorderLayout.CENTER);

//        // Agregar listScrollPane al centro del BorderLayout
//        mainPanel.add(listScrollPane, BorderLayout.CENTER);

        // Agregar sideBarPanel a la parte izquierda del BorderLayout
        mainPanel.add(sideBarPanel, BorderLayout.WEST);

        // Agregar panel principal al JFrame
        add(mainPanel);

        // Establecer el color del texto en blanco
        setTextColor(Color.WHITE, this);
        
        
        setVisible(true);
    }


    private void setTextColor(Color color, Component component) {
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setTextColor(color, child);
            }
        }

        if (component instanceof JComponent) {
            ((JComponent) component).setForeground(color);
        }
    }
}
