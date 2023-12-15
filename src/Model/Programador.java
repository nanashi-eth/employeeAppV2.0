package Model;

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
    public void actualizarSalarioConPlus() {
        double nuevoSalario = getSueldo() + sueldoExtraMensual;
        setSueldo(nuevoSalario);
    }
}
