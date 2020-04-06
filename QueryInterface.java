import java.rmi.*;
import java.util.ArrayList;

public interface QueryInterface extends Remote {
    public ArrayList<Workout> queryWorkout(String x) throws RemoteException;
}
