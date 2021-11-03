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

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        if (goal > 0) {
            this.goal = goal;
        }
    }

    public boolean reachedLimit(){
        return (currentCalorieIntake >= goal);
    }

    public String getGoalMessage() {
        String message = "You haven't eaten so much today! Let's eat more!";
        if(currentCalorieIntake > goal){
            message = "You've exceeded your limit for today :(\nBetter luck next time.";
        } else if(currentCalorieIntake == goal){
            message = "You've reached 100% of your daily limit! Stop eating.";
        } else if((double)currentCalorieIntake/goal > .75){
            message = "You are more than 75% of the way to your daily limit)";
        } else if((double)currentCalorieIntake/goal > .50){
            message = "You are more than 50% of the way to your daily limit";
        } else if((double)currentCalorieIntake/goal > .25){
            message = "You are more than 25% of the way to your daily limit.";
        }
        return message;
    }


    public void addCalories(int caloriesToAdd){

        if (caloriesToAdd > 0) {
            currentCalorieIntake += caloriesToAdd;
        }
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("Your daily goal is " + goal + " and your current calorie intake is " + currentCalorieIntake + ".");
        return string.toString();
    }
}
