package health;

import java.util.ArrayList;

import javax.swing.*;

public class Main {
	public static void main(String args[]) {
		Person user = createPerson();
		ArrayList<String> food = new ArrayList<String>();

		menu(user, food);
	}

	public static Person createPerson() {
		String name = JOptionPane.showInputDialog("Hello! What is your name? ");
		int height = Integer.parseInt(JOptionPane.showInputDialog("Please enter your height in inches: "));
		while (height < 36 || height > 100) {
			height = Integer
					.parseInt(JOptionPane.showInputDialog("That is invalid! Please enter your height in inches: "));
		}
		int weight = Integer
				.parseInt(JOptionPane.showInputDialog("Please enter your weight rounded to the nearest pound: "));
		while (weight < 45 || weight > 1500) {
			weight = Integer
					.parseInt(JOptionPane.showInputDialog("That is invalid! Please enter your weight in pounds: "));
		}

		int goal = Integer.parseInt(JOptionPane.showInputDialog("Please enter your desired calorie intake per day: "));
		while (goal < 0) {
			goal = Integer.parseInt(JOptionPane.showInputDialog("Please enter your desired calorie intake per day: "));
		}

		int addWaterGoal = JOptionPane.showConfirmDialog(null, "Would you like to add a water intake goal?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		int waterGoal; 
		if (addWaterGoal == JOptionPane.YES_OPTION) {
			waterGoal= Integer
					.parseInt(JOptionPane.showInputDialog("Please enter your desired water intake per day: "));
			while (waterGoal < 0) {
				waterGoal = Integer
						.parseInt(JOptionPane.showInputDialog("Please enter your desired water intake per day: "));

			}
		} else {
			waterGoal = 64;
			JOptionPane.showMessageDialog(null, "You should drink at least 64 oz today.");
		}
		Person user = new Person(name, height, weight, goal,waterGoal);
		JOptionPane.showMessageDialog(null, user.displayProfile());

		return user;
	}

	public static void menu(Person user, ArrayList<String> food) {
		int choice;
		CalorieTracker tracker = user.getCalorieTracker();

		do {

			choice = Integer.parseInt(JOptionPane.showInputDialog("Main Menu Options:\n" + "1. Update Calories \n"
					+ "2. Add Water Intake \n" + "3. Go to Update Menu  \n" + "4. View Progress \n"
					+ "5. View Profile \n" + "6. End Day \n" + "Enter menu option (1-6) you would like to perform: "));

			while (choice > 6 || choice < 1) {
				choice = Integer.parseInt(JOptionPane.showInputDialog("Invalid Entry! Enter a number from 1-6: \n"
						+ "Main Menu Options:\n" + "1. Update Calories \n" + "2. Add Water Intake \n"
						+ "3. Go to Update Menu  \n" + "4. View Progress \n" + "5. View Profile \n" + "6. End Day \n"
						+ "Enter menu option (1-6) you would like to perform: "));
			}

			switch (choice) {

			case 1:
				int calChoice = Integer.parseInt(JOptionPane.showInputDialog(
						"Options: \n" + "1. Add Calories \n" + "2. Decrease Calories through Exercise "));
					while(calChoice != 1 && calChoice !=2 ) {
						calChoice = Integer.parseInt(JOptionPane.showInputDialog(
								"Options: \n" + "1. Add Calories \n" + "2. Decrease Calories through Exercise "));				
					}
				if (calChoice == 1) {
					food.add(JOptionPane.showInputDialog("What did you eat?"));
					int caloriesToAdd = Integer.parseInt(JOptionPane.showInputDialog("How many calories? "));
					tracker.addCalories(caloriesToAdd);
					JOptionPane.showMessageDialog(null,
							"Your current calorie intake is: " + tracker.getCurrentCalories() + " calories.");
				} else {
					int exercise = JOptionPane.showConfirmDialog(null, "Did you do any exercise?", "",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (exercise == JOptionPane.YES_OPTION) {
						int caloriesBurned = caloriesBurnedCalc();
						tracker.burnCalories(caloriesBurned);
						JOptionPane.showMessageDialog(null,
								"You burned " + caloriesBurned + " calories! Excellent job!!"
										+ "\nYour current calorie intake is now: " + tracker.getCurrentCalories());
					} else {
						JOptionPane.showMessageDialog(null, "No exercise added. Returning you home...");
					}
				}

				if (tracker.reachedLimit()) {
					JOptionPane.showMessageDialog(null, tracker.getGoalMessage());
				}
				break;
			case 2:
				addWater(user);
				break;

			case 3:
				updateMenu(user, tracker);
				break;

			case 4:
				JOptionPane.showMessageDialog(null, user.displayInfo());
				break;

			case 5:
				JOptionPane.showMessageDialog(null, user.displayProfile());
				break;

			default:

				if (food.size() > 0) {
					int seeFoodRep = JOptionPane.showConfirmDialog(null,
							"END OF DAY REPORT:\n" + user.displayInfo()
									+ "\n\nWould you like to view a list of what you ate today?",
							"", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (seeFoodRep == JOptionPane.YES_OPTION) {
						StringBuilder allFoods = new StringBuilder();
						for (int i = 0; i < food.size(); i++) {
							allFoods.append("\n" + (i + 1) + ". " + food.get(i));
						}
						JOptionPane.showMessageDialog(null, "Today's Food: \n" + allFoods);
					}
				} else {
					JOptionPane.showMessageDialog(null, "END OF DAY REPORT:\n" + user.displayInfo());
				}
				JOptionPane.showMessageDialog(null, "Exiting.\nSee you tomorrow!");

			}
		} while (choice != 6);
	}

	public static int caloriesBurnedCalc() {
		String level;
		int minutes = 0;

		do {
			minutes = Integer.parseInt(JOptionPane.showInputDialog("How long did you exercise for? (minutes) "));
		} while (minutes < 0);

		do {
			level = (JOptionPane.showInputDialog(
					"How intense was your exercise?\n" + "L for Low\n" + "M for Moderate\n" + "V for Vigorous"));
		} while (!level.toUpperCase().equals("L") && !level.toUpperCase().equals("M")
				&& !level.toUpperCase().equals("V"));

		// we can add in based on gender
		int caloriesBurned = 0;
		if (level.toUpperCase().equals("L")) {
			caloriesBurned = minutes * 5;
		} else if (level.toUpperCase().equals("M")) {
			caloriesBurned = minutes * 7;
		} else {
			caloriesBurned = minutes * 15;
		}

		return caloriesBurned;
	}

	public static void updateMenu(Person user, CalorieTracker tracker) {
		int choice;
		do {
			choice = Integer.parseInt(
					JOptionPane.showInputDialog("Update Menu Options:\n" + "1. Update Goal \n" + "2. Update Profile \n"
							+ "3. Go back to Main Menu \n" + "Enter menu option (1-3) you would like to perform: "));

			while (choice > 3 || choice < 1) {
				choice = Integer.parseInt(JOptionPane.showInputDialog("Invalid Entry! Enter a number from 1-3: \n"
						+ "Menu Options:\n" + "1. Update Goal \n" + "2. Update Profile \n"
						+ "3. Go back to Main Menu \n" + "Enter menu option (1-3) you would like to perform: "));

			}
			switch (choice) {

			case 1:
				int goal = Integer.parseInt(JOptionPane.showInputDialog(
						"Your current daily calorie intake goal is " + user.getCalorieTracker().getGoal() + " calories."
								+ "\nWhat do you want want your new goal to be? "));

				while (goal < 0) {
					goal = Integer.parseInt(JOptionPane.showInputDialog("Invalid Goal! \nEnter a positive number."));
				}

				tracker.setGoal(goal);
				JOptionPane.showMessageDialog(null, "Calorie goal has been updated to " + goal + " calories.");
				break;

			case 2:
				int profileOption = Integer
						.parseInt(JOptionPane.showInputDialog("What would you like to update?\n" + "1. Weight \n"
								+ "2. Height \n" + "Enter the profile option (1-2) you would like to update: "));

				while (profileOption > 2 || profileOption < 1) {
					profileOption = Integer.parseInt(JOptionPane.showInputDialog(
							"Invalid Entry! Enter number from 1-2: \n" + "1. Weight \n" + "2. Height"));
				}
				switch (profileOption) {

				case 1:
					int weight = Integer
							.parseInt(JOptionPane.showInputDialog("Please enter your new weight in pounds: "));
					user.setWeight((weight));
					JOptionPane.showMessageDialog(null, "Your weight has been updated to " + weight + " pounds!");
					break;

				default:
					int height = Integer
							.parseInt(JOptionPane.showInputDialog("Please enter your new height in inches: "));
					user.setHeight(height);
					JOptionPane.showMessageDialog(null, "Your height has been updated to " + height + " inches!");
					break;
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Back to Main Menu");

			}
		} while (choice != 3);
	}

	public static void addWater(Person user) {

		double water = Double.parseDouble(JOptionPane.showInputDialog("Enter water intake in oz: "));
		if (water > 0) {
			user.addWater(water);
			JOptionPane.showMessageDialog(null, user.getWaterIntake());
		}
	}

}
