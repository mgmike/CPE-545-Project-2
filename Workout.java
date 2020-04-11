public class Workout {
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
