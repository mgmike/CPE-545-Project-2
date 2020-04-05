import java.rmi.*;

public interface QueryInterface extends Remote {
    public String queryClassRoster(String x) throws RemoteException;
}