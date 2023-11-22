package Model;

import java.util.Date;

public class Analista extends Empleado implements CalculoFechas{
    private double plusAnual;

    public Analista(String nombre, double sueldo, double sueldoMaximo, Date fechaAlta) {
        super(nombre, sueldo, sueldoMaximo, fechaAlta);
    }
    // Otro atributo personalizado


    public double getPlusAnual() {
        return plusAnual;
    }

    public void setPlusAnual(double plusAnual) {
        this.plusAnual = plusAnual;
    }
}