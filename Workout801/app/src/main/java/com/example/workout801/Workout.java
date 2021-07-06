package com.example.workout801;


public class Workout {
    private String name = null;//운동 이름
    private String description = null;//설명
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String toString(){return this.name;}

    public Workout(String name, String description){
        this.name = name;
        this.description = description;
    }
    public static final Workout[] workouts = {
            new Workout("The Limb Loosener", "5 Handstand Push-ups\n10 1-legged Squarts\n15 Pull-ups"),
            new Workout("Core Agony", "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n 100 Squarts"),
            new Workout("The Wimp Special", "5 Pull-ups\n10 Push-ups\n15 Squarts"),
            new Workout("Strength and Length", "500 meter run\n21*1.5 pood kettleball swings\n15 Pull-ups")
    };
}
