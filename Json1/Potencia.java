package Json1;

public class Potencia{
    private int valor;
    private String unidad;

    @Override
    public String toString() {
        return "Potencia{" +
                "valor=" + valor +
                ", unidad='" + unidad + '\'' +
                '}';
    }

    public Potencia(int valor, String unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }
}
