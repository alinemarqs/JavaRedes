
package atividade6;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;

public interface IUrna extends Remote {

    public HashSet<Candidato> getCandidatos() throws RemoteException;

    public boolean votar(Integer numero) throws RemoteException;
}
