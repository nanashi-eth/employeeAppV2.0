package Model;

import java.util.Date;

public class Programador extends Empleado implements CalculoFechas{
    private double sueldoExtraMensual;

    public Programador(String nombre, double sueldo, double sueldoMaximo, Date fechaAlta) {
        super(nombre, sueldo, sueldoMaximo, fechaAlta);
    }
    // Otro atributo personalizado, distinto en nombre y tipo al de Model.Analista

    // Constructor, getters y setters específicos para Model.Programador
}
