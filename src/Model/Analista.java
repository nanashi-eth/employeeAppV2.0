package Model;

import java.io.Serializable;
import java.util.Date;

public class Analista extends Empleado implements CalculoFechas, Serializable {
    private double plusAnual;
    private String tipoAnalisis; // Nuevo atributo para Analista

    public Analista(String nombre, double sueldo, double sueldoMaximo, Date fechaAlta, String tipoAnalisis, int number) {
        super(nombre, sueldo, sueldoMaximo, fechaAlta, number);
        this.tipoAnalisis = tipoAnalisis;
    }
    public Analista() {
        super(); // Llama al constructor de la clase base
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

    // Método para modificar el salario a partir del plus anual
    public void actualizarSalarioConPlus() {
            double nuevoSalario = getSueldo() + plusAnual;
        setSueldo(nuevoSalario);
    }
}