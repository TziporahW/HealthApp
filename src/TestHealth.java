package health;

import static org.junit.Assert.*;
import org.junit.*;


public class TestHealth {
	
	private Person person;
	
	@Before
	public void setUp() {
		person = new Person("Toby","Inches/Pounds",62,125,1300,120);
		person.addCalories(500);
			
	}
	
	@Test
	public void addToCalories() {
		person.addCalories(40);
		assertEquals(540,person.getCurrentCalories());
	}
	
	@Test
	public void removeFromCalories() {
		person.burnCalories(20);
		assertEquals(480,person.getCurrentCalories());
	}
	
	@Test
	public void isBMICorrect() {
		assertEquals(22.86,person.getBMI(),.0001);
	}
	
	@Test	
	public void isCalorieLimitReached() {
		person.addCalories(1300);
		assertTrue(person.reachedCalLimit());
		person.burnCalories(600);
		assertFalse(person.reachedCalLimit());
	}
	
	@Test
	public void percentageOfCarbsCorrect() {
		person.addCarbs(250);
		assertEquals(50,person.getCarbsPercentage(),.0001);
		
	}
	

}
