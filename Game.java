/**
 * 
 */
package artemisLite;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.junit.platform.commons.util.StringUtils;


public class Game {

	public static List<Player> players = new ArrayList<Player>();
	public static final int MIN_PLAYERS = 2;
	public static final int MAX_PLAYERS = 4;
	public static final int SLS_ELEMENTS = 2;
	public static final int ORION_ELEMENTS = 3;
	public static final int GATEWAY_ELEMENTS = 2;
	public static final int SPACESUIT_ELEMENTS = 3;
	public static final int SLS_DEVELOPMENTS = 8;
	public static final int ORION_DEVELOPMENTS = 12;
	public static final int GATEWAY_DEVELOPMENTS = 8;
	public static final int SPACESUIT_DEVELOPMENTS = 12;
	public static Scanner scanner = new Scanner(System.in);

	public Player activePlayer = new Player();
	public Board board = new Board();
	private int goMoney;
	private boolean victory = false;
	public static boolean gameOngoing = true;


	/**
	 * 
	 * @param goMoney
	 */
	public Game(int goMoney) {
		this.goMoney = goMoney;
	}

	/**
	 * 
	 * @return
	 */
	public int getGoMoney() {
		return goMoney;
	}

	/**
	 * 
	 * @param goMoney
	 */
	public void setGoMoney(int goMoney) {
		this.goMoney = goMoney;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVictory() {
		return victory;
	}

	/**
	 * 
	 * @param victory
	 */
	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	/**
	 * Method instantiates and initialises the board object, requests number of
	 * players (which must be between a max and min) creates the player objects and
	 * displays the intro message.
	 * 
	 */
	public void startGame() {

		gameOngoing = true;
		
		String playerOption = "";

		System.out.println("Would you like to see the game's opening story? Y/N?");
		
		do {
			playerOption = Game.scanner.next();

			if (playerOption.trim().equalsIgnoreCase("y")) {
				Script.displayIntro();
				continue;
			} else if (playerOption.trim().equalsIgnoreCase("n")) {
				continue;
			} else {
				System.out.println("Invalid input. Please enter Y or N.");
			}
		} while (!playerOption.trim().equalsIgnoreCase("y") && !playerOption.trim().equalsIgnoreCase("n"));
		
		System.out.println("Would you like to see the rules of the game? Y/N?");
		
		do {
			playerOption = Game.scanner.next();

			if (playerOption.trim().equalsIgnoreCase("y")) {
				Script.displayInstructions();
				continue;
			} else if (playerOption.trim().equalsIgnoreCase("n")) {
				continue;
			} else {
				System.out.println("Invalid input. Please enter Y or N.");
			}
		} while (!playerOption.trim().equalsIgnoreCase("y") && !playerOption.trim().equalsIgnoreCase("n"));

		board.initialiseBoard();
		System.out.println("Please enter the number of players (between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ")");
		int numOfPlayers = 0;
		do {
			try {
				numOfPlayers = Game.scanner.nextInt();
				if(numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
					System.out.println("Invalid input. Please enter the number of players (between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ")");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter the number of players (between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ")");
				Game.scanner.next();
			}
		} while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS);
		Game.scanner.nextLine();
		setUpPlayers(numOfPlayers);
		activePlayer = players.get(0);

	}

	/**
	 * this method sets up a fixed number of players based on integer entered number
	 * of players - each player must have a unique name
	 * 
	 * @param numberOfPlayers
	 */
	public void setUpPlayers(int numOfPlayers) {
		
		
		int loop = 1;
		while (loop <= numOfPlayers) {
			System.out.println("Player " + loop + ", please enter your name");
			String playerName = scanner.nextLine();
			
			
			if (StringUtils.isBlank(playerName)) {
				System.out.println("Not an accepted input.");
			} else {

			switch (loop) {
			case 1:
				Player player1 = new Player(playerName, 500, 0);
				players.add(player1);
				System.out.println("Thank you " + player1.getName() + "!");
				break;
			case 2:
				while (playerName.equals(players.get(0).getName())) {
					System.out.println("That name has already been taken. Please enter a different name.");
					playerName = scanner.nextLine();
				}
				Player player2 = new Player(playerName, 500, 0);
				players.add(player2);
				System.out.println("Thank you " + player2.getName() + "!");
				break;
			case 3:
				while (playerName.equals(players.get(0).getName()) || playerName.equals(players.get(1).getName())) {
					System.out.println("That name has already been taken. Please enter a different name.");
					playerName = scanner.nextLine();
				}
				Player player3 = new Player(playerName, 500, 0);
				players.add(player3);
				System.out.println("Thank you " + player3.getName() + "!");
				break;
			case 4:
				while (playerName.equals(players.get(0).getName()) || playerName.equals(players.get(1).getName())
						|| playerName.equals(players.get(2).getName())) {
					System.out.println("That name has already been taken. Please enter a different name.");
					playerName = scanner.nextLine();
				}
				Player player4 = new Player(playerName, 500, 0);
				players.add(player4);
				System.out.println("Thank you " + player4.getName() + "!");
				break;
			default:
				System.out.println("uh oh");
			}
			
			loop++;
		}
		}
		

	}

	/**
	 * Method sets the active player as the next player in the players array. If the
	 * previous active player was the last player in the players array, the current
	 * active player will be the first player in the array
	 */
	public void nextTurn() {
		if (players.indexOf(activePlayer) + 1 >= players.size()) {
			activePlayer = players.get(0);
		} else {
			activePlayer = players.get(players.indexOf(activePlayer) + 1);
		}

	}

	/**
	 * this method will kick off methods that follow, once the game has been started
	 */
	public void continueGame() {

		while (gameOngoing) {
			System.out.println("Ok, " + activePlayer.getName() + " , it's your turn. Hit enter to roll the dice.");
			scanner.nextLine();

			int diceThrown = rollDice();
			movePlayer(diceThrown);

			Square playSquare = board.playSquare(activePlayer.getPosition());
			playSquare.landedOn(activePlayer);
			if (gameOngoing == false) {
				setVictory(false);
				endGame();
				break;
			}
			boolean turnOngoing = true;
			///////
			if(playSquare.getSquareType() != SquareType.EXTERNAL_INVESTIGATION) {
				getMenuOptions(playSquare);
				System.out.println(interactWithPlayer());
			}
			
			if (gameOngoing == false) {
				setVictory(false);
				endGame();
				break;
			}
			
			if(systemsDevelopedCount().get(0) == ORION_DEVELOPMENTS && systemsDevelopedCount().get(1) == GATEWAY_DEVELOPMENTS 
			&& systemsDevelopedCount().get(2) == SLS_DEVELOPMENTS && systemsDevelopedCount().get(3) == SPACESUIT_DEVELOPMENTS) {
				setVictory(true);
				endGame();
				break;
			}
			
			
			nextTurn();
		}
	}

	/**
	 * returns a random string holding a simple interaction message
	 */
	public String interactWithPlayer() {
		Random rand = new Random();
		List<String> playerInteractions = new ArrayList<String>();
		playerInteractions.add("Hurrah, another stupendous achievement from a hero of space exploration!");
		playerInteractions.add("One small step for a research scientist, another giant LEAP for humankind.");
		playerInteractions.add("Ah yes, excellent progress. Soon we'll be unstoppable in our mission!");
		playerInteractions.add("As a great prophet once said... to infinity, and beyond!!");
		playerInteractions.add("Soon, the sky will be our limit no more!");
		playerInteractions.add("Some say mankind was born on Earth... it was never our fate to die here");
		playerInteractions.add("Our greatest accomplishments cannot be behind us, because our destiny lies above us.");

		int size = playerInteractions.size();
		String interaction = playerInteractions.get(rand.nextInt(size));
		return interaction;
	}

	/**
	 * I've not called this method anywhere yet as it's still being worked on :)
	 * 
	 * @param playSquare
	 */
	public void getMenuOptions(Square playSquare) {
		List<String> menuOptions = new ArrayList<String>();
		System.out.println("Okay " + activePlayer.getName() + ", here are the rest of your options this turn:");

		menuOptions.add("Check your current state of play");
		menuOptions.add("Finish your turn");
		menuOptions.add("Exit game");

		List<Square> squaresOwned = activePlayer.getPropertiesOwned();

		// if a player owns all squares in a system, they can develop one of these
		List<SystemGroup> systems = board.getSystemGroups();
		List<SystemGroup> developableSystems = new ArrayList<SystemGroup>();

		// check whether a player can develop a square
		for (SystemGroup sys : systems) {
			int count = 0;
			int squaresInSys = sys.getSquares().size();
			List<Square> ownedByPlayer = activePlayer.getPropertiesOwned();
			// loop through squares owned by player
			for (Square sq : ownedByPlayer) {
				if (sq.getSystem().equals(sys.getName())) {
					count++;
				}
			}
			if (count == squaresInSys) {
				// player may develop any of these squares
				developableSystems.add(sys);
				//menuOptions.add("Develop an element in : " + sys.getName());
			}
			
		}
		
		for (SystemGroup sys : systems) {
			int count = 0;
			int squaresInSys = sys.getSquares().size();
			List<Square> ownedByPlayer = activePlayer.getPropertiesOwned();
			// loop through squares owned by player
			for (Square sq : ownedByPlayer) {
				if (sq.getSystem().equals(sys.getName())) {
					count++;
				}
			}
			if (count == squaresInSys) {
				menuOptions.add("Develop an element");
				break;
			}
			
		}
			
		
		/**
		// loop through developable systems
		for (SystemGroup sys : developableSystems) {
			// loop through squares in each system
			for (Square sq : sys.getSquares()) {
				if (sq.getNumOfDevelopments() == 3) {
					menuOptions.add("Perform a major development on " + sq.getName());
				}
			}
		}
		**/

		int menuOption;

		boolean turnOngoing = true;

		do {

			for (String option : menuOptions) {
				// display options
				int optionNumber = menuOptions.indexOf(option) + 1;
				System.out.print(optionNumber + ". " + option + "\n");
			}

			// take user input
			menuOption = scanner.nextInt();
			scanner.nextLine();

			switch (menuOption) {
			case 1:
				System.out.println(getStateOfPlay(activePlayer));
				break;
			case 2:
				turnOngoing = false;
				break;
			case 3:
				System.out.println("Are you sure you want to exit the game? The mission will certainly fail... (Y/N)");
				String inp = scanner.nextLine();
				if (inp.equalsIgnoreCase("Y")) {
					turnOngoing = false;
					gameOngoing = false;
				} else if (inp.equalsIgnoreCase("N")){
					System.out.println("Alright, our mission isn't over yet!");
				} else {
					System.out.println("Sorry, I don't understand that input. Try again?");
				}
				break;
					
			case 4:
				System.out.println("Which squares do you want to develop?");
				for (SystemGroup sys : developableSystems) {
					int num = 1;
					for (Square sq : sys.getSquares()) {
						if (sq.getPriceToDevelop() <= activePlayer.getResources() &&  sq.getNumOfDevelopments() < 5) {
							System.out.println("Do you want to develop " + sq.getName() + " for a cost of "
									+ sq.getPriceToDevelop() + "? Y/N");
							num++;

							String userInp = scanner.nextLine();
							if (userInp.equalsIgnoreCase("Y")) {
								sq.develop(activePlayer);
								System.out.println("Your new balance is " + activePlayer.getResources() + "\n");
							} else {
								System.out.println(sq.getName() + " will not be developed on this turn.");
							}
						} 
					}
				}
				turnOngoing = false;
				break;
			}
		} while (turnOngoing);

	}

	/**
	 * sets up a number of die objects and rolls them, returning a total dice roll
	 * number informs the active player of the total of each dice and the overall
	 * total
	 */
	public int rollDice() {
		Dice dice1 = new Dice();
		Dice dice2 = new Dice();

		dice1.setNumOfSides(6);
		dice2.setNumOfSides(6);

		int roll1, roll2;
		int total = 0;

		roll1 = dice1.rollDice();
		roll2 = dice2.rollDice();

		total = roll1 + roll2;

		System.out.println("Nice, " + activePlayer.getName() + "! You've rolled a " + roll1 + " and a " + roll2);
		// + " - that makes " + total);

		return total;
	}

	/**
	 * moves a player along the board given a number of spaces to move displays
	 * starting square and ending square if player passes go, the passGo method will
	 * be called
	 * 
	 * @param player
	 * @param diceRoll
	 */
	public Square movePlayer(int spacesToMove) {
		System.out.println("Now let's move " + spacesToMove + " spaces...");
		int startPosition = activePlayer.getPosition();
		Square startSquare = board.getCurrentBoard().get(startPosition);

		// start position
		// System.out.println("started on : Square " + activePlayer.getPosition() + ", "
		// + startSquare.getName());

		// adjust player position based on the dice roll
		activePlayer.movePosition(spacesToMove);

		// get new position
		int newPosition = activePlayer.getPosition();

		// check if the new index is >= board size, if it is you'll need to
		// adjust the board position and update the resources to show that the player
		// has passed go
		int boardSize = board.getCurrentBoard().size();
		// System.out.println("board size : " + boardSize);
		if (newPosition >= boardSize) {
			int diff = newPosition - boardSize;
			activePlayer.setPosition(diff);
			passGo();
		}

		// update the player on where they have landed
		int finalPosition = activePlayer.getPosition();
		Square currentSquare = board.getCurrentBoard().get(finalPosition);
		// System.out.println("Position after move : Square " + (finalPosition) + ", " +
		// currentSquare.getName());
		return currentSquare;

	}

	/**
	 * method that adds to player's research tokens when they pass the go square on
	 * the board
	 */
	public void passGo() {
		activePlayer.addResources(goMoney);
		System.out.println(activePlayer.getName() + ", you have passed by government buildings. On your way, you popped in and made a petition for extra funding towards the mission. "
				+ "Lucky you - they were convinced!\nThey've contributed an extra " + goMoney
				+ " research tokens towards the Artemis space mission.");
	}

	/**
	 * displays all of the relevant information for the current active player
	 * 
	 * @return
	 */
	public String getStateOfPlay(Player player) {
		String newLine = "\n";
		StringBuilder sb = new StringBuilder();
		sb.append("Player name       : ");
		sb.append(player.getName());
		sb.append(newLine);		
		sb.append("Resources         : ");
		sb.append(player.getResources());
		sb.append(newLine);
		sb.append("Position          : ");
		sb.append("Square " + player.getPosition() + ", " + board.playSquare(player.getPosition()).getName());
		sb.append(newLine);
				
		sb.append("Total Systems     :");
		sb.append(" Orion: " + systemsOwnedCount().get(0) + "/" + ORION_ELEMENTS + " " + "  Gateway: "
				+ systemsOwnedCount().get(1) + "/" + GATEWAY_ELEMENTS + " " + "  SLS: " + systemsOwnedCount().get(2) + "/"
				+ SLS_ELEMENTS + " " + "  Spacesuits: " + systemsOwnedCount().get(3) + "/" + SPACESUIT_ELEMENTS);
		sb.append(newLine);
		sb.append("Your systems	  : ");

		int orionCount = 0;
		int slsCount = 0;
		int gatewayCount = 0;
		int spacesuitCount = 0;

		List<Square> propOwned = player.getPropertiesOwned();
		for (Square sq : propOwned) {
			if (sq.getSystem().equals(SystemName.ORION)) {
				orionCount++;
			} else if (sq.getSystem().equals(SystemName.ARTEMIS_GENERATION_SPACESUITS)) {
				spacesuitCount++;
			} else if (sq.getSystem().equals(SystemName.GATEWAY)) {
				gatewayCount++;
			} else {
				slsCount++;
			}
		}
		sb.append("Orion: " + orionCount + "/" + ORION_ELEMENTS + " " + "  Gateway: " + gatewayCount + "/"
				+ GATEWAY_ELEMENTS + " " + "  SLS: " + slsCount + "/" + SLS_ELEMENTS + " " + "  Spacesuits: "
				+ spacesuitCount + "/" + SPACESUIT_ELEMENTS);
		sb.append(newLine);
		
		sb.append("Total Developments:");
		sb.append(" Orion: " + systemsDevelopedCount().get(0) + "/" + ORION_DEVELOPMENTS + " " + " Gateway: "
					+ systemsDevelopedCount().get(1) + "/" + GATEWAY_DEVELOPMENTS + " " + "  SLS: " + systemsDevelopedCount().get(2) + "/"
					+ SLS_DEVELOPMENTS + " " + "  Spacesuits: " + systemsDevelopedCount().get(3) + "/" + SPACESUIT_DEVELOPMENTS);
		sb.append(newLine);
		sb.append("Properties owned  : ");
		for(int loop=0; loop < propOwned.size(); loop++) {
			if(loop==0) {
				sb.append(propOwned.get(loop).getName()+", "+propOwned.get(loop).getSystem().toString());
			} else {
				sb.append("\n                    "+propOwned.get(loop).getName()+", "+propOwned.get(loop).getSystem().toString());
			}
		}
		sb.append(newLine);
		return sb.toString();
	}

	/**
	 * Returns an arraylist containing total elements purchased in each system
	 * @return 
	 */
	public static List<Integer> systemsOwnedCount() {

		List<Integer> systemsOwned = new ArrayList<Integer>();

		int orionCount = 0;
		int slsCount = 0;
		int gatewayCount = 0;
		int spacesuitCount = 0;

		for (Player player : players) {
			List<Square> systems = player.getPropertiesOwned();
			for (Square sq : systems) {
				if (sq.getSystem().equals(SystemName.ORION)) {
					orionCount++;
				} else if (sq.getSystem().equals(SystemName.ARTEMIS_GENERATION_SPACESUITS)) {
					spacesuitCount++;
				} else if (sq.getSystem().equals(SystemName.GATEWAY)) {
					gatewayCount++;
				} else {
					slsCount++;
				}
			}
		}

		systemsOwned.add(orionCount);
		systemsOwned.add(gatewayCount);
		systemsOwned.add(slsCount);
		systemsOwned.add(spacesuitCount);

		return systemsOwned;

	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Integer> systemsDevelopedCount() {
		
		List<Integer> systemsDeveloped = new ArrayList<Integer>();
		
		int orionCount = 0;
		int slsCount = 0;
		int gatewayCount = 0;
		int spacesuitCount = 0;
		
		for (Player player : players) {
			List<Square> systems = player.getPropertiesOwned();
			for (Square sq : systems) {
				if (sq.getSystem().equals(SystemName.ORION)) {
					orionCount+=sq.getNumOfDevelopments();
				} else if (sq.getSystem().equals(SystemName.ARTEMIS_GENERATION_SPACESUITS)) {
					spacesuitCount+=sq.getNumOfDevelopments();
				} else if (sq.getSystem().equals(SystemName.GATEWAY)) {
					gatewayCount+=sq.getNumOfDevelopments();
				} else if (sq.getSystem().equals(SystemName.SPACE_LAUNCH_SYSTEM)) {
					slsCount+=sq.getNumOfDevelopments();
				}
			}
		}
		
		systemsDeveloped.add(orionCount);
		systemsDeveloped.add(gatewayCount);
		systemsDeveloped.add(slsCount);
		systemsDeveloped.add(spacesuitCount);
		
		return systemsDeveloped;
		
	}
	
	public void endGame() {
		// will need to display an end of game text (will be different on whether or not
		// the game has been won or lost)
		// might be an idea to pass a boolean through and then display the winning outro
		// if it's true and the
		// losing outro if it's false

		// gameOngoing = false;

		if (isVictory()) {
			Script.displayWinningOutro();
		} else {
			Script.displayLosingOutro();
		}

		// will also need to display a final state of play
		System.out.println("Final state of play:\n");
		for (Player p : players) {
			System.out.println("Player Name      : "+p.getName());
			System.out.println("Position         : "+p.getPosition()+ ", " + board.playSquare(p.getPosition()).getName());
			System.out.println("Resources        : "+p.getResources());
			System.out.println("Properties Owned :");
			List<Square> propOwned = p.getPropertiesOwned();
			for(int loop=0; loop < propOwned.size(); loop++) {
				if(loop==0) {
					System.out.println(propOwned.get(loop).getName()+", "+propOwned.get(loop).getSystem().toString());
				} else {
					System.out.println("\n                    "+propOwned.get(loop).getName()+", "+propOwned.get(loop).getSystem().toString());
				}
			}
			System.out.println();
		}

	}
	

}
