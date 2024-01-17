package Json1;

public class Procesador extends Componente{
    private String modelo;
    private String velocidad;

    @Override
    public String toString() {
        return "Procesador{" +
                "modelo='" + modelo + '\'' +
                ", velocidad='" + velocidad + '\'' +
                '}';
    }

    public Procesador(String marca, String modelo, String velocidad) {
        super(marca);
        this.modelo = modelo;
        this.velocidad = velocidad;
    }
}
