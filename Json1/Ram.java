package Json1;

public class Ram extends Componente{
    private int id;
    private String tipo;
    private String capacidad;

    @Override
    public String toString() {
        return "Ram{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", capacidad='" + capacidad + '\'' +
                '}';
    }

    public Ram(String marca, int id, String tipo, String capacidad) {
        super(marca);
        this.id = id;
        this.tipo = tipo;
        this.capacidad = capacidad;
    }
}
