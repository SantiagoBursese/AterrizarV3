package aterrizarv2.asientos;

public class Asiento {
    private ClaseAsiento clase;
    private CodigoAsiento codigo;
    private EstadoAsiento estado;
    private PrecioAsiento precio;
    private UbicacionAsiento ubicacion;

    public Asiento(ClaseAsiento clase, CodigoAsiento codigo, EstadoAsiento estado, PrecioAsiento precio, UbicacionAsiento ubicacion) {
        this.clase = clase;
        this.codigo = codigo;
        this.estado = estado;
        this.precio = precio;
        this.ubicacion = ubicacion;
    }

    public ClaseAsiento getClase() {
        return clase;
    }

    public CodigoAsiento getCodigo() {
        return codigo;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public PrecioAsiento getPrecio() {
        return precio;
    }

    public UbicacionAsiento getUbicacion() {
        return ubicacion;
    }

    
    
    
}
