import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static final int PUERTO = 2017;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("      APLICACION DE SERVIDOR      ");
		System.out.println("----------------------------------");

		InputStreamReader entrada = null;

		PrintStream salida = null;

		Socket socketAlCliente = null;

		InetSocketAddress direccion = new InetSocketAddress(PUERTO);

		try (ServerSocket serverSocket = new ServerSocket()) {

			serverSocket.bind(direccion);

			int peticion = 0;

			while (true) {
				System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);

				socketAlCliente = serverSocket.accept();
				System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");

				entrada = new InputStreamReader(socketAlCliente.getInputStream());
				BufferedReader bf = new BufferedReader(entrada);

				String stringRecibido = bf.readLine();// operacion-1num-2num

				System.out.println("SERVIDOR: Me ha llegado del cliente: " + stringRecibido);

				String[] operadores = stringRecibido.split("-");

				int operacion = Integer.parseInt(operadores[0]);
				int num1 = Integer.parseInt(operadores[1]);
				int num2 = Integer.parseInt(operadores[2]);
				int resultado = 0;
				if (operacion == 1) {
					// +
					resultado = num1 + num2;
					System.out.println("El resultado de la suma es: " + resultado);
				}
				if (operacion == 2) {
					// -
					resultado = num1 - num2;
					System.out.println("El resultado de la resta es: " + resultado);
				}
				if (operacion == 3) {
					// *
					resultado = num1 * num2;
					System.out.println("El resultado de la multiplicacion es: " + resultado);
				}
				if (operacion == 4) {
					// ../
					resultado = num1 / num2;
					System.out.println("El resultado de la division es: " + resultado);
				}
				salida = new PrintStream(socketAlCliente.getOutputStream());
				salida.print(resultado);

				socketAlCliente.close();
			}

		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error -> " + e);
			e.printStackTrace();
		}
	}

}