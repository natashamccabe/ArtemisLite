/**
 * 
 */
package artemisLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Script {

	public static List<String> text = new ArrayList<String>();
	public static ReadScript readScript = new ReadScript();

	/**
	 * 
	 */
	public static void displayIntro() {
		String playerNames = "";
		for (Player p : Game.players) {
			playerNames += p.getName() + ", ";
		}
		Collections.addAll(text, 
				"Welcome to our game brave heroes of space, " + playerNames,
				"It is a period of great turmoil,", 
				"Our home planet,", 
				"ravaged by climate change,",
				"diseases running rife",
				"is becoming nigh on uninhabitable.", 
				"On earth a group of scientists",
				"the best, the brightest,", 
				"undertake a crucial mission:",
				"push the boundaries",
				"of space exploration.",
				"Humanity is on a journey to Mars,",
				"first stop: the Moon,",
				"only through collective effort,",
				"will this great goal be fulfilled,",
				"welcome, human,",
				"to your ArtemisLite quest.\n");
		Thread readIntroThread = new Thread(readScript);
		readIntroThread.start();
		while (readIntroThread.isAlive()) {
			// this stops the method finishing before the thread finishes, not sure it's the
			// most elegant way but it works for now
		}

	}
	
	public static void displayInstructions() {
		Collections.addAll(text, 
				"Your task is to develop a total of four systems.",
				"First, there is the space launch system,", 
				"which consists of two elements,",
				"the engines and the boosters.",
				"Second, the orion spacecraft,", 
				"which consists of three elements,",
				"the service module, crew module and launch abort system.",
				"Third, the gateway (an outpost for further exploration),",
				"which consists of two elements,",
				"the power and propulsion element and,",
				"the habitation and logistics outpost (HALO).",
				"Finally, the Artemis Generation Spacesuits,",
				"which consists of three elements,",
				"the primary life support systems, communication carrier assembly,",
				"and the display and control module.",
				"First you will roll two dice,", 
				"then after landing on a square you may purchase its element,",
				"or if you own each element in a system,",
				"then you may begin developing the elements of that system.", 
				"Once the three minor developments have been completed you may begin a major development.",
				"However... if one player owns part of a system,",
				"and another player owns the other part...",
				"Development will be impossible and your mission will fail.",
				"Purchasing elements and developing systems will require resources.",
				"These can be collected after landing on or passing the go square.",
				"However, if another player lands on a square you own,",
				"then you may charge them rent.",
				"Be careful though as they may charge you in the future as well,",
				"and if one player runs out of resources then the mission fails,",
				"and the game will end.",
				"You may also land on a research paused square.",
				"In this case your actions will be limited for one turn.",
				"Once all systems have been fully completed you have accomplished your mission.",
				"Good luck in your quest brave heroes.",
				"Humanity is counting on you.\n");
		Thread readIntroThread = new Thread(readScript);
		readIntroThread.start();
		while (readIntroThread.isAlive()) {

		}
	}
	
	/**
	 * alternate version for losing outro :
	 * 			"Humanity's need was dire, " + playerNames,
				"Despite the work of many countries, businesses,",
				"and the finest scientific minds on Earth,",
				"this mission has failed.",
				"ArtemisLite was a quest to explore,",
				"to conquer unknown parts of the moon,",
				"and ultimately to create a path to Mars.",
				"All our hopes went with you brave heroes.",
				"Your failure is humanity's failure,",
				"the consequences will be felt by every man,",
				"woman and child on Earth. Nothing can save us now.");
	 */

	/**
	 * 
	 */
	public static void displayLosingOutro() {
		String playerNames = "";
		for (Player p : Game.players) {
			playerNames += p.getName() + ", ";
		}
		Collections.addAll(text, 
				"Brave heroes, " + playerNames,
				"your mission has been unsuccessful.",
				"With no way to escape the planet and colonise Mars.",
				"Your failure is humanity's failure,",
				"the consequences will be felt by every man,",
				"woman and child on Earth. Nothing can save us now.\n");
		Thread readLoserOutroThread = new Thread(readScript);
		readLoserOutroThread.start();
		while (readLoserOutroThread.isAlive()) {
			// this stops the method finishing before the thread finishes, not sure it's the
			// most elegant way but it works for now
		}
	}
	
	public static void displayLosingOutroReason() {
		
		
		//"(should we have it state the reason we lost?)",
	}
	
	
	/**
	 * alternate version of winning outro :
	 * 
	 * 			"With the completion of " + "last finished system",
				"ArtemisLite is ready to launch!",
				"A crew of humans, united by a common goal",
				"and determination to push the boundaries",
				"of space travel, succeed in their mission.",
				"All that they have built,",
				"all that they have studied,",
				"all that they have done,",
				"has led up to this moment.");
	 */
	
	/**
	 * second alternate version of winning outro - providing timeline resembling that of requirements
	 * 			
	 * 			String playerNames = "";
					for (Player p : Game.players) {
					playerNames += p.getName() + ", ";
				}
				Collections.addAll(text, 
	 * 					"With the completion of these systems",
						"project ArtemisLite was a success.",
						"The story of this project and humanity's salvation,",
						"is now taught in schools to inspire the next generation of heroes.",
						"In 2024 we saw the first woman walk on the moon and the next man.",
						"In 2030 the first settlement on the moon was established.",
						"Six years after this mankind reached Mars.",
						"In 2040 the first settlements were constructed.",
						"The process of evacuating Earth could then begin.",
						"It would take a decade to complete.",
						"During this time the people of Earth suffered.",
						"Conditions on the planet were too harsh for many.",
						"Truly this was humanity's darkest hour.",
						"But we raged against the dying of the light",
						"and the evacuation was complete,",
						"mankind saved.",
						"this would not have been possible without the brave heroes,",
						"of project ArtemisLite.",
						"Humanity extends its thanks to, "+ playerNames,
						"your work will not be forgotten.");
				Thread readLoserOutroThread = new Thread(readScript);
				readLoserOutroThread.start();
				while (readLoserOutroThread.isAlive()) {

				}
	 * 
	 */

	/**
	 * 
	 */
	public static void displayWinningOutro() {
		String playerNames = "";
		for (Player p : Game.players) {
			playerNames += p.getName() + ", ";
		} 
		Collections.addAll(text, 

				"The story of this project and humanity's salvation,",
				"is now taught in schools to inspire the next generation of heroes.",				
				"In the year 2022...Artemis lands on the moon.",
				"In 2024 we saw the first woman walk on the moon and the next man.",
				"In 2030 the first settlement on the moon was established.",
				"Six years after this mankind reached Mars.",
				"In 2040 the first settlements were constructed.",
				"The process of evacuating Earth could then begin.",
				"It would take a decade to complete.",
				"During this time the people of Earth suffered.",
				"Conditions on the planet were too harsh for many.",
				"Truly this was humanity's darkest hour.",
				"But we raged against the dying of the light",
				"and the evacuation was complete,",
				"mankind saved.",
				"this would not have been possible without the brave heroes,",
				"of project ArtemisLite.",
				"Humanity extends its thanks to, "+ playerNames,
				"your work will not be forgotten.\n");
		Thread readWinnerOutroThread = new Thread(readScript);
		readWinnerOutroThread.start();
		while (readWinnerOutroThread.isAlive()) {
			// this stops the method finishing before the thread finishes, not sure it's the
			// most elegant way but it works for now
		}
	}

}
