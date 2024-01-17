package Json1;

public class FuenteAlimentacion extends Componente{
    private String modelo;

    private Potencia potencia;

    @Override
    public String toString() {
        return "FuenteAlimentacion{" +
                "modelo='" + modelo + '\'' +
                ", potencia=" + potencia +
                '}';
    }

    public FuenteAlimentacion(String marca, String modelo, Potencia potencia) {
        super(marca);
        this.modelo = modelo;
        this.potencia = potencia;
    }
}
