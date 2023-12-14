import View.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Main {
        public static void main(String[] args) {
            // runs in AWT thread
            SwingUtilities.invokeLater(() -> {
                FlatLightLaf.setup();
                try {
                    UIManager.setLookAndFeel(new FlatMaterialDarkerIJTheme());
                } catch (Exception ex) {
                    System.err.println("Failed to initialize LaF");
                }
                UIManager.put("Component.arrowType", "triangle");
                UIManager.put("Component.focusWidth", 1);
                UIManager.put("Button.arc", 999);
                new View();
            });
        }
}