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

    public double getCurrentIntake() {

        return currentIntake;
    }

    public boolean reachedGoal() {

        return currentIntake >= goal;
    }

    public String getGoalMessage() {
        String message = "You haven't drank enough today! Drink more! You don't want to dehydrate!";
        if (reachedGoal()) {
            message = "Good job! You've drinken enough today";
        }
        return message;
    }

    public String toString() {
        return "Your water intake is " + currentIntake + " oz. \n"   + getGoalMessage();
    }
}