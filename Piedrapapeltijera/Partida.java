package Piedrapapeltijera;

import java.io.IOException;

public class Partida implements Runnable {
    private Hilopartida cliente1;
    private Hilopartida cliente2;

    public Partida(Hilopartida cliente1, Hilopartida cliente2) {
        this.cliente1 = cliente1;
        this.cliente2 = cliente2;
    }

   
public void run() {
    try {
        cliente1.getSalida().println("La partida ha comenzado. Elige entre piedra, papel o tijera.");
        cliente2.getSalida().println("La partida ha comenzado. Elige entre piedra, papel o tijera.");

        String eleccionCliente1 = cliente1.getEntrada().readLine();
        String eleccionCliente2 = cliente2.getEntrada().readLine();

        String resultadoRonda = determinarGanador(eleccionCliente1, eleccionCliente2);

        cliente1.getSalida().println("Tú elegiste " + eleccionCliente1 + ". " + resultadoRonda);
        cliente2.getSalida().println("Tú elegiste " + eleccionCliente2 + ". " + resultadoRonda);


    } catch (IOException e) {
        e.printStackTrace();
    }
}

private String determinarGanador(String eleccionCliente1, String eleccionCliente2) {
   
    if (eleccionCliente1.equals(eleccionCliente2)) {
        return "¡Es un empate!";
    } else if ((eleccionCliente1.equals("piedra") && eleccionCliente2.equals("tijera")) ||
               (eleccionCliente1.equals("tijera") && eleccionCliente2.equals("papel")) ||
               (eleccionCliente1.equals("papel") && eleccionCliente2.equals("piedra"))) {
        return "¡Jugador 1 gana!";
    } else {
        return "¡Jugador 2 gana!";
    }
}


}
