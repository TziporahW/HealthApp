
import javax.swing.*;

public class Main {
    public static void main(String args[]){
        Person user = createPerson();

    }

    public static Person createPerson(){
        String name = JOptionPane.showInputDialog("Hello! What is your name? ");
        int height = Integer.parseInt(JOptionPane.showInputDialog("Please enter your height in inches: "));
        while(height < 36 || height > 100){
            height = Integer.parseInt(JOptionPane.showInputDialog("That is invalid! Please enter your height in inches: "));
        }
        int weight = Integer.parseInt(JOptionPane.showInputDialog("Please enter your weight in pounds: "));
        while(weight < 45 || weight > 1500){
            weight = Integer.parseInt(JOptionPane.showInputDialog("That is invalid! Please enter your weight in pounds: "));
        }
        int goal = Integer.parseInt(JOptionPane.showInputDialog("Please enter your desired calorie intake per day: "));
        while(goal < 0){
            goal = Integer.parseInt(JOptionPane.showInputDialog("Please enter your desired calorie intake per day: "));
        }
        Person user = new Person(name, height, weight, goal);
        return user;
    }
}
