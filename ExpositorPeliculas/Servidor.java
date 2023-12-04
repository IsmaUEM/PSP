package ExpositorPeliculas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	private static List<Pelicula> biblioteca = new ArrayList<>();

	public static void main(String[] args) {
		cargarPeliculasPreestablecidas();

		try (ServerSocket serverSocket = new ServerSocket(12345)) {
			System.out.println("Servidor esperando conexiones...");

			while (true) {
				Socket clienteSocket = serverSocket.accept();
				System.out.println("Cliente conectado: " + clienteSocket);

				Thread hiloCliente = new Thread(new GestorPeliculas(clienteSocket, biblioteca));
				hiloCliente.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void cargarPeliculasPreestablecidas() {
		biblioteca.add(new Pelicula(1, "Pelicula1", "Director1", 10.0));
		biblioteca.add(new Pelicula(2, "Pelicula2", "Director2", 15.0));
		biblioteca.add(new Pelicula(3, "Pelicula3", "Director1", 12.0));
		biblioteca.add(new Pelicula(4, "Pelicula4", "Director3", 18.0));
		biblioteca.add(new Pelicula(5, "Pelicula5", "Director2", 20.0));
	}

	public static synchronized List<Pelicula> getBiblioteca() {
		return new ArrayList<>(biblioteca);
	}

	public static synchronized void agregarPelicula(Pelicula nuevaPelicula) {
		biblioteca.add(nuevaPelicula);
	}
}

