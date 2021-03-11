package atividade4;

import atividade2.ServidorMulti;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSwingMulti extends Thread {

    private Socket socket;

    public ServidorSwingMulti(Socket conexao) {
        this.socket = conexao;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream entrada =
                    new ObjectInputStream(socket.getInputStream());

            Pessoa p = (Pessoa) entrada.readObject();

            String mensagem;

            if (p != null) {
                mensagem = "Dados recebidos corretamente!";
            } else {
                mensagem = "Houve um problema no servidor!";
            }

            DataOutputStream saida =
                    new DataOutputStream(socket.getOutputStream());

            System.out.println("Nome: " + p.getNome() + ", Idade: " + p.getIdade());
            saida.writeUTF(mensagem);

            socket.close();

        } catch(Exception e) {

        }
    }

    public static void main(String[] args) {

        try {

            ServerSocket server = new ServerSocket(50000);

            while(true) {

                Socket conexao = server.accept();
                ServidorSwingMulti sThread = new ServidorSwingMulti(conexao);
                System.out.println("Algum cliente se conectou...");
                sThread.start();

            }
        } catch (Exception e) {}
    }
}
