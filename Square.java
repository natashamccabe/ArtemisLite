/**
 * 
 */
package artemisLite;

import java.util.List;


public class Square {

	// private member vars
	private String name;
	private SquareType squareType;
	private int priceToAcquire;
	private int[] priceOfRent = new int[5];
	private int priceToDevelop;
	private int priceToMajorDevelop;
	private int numOfDevelopments;
	private Player owner;
	private SystemName system;
	
	/**
	 * default constructor
	 */
	public Square() {
		
	}

	/**
	 * Creates a square with name and squareType.
	 * 
	 * @param name
	 * @param squareType
	 */
	public Square(String name, SquareType squareType) {
		this.name = name;
		this.squareType = squareType;
	}

	/**
	 * Creates a square with all arguments.
	 * 
	 * @param name
	 * @param squareType
	 * @param priceToAcquire
	 * @param priceOfRent
	 * @param priceToDevelop
	 * @param priceToMajorDevelop
	 * @param numOfDevelopments
	 * @param owner
	 * @param system
	 */
	public Square(String name, SquareType squareType, int priceToAcquire, int[] priceOfRent, int priceToDevelop,
			int priceToMajorDevelop, int numOfDevelopments, Player owner, SystemName system) {
		this.name = name;
		this.squareType = squareType;
		this.priceToAcquire = priceToAcquire;
		this.priceOfRent = priceOfRent;
		this.priceToDevelop = priceToDevelop;
		this.priceToMajorDevelop = priceToMajorDevelop;
		this.numOfDevelopments = numOfDevelopments;
		this.owner = owner;
		this.system = system;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the squareType
	 */
	public SquareType getSquareType() {
		return squareType;
	}

	/**
	 * @param squareType the squareType to set
	 */
	public void setSquareType(SquareType squareType) {
		this.squareType = squareType;
	}

	/**
	 * @return the priceToAcquire
	 */
	public int getPriceToAcquire() {
		return priceToAcquire;
	}

	/**
	 * @param priceToAcquire the priceToAcquire to set
	 */
	public void setPriceToAcquire(int priceToAcquire) {
		this.priceToAcquire = priceToAcquire;
	}

	/**
	 * @return the priceOfRent
	 */
	public int[] getPriceOfRent() {
		return priceOfRent;
	}

	/**
	 * @param priceOfRent the priceOfRent to set
	 */
	public void setPriceOfRent(int[] priceOfRent) {
		this.priceOfRent = priceOfRent;
	}

	/**
	 * @return the priceToDevelop
	 */
	public int getPriceToDevelop() {
		return priceToDevelop;
	}

	/**
	 * @param priceToDevelop the priceToDevelop to set
	 */
	public void setPriceToDevelop(int priceToDevelop) {
		this.priceToDevelop = priceToDevelop;
	}

	/**
	 * @return the priceToMajorDevelop
	 */
	public int getPriceToMajorDevelop() {
		return priceToMajorDevelop;
	}

	/**
	 * @param priceToMajorDevelop the priceToMajorDevelop to set
	 */
	public void setPriceToMajorDevelop(int priceToMajorDevelop) {
		this.priceToMajorDevelop = priceToMajorDevelop;
	}

	/**
	 * @return the numOfDevelopments
	 */
	public int getNumOfDevelopments() {
		return numOfDevelopments;
	}

	/**
	 * @param numOfDevelopments the numOfDevelopments to set
	 */
	public void increaseNumOfDevelopments() {
		this.numOfDevelopments = numOfDevelopments+1;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @return the system
	 */
	public SystemName getSystem() {
		return system;
	}

	/**
	 * @param system the system to set
	 */
	public void setSystem(SystemName system) {
		this.system = system;
	}

	/**
	 * 
	 * @param player
	 */
	public void landedOn(Player player) {
		String playerOption = "";

		switch (this.getSquareType()) {
		case GOVERNMENT_GRANT:
			break;
		case EXTERNAL_INVESTIGATION:
			System.out.println("You've landed in some hot water! An pesky external auditor has been poking around and something in the books isn't quite adding up. "
					+ "\nYour research has been put on hold until the investigation is resolved.");
			break;
		case PURCHASABLE:
			System.out.println("You have landed on " + this.getName()+"!");
			int currentRent = priceOfRent[this.numOfDevelopments];
			for(Player player2 : Game.players) {
				List<Square> systems = player2.getPropertiesOwned();
				for (Square sq : systems) {
					if (this.system.equals(sq.getSystem())&&player2.getName()!=player.getName()&&this.getOwner() == null) {
						System.out.println("\nWarning : This element is in a system owned by "+player2.getName()+".");
						System.out.println("If all elements are not controlled by the same player it is");
						System.out.println("not possible to develop all systems and win the game\n");
						break;
					} 
				} 
			}
			
			if (this.getOwner() == null && player.getResources() > this.getPriceToAcquire()) {
				// there is no owner and the player has sufficient funds

				System.out.println(this.getName() + " is available for purchase for a price of "
						+ this.getPriceToAcquire() + " research tokens. \nYour current balance is "
						+ player.getResources() + " research tokens. \nWould you like to purchase this square? Y/N");

				do {
					playerOption = Game.scanner.next();
	
					if (playerOption.trim().equalsIgnoreCase("y")) {
						// player decides to buy property
						player.deductResources(this.getPriceToAcquire());
						player.addProperty(this);
						this.setOwner(player);
						System.out.println("You've purchased " + this.getName() + "! \nYour new balance is "
								+ player.getResources() + " research tokens.");
	
						
	
					} else if (playerOption.trim().equalsIgnoreCase("n")) {
						// player decides not to purchase the property
	
						System.out.println(player.getName() + " does not wish to buy " + this.getName() + ".\n" + this.getName() + " will now be offered to the other players.");
						Player nextPlayerToBeOffered;
						if(Game.players.indexOf(player)+1 >= Game.players.size()) {
							nextPlayerToBeOffered = Game.players.get(0);
						} else {
							nextPlayerToBeOffered = Game.players.get(Game.players.indexOf(player)+1);
						}
						do {
							if(nextPlayerToBeOffered.getResources() > this.priceToAcquire) {
								// other player can afford it
								offerSquare(nextPlayerToBeOffered);
								if(Game.players.indexOf(nextPlayerToBeOffered)+1 >= Game.players.size()) {
									nextPlayerToBeOffered = Game.players.get(0);
								} else {
									nextPlayerToBeOffered = Game.players.get(Game.players.indexOf(nextPlayerToBeOffered)+1);
								}
								
							} else {
								System.out.println(nextPlayerToBeOffered.getName()+" cannot afford to purchase this square.");
								if(Game.players.indexOf(nextPlayerToBeOffered)+1 >= Game.players.size()) {
									nextPlayerToBeOffered = Game.players.get(0);
								} else {
									nextPlayerToBeOffered = Game.players.get(Game.players.indexOf(nextPlayerToBeOffered)+1);
								}
							}
						} while(this.owner == null && nextPlayerToBeOffered != player);
						
						// no one bought the square
						if(this.owner == null) {
							System.out.println("No one took charge of "+this.getName()+". \nIt remains available for purchase");
						}
	
					} else {
						System.out.println("Invalid input. Please enter Y or N.");
					}
				} while (!playerOption.trim().equalsIgnoreCase("y") && !playerOption.trim().equalsIgnoreCase("n"));


			} else if (this.getOwner() == null && player.getResources() < this.getPriceToAcquire()) {
				// there is no owner but the player has insufficient funds
				System.out.println(this.getName() + " is available for purchase for a price of "
						+ this.getPriceToAcquire() + " research tokens. \nYour current balance is "
						+ player.getResources() + " research tokens. \nYou do not have sufficient funds. \n"
						+ this.getName() + " will now be offered to the other players.");
				
				Player nextPlayerToBeOffered;
				if(Game.players.indexOf(player)+1 >= Game.players.size()) {
					nextPlayerToBeOffered = Game.players.get(0);
				} else {
					nextPlayerToBeOffered = Game.players.get(Game.players.indexOf(player)+1);
				}
				do {
					if(nextPlayerToBeOffered.getResources() >= this.priceToAcquire) {
						// other player can afford it
						offerSquare(nextPlayerToBeOffered);
						if(Game.players.indexOf(nextPlayerToBeOffered)+1 >= Game.players.size()) {
							nextPlayerToBeOffered = Game.players.get(0);
						} else {
							nextPlayerToBeOffered = Game.players.get(Game.players.indexOf(nextPlayerToBeOffered)+1);
						}
						
					} else {
						if(Game.players.indexOf(nextPlayerToBeOffered)+1 >= Game.players.size()) {
							nextPlayerToBeOffered = Game.players.get(0);
						} else {
							nextPlayerToBeOffered = Game.players.get(Game.players.indexOf(nextPlayerToBeOffered)+1);
						}
					}
				} while(this.owner == null && nextPlayerToBeOffered != player);
				
				// no one bought the square
				if(this.owner == null) {
					System.out.println("No one took charge of "+this.getName()+". \nIt remains available for purchase");
				}

			} else {
				// the property is already owned
				if(this.owner != player) {
					System.out.println(this.getName() + " is owned by " + this.getOwner().getName()
							+ ". \nThe rent on this property is " + currentRent
							+ " resource tokens. \nYour current balance is " + player.getResources() + " resource tokens. "
							+ this.getOwner().getName() + " would you like to charge rent? Y/N");
					do {
						playerOption = Game.scanner.next();
		
						if (playerOption.trim().equalsIgnoreCase("y")) {
							// owner decides to charge rent
		
							if (player.getResources() < currentRent) {
								// the player cannot afford the rent
		
								owner.addResources(player.getResources());
								player.deductResources(player.getResources());
								System.out.println(this.owner.getName() + " has decided to charge rent. \n" + player.getName()
										+ " you have been made bankrupt! \nGame Over!\nThe Artemis Project has failed...");
								Game.gameOngoing = false;
							} else {
								// the player can afford the rent
		
								owner.addResources(currentRent);
								player.deductResources(currentRent);
								System.out.println(this.owner.getName() + " has decided to charge rent. \n"+player.getName()+" your new balance is "
										+ player.getResources());
							}
		
						} else if (playerOption.trim().equalsIgnoreCase("n")) {
							// owner decides not to charge rent
		
							System.out.println(this.owner.getName() + " has decided not to charge rent. \n"+player.getName()+" your balance is still "
									+ player.getResources());
						} else {
							// invalid input
							System.out.println("Invalid input. Please enter Y or N.");
						}
					} while (!playerOption.trim().equalsIgnoreCase("y") && !playerOption.trim().equalsIgnoreCase("n"));
				} else {
					System.out.println("You already own this square!");
				}
			}

			break;
		default:
			System.out.println("uh oh");
		}
	}

	/**
	 * 
	 * @param player
	 */
	public void develop(Player player) {
		if ((player.getResources() < this.priceToDevelop && this.numOfDevelopments < 3)
				|| (player.getResources() < this.priceToMajorDevelop && this.numOfDevelopments == 4)) {
			// player has insufficient funds
			System.out.println("You do not have sufficient funds to complete this action.");
		} else {
			// player has sufficient funds
			if (this.numOfDevelopments < 3) {
				// normal development
				this.numOfDevelopments += 1;
				player.deductResources(this.priceToDevelop);
				System.out.println("Successful development! " + this.getName() + " has now been developed "
						+ this.getNumOfDevelopments() + " times.");
			} else if (this.numOfDevelopments == 3) {
				// major development
				System.out.println(this.getName() + " has already been developed " + this.getNumOfDevelopments()
						+ " times. A major development will now take place. \nSuccessful major development! "
						+ this.getName() + " is now fully developed.");
				this.numOfDevelopments += 1;
				player.deductResources(priceToMajorDevelop);				
			} else {
				System.out.println(this.getName() + " is already fully developed");
			}
		}
	}


	/**
	 * 
	 * @param player
	 */
	public void offerSquare(Player player) {
		String playerOption = "";
		
		for(Player player2 : Game.players) {
			List<Square> systems = player2.getPropertiesOwned();
			for (Square sq : systems) {
				if (this.system.equals(sq.getSystem())&&player2.getName()!=player.getName()&&this.getOwner() == null) {
					System.out.println("\nWarning : This element is in a system owned by "+player2.getName()+".");
					System.out.println("If all elements are not controlled by the same player it is");
					System.out.println("not possible to develop all systems and win the game\n");
					break;
				} 
			} 
		}
		
		System.out.println(player.getName()+", your current balance is "+ player.getResources()+" resource tokens.\nWould you like to purchase "+this.getName() + " for a price of "
				+ this.getPriceToAcquire() + " resource tokens? Y/N");

		do {
			playerOption = Game.scanner.next();
			if(playerOption.trim().equalsIgnoreCase("y")) {
				// bought it
				player.deductResources(this.getPriceToAcquire());
				player.addProperty(this);
				this.setOwner(player);
				System.out.println(player.getName() + " has purchased " + this.getName() + ". \n"+player.getName()+" your new balance is "
						+ player.getResources() + " resource tokens.");

			} else if (playerOption.trim().equalsIgnoreCase("n")) {
				System.out.println(player.getName() + " does not wish to buy " + this.getName() + ".");

			} else {
				System.out.println("Invalid input. Please enter Y or N.");
			}
		} while (!playerOption.trim().equalsIgnoreCase("y") && !playerOption.trim().equalsIgnoreCase("n"));
		
	}
}
