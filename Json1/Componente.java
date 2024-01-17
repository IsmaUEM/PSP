package Json1;

public class Componente {
    private String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "marca='" + marca + '\'' +
                '}';
    }

    public Componente(String marca) {
        this.marca = marca;
    }
}
