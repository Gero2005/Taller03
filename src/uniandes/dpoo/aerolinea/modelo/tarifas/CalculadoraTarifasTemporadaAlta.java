package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	private static final int COSTO_POR_KM = 1000;

    @Override
    public double calcularCostoBase(Vuelo vuelo, Cliente cliente) {
    	Aeropuerto aero1 = vuelo.getRuta().getOrigen();
        Aeropuerto aero2 = vuelo.getRuta().getDestino();
        double distancia = Aeropuerto.calcularDistancia(aero1,aero2);
        return distancia * COSTO_POR_KM;
    }

    @Override
    public double calcularPorcentajeDescuento(Cliente cliente) {
        
        return 0.0;
    }
	

}
