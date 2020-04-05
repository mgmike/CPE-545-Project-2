import java.rmi.*;
import java.rmi.registry.*;

public class Client {
    public static void main(String args[]){
	QueryInterface Server;
	Registry registry;
	String serverAddr = args[0];
	String serverPort = args[1];
	String text = args[2];

	System.out.println("sending " + text + " to " + serverAddr + ":" + serverPort);

	try{
            registry = LocateRegistry.getRegistry(serverAddr,(new Integer(serverPort)).intValue());
            Server = (QueryInterface) (registry.lookup("Server"));
            //call the remote method
            System.out.println( Server.queryClassRoster(text) );
	} catch(RemoteException e){
            e.printStackTrace();
	} catch(NotBoundException e){
            System.err.println(e);
	}
    }
}