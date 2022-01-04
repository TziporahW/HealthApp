package health;

public class WaterTracker {

    private int goal;
    private double currentIntake;

    public WaterTracker(int goal) {
        this.goal = goal;
        currentIntake = 0;
    }

    public void addWater(double waterIntake) {

        if (waterIntake > 0) {
            currentIntake += waterIntake;
        }
    }

    public void setGoal(int goal) {

        if (goal > 0)
            this.goal = goal;
    }

    public int getGoal() {
        return goal;
    }

    public double getCurrentIntake() {

        return currentIntake;
    }

    public boolean reachedGoal() {

        return currentIntake >= goal;
    }

    public String getGoalMessage() {
        String message = "You haven't drank enough today!\nDrink more so you don't dehydrate!";
        if (reachedGoal()) {
            message = "Good job! You drank enough today";
        }
        return message;
    }

    public String toString() {
        return "Your water intake is " + currentIntake + " oz. \n"   + getGoalMessage();
    }
}