import java.util.Calendar;
import java.util.Date;

public interface CalculoFechas {
    // Constantes en español
    int DIA_DEL_MES = Calendar.DAY_OF_MONTH;
    int MES = Calendar.MONTH;
    int ANIO = Calendar.YEAR;

    // Métodos para controlar si se cumplen meses o años
    default boolean cumpleMes(Date fechaApertura) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaApertura);
        int mesApertura = calendar.get(MES);

        Calendar fechaActual = Calendar.getInstance();
        int mesActual = fechaActual.get(MES);

        return mesApertura == mesActual;
    }

    default boolean cumpleAnio(Date fechaApertura) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaApertura);
        int anioApertura = calendar.get(ANIO);

        Calendar fechaActual = Calendar.getInstance();
        int anioActual = fechaActual.get(ANIO);

        return anioApertura == anioActual;
    }
}
