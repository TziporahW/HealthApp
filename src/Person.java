package health;

import java.text.DecimalFormat;

public class Person {
	private String name;
	private String selectedMeasurement;
	private int weight;
	private int height;
	private CalorieTracker calTracker;
	private WaterTracker waterTracker;
	private static final DecimalFormat df = new DecimalFormat("0.00");

	public Person(String name, String selectedMeasurement, int height, int weight, int goal, int waterGoal) {
		this.name = name;
		this.selectedMeasurement = selectedMeasurement;
		this.height = height;
		this.weight = weight;

		// goal is set to 0 or higher
		if (goal < 0) {
			goal = 0;
		}
		calTracker = new CalorieTracker(goal);
		waterTracker = new WaterTracker(waterGoal);
	}

	public double getBMI() {
		double BMI;
		int usedW = weight;
		int usedH = height;
		if (selectedMeasurement.equals("Inches/Pounds")) {
			BMI = (usedW / usedH / usedH) * 703;
		} else {
			// convert height to meters
			double heightMeters = usedH / 100;
			BMI = (usedW / heightMeters / heightMeters);
		}
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
	
	public String getSelectedMeasurement() {
		return selectedMeasurement;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String displayInfo() {
		StringBuilder string = new StringBuilder();
		string.append("Hello " + name + "!\n" + calTracker.toString());
		string.append("\n" + waterTracker.toString());
		return string.toString();
	}

	public String displayProfile() {
		StringBuilder string = new StringBuilder();
		if (selectedMeasurement.equals("Inches/Pounds")) {
			string.append("Hello " + name + "!\n" + "Your height is: " + (height / 12) + "'" + (height % 12)
					+ " and your weight is: " + weight + " pounds. \n" + "Your BMI is: " + getBMI());
		}
		else {
			string.append("Hello " + name + "!\n" + "Your height is: " + height + " centimeters"
					+ " and your weight is: " + weight + " kilograms. \n" + "Your BMI is: " + getBMI());
		}
		return string.toString();
	}

}
