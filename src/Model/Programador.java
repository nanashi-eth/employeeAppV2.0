package Model;

import java.util.Date;

public class Programador extends Empleado implements CalculoFechas{
    private double sueldoExtraMensual;
    private String lenguajePrincipal; 

    public Programador(String nombre, double sueldo, double sueldoMaximo, Date fechaAlta, String lenguajePrincipal, int number) {
        super(nombre, sueldo, sueldoMaximo, fechaAlta, number);
        this.lenguajePrincipal = lenguajePrincipal;
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
}
