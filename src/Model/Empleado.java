package Model;

import java.io.Serializable;
import java.util.Date;

public class Empleado {
    private transient String nombre;
    private transient int number;
    private double sueldo;
    private double sueldoMaximo;
    private Date fechaAlta;

    public Empleado(String nombre, double sueldo, double sueldoMaximo, Date fechaAlta, int number) {
        this.nombre = nombre;
        this.number = number;
        this.sueldo = sueldo;
        this.sueldoMaximo = sueldoMaximo;
        this.fechaAlta = fechaAlta;
    }

    public Empleado() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getSueldoMaximo() {
        return sueldoMaximo;
    }

    public void setSueldoMaximo(double sueldoMaximo) {
        this.sueldoMaximo = sueldoMaximo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
