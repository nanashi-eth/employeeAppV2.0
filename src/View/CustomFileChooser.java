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

            // Comprueba si el archivo ya existe
            if (selectedFile.exists()) {
                int overwriteConfirmation = JOptionPane.showConfirmDialog(parent,
                        "El archivo ya existe. ¿Deseas sobrescribirlo?",
                        "Confirmar sobrescritura", JOptionPane.YES_NO_OPTION);

                if (overwriteConfirmation != JOptionPane.YES_OPTION) {
                    return CANCEL_OPTION; // El usuario optó por no sobrescribir el archivo
                }
            }

            // Crea el archivo y escribe en él
            writeToFile(selectedFile, "Contenido del archivo.");

            JOptionPane.showMessageDialog(parent, "Archivo creado exitosamente.");
        }

        return result;
    }

    private void writeToFile(File file, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}