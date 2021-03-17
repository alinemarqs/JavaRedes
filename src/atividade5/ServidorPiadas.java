package atividade5;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;


public class ServidorPiadas {

    private static String piada = "";

    public static void main(String[] args) {

        try {

            DatagramSocket dgSocket = new DatagramSocket(40000);

            byte[] msg = new byte[128];

            DatagramPacket dgPacket = new DatagramPacket(msg, msg.length);

            dgSocket.receive(dgPacket);

            String solicitacao = new String(dgPacket.getData());
            System.out.println(solicitacao);

            InetAddress endereco = dgPacket.getAddress();
            int porta = dgPacket.getPort();
            Integer numPiada = 1;

            while (!piada.contains("Sem mais piadas para enviar")) {

                solicitacao = new String(dgPacket.getData());
                System.out.println(solicitacao);

                if (numPiada < 6) {
                    piada = retornaPiada(numPiada);
                    numPiada++;
                } else {
                    piada = "Sem mais piadas para enviar";
                }
                msg = piada.getBytes();
                dgPacket = new DatagramPacket(msg, msg.length, endereco, porta);
                dgSocket.send(dgPacket);

                dgSocket.receive(dgPacket);
            }

        } catch(Exception e) {

        }
    }

    public static String retornaPiada(Integer numPiada) {
        try {
            FileReader arquivo = new FileReader("src\\atividade5\\piadas.txt");
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            Boolean isTrue = Boolean.TRUE;
            String piada = "";

            do {
                piada = lerArquivo.readLine();
                if (piada.startsWith(numPiada + ".")) {
                    isTrue = false;
                }
            } while (isTrue || numPiada > 5);

            return piada;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
