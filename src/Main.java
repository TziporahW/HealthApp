import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        Person user = createPerson();

        menu(user);

    }

    public static Person createPerson() {
        String name = JOptionPane.showInputDialog("Hello! What is your name? ");
        int height = Integer.parseInt(JOptionPane.showInputDialog("Please enter your height in inches: "));
        while (height < 36 || height > 100) {
            height = Integer
                    .parseInt(JOptionPane.showInputDialog("That is invalid! Please enter your height in inches: "));
        }
        int weight = Integer.parseInt(JOptionPane.showInputDialog("Please enter your weight rounded to the nearest pound: "));
        while (weight < 45 || weight > 1500) {
            weight = Integer
                    .parseInt(JOptionPane.showInputDialog("That is invalid! Please enter your weight in pounds: "));
        }

        int goal = Integer.parseInt(JOptionPane.showInputDialog("Please enter your desired calorie intake per day: "));
        while (goal < 0) {
            goal = Integer.parseInt(JOptionPane.showInputDialog("Please enter your desired calorie intake per day: "));
        }
        Person user = new Person(name, height, weight, goal);
        JOptionPane.showMessageDialog(null, user.displayProfile());

        return user;
    }

    public static void menu(Person user) {
        int choice;
        CalorieTracker tracker = user.getCalorieTracker();

        do {

            choice =  Integer.parseInt(JOptionPane.showInputDialog("Menu Options:\n"
                    + "1. Add Calories \n"
                    + "2. View Progress \n"
                    + "3. Update Goal \n"
                    + "4. View Profile \n"
                    + "5. Update Profile \n"
                    + "6. End Day \n"
                    + "Enter menu option (1-6) you would like to perform: "));

            while(choice > 6 || choice < 1) {
                choice =  Integer.parseInt(JOptionPane.showInputDialog("Invalid Entry! Enter a number from 1-6: "));
            }

            switch(choice) {

                case 1:
                    int caloriesToAdd = Integer.parseInt(JOptionPane.showInputDialog("How many calories did you eat since we saw you last? "));
                    tracker.addCalories(caloriesToAdd);
                    JOptionPane.showMessageDialog(null, tracker.getGoalMessage());

                    if (tracker.reachedLimit()) {
                        JOptionPane.showMessageDialog(null, tracker.getGoalMessage());
                    }
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, user.displayInfo()
                            + "\n" + tracker.getGoalMessage());
                    break;
                case 3:
                    int goal = Integer.parseInt(JOptionPane.showInputDialog("Your current daily calorie intake goal is " + user.getCalorieTracker().getGoal()
                            + "\nWhat do you want want your new goal to be? "));
                    tracker.setGoal(goal);
                    JOptionPane.showMessageDialog(null, "Calorie goal has been updated to " + goal);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, user.displayProfile());
                    break;
                case 5:
                    int profileOption =  Integer.parseInt(JOptionPane.showInputDialog("What would you like to update?\n"
                            + "1. Weight \n"
                            + "2. Height \n"
                            + "Enter the profile option (1-2) you would like to update: "));

                    while(profileOption > 2 || profileOption < 1) {
                        profileOption =  Integer.parseInt(JOptionPane.showInputDialog("Invalid Entry! Enter number from 1-2: "));
                    }
                    switch(profileOption) {
                        case 1:
                            int weight = Integer.parseInt(JOptionPane.showInputDialog("Please enter your new weight in pounds: "));
                            user.setWeight((weight));
                            JOptionPane.showMessageDialog(null, "Your weight has been updated to " + weight +" pounds!");
                            break;
                        default:
                            int height = Integer.parseInt(JOptionPane.showInputDialog("Please enter your new height in inches: "));
                            user.setHeight(height);
                            JOptionPane.showMessageDialog(null, "Your height has been updated to " + height + " inches!");
                            break;
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "END OF DAY REPORT:\n" +  user.displayInfo()
                            + "\n" + tracker.getGoalMessage());
                    JOptionPane.showMessageDialog(null, "Exciting.\nSee you tomorrow!");
            }
        } while(choice!=6);
    }
}
