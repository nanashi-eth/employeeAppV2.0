package Model;

import java.util.Date;

public class Programador extends Empleado implements CalculoFechas{
    private double sueldoExtraMensual;
    

    public Programador(String nombre, double sueldo, double sueldoMaximo, Date fechaAlta) {
        super(nombre, sueldo, sueldoMaximo, fechaAlta);
    }

    public double getSueldoExtraMensual() {
        return sueldoExtraMensual;
    }

    public void setSueldoExtraMensual(double sueldoExtraMensual) {
        this.sueldoExtraMensual = sueldoExtraMensual;
    }
}
