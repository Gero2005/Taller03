package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
	private static final int COSTO_POR_KM_NATURAL = 600;
    private static final int COSTO_POR_KM_CORPORATIVO = 900;

    private static final double DESCUENTO_PEQ = 0.02;
    private static final double DESCUENTO_GRANDE = 0.10;

    @Override
    public double calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        Aeropuerto aero1 = vuelo.getRuta().getOrigen();
        Aeropuerto aero2 = vuelo.getRuta().getDestino();
        double distancia = Aeropuerto.calcularDistancia(aero1,aero2);
        
        String tipoCliente = cliente.getTipoCliente();

        if ("NATURAL".equalsIgnoreCase(tipoCliente)) {
            return distancia * COSTO_POR_KM_NATURAL;
        } else {
            return distancia * COSTO_POR_KM_CORPORATIVO;
        }
    }

    @Override
    public double calcularPorcentajeDescuento(Cliente cliente) {
        
    	if (cliente instanceof ClienteCorporativo) {
            ClienteCorporativo corp = (ClienteCorporativo) cliente;

            switch (corp.getTamanoEmpresa()) {
                case ClienteCorporativo.GRANDE:
                    return DESCUENTO_GRANDE; 
                case ClienteCorporativo.PEQUENA:
                    return DESCUENTO_PEQ; // 
                default:
                    return 0.0; 
            }
        }

        return 0.0; 
    }

}
