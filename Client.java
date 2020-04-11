import java.rmi.*;
import java.rmi.registry.*;

public class Client {
    public static void main(String args[]){
	QueryInterface Server;
	Registry registry;
	String serverAddr = args[0];
	String serverPort = args[1];
	String wrkoutName = args[2];

	System.out.println("Sending " + wrkoutName + " to " + serverAddr + ":" + serverPort);

	try{
            registry = LocateRegistry.getRegistry(serverAddr, (new Integer(serverPort)));
            Server = (QueryInterface) (registry.lookup("Server"));
            System.out.println(Server.queryWorkout(wrkoutName));
	} catch(RemoteException e){
            e.printStackTrace();
	} catch(NotBoundException e){
            System.err.println(e);
	}
    }
}