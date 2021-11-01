public class Person {
    private String name;
    private int weight;
    private int height;
    private CalorieTracker tracker;

    public Person(String name, int height, int weight, int goal){
        this.name = name;
        tracker = new CalorieTracker(goal);
        this.height = height;
        this.weight = weight;
    }

    public double getBMI(){
        return (weight/height/height) * 703;
    }

    public CalorieTracker getCalorieTracker(){
        return tracker;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public String displayInfo(){
        StringBuilder string = new StringBuilder();
        string.append("Hello " + name + "!" + tracker.display());
        return string.toString();
    }

}
