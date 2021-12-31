package health;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.*;

public class Main {
	public static void main(String args[]) {

		LocalDate todayDate = LocalDate.now();
		String today = todayDate.getMonth() + " " + todayDate.getDayOfMonth() + ", " + todayDate.getYear();

		Person user = createPerson(today);

		ArrayList<String> food = new ArrayList<String>();

		menu(user, food, today);
	}

	public static Person createPerson(String today) {

		JOptionPane.showMessageDialog(null,
				"Hello! Today, " + today + ", is a fabulous day! \n" + "Let's create a profile!");
		String name = JOptionPane.showInputDialog("What is your name? ");
		Object[] measurements = { "Inches/Pounds", "Centimeters/Kilograms" };
		Object pick = JOptionPane.showInputDialog(null, "Height and weight in?", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, measurements, measurements[0]);

		int height;
		int weight;
		String selectedMeasurement = (String) pick;

		if (selectedMeasurement.equals("Inches/Pounds")) {

			height = Integer.parseInt(JOptionPane.showInputDialog("Please enter your height in inches: "));

			while (height < 36 || height > 100) {
				height = Integer
						.parseInt(JOptionPane.showInputDialog("That is invalid! Please enter your height in inches: "));
			}

			weight = Integer
					.parseInt(JOptionPane.showInputDialog("Please enter your weight rounded to the nearest pound: "));
			while (weight < 45 || weight > 1500) {
				weight = Integer
						.parseInt(JOptionPane.showInputDialog("That is invalid! Please enter your weight in pounds: "));
			}
		} else {
			height = Integer
					.parseInt(JOptionPane.showInputDialog("Please enter your height in centimeters (no decimals): "));

			while (height < 91 || height > 254) {
				height = Integer.parseInt(JOptionPane
						.showInputDialog("That is invalid! Please enter your height in centimeters (no decimals): "));
			}

			weight = Integer.parseInt(JOptionPane
					.showInputDialog("Please enter your weight rounded to the nearest kilograms (no decimals): "));
			while (weight < 20 || weight > 680) {
				weight = Integer.parseInt(JOptionPane
						.showInputDialog("That is invalid! Please enter your weight in kilograms (no decimals): "));
			}

		}

		int goal = Integer.parseInt(JOptionPane.showInputDialog("Please enter your desired calorie intake per day: "));
		while (goal < 0) {
			goal = Integer.parseInt(JOptionPane
					.showInputDialog("That is an invalid number\nPlease enter your desired calorie intake per day: "));
		}

		int addWaterGoal = JOptionPane.showConfirmDialog(null, "Would you like to add a water intake goal?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		int waterGoal;
		if (addWaterGoal == JOptionPane.YES_OPTION) {
			waterGoal = Integer.parseInt(
					JOptionPane.showInputDialog("Please enter your desired water intake per day in ounces: "));
			while (waterGoal < 0) {
				waterGoal = Integer.parseInt(JOptionPane.showInputDialog(
						"That is an invalid number\\nPlease enter your desired water intake per day in ounces: "));

			}
		} else {
			waterGoal = 64;
			JOptionPane.showMessageDialog(null, "Default water intake goal set to recommended amount - 64 oz.");
		}
		Person user = new Person(name, selectedMeasurement, height, weight, goal, waterGoal);
		JOptionPane.showMessageDialog(null, user.displayProfile());

		return user;
	}

	public static int getMenuOption() {
		int choice;
		choice = Integer.parseInt(JOptionPane.showInputDialog("Main Menu Options:\n" + "1. Update Calories \n"
				+ "2. Add Water Intake \n" + "3. Go to Update Menu  \n" + "4. View Progress \n" + "5. View Profile \n"
				+ "6. End Day \n" + "Enter menu option (1-6) you would like to perform: "));

		while (choice > 6 || choice < 1) {
			choice = Integer.parseInt(JOptionPane.showInputDialog("Invalid Entry! Enter a number from 1-6: \n"
					+ "Main Menu Options:\n" + "1. Update Calories \n" + "2. Add Water Intake \n"
					+ "3. Go to Update Menu  \n" + "4. View Progress \n" + "5. View Profile \n" + "6. End Day \n"
					+ "Enter menu option (1-6) you would like to perform: "));
		}
		return choice;
	}

	public static void menu(Person user, ArrayList<String> food, String today) {
		int choice;
		do {
			choice = getMenuOption();
			switch (choice) {

			case 1:
				int calChoice = Integer.parseInt(JOptionPane.showInputDialog(
						"Options: \n" + "1. Add Calories \n" + "2. Decrease Calories through Exercise "));
				while (calChoice != 1 && calChoice != 2) {
					calChoice = Integer.parseInt(JOptionPane.showInputDialog("Enter number 1 or 2." + "Options: \n"
							+ "1. Add Calories \n" + "2. Decrease Calories through Exercise "));
				}
				if (calChoice == 1) {
					Object[] foodGroups = { "Fruit/Vegetables", "Carbs", "Protein" };
					Object pick = JOptionPane.showInputDialog(null, "Which food group did you eat?", "Input",
							JOptionPane.INFORMATION_MESSAGE, null, foodGroups, foodGroups[0]);
					String selectedFoodGroup = (String) pick;
					food.add(JOptionPane.showInputDialog("What did you eat?"));
					int caloriesToAdd = Integer.parseInt(JOptionPane.showInputDialog("How many calories? "));
					user.addCalories(caloriesToAdd);
					if (selectedFoodGroup == "Fruit/Vegetables") {
						user.addFruitVeggies(caloriesToAdd);
					} else if (selectedFoodGroup == "Carbs") {
						user.addCarbs(caloriesToAdd);
					} else if (selectedFoodGroup == "Protein") {
						user.addProtein(caloriesToAdd);
					}
					JOptionPane.showMessageDialog(null,
							"Your current calorie intake is: " + user.getCurrentCalories() + " calories.");
				} else {
					int exercise = JOptionPane.showConfirmDialog(null, "Did you do any exercise?", "",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (exercise == JOptionPane.YES_OPTION) {
						int caloriesBurned = caloriesBurnedCalc();
						user.burnCalories(caloriesBurned);
						JOptionPane.showMessageDialog(null,
								"You burned " + caloriesBurned + " calories! Excellent job!!"
										+ "\nYour current calorie intake is now: " + user.getCurrentCalories());
					} else {
						JOptionPane.showMessageDialog(null, "No exercise added. Returning you home...");
					}
				}

				if (user.reachedCalLimit()) {
					JOptionPane.showMessageDialog(null, user.getCalGoalMessage());
				}
				break;
			case 2:
				addWater(user);
				break;

			case 3:
				updateMenu(user);
				break;

			case 4:
				JOptionPane.showMessageDialog(null, user.displayInfo());
				JOptionPane.showMessageDialog(null, user.getFoodBreakdown());
				break;

			case 5:
				JOptionPane.showMessageDialog(null, user.displayProfile());
				break;

			default:
				JOptionPane.showMessageDialog(null, "\tEND OF DAY REPORT:\n" + user.displayInfo(), today,
						JOptionPane.INFORMATION_MESSAGE);

				if (food.size() > 0) {
					JOptionPane.showMessageDialog(null, user.getFoodBreakdown());
					int seeFoodReport = JOptionPane.showConfirmDialog(null,
							"Would you like to view a list of what you ate today?", "", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (seeFoodReport == JOptionPane.YES_OPTION) {
						StringBuilder allFoods = new StringBuilder();
						for (int i = 0; i < food.size(); i++) {
							allFoods.append("\n" + (i + 1) + ". " + food.get(i));
						}
						JOptionPane.showMessageDialog(null, "Today's Food: \n" + allFoods);
					}
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

	public static int getUpdateMenuOption() {
		int choice;
		choice = Integer.parseInt(JOptionPane.showInputDialog("Update Menu Options:\n" + "1. Update Calorie Goal \n"
				+ "2. Update Water Goal \n" + "3. Update Profile \n" + "4. Go back to Main Menu \n"
				+ "Enter menu option (1-4) you would like to perform: "));

		while (choice > 4 || choice < 1) {
			choice = Integer.parseInt(
					JOptionPane.showInputDialog("Invalid Entry - Enter a number from 1-4: \n" + "Menu Options:\n"
							+ "1. Update Calorie Goal \n" + "2. Update Water Goal \n" + "3. Update Profile \n"
							+ "4. Go back to Main Menu \n" + "Enter menu option (1-4) you would like to perform: "));

		}
		return choice;
	}

	public static void updateMenu(Person user) {
		int choice;
		do {
			choice = getUpdateMenuOption();
			switch (choice) {

			case 1:
				int goal = Integer.parseInt(JOptionPane.showInputDialog("Your current daily calorie intake goal is "
						+ user.getCalGoal() + " calories." + "\nWhat do you want want your new goal to be? "));

				while (goal < 0) {
					goal = Integer.parseInt(JOptionPane.showInputDialog("Invalid Goal \nEnter a positive number."));
				}

				user.setCalGoal(goal);
				JOptionPane.showMessageDialog(null, "Calorie goal has been updated to " + goal + " calories.");
				break;
			case 2:
				goal = Integer.parseInt(JOptionPane.showInputDialog("Your current daily water intake goal is "
						+ user.getWaterGoal() + " oz." + "\nWhat do you want want your new goal to be? "));

				while (goal < 0) {
					goal = Integer.parseInt(JOptionPane.showInputDialog("Invalid Goal \nEnter a positive number."));
				}

				user.setWaterGoal(goal);
				JOptionPane.showMessageDialog(null, "Water intake goal has been updated to " + goal + " oz.");
				break;
			case 3:
				int profileOption = getProfileOption();
				switch (profileOption) {

				case 1:
					int oldWeight = user.getWeight();
					String message;
					if (user.getSelectedMeasurement().equals("Inches/Pounds")) {
						int weight = Integer
								.parseInt(JOptionPane.showInputDialog("Please enter your new weight in pounds: "));
						user.setWeight((weight));

						// message for losing weight / empty message if didn't lose weight
						message = oldWeight > weight ? "You lost " + (oldWeight - weight) + " pound(s)!\nKeep it up!!\n"
								: "";
						JOptionPane.showMessageDialog(null,
								message + "Your weight has been updated to " + weight + " pounds!");
					} else {
						int weight = Integer.parseInt(JOptionPane
								.showInputDialog("Please enter your new weight in kilograms (no decimals): "));
						user.setWeight((weight));
						// message for losing weight / empty message if didn't lose weight
						message = oldWeight > weight
								? "You lost " + (oldWeight - weight) + " kilogram(s)!\nKeep it up!!"
								: "";
						JOptionPane.showMessageDialog(null,
								message + "Your weight has been updated to " + weight + " kilograms!");
					}

					break;

				default:
					if (user.getSelectedMeasurement().equals("Inches/Pounds")) {
						int height = Integer
								.parseInt(JOptionPane.showInputDialog("Please enter your new height in inches: "));
						user.setHeight(height);
						JOptionPane.showMessageDialog(null, "Your height has been updated to " + height + " inches!");
					} else {
						int height = Integer.parseInt(JOptionPane
								.showInputDialog("Please enter your new height in centimeters (no decimals): "));
						user.setHeight(height);
						JOptionPane.showMessageDialog(null,
								"Your height has been updated to " + height + " centimeters!");
					}

					break;
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Back to Main Menu");

			}
		} while (choice != 4);
	}

	public static int getProfileOption() {
		int profileOption = Integer.parseInt(JOptionPane.showInputDialog("What would you like to update?\n"
				+ "1. Weight \n" + "2. Height \n" + "Enter the profile option (1-2) you would like to update: "));

		while (profileOption > 2 || profileOption < 1) {
			profileOption = Integer.parseInt(JOptionPane
					.showInputDialog("Invalid Entry! Enter number from 1-2: \n" + "1. Weight \n" + "2. Height"));
		}
		return profileOption;
	}

	public static void addWater(Person user) {

		double water = Double.parseDouble(JOptionPane.showInputDialog("Enter water intake in oz: "));
		while (water < 0) {
			water = Double
					.parseDouble(JOptionPane.showInputDialog("Invalid Number\nPlease enter water intake in oz: "));
		}

		user.addWater(water);
		JOptionPane.showMessageDialog(null, user.getWaterIntake());

	}

}
