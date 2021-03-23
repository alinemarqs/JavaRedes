
package atividade6;

import java.awt.*;
import java.rmi.Naming;
import java.util.Scanner;
import java.util.Set;

public class Cliente implements Runnable{

    private IUrna urna;

    @Override
    public void run() {
        try {
            this.urna = this.getUrnaService();
            System.out.println("cliente@localhost$> Urna localizada, pronto para votar!");
        } catch (Exception ex) {
            System.err.println(ex);
            System.exit(1);
        }

        this.capturarVotos();
    }

    public void capturarVotos() {

        boolean exit = false;

        try {
            final Scanner teclado = new Scanner(System.in);

            System.out.println("##########################################");
            System.out.println("## Escolha um candidato e em seguida    ##");
            System.out.println("## digite o numero correspondente a ele ##");
            System.out.println("## para contabilizar 1 voto             ##");
            System.out.println("## Exemplo:                             ##");
            System.out.println("##    Candidato Biro, numero 13       ##");
            System.out.println("## Digite 13 no console:                ##");
            System.out.println("## cliente@localhost$> 13 [enter]       ##");
            System.out.println("##########################################");

            while (!exit) {

                System.out.println("\n## Candidatos ##");
                this.mostrarCandidatos();
                System.out.println("################");

                System.out.print("cliente@localhost$> ");
                final String voto = Console.readCommand(teclado);

                try {
                    Integer numero = Integer.parseInt(voto);
                    this.urna.votar(numero);
                } catch (NumberFormatException ex) {
                    System.err.println("cliente@localhost$> voto invalido!");
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private void mostrarCandidatos() throws Exception {

        final Set<Candidato> candidatos = this.urna.getCandidatos();

        candidatos.stream().forEach((candidato) -> {
            System.out.println(String.format("-> Nome %s, número %s",
                    candidato.getNome(), candidato.getNumero()));
        });
    }

    private IUrna getUrnaService() throws Exception {
        return (IUrna) Naming.lookup("rmi://localhost/Urna");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Cliente());
    }
}
