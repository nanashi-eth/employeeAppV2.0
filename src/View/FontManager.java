package View;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
    private static Font customFont;
    private static Font customIconFont;

    static {
        initializeCustomIconFont();
        initializeCustomFont();
    }

    private static void initializeCustomIconFont() {
        try (InputStream is = FontManager.class.getResourceAsStream("../assets/fonts/Awesome.otf")) {
            customIconFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            handleFontException(e);
        }
    }

    private static void initializeCustomFont() {
        try (InputStream is = FontManager.class.getResourceAsStream("../assets/fonts/Main.ttf")) {
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            handleFontException(e);
        }
    }

    private static void handleFontException(Exception e) {
        e.printStackTrace(); // Aquí podrías considerar un manejo más robusto del error, dependiendo de tus necesidades.
    }

    public static Font getCustomFont() {
        return customFont;
    }

    public static Font getCustomIconFont() {
        return customIconFont;
    }
}
