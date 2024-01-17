// File: Periferico.java
package Json1;

public class periferico {
    private String nombre;
    private String marca;

    @Override
    public String toString() {
        return "periferico{" +
                "nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    public periferico(String nombre, String marca) {
        this.nombre = nombre;
        this.marca = marca;
    }



}
