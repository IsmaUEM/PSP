package Piedrapapeltijera;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {
    public static final int PUERTO = 2018;
    private static final int MAX_CLIENTES_ESPERANDO = 2;

    private static List<Hilopartida> clientesEsperando = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("      APLICACIÓN DE SERVIDOR CON HILOS     ");
		System.out.println("-------------------------------------------");

        int peticion = 0;

       try (ServerSocket servidor = new ServerSocket()){			
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			servidor.bind(direccion);

			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
			
			while (true) {
				Socket socketAlCliente = servidor.accept();
				System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");
	

                Hilopartida manejadorCliente = new Hilopartida(socketAlCliente);
                clientesEsperando.add(manejadorCliente);

                if (clientesEsperando.size() >= MAX_CLIENTES_ESPERANDO) {
                    iniciarPartida(); 
                }
			}			
		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error");
			e.printStackTrace();
		}

    }
    private static void iniciarPartida() {
        Hilopartida cliente1 = clientesEsperando.get(0);
        Hilopartida cliente2 = clientesEsperando.get(1);

        clientesEsperando.remove(cliente1);
        clientesEsperando.remove(cliente2);

        Partida partida = new Partida(cliente1, cliente2);
        Thread hiloPartida = new Thread(partida);
        hiloPartida.start();
    }
}
