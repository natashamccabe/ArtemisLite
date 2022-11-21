/**
 * 
 */
package artemisLite;

import java.util.Random;
//import java.util.Scanner;

/**
 * @author natashamccabe
 *
 */
public class Dice {

	// private member vars for numOfSides, numOfDice, dice and diceTotal
	private int numOfSides;
	private int numOfDice;
	private int dice;
	private int diceTotal;
	// end of vars

	// constructors default & args
	public Dice() {
	}

	public Dice(int numOfSides, int numOfDice, int dice, int diceTotal) {

		this.numOfSides = numOfSides;
		this.numOfDice = numOfDice;
		this.dice = dice;
		this.diceTotal = diceTotal;
	}// end of constructors

	// getters & setters
	public int getNumOfSides() {
		return numOfSides;
	}

	public void setNumOfSides(int numOfSides) {
		this.numOfSides = numOfSides;
	}

	public int getNumOfDice() {
		return numOfDice;
	}

	public void setNumOfDice(int numOfDice) {
		this.numOfDice = numOfDice;
	}

	public int getDice() {
		return dice;
	}

	public void setDice(int dice) {
		this.dice = dice;
	}

	public int getDiceTotal() {
		return diceTotal;
	}

	public void setDiceTotal(int diceTotal) {
		this.diceTotal = diceTotal;
	}
	// end of getters & setters

	/**
	 * rolls a single dice by returning a random number
	 * number will vary based on number of sides of the dice
	 * @return
	 */
	public int rollDice() {
		
				Random myDiceRoll = new Random();

				int diceTotal = myDiceRoll.nextInt(numOfSides) + 1;
				
				return diceTotal;

		}
	}