public class Workout {
    private String name;
    private String muscleGroup;
    private int sets;
    private int reps;

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

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
