import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.*;

public class Server extends java.rmi.server.UnicastRemoteObject implements QueryInterface{
    private static String addr = "";
    private Registry registry;
    private static ArrayList<Workout> workoutList = new ArrayList<Workout>();

    @Override
    public String queryWorkout(String muscleGroup) throws RemoteException{
        ArrayList<String> muscleGroupFound = new ArrayList<String>();
        for (int i = 0; i<workoutList.size(); i++){
            if(workoutList.get(i).getMuscleGroup().equals(muscleGroup)){
                muscleGroupFound.add(workoutList.get(i).getName());
            }
        }

        System.out.println("Message recieved");
        if(muscleGroupFound.size() < 1){
                return null;
        } else {
            System.out.println("Returning stuff");
                return Arrays.toString(muscleGroupFound.toArray());
        }
    }

    public Server() throws RemoteException{
        try{
            if(addr.equals("")) {
                addr = (InetAddress.getLocalHost()).toString();
                //addr = "192.168.1.133";
            }
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

    private static void populateWorkoutList(){
        workoutList.add(new Workout("Bench Press", "Chest"));
        workoutList.add(new Workout("Squat", "Legs"));
        workoutList.add(new Workout("Arnold Press", "Shoulders"));
        workoutList.add(new Workout("Push Press", "Shoulders"));
        workoutList.add(new Workout("Overhead Press", "Shoulders"));
        workoutList.add(new Workout("Military Press", "Shoulders"));
        workoutList.add(new Workout("Upright Row", "Shoulders"));
        workoutList.add(new Workout("Pull-ups", "Back"));
        workoutList.add(new Workout("Dips", "Triceps"));
        workoutList.add(new Workout("Lateral Pull-down", "Back"));
        workoutList.add(new Workout("Seated Row", "Back"));
        workoutList.add(new Workout("Dumbbell Row", "Back"));
        workoutList.add(new Workout("Row", "Back"));
        workoutList.add(new Workout("Curls", "Bicep"));
        workoutList.add(new Workout("Overhead Extension", "Triceps"));
        workoutList.add(new Workout("Close-grip Bench", "Triceps"));
    }

    public static void main(String args[]){
        populateWorkoutList();
        if(args.length > 0){
            addr = args[0];
        }
        try{
                Server server = new Server();

        } catch (Exception e){
                e.printStackTrace();
                System.exit(1);
        }
    }
}
