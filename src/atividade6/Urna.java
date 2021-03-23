
package atividade6;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;

public class Urna extends UnicastRemoteObject implements IUrna {

    private HashMap<Candidato, Integer> votos;

    protected Urna() throws RemoteException { }

    @Override
    public HashSet<Candidato> getCandidatos() throws RemoteException {
        return new HashSet<>(this.votos.keySet());
    }

    @Override
    public boolean votar(Integer numero) throws RemoteException {

        this.votos.keySet().stream().filter((candidato) ->
                (candidato.getNumero().equals(numero))).forEach((candidato) -> {
            this.votos.put(candidato, this.votos.get(candidato) + 1);
        });

        return true;
    }

    public void adicionarCandidatos(Candidato candidato) {

        if (this.votos == null) {
            this.votos = new HashMap<>();
        }

        this.votos.put(candidato, 0);
    }

    public void calcularParcial() {

        System.out.println("\nurna@localhost$> Parcial: ");

        this.votos.keySet().stream().forEach((candidato) -> {
            System.out.println(String.format("Candidato %s "
                    + "recebeu %s votos", candidato.getNome(), this.votos.get(candidato)));
        });
    }
}
