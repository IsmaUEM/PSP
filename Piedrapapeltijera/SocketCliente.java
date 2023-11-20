package Piedrapapeltijera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketCliente {
	public static final int PUERTO = 2018;
	public static final String IP_SERVER = "localhost";

	public static void main(String[] args) {
		try (Socket socketAlServidor = new Socket()) {
			InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		    socketAlServidor.connect(direccionServidor); 
		    BufferedReader entrada = new BufferedReader(new InputStreamReader(socketAlServidor.getInputStream()));
		    PrintWriter salida = new PrintWriter(socketAlServidor.getOutputStream(), true);

			new Thread(() -> {
				try {
					String mensajeServidor;
					while ((mensajeServidor = entrada.readLine()) != null) {
						System.out.println("Servidor: " + mensajeServidor);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();

			BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String eleccion = entradaUsuario.readLine();
				salida.println(eleccion);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}