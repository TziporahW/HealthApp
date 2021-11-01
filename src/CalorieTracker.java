public class CalorieTracker {
    private int goal;
    private int currentCalorieIntake;

    public CalorieTracker(int goal){
        this.goal = goal;
        this.currentCalorieIntake = 0;
    }

    public int getCurrentCalories(){
        return currentCalorieIntake;
    }

    public boolean reachedLimit(){
        return (currentCalorieIntake >= goal);
    }

    public String getGoalMessage() {
        String message = "You haven't eaten so much today! lets eat more";
        if(currentCalorieIntake > goal){
            message = "You've exceeded your limit for today :(";
        } else if(currentCalorieIntake == goal){
            message = "You've reached your limit! Stop eating.";
        } else if(currentCalorieIntake/goal > .75){
            message = "You are 3/4 of the way to your limit.";
        } else if(currentCalorieIntake/goal > .50){
            message = "You are more than halfway to your limit";
        } else if(currentCalorieIntake/goal > .25){
            message = "You're a quarter of the way to your daily limit.";
        }
        return message;
    }

    public void updateCalorieIntake(int caloriesToAdd){
        currentCalorieIntake += caloriesToAdd;
    }

    public String display(){
        StringBuilder string = new StringBuilder();
        string.append("Your daily goal is " + goal + " and your current calorie intake is " + currentCalorieIntake);
        return string.toString();
    }
}
