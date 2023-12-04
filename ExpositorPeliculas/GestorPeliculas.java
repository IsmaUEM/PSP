package ExpositorPeliculas;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GestorPeliculas implements Runnable {

	private Socket clienteSocket;
	private List<Pelicula> biblioteca;

	public GestorPeliculas(Socket clienteSocket, List<Pelicula> biblioteca) {
		this.clienteSocket = clienteSocket;
		this.biblioteca = biblioteca;
	}

	@Override
	public void run() {
		try (ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream())) {

			while (true) {
				int opcion = in.readInt();

				switch (opcion) {
				case 1:
					consultarPorId(out, in);
					break;
				case 2:
					consultarPorTitulo(out, in);
					break;
				case 3:
					consultarPorDirector(out, in);
					break;
				case 4:
					agregarPelicula(out, in);
					break;
				default:
					System.out.println("Opción no válida");
					break;
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				clienteSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void consultarPorId(ObjectOutputStream out, ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		int id = in.readInt();

		synchronized (biblioteca) {
			for (Pelicula pelicula : biblioteca) {
				if (pelicula.getId() == id) {
					out.writeObject(pelicula);
					out.flush();
					return;
				}
			}
		}

		out.writeObject(null);
		out.flush();
	}

	private void consultarPorTitulo(ObjectOutputStream out, ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		String titulo = (String) in.readObject();
		Pelicula peliculaEncontrada = null;

		synchronized (biblioteca) {
			for (Pelicula pelicula : biblioteca) {
				if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
					peliculaEncontrada = pelicula;
					break;
				}
			}
		}

		out.writeObject(peliculaEncontrada);
		out.flush();
	}

	private void consultarPorDirector(ObjectOutputStream out, ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		String director = (String) in.readObject();
		List<Pelicula> peliculasEncontradas = new java.util.ArrayList<>();

		synchronized (biblioteca) {
			for (Pelicula pelicula : biblioteca) {
				if (pelicula.getDirector().equalsIgnoreCase(director)) {
					peliculasEncontradas.add(pelicula);
				}
			}
		}

		out.writeObject(peliculasEncontradas);
		out.flush();
	}

	private synchronized void agregarPelicula(ObjectOutputStream out, ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		Pelicula nuevaPelicula = (Pelicula) in.readObject();

		synchronized (biblioteca) {
			biblioteca.add(nuevaPelicula);
		}

		out.reset();
		out.writeUTF("Película agregada correctamente.");
		out.flush();
	}
}