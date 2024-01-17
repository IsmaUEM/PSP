package Json1;

public class TarjetaGrafica extends Componente{
    private String modelo;
    private String precio;
    private String potencia;

    @Override
    public String toString() {
        return "TarjetaGrafica{" +
                "modelo='" + modelo + '\'' +
                ", precio='" + precio + '\'' +
                ", potencia='" + potencia + '\'' +
                '}';
    }

    public TarjetaGrafica(String marca, String modelo, String precio, String potencia) {
        super(marca);
        this.modelo = modelo;
        this.precio = precio;
        this.potencia = potencia;
    }
}
