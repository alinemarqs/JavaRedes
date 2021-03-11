package atividade3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteSwing extends JFrame {

    private static Socket socket;
    private static DataInputStream entrada;
    private static ObjectOutputStream saida;

    private JButton enviarButton;
    private JPanel mainPanel;
    private JLabel labelNome;
    private JLabel labelIdade;
    private JLabel labelRetorno;

    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextArea txtAreaRetorno;

    public static void main(String[] args) {

        JFrame frame = new ClienteSwing("Nova Pessoa:");
        frame.setVisible(true);

    }

    public ClienteSwing(String title) {
        super(title);

        enviarButton.addActionListener(this::btEnviarActionPerformed);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(800, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
    }

    private void btEnviarActionPerformed(ActionEvent evt) {

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(txtNome.getText());
        pessoa.setIdade(Integer.parseInt(txtIdade.getText()));

        try {

            socket = new Socket("127.0.0.1", 50000);

            entrada = new DataInputStream(socket.getInputStream());
            saida = new ObjectOutputStream(socket.getOutputStream());

            saida.writeObject(pessoa);

            txtAreaRetorno.setText("Recebeu do servidor: " + "\n" + entrada.readUTF());

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




