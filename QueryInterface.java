import java.rmi.*;
import java.util.ArrayList;

public interface QueryInterface extends Remote {
    public String queryWorkout(String x) throws RemoteException;
}
