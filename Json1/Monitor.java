package Json1;

public class Monitor extends Componente{
    private String nombre;
    private String resolucion;

    @Override
    public String toString() {
        return "Monitor{" +
                "nombre='" + nombre + '\'' +
                ", resolucion='" + resolucion + '\'' +
                '}';
    }

    public Monitor(String marca, String nombre, String resolucion) {
        super(marca);
        this.nombre = nombre;
        this.resolucion = resolucion;
    }
}
