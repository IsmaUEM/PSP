package Piedrapapeltijera;

import java.io.IOException;

public class Partida implements Runnable {
    private Hilopartida cliente1;
    private Hilopartida cliente2;
    private int victoriasCliente1;
    private int victoriasCliente2;

    public Partida(Hilopartida cliente1, Hilopartida cliente2) {
        this.cliente1 = cliente1;
        this.cliente2 = cliente2;
        this.victoriasCliente1 = 0;
        this.victoriasCliente2 = 0;
    }

    public void run() {
        try {
            while (victoriasCliente1 < 3 && victoriasCliente2 < 3) {
                cliente1.getSalida().println("La partida ha comenzado Jugador 1. Elige entre piedra, papel o tijera.");
                cliente2.getSalida().println("La partida ha comenzado Jugador 2. Elige entre piedra, papel o tijera.");

                String eleccionCliente1 = cliente1.getEntrada().readLine();
                String eleccionCliente2 = cliente2.getEntrada().readLine();

                String resultadoRonda = determinarGanador(eleccionCliente1, eleccionCliente2);

                cliente1.getSalida().println("Tú elegiste " + eleccionCliente1 + ". " + resultadoRonda);
                cliente2.getSalida().println("Tú elegiste " + eleccionCliente2 + ". " + resultadoRonda);

                if (resultadoRonda.equals("¡Jugador 1 gana!")) {
                    victoriasCliente1++;
                } else if (resultadoRonda.equals("¡Jugador 2 gana!")) {
                    victoriasCliente2++;
                }


                cliente1.getSalida().println("Victorias - Jugador 1: " + victoriasCliente1 + ", Jugador 2: " + victoriasCliente2);
                cliente2.getSalida().println("Victorias - Jugador 1: " + victoriasCliente1 + ", Jugador 2: " + victoriasCliente2);
            }

            if (victoriasCliente1 >= 3) {
                cliente1.getSalida().println("¡Felicidades! ¡Eres el ganador!");
                cliente2.getSalida().println("¡Lo siento! Has perdido.");
            } else {
                cliente1.getSalida().println("¡Lo siento! Has perdido.");
                cliente2.getSalida().println("¡Felicidades! ¡Eres el ganador!");
            }

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
