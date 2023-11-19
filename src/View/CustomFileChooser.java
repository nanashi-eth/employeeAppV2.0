package View;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomFileChooser extends JFileChooser {

    public CustomFileChooser() {
        super();
    }

    @Override
    public int showSaveDialog(java.awt.Component parent) {
        int result = super.showSaveDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = getSelectedFile();

            if (fileExists(selectedFile, parent)) {
                return CANCEL_OPTION; // El usuario optó por no sobrescribir el archivo
            }

            createAndWriteToFile(selectedFile, "Contenido del archivo.");

            JOptionPane.showMessageDialog(parent, "Archivo creado exitosamente.");
        }

        return result;
    }

    private boolean fileExists(File file, java.awt.Component parent) {
        if (file.exists()) {
            int overwriteConfirmation = JOptionPane.showConfirmDialog(parent,
                    "El archivo ya existe. ¿Deseas sobrescribirlo?",
                    "Confirmar sobrescritura", JOptionPane.YES_NO_OPTION);

            return overwriteConfirmation != JOptionPane.YES_OPTION;
        }
        return false;
    }

    private void createAndWriteToFile(File file, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            handleFileWriteError();
        }
    }

    private void handleFileWriteError() {
        JOptionPane.showMessageDialog(null, "Error al escribir en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
