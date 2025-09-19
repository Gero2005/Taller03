package uniandes.dpoo.aerolinea.modelo;
import java.util.Collection;
import uniandes.dpoo.aerolinea.calculadora.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	private String fecha;
    private Ruta ruta;
    private Avion avion;
    private Collection<Tiquete> tiquetes;
    
    public Vuelo(String fecha, Ruta ruta, Avion avion) {
        this.fecha = fecha;
        this.ruta = ruta;
        this.avion = avion;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public Ruta getRuta() {
        return ruta;
    }
    
    public Avion getAvion() {
        return avion;
    }
    
    public Collection<Tiquete> getTiquetes() {
        return tiquetes;
    }

    
    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) {
        // lógica pendiente: usar la calculadora de tarifas, generar tiquetes y sumarlos
        return 0;
    }
    
    
    
	

}
