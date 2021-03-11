/* Author: Aline Marques da Silva */

package atividade1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private static Socket socket;
    private static ServerSocket server;
    
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {
        
        try {
        
            //Criar porta de recepção
            server = new ServerSocket(50000);
            socket = server.accept();
    
            //Criar os fluxos de entrada e saída
            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());
            
            //Recebimento do valor
            String cpf = entrada.readUTF();
            System.out.println(cpf);

            boolean cpfvalido;

            //Validação e Processamento do valor
            if (cpf.isBlank() || cpf.isEmpty()) {
                cpfvalido = false;
            } else {
                cpfvalido = ValidarCpf.isCPF(cpf);
            }

            //Envio dos dados (resultado)
            saida.writeBoolean(cpfvalido);

            socket.close();
            
        } catch(Exception e) {
            
        }
    }
}
