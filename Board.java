/**
 * 
 */
package artemisLite;

import java.util.ArrayList;
import java.util.List;


public class Board {

	private List<Square> currentBoard = new ArrayList<Square>();
	private List<SystemGroup> systemGroups = new ArrayList<SystemGroup>();
	private static final int NUMBER_OF_SQUARES = 12;

	/**
	 * Default constructor
	 */
	public Board() {

	}

	/**
	 * Constructor with args
	 * 
	 * @param currentBoard
	 */
	public Board(List<Square> currentBoard) {
		this.currentBoard = currentBoard;
	}



	public List<Square> getCurrentBoard() {
		return currentBoard;
	}
	
	public List<SystemGroup> getSystemGroups(){
		return systemGroups;
	}

	/**
	 * Populates a default board.
	 */
	public void initialiseBoard() {
		SystemGroup sys1, sys2, sys3, sys4;

		sys1 = new SystemGroup(SystemName.SPACE_LAUNCH_SYSTEM);
		sys2 = new SystemGroup(SystemName.ARTEMIS_GENERATION_SPACESUITS);
		sys3 = new SystemGroup(SystemName.ORION);
		sys4 = new SystemGroup(SystemName.GATEWAY);

		for (int loop = 0; loop < NUMBER_OF_SQUARES; loop++) {
			Square sq;
			switch (loop) {
			case 0:
				sq = new Square("Go", SquareType.GOVERNMENT_GRANT);
				break;
			case 1:
				sq = new Square("Boosters", SquareType.PURCHASABLE, 60, new int[] {2, 10, 30, 90, 160}, 50, 100, 0, null,
						SystemName.SPACE_LAUNCH_SYSTEM);
				sys1.addSquare(sq);
				break;
			case 2:
				sq = new Square("Engines", SquareType.PURCHASABLE, 60, new int[] {4, 20, 60, 180, 320}, 50, 100, 0, null, SystemName.SPACE_LAUNCH_SYSTEM);
				sys1.addSquare(sq);
				break;
			case 3:
				sq = new Square("Primary Life Support Systems", SquareType.PURCHASABLE, 140, new int[] {10, 50, 150, 450, 625}, 100, 200, 0, null,
						SystemName.ARTEMIS_GENERATION_SPACESUITS);
				sys2.addSquare(sq);
				break;
			case 4:
				sq = new Square("Communication Carrier Assembly", SquareType.PURCHASABLE, 140, new int[] {10, 50, 150, 450, 625}, 100, 200, 0, null,
						SystemName.ARTEMIS_GENERATION_SPACESUITS);
				sys2.addSquare(sq);
				break;
			case 5:
				sq = new Square("Display and Control Module", SquareType.PURCHASABLE, 160, new int[] {12, 60, 180, 500, 700}, 100, 200, 0, null,
						SystemName.ARTEMIS_GENERATION_SPACESUITS);
				sys2.addSquare(sq);
				break;
			case 6:
				sq = new Square("Pause Research", SquareType.EXTERNAL_INVESTIGATION);
				break;
			case 7:
				sq = new Square("Service Module", SquareType.PURCHASABLE, 220, new int[] {18, 90, 250, 700, 875}, 150, 300, 0, null, SystemName.ORION);
				sys3.addSquare(sq);
				break;
			case 8:
				sq = new Square("Crew Module", SquareType.PURCHASABLE, 220, new int[] {18, 90, 250, 700, 875}, 150, 300, 0, null, SystemName.ORION);
				sys3.addSquare(sq);
				break;
			case 9:
				sq = new Square("Launch Abort System", SquareType.PURCHASABLE, 240, new int[] {20, 100, 300, 750, 925}, 150, 300, 0, null, SystemName.ORION);
				sys3.addSquare(sq);
				break;
			case 10:
				sq = new Square("Power and Propulsion Element", SquareType.PURCHASABLE, 350, new int[] {35, 175, 500, 1100, 1300}, 200, 400, 0, null,
						SystemName.GATEWAY);
				sys4.addSquare(sq);
				break;
			case 11:
				sq = new Square("Habitation and Logistics Outpost (HALO)", SquareType.PURCHASABLE, 400, new int[] {50, 200, 600, 1400, 1700}, 200, 400, 0,
						null, SystemName.GATEWAY);
				sys4.addSquare(sq);
				break;
			default:
				sq = new Square();
				break;
			}

			currentBoard.add(sq);
		}
		
		systemGroups.add(sys1);
		systemGroups.add(sys2);
		systemGroups.add(sys3);
		systemGroups.add(sys4);

	}

	/**
	 * adds a square to a board
	 */
	public void addSquare(Square newSquare) {
		currentBoard.add(newSquare);
	}

	/**
	 * returns the details of the square a player is on, based on their position in
	 * a list of squares
	 * 
	 * @param playerPosition
	 * @return
	 */
	public Square playSquare(int playerPosition) {
		return currentBoard.get(playerPosition);
	}
	
	public void displayBoard() {
		for (Square sq: currentBoard) {
			System.out.println(sq.getName());
		}
	}

}
