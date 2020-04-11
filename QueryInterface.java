import java.rmi.*;

public interface QueryInterface extends Remote {
    public String queryWorkout(String x) throws RemoteException;
}
