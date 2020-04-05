import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.*;

public class Server extends java.rmi.server.UnicastRemoteObject implements QueryInterface{
    String addr;
    Registry registry;
    ArrayList<String> workoutList = new ArrayList<>(Arrays.asList("Bench Press Chest", "Squat Legs"));

    @Override
    public String queryWorkout(String x) throws RemoteException{
	ArrayList<String> lastNamesFound = new ArrayList<String>();
	for (int i = 0; i<workoutList.size(); i++){
            if(x.equals(workoutList.get(i).split("\\s+")[0])){
		lastNamesFound.add(workoutList.get(i).split("\\s+")[1]);
            }
	}

	if(lastNamesFound.size() < 1){
            return "this name is not in the class roster";
	} else{
            return Arrays.toString(lastNamesFound.toArray());
	}
    }

    public Server() throws RemoteException{
	try{
            addr = (InetAddress.getLocalHost()).toString();
	} catch(Exception e){
            System.out.println("cant get inet address");
        }
	int port = 3232;
	System.out.println("this address= " + addr + ", port= " + port);

	try{
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("Server", this);
	} catch(RemoteException e){
            System.out.println("remote exception " + e);
	}
    }

    public static void main(String args[]){
	try{
            Server server = new Server();
	} catch (Exception e){
            e.printStackTrace();
            System.exit(1);
	}
    }
}