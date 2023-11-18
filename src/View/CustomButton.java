package View;
import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    private static final int ICON_SIZE = 15;
    private static final int TEXT_SIZE = 13;
    private Font font = FontManager.getCustomIconFont();
    private Font main = FontManager.getCustomFont();
    private final JLabel iconLabel;
    public CustomButton(String text, String icon) {
        // Configurar el texto del botón
        super("        " + text);
        this.iconLabel = new JLabel(icon);
        font = font.deriveFont(Font.PLAIN, ICON_SIZE);
        main = main.deriveFont(Font.BOLD, TEXT_SIZE);
        setFont(main);
        iconLabel.setFont(font);
        // Configurar posición del texto y del icono a la izquierda
        setHorizontalAlignment(JButton.LEFT);

        // Configurar posición vertical del texto
        setVerticalTextPosition(JButton.CENTER);

        // Configurar espacio entre icono y texto
        setIconTextGap(10);

        // Configurar alineación
        setAlignmentX(Component.LEFT_ALIGNMENT);
        

        // Quitar el borde
        setBorderPainted(false);

        // Obtener el color de fondo predeterminado de UIManager
        Color backgroundColor = UIManager.getColor("Frame.background");

        // Configurar el color de fondo del botón
        setBackground(backgroundColor);

        // Configurar el diseño con un JLabel para el icono en la posición EAST (derecha)
        setLayout(new BorderLayout());
        add(iconLabel, BorderLayout.WEST);

        // Aplicar ButtonUI personalizado
        setUI(new CustomButtonUI());
    }
}
