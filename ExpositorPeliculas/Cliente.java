package ExpositorPeliculas;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws ClassNotFoundException {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)
        ) {
            while (true) {
                mostrarMenu();
                int opcion = scanner.nextInt();

                out.writeInt(opcion);
                out.flush();

                switch (opcion) {
                    case 1:
                        consultarPorId(out, in, scanner);
                        break;
                    case 2:
                        consultarPorTitulo(out, in, scanner);
                        break;
                    case 3:
                        consultarPorDirector(out, in, scanner);
                        break;
                    case 4:
                        agregarPelicula(out, in, scanner);
                        break;
                    case 5:
                        System.out.println("Saliendo de la aplicación.");
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarMenu() {
        System.out.println("----- Menú -----");
        System.out.println("1. Consultar película por ID");
        System.out.println("2. Consultar película por título");
        System.out.println("3. Consultar películas por director");
        System.out.println("4. Agregar película");
        System.out.println("5. Salir");
        System.out.print("Ingrese su opción: ");
    }

    private static void consultarPorId(ObjectOutputStream out, ObjectInputStream in, Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.print("Ingrese el ID de la película: ");
        int id = scanner.nextInt();
        out.writeInt(id);
        out.flush();

        Pelicula pelicula = (Pelicula) in.readObject();
        if (pelicula != null) {
            System.out.println("Pelicula encontrada: " + pelicula);
        } else {
            System.out.println("Pelicula no encontrada.");
        }
    }

    private static void consultarPorTitulo(ObjectOutputStream out, ObjectInputStream in, Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.print("Ingrese el título de la película: ");
        String titulo = scanner.next();
        out.writeObject(titulo);
        out.flush();

        Pelicula pelicula = (Pelicula) in.readObject();
        if (pelicula != null) {
            System.out.println("Pelicula encontrada: " + pelicula);
        } else {
            System.out.println("Pelicula no encontrada.");
        }
    }

    private static void consultarPorDirector(ObjectOutputStream out, ObjectInputStream in, Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.print("Ingrese el director de la película: ");
        String director = scanner.next();
        out.writeObject(director);
        out.flush();
        List<Pelicula> peliculas = (List<Pelicula>) in.readObject();
        if (peliculas != null && !peliculas.isEmpty()) {
            System.out.println("Películas encontradas: ");
            for (Pelicula pelicula : peliculas) {
                System.out.println(pelicula);
            }
        } else {
            System.out.println("No se encontraron películas para ese director.");
        }
    }

    private static void agregarPelicula(ObjectOutputStream out, ObjectInputStream in, Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.print("Ingrese el ID de la película: ");
        int id = scanner.nextInt();
        System.out.print("Ingrese el título de la película: ");
        String titulo = scanner.next();
        System.out.print("Ingrese el director de la película: ");
        String director = scanner.next();
        System.out.print("Ingrese el precio de la película: ");
        double precio = scanner.nextDouble();

        Pelicula nuevaPelicula = new Pelicula(id, titulo, director, precio);

        out.writeObject(nuevaPelicula);
        out.flush();

        String mensaje = in.readUTF();
        System.out.println(mensaje);
    }
}
