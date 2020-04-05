import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.*;

public class Server extends java.rmi.server.UnicastRemoteObject implements QueryInterface{
    ArrayList<String> classRoster = new ArrayList<>(Arrays.asList("Matthew Aquiles", "Yingsheng Bao",
								"AravindBaskaran", "Rudresh Bhatt", "Amit Bhorania", "Bryan Charalambous",
								"Akanksha Chatra", "Zhiyuan Chen", "Yingsheng Cui", "Constantine Davantzis",
                                                                "Shivani Devoor", "Prerakkumar Doshi", "Elaina Ferrer", "Aakanksha Gokhe",
								"Dillon Guarino", "Poonam Gupta", "Yabin Han", "Yang Li", "Nito Lugo",
                                                                "Brian Madden", "Madjid Mousavi", "Sneha Nagmoti", "Kashmira Pardeshi",
                                                                "Chintan Patelæ", "Michael Sasso", "Milin Shah", "Alan Steinberg", "Tanvir Talukder",
								"Lei Tang", "Nathalie Tran", "Lei Yan", "Jingwei Zhang"));

    String addr;
    Registry registry;

    public String queryWorkout(String x) throws RemoteException{
	ArrayList<String> lastNamesFound = new ArrayList<String>();
	for (int i = 0; i<classRoster.size(); i++){
            if(x.equals(classRoster.get(i).split("\\s+")[0])){
		lastNamesFound.add(classRoster.get(i).split("\\s+")[1]);
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