package health;

public class WaterTracker {
	
	private final int goal = 64; 
	private double currentIntake;
	
	public WaterTracker() {
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
	
	public String toString() {
		return "Your water intake is " + currentIntake + " oz.";
	}
}
