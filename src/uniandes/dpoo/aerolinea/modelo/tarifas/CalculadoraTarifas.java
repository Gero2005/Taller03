package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
public abstract class CalculadoraTarifas {
	protected static final double IMPUESTO = 0.28;

    public abstract double calcularCostoBase(Vuelo vuelo, Cliente cliente);

    public abstract double calcularPorcentajeDescuento(Cliente cliente);

    public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
        double costoBase = calcularCostoBase(vuelo, cliente);
        double descuento = calcularPorcentajeDescuento(cliente);
        return (int) (costoBase * (1 - descuento));
    }

    public int calcularValorImpuestos(int costoBase) {
        return (int) (costoBase * IMPUESTO);
    }


}
