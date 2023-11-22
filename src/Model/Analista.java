package Model;

import java.util.Date;

public class Analista extends Empleado implements CalculoFechas{
    private double plusAnual;
    private String tipoAnalisis; // Nuevo atributo para Analista

    public Analista(String nombre, double sueldo, double sueldoMaximo, Date fechaAlta, String tipoAnalisis) {
        super(nombre, sueldo, sueldoMaximo, fechaAlta);
        this.tipoAnalisis = tipoAnalisis;
    }

    public double getPlusAnual() {
        return plusAnual;
    }

    public void setPlusAnual(double plusAnual) {
        this.plusAnual = plusAnual;
    }

    public String getTipoAnalisis() {
        return tipoAnalisis;
    }

    public void setTipoAnalisis(String tipoAnalisis) {
        this.tipoAnalisis = tipoAnalisis;
    }
}