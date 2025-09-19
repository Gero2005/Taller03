package uniandes.dpoo.aerolinea.persistencia;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {
	@Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws Exception {
       
        System.out.println("Cargando aerolínea desde JSON: " + archivo);
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws Exception {
        System.out.println("Salvando aerolínea en JSON: " + archivo);
    }
}
