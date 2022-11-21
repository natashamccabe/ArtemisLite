/**
 * 
 */
package artemisLite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author emmadeane
 *
 */
public class Player {

	
	public static void main(String[] args) {
		Player p1 = new Player();
		
		p1.setPosition(0);//player starts on first square (first element in board list)
		
	}
	
	// private member vars for name, resources, position (int -> index of the board
	// list) and properties owned.
	private String name;
	private int resources;
	private int position;
	private List<Square> propertiesOwned = new ArrayList<Square>();

	/**
	 * default constructor
	 */
	public Player() {

	}

	/**
	 * Constructor with arguments
	 * 
	 * @param name
	 * @param resources
	 * @param position
	 */
	public Player(String name, int resources, int position) {
		super();
		this.name = name;
		this.resources = resources;
		this.position = position;
	}
	
	/**
	 * player moves a number of squares based on dice roll
	 * @param diceTotal
	 */
	public void movePosition(int diceTotal) {
		position = position + diceTotal;
	}


	/**
	 * 
	 * @param resourcesToAdd
	 */
	public void addResources(int resourcesToAdd) {
		resources = resources + resourcesToAdd;
	}

	/**
	 * 
	 * @param resourcesToDeduct
	 */
	public void deductResources(int resourcesToDeduct) {
		int balance;
		
		balance = resources - resourcesToDeduct;
		
		if (balance <= 0) {
			Game.gameOngoing = false;
		} else {
			resources = balance;
		}
	}

	/**
	 * 
	 * @param propertyToAdd
	 */
	public void addProperty(Square propertyToAdd) {
		if (!propertiesOwned.contains(propertyToAdd)) {
			propertiesOwned.add(propertyToAdd);
		} else {
			//player already owns this property
		}
		
	}

	

	// getters & setters
	/**
	 * returns a list of all Squares player is in charge of
	 * @return
	 */
	public List<Square> getPropertiesOwned() {
		return propertiesOwned;
	}

	/**
	 * 
	 * @param propertiesOwned
	 */
	public void setPropertiesOwned(List<Square> propertiesOwned) {
		this.propertiesOwned = propertiesOwned;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public int getResources() {
		return resources;
	}

	/**
	 * 
	 * @param resources
	 */
	public void setResources(int resources) {
		this.resources = resources;
	}

	/**
	 * 
	 * @return
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	

}
