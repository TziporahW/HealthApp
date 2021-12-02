package health;

import java.text.DecimalFormat;

public class Person {
    private String name;
    private int weight;
    private int height;
    private CalorieTracker calTracker;
    private WaterTracker waterTracker;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Person(String name, int height, int weight, int goal){
        this.name = name;
        this.height = height;
        this.weight = weight;
        
        //goal is set to 0 or higher
        if (goal < 0) {
        	goal = 0;
        }
        calTracker = new CalorieTracker(goal);
        waterTracker = new WaterTracker();
    }

    public double getBMI(int height, int weight){
        double weight2 = weight;
        double BMI = (weight2/ height/ height) * 703;
        return Double.parseDouble(df.format(BMI));
    }

    public CalorieTracker getCalorieTracker(){
        return calTracker;
    }
    
    public double getWaterIntake() {
    	return waterTracker.getCurrentIntake();
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
                "Your height is: " + height + " inches and your weight is: " + weight + " pounds. \n" +
                "Your BMI is: " + getBMI(height, weight));
        return string.toString();
    }

}
