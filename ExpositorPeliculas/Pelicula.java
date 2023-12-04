package ExpositorPeliculas;

import java.io.Serializable;

public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String titulo;
	private String director;
	private double precio;

	public Pelicula(int id, String titulo, String director, double precio) {
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDirector() {
		return director;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Pelicula{" + "id=" + id + ", titulo='" + titulo + '\'' + ", director='" + director + '\'' + ", precio="
				+ precio + '}';
	}
}

