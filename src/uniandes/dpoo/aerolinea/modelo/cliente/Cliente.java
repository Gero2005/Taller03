package uniandes.dpoo.aerolinea.modelo.cliente;

public abstract class Cliente {

	protected Cliente() {
		
	}	
	public abstract String getTipoCliente();
	public abstract String getIdentificador();
	public void agregarTiquete(Tiquete tiquete) {
        // Implementación pendiente
    }

    public int calcularValorTotalTiquetes() {
        // Implementación pendiente
        return 0;
    }

    public void usarTiquetes(Vuelo vuelo) {
        // Implementación pendiente
    }
	
	

	

}
