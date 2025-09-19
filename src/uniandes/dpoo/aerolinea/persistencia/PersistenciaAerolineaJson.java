package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

/**
 * Persistencia en formato JSON para la información "estable" de la aerolínea:
 * aviones, rutas y vuelos.
 */
public class PersistenciaAerolineaJson implements IPersistenciaAerolinea
{
    private static final String AVIONES = "aviones";
    private static final String RUTAS = "rutas";
    private static final String VUELOS = "vuelos";

    private static final String NOMBRE_AVION = "nombre";
    private static final String CAPACIDAD = "capacidad";

    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String ORIGEN = "origen";
    private static final String DESTINO = "destino";

    private static final String FECHA = "fecha";
    private static final String AVION = "avion";

    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException
    {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        // Aviones
        JSONArray jAviones = raiz.optJSONArray(AVIONES);
        if (jAviones != null) {
            for (int i = 0; i < jAviones.length(); i++)
            {
                JSONObject jAvion = jAviones.getJSONObject(i);
                String nombre = jAvion.getString(NOMBRE_AVION);
                int capacidad = jAvion.getInt(CAPACIDAD);
                Avion avion = new Avion(nombre, capacidad);
                aerolinea.agregarAvion(avion);
            }
        }

        // Rutas
        JSONArray jRutas = raiz.optJSONArray(RUTAS);
        if (jRutas != null) {
            for (int i = 0; i < jRutas.length(); i++)
            {
                JSONObject jRuta = jRutas.getJSONObject(i);
                String codigo = jRuta.getString(CODIGO_RUTA);
                String origenCodigo = jRuta.getString(ORIGEN);
                String destinoCodigo = jRuta.getString(DESTINO);

                // Recuperar aeropuertos ya existentes en la aerolínea
                Aeropuerto origen = aerolinea.getAeropuerto(origenCodigo);
                Aeropuerto destino = aerolinea.getAeropuerto(destinoCodigo);

                if (origen == null || destino == null) {
                    throw new InformacionInconsistenteException(
                        "Ruta con aeropuerto inexistente: " + origenCodigo + " o " + destinoCodigo);
                }

                // Las horas se ponen por defecto, si no están en el JSON
                Ruta ruta = new Ruta(codigo, origen, destino, "0000", "0000");
                aerolinea.agregarRuta(ruta);
            }
        }

        // Vuelos
        JSONArray jVuelos = raiz.optJSONArray(VUELOS);
        if (jVuelos != null) {
            for (int i = 0; i < jVuelos.length(); i++)
            {
                JSONObject jVuelo = jVuelos.getJSONObject(i);
                String codigoRuta = jVuelo.getString(CODIGO_RUTA);
                String fecha = jVuelo.getString(FECHA);
                String nombreAvion = jVuelo.getString(AVION);

                Ruta ruta = aerolinea.getRuta(codigoRuta);
                Avion avion = aerolinea.getAvion(nombreAvion);

                if (ruta == null || avion == null)
                    throw new InformacionInconsistenteException(
                        "Vuelo con ruta o avión inexistente: ruta=" + codigoRuta + " avion=" + nombreAvion);

                Vuelo vuelo = new Vuelo(fecha, ruta, avion);
                aerolinea.agregarVuelo(vuelo);
            }
        }
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException
    {
        JSONObject raiz = new JSONObject();

        // Aviones
        JSONArray jAviones = new JSONArray();
        for (Avion avion : aerolinea.getAviones())
        {
            JSONObject jAvion = new JSONObject();
            jAvion.put(NOMBRE_AVION, avion.getNombre());
            jAvion.put(CAPACIDAD, avion.getCapacidad());
            jAviones.put(jAvion);
        }
        raiz.put(AVIONES, jAviones);

        // Rutas
        JSONArray jRutas = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas())
        {
            JSONObject jRuta = new JSONObject();
            jRuta.put(CODIGO_RUTA, ruta.getCodigoRuta());
            jRuta.put(ORIGEN, ruta.getOrigen().getCodigo());
            jRuta.put(DESTINO, ruta.getDestino().getCodigo());
            jRutas.put(jRuta);
        }
        raiz.put(RUTAS, jRutas);

        // Vuelos
        JSONArray jVuelos = new JSONArray();
        for (Vuelo vuelo : aerolinea.getVuelos())
        {
            JSONObject jVuelo = new JSONObject();
            jVuelo.put(CODIGO_RUTA, vuelo.getRuta().getCodigoRuta());
            jVuelo.put(FECHA, vuelo.getFecha());
            jVuelo.put(AVION, vuelo.getAvion().getNombre());
            jVuelos.put(jVuelo);
        }
        raiz.put(VUELOS, jVuelos);

        try (PrintWriter pw = new PrintWriter(archivo))
        {
            raiz.write(pw, 2, 0);
        }
    }
}
