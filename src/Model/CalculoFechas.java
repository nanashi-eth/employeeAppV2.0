package Model;

import java.util.Calendar;
import java.util.Date;

public interface CalculoFechas {
    // Constantes en español
    int DIA_DEL_MES = Calendar.DAY_OF_MONTH;
    int MES = Calendar.MONTH;
    int ANIO = Calendar.YEAR;

    // Métodos para controlar si se cumplen meses o años
    default boolean cumpleMes(Date fechaAlta) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaAlta);
        int mesAlta = calendar.get(MES);

        Calendar fechaActual = Calendar.getInstance();
        int mesActual = fechaActual.get(MES);

        return mesAlta == mesActual;
    }

    default boolean cumpleAnio(Date fechaAlta) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaAlta);
        int anioAlta = calendar.get(ANIO);

        Calendar fechaActual = Calendar.getInstance();
        int anioActual = fechaActual.get(ANIO);

        return anioAlta == anioActual;
    }
}
