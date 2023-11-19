package Piedrapapeltijera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Hilopartida {
    private Socket socketAlCliente;
    private BufferedReader entrada;
    private PrintWriter salida;

    public Hilopartida(Socket socketAlCliente) {
        this.socketAlCliente = socketAlCliente;
        try {
            this.entrada = new BufferedReader(new InputStreamReader(socketAlCliente.getInputStream()));
            this.salida = new PrintWriter(socketAlCliente.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getEntrada() {
        return entrada;
    }

    public PrintWriter getSalida() {
        return salida;
    }

}
