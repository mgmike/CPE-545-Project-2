import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.*;

public class Server extends java.rmi.server.UnicastRemoteObject implements QueryInterface{
    String addr;
    Registry registry;
    ArrayList<String> workoutList = new ArrayList<>(Arrays.asList("Bench Press Chest", "Squat Legs", "Arnold Press Shoulders", 
                                                                "Push Press Shoulders", "Overhead Press Shoulders", 
                                                                "Military Press Shoulders", "Upright Row Shoulders", 
                                                                "Pull-ups Back", "Dips Triceps", "Lateral Pull-down Back", 
                                                                "Seated Row Back", "Dumbbell Row Back", "Row Back", "Curls Bicep", 
                                                                "Barbell Row Back", "Preacher Curls Biceps", 
                                                                "Incline Bench Curls Biceps", "Incline Bench Press Chest", 
                                                                "Decline Bench Press Chest", "Flies Chest", "Skull-crusher Triceps", 
                                                                "Close-grip Bench Triceps", "Overhead Extension Triceps"));

    @Override
    public String queryWorkout(String x) throws RemoteException{
	ArrayList<String> workoutFound = new ArrayList<String>();
	for (int i = 0; i < workoutList.size(); i++) {
            if (x.equals(workoutList.get(i).split("\\s+")[0])){
		workoutFound.add(workoutList.get(i).split("\\s+")[1]);
            }
	}
	if (workoutFound.size() < 1)
            return "This exercise is not in the list of known workouts!";
	else
            return Arrays.toString(workoutFound.toArray());
    }

    public Server() throws RemoteException{
	try{
            addr = (InetAddress.getLocalHost()).toString();
	} catch(Exception e){
            System.out.println("Cant get inet address!");
        }
	int port = 3232;
	System.out.println("This address: " + addr + ", port: " + port);
	try{
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("Server", this);
	} catch(RemoteException e){
            System.out.println("Remote exception: " + e);
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