package View;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Font;

public class FontManager {
    private static Font customFont;
    private static Font customIconFont;

    static {
        try (InputStream is = FontManager.class.getResourceAsStream("../assets/Awesome.otf")) {
            customIconFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        try (InputStream is = FontManager.class.getResourceAsStream("../assets/Main.ttf")) {
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Font getCustomFont() {
        return customFont.deriveFont(Font.PLAIN, 12); // Ajusta según tus necesidades
    }

    public static Font getCustomIconFont() {
        return customIconFont.deriveFont(Font.PLAIN, 12); // Ajusta según tus necesidades
    }
}
