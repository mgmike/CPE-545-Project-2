import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.*;

public class Server extends java.rmi.server.UnicastRemoteObject implements QueryInterface{
    private String addr;
    private Registry registry;
    private static ArrayList<Workout> workoutList = new ArrayList<Workout>();

    @Override
    public String queryWorkout(String muscleGroup) throws RemoteException{
        ArrayList<Workout> muscleGroupFound = new ArrayList<Workout>();
        for (int i = 0; i<workoutList.size(); i++){
            if(workoutList.get(i).getMuscleGroup().equals(muscleGroup)){
                muscleGroupFound.add(workoutList.get(i));
            }
        }

        if(muscleGroupFound.size() < 1){
                System.out.println("Message recieved");
                return "This name is not in the class roster";
        } else{
                return Arrays.toString(muscleGroupFound.toArray());
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
        try{
                Server server = new Server();

        } catch (Exception e){
                e.printStackTrace();
                System.exit(1);
        }
    }
}

class Workout{
    private String name;
    private String muscleGroup;

    public Workout(String n, String mg){
        name = n;
        muscleGroup = mg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getName(){
        return name;
    }

}