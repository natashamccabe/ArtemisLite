/**
 * 
 */
package artemisLite;

import java.util.Scanner;



public class AppManager {

	/**
	 * start point of application - kicks off the game
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner mainScanner = new Scanner(System.in);
		System.out.println("Press ENTER to begin your Artemis adventure");
		mainScanner.nextLine();
		
		
		Game game = new Game(200);
		game.startGame();
		game.continueGame();
		
	
		mainScanner.close();
	}

}
