package Exceptions;

public class SueldoInvalidoException extends Exception {
    public SueldoInvalidoException(String message) {
        super(message);
    }

    public static void verificarSueldo(double sueldo, double sueldoMaximo) throws SueldoInvalidoException {
        if (sueldo > sueldoMaximo) {
            throw new SueldoInvalidoException("El sueldo no puede superar el sueldo máximo.");
        }
    }
}

