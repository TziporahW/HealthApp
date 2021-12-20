import java.text.DecimalFormat;

public class Person {
    private String name;
    private String selectedMeasurement;
    private int weight;
    private int height;
    private CalorieTracker calTracker;
    private WaterTracker waterTracker;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int carbs;
    private int protein;
    private int fruitVeggies;

    public Person(String name, String selectedMeasurement, int height, int weight, int goal, int waterGoal) {
        this.name = name;
        this.selectedMeasurement = selectedMeasurement;
        this.height = height;
        this.weight = weight;
        this.carbs = 0;
        this.protein = 0;
        this.fruitVeggies = 0;

        // goal is set to 0 or higher
        if (goal < 0) {
            goal = 0;
        }
        calTracker = new CalorieTracker(goal);
        waterTracker = new WaterTracker(waterGoal);
    }

    public double getBMI() {
        double BMI;

        if (selectedMeasurement.equals("Inches/Pounds")) {
            BMI =  (weight / (double) (height * height)) * 703;

        } else {
            // convert height to meters
            double heightInMeters = height / 100;
            BMI =  (weight / (double) (heightInMeters * heightInMeters));
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

    public void addCarbs(int carbs){
        this.carbs += carbs;
    }

    public int getCarbs(){
        return carbs;
    }

    public void addProtein(int protein){
        this.protein += protein;
    }

    public int getProtein(){
        return protein;
    }

    public void addFruitVeggies(int fruitVeggies){
        this.fruitVeggies += fruitVeggies;
    }

    public int getFruitVeggies(){
        return fruitVeggies;
    }

    public double getCarbsPercentage(){
        double percentage = (Double.valueOf(getCarbs()) / calTracker.getCurrentCalories()) * 100;
        return Double.parseDouble(df.format(percentage));
    }

    public double getProteinPercentage(){
        double percentage = (Double.valueOf(getProtein()) / calTracker.getCurrentCalories()) * 100;
        return Double.parseDouble(df.format(percentage));
    }

    public double getFruitsVeggiesPercentage(){
        double percentage = (Double.valueOf(getFruitVeggies()) / calTracker.getCurrentCalories()) * 100;
        return Double.parseDouble(df.format(percentage));
    }



    public String displayInfo() {
        StringBuilder string = new StringBuilder();
        string.append("Hello " + name + "!\n" + calTracker.toString());
        string.append("\n" + waterTracker.toString());
        string.append("\nYou ate: " + getCarbsPercentage() + "% carbs, " + getProteinPercentage() + "% protein, and " + getFruitsVeggiesPercentage() + "% fruit/vegetables.");
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