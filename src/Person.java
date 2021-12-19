package health;

import java.text.DecimalFormat;

public class Person {
    private String name;
    private int weight;
    private int height;
    private CalorieTracker calTracker;
    private WaterTracker waterTracker;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Person(String name, int height, int weight, int goal,int waterGoal){
        this.name = name;
        this.height = height;
        this.weight = weight;
        
        //goal is set to 0 or higher
        if (goal < 0) {
        	goal = 0;
        }
        calTracker = new CalorieTracker(goal);
        waterTracker = new WaterTracker(waterGoal);
    }

    public double getBMI(int height, int weight){
        double weight2 = weight;
        double BMI = (weight2/ height/ height) * 703;
        return Double.parseDouble(df.format(BMI));
    }
    
    public void addCalories(int calories) {
    	calTracker.addCalories(calories);
    }

    public int getCurrentCalories() {
    	return calTracker.getCurrentCalories();
    }
    
    public void burnCalories(int calories) {
    	calTracker.burnCalories(calories);
    }
    
   
    public boolean reachedCalLimit() {
    	return calTracker.reachedLimit();
    }
    public void setCalGoal(int goal) {
    	calTracker.setGoal(goal);
    }
    
    public int getCalGoal() {
    	return calTracker.getGoal();
    }
    public String getCalGoalMessage() {
    	return calTracker.getGoalMessage();
    }
    
    public String getWaterIntake() {
    	return waterTracker.toString();
    }
    
    public void addWater(double waterIntake) {
    	
    	if (waterIntake >= 0) {
    		waterTracker.addWater(waterIntake);
    	}
    }
    

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public String displayInfo(){
        StringBuilder string = new StringBuilder();
        string.append("Hello " + name + "!\n" + calTracker.toString());
        string.append("\n" + waterTracker.toString());
        return string.toString();
    }

    public String displayProfile(){
        StringBuilder string = new StringBuilder();
        string.append("Hello " + name + "!\n" +
                "Your height is: " + (height/12) + "'"+ (height%12) + " and your weight is: " + weight + " pounds. \n" +
                "Your BMI is: " + getBMI(height, weight));
        return string.toString();
    }

}
