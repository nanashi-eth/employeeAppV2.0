package Model;

import Exceptions.SueldoInvalidoException;

import java.io.Serializable;
import java.util.Date;

public class Programador extends Empleado implements CalculoFechas, Serializable {
    private double sueldoExtraMensual;
    private String lenguajePrincipal; 

    public Programador(String nombre, double sueldo, double sueldoMaximo, Date fechaAlta, String lenguajePrincipal, int number) {
        super(nombre, sueldo, sueldoMaximo, fechaAlta, number);
        this.lenguajePrincipal = lenguajePrincipal;
    }

    public Programador() {
        super(); // Llama al constructor de la clase base
    }

    public double getSueldoExtraMensual() {
        return sueldoExtraMensual;
    }

    public void setSueldoExtraMensual(double sueldoExtraMensual) {
        this.sueldoExtraMensual = sueldoExtraMensual;
    }

    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }

    public void setLenguajePrincipal(String lenguajePrincipal) {
        this.lenguajePrincipal = lenguajePrincipal;
    }
    public void actualizarSalarioConPlus() throws SueldoInvalidoException{
        double nuevoSalario = getSueldo() + sueldoExtraMensual;
        if (nuevoSalario > super.getSueldoMaximo()) {
            throw new SueldoInvalidoException("El salario es mayor que el salario maximo");
        }
        else {
            setSueldo(nuevoSalario);
        }
    }
}
