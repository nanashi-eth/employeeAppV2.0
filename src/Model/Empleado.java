package Model;

import java.io.Serializable;
import java.util.Date;

public class Empleado implements Serializable {
    private transient String nombre;
    private double sueldo;
    private double sueldoMaximo;
    private Date fechaAlta;

    // Constructor, getters y setters para los atributos
}
