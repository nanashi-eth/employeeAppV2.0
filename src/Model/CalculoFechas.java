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
        int diaAlta = calendar.get(DIA_DEL_MES);

        Calendar fechaActual = Calendar.getInstance();
        int mesActual = fechaActual.get(MES);
        int diaActual = fechaActual.get(DIA_DEL_MES);

        return mesAlta == mesActual && diaAlta == diaActual;
    }

    default boolean cumpleAnio(Date fechaAlta) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaAlta);
        int anioAlta = calendar.get(ANIO);
        int mesAlta = calendar.get(MES);

        Calendar fechaActual = Calendar.getInstance();
        int anioActual = fechaActual.get(ANIO);
        int mesActual = fechaActual.get(MES);

        return anioAlta == anioActual && mesAlta == mesActual;
    }
}
