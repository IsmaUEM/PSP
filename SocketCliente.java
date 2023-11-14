import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import com.formdev.flatlaf.FlatDarkLaf;


public class SocketCliente extends JFrame implements ActionListener {

    private JTextField textField1, textField2, resultadoField;
    private JButton sumarButton, restarButton, multiplicarButton, dividirButton, salirButton;

    public static final int PUERTO = 2017;
    public static final String IP_SERVER = "192.168.0.29";

    public SocketCliente() {
        super("Calculadora");

        // Inicializa componentes
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        resultadoField = new JTextField(10);
        sumarButton = new JButton("+");
        restarButton = new JButton("-");
        multiplicarButton = new JButton("*");
        dividirButton = new JButton("/");
        salirButton = new JButton("Salir");

        // Configura el diseño
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Añade componentes
        add(new JLabel("Número 1:"));
        add(textField1);
        add(new JLabel("Número 2:"));
        add(textField2);
        add(new JLabel("Resultado:"));
        add(resultadoField);
        add(sumarButton);
        add(restarButton);
        add(multiplicarButton);
        add(dividirButton);
        add(salirButton);

        // Asigna controladores de eventos
        sumarButton.addActionListener(this);
        restarButton.addActionListener(this);
        multiplicarButton.addActionListener(this);
        dividirButton.addActionListener(this);
        salirButton.addActionListener(this);

        // Configura la ventana
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }

    public static void main(String[] args) {
        // Configura FlatLaf con tema oscuro
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Carga la interfaz gráfica
        SwingUtilities.invokeLater(() -> new SocketCliente());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String numero1 = textField1.getText();
        String numero2 = textField2.getText();

        if (e.getSource() == sumarButton) {
            realizarOperacion("1", numero1, numero2);
        } else if (e.getSource() == restarButton) {
            realizarOperacion("2", numero1, numero2);
        } else if (e.getSource() == multiplicarButton) {
            realizarOperacion("3", numero1, numero2);
        } else if (e.getSource() == dividirButton) {
            realizarOperacion("4", numero1, numero2);
        } else if (e.getSource() == salirButton) {
            System.exit(0);
        }
    }

    private void realizarOperacion(String operacion, String num1, String num2) {
        try (Socket socketAlServidor = new Socket()) {
            InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);

            System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
            socketAlServidor.connect(direccionServidor);
            System.out.println("CLIENTE: Conexión establecida... a " + IP_SERVER + " por el puerto " + PUERTO);

            PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
            salida.println(operacion + "-" + num1 + "-" + num2);

            InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
            BufferedReader bf = new BufferedReader(entrada);

            System.out.println("CLIENTE: Esperando el resultado del servidor...");

            String resultado = bf.readLine();
            resultadoField.setText(resultado);
        } catch (UnknownHostException ex) {
            System.err.println("CLIENTE: No encuentro el servidor en la dirección " + IP_SERVER);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("CLIENTE: Error de entrada/salida");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("CLIENTE: Error -> " + ex);
            ex.printStackTrace();
        }
    }
}
