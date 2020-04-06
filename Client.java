import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;

public class Client {
    public static void main(String args[]){
	QueryInterface Server;
	Registry registry;
	String serverAddr = args[0];
	String serverPort = args[1];
	String workoutName = args[2];
        
	System.out.println("Sending " + workoutName + " to " + serverAddr + ":" + serverPort);

	try{
		registry = LocateRegistry.getRegistry(serverAddr, (new Integer(serverPort)));
		Server = (QueryInterface) (registry.lookup("Server"));
		ArrayList<Workout> workouts = Server.queryWorkout(workoutName);
		if(workouts.size() > 0) {
			System.out.print("Exercises that work out your " + workoutName + " are ");
			for (int i = 0; i < workouts.size(); i++) {
				System.out.print( workouts.get(i).getName() + ", ");
			}
			System.out.println(".");
		} else {
			System.out.println("There are no exercises that work out that muscle group.");
		}
	} catch(RemoteException e){
		e.printStackTrace();
	} catch(NotBoundException e){
		System.err.println(e);
	}
    }
}