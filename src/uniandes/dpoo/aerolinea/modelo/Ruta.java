package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    private String codigoRuta;
    private String horaSalida;
    private String horaLlegada;
    private Aeropuerto origen;
    private Aeropuerto destino;

    public Ruta(String codigoRuta, Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada) {
        this.codigoRuta = codigoRuta;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public String getCodigoRuta() { return codigoRuta; }
    public Aeropuerto getOrigen() { return origen; }
    public Aeropuerto getDestino() { return destino; }
    public String getHoraSalida() { return horaSalida; }
    public String getHoraLlegada() { return horaLlegada; }

    public static int getMinutos(String horaCompleta) {
        return Integer.parseInt(horaCompleta) % 100;
    }

    public static int getHoras(String horaCompleta) {
        return Integer.parseInt(horaCompleta) / 100;
    }

    public static int getDuracion(String horaSalida, String horaLlegada) {
        int salida = getHoras(horaSalida) * 60 + getMinutos(horaSalida);
        int llegada = getHoras(horaLlegada) * 60 + getMinutos(horaLlegada);
        int duracion = llegada - salida;
        if (duracion < 0) duracion += 24 * 60;
        return duracion;
    }
}
