package Json1;

import java.util.List;

public class ordenador {
    private String nombre;
    private double precio;

    private List<periferico> listaPerifericos;

    private List<Componente> listaComponentes;

    @Override
    public String toString() {
        return "ordenador{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", listaPerifericos=" + listaPerifericos +
                ", listaComponentes=" + listaComponentes +
                '}';
    }

    public ordenador(String nombre, double precio, List<periferico> listaPerifericos, List<Componente> listaComponentes) {
        this.nombre = nombre;
        this.precio = precio;
        this.listaPerifericos = listaPerifericos;
        this.listaComponentes = listaComponentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<periferico> getListaPerifericos() {
        return listaPerifericos;
    }

    public void setListaPerifericos(List<periferico> listaPerifericos) {
        this.listaPerifericos = listaPerifericos;
    }

    public List<Componente> getListaComponentes() {
        return listaComponentes;
    }

    public void setListaComponentes(List<Componente> listaComponentes) {
        this.listaComponentes = listaComponentes;
    }
}
