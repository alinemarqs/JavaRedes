package atividade5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ClientePiada {

    public static void main(String[] args) {
        
        try {
            
            DatagramSocket dgSocket = new DatagramSocket();
            byte[] msg;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            InetAddress endereco = InetAddress.getByName("localhost");

            System.out.println("BEM VINDO A PIADAS! PARA RECEBER A PIADA APERTE ENTER");
            br.readLine();

            String solicitacao = "mande uma piada";
            msg = solicitacao.getBytes();

            DatagramPacket dgPacket = new DatagramPacket(msg, msg.length, endereco, 40000);
            dgSocket.send(dgPacket);

            msg = new byte[128];
            dgPacket = new DatagramPacket(msg, msg.length);
            dgSocket.receive(dgPacket);
            
            String piada = new String(dgPacket.getData());
            System.out.println("A piada é: " + piada);

            while (!piada.equals("Sem mais piadas para enviar")) {

                System.out.println("PARA RECEBER OUTRA PIADA APERTE ENTER");
                br.readLine();

                msg = solicitacao.getBytes();
                dgPacket = new DatagramPacket(msg, msg.length, endereco, 40000);
                dgSocket.send(dgPacket);

                msg = new byte[128];
                dgPacket = new DatagramPacket(msg, msg.length);
                dgSocket.receive(dgPacket);

                piada = new String(dgPacket.getData());
                System.out.println("A piada é: " + piada);

                if (piada.trim().equals("Sem mais piadas para enviar")) {
                    break;
                }
            }
        } catch(Exception e) {
        }
    }
}
