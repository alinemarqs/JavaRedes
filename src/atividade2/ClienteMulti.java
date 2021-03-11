/* Author: Aline Marques da Silva */

package atividade2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClienteMulti {
    
    private static Socket socket;
    
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    private static void sentToServer(String frase) {

        try {

            socket = new Socket("127.0.0.1", 50000);

            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());

            saida.writeUTF(frase);

            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        
        try {

            String frase = "";

            do {

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Digite uma frase ou [sair] pra encerrar: ");
                frase = br.readLine();

                if (!frase.equalsIgnoreCase("sair")) {
                    sentToServer(frase);
                }
            } while (!frase.equalsIgnoreCase("SAIR"));

            System.out.println("O programa foi encerrado");

        } catch(Exception e) {
            
        }
        
    }


    
}
